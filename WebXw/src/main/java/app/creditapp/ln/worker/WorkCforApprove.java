package app.creditapp.ln.worker;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import app.base.SourceTemplate;
import app.base.httpclient.entity.SendMessageEntity;
import app.base.httpclient.entity.SendMessageType;
import app.base.httpclient.work.SendMessageMain;
import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;
import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.inf.client.RuleTrans;
import app.creditapp.inf.client.RulesService;
import app.creditapp.inf.client.entity.Request;
import app.creditapp.inf.client.entity.RequestObj;
import app.creditapp.inf.client.entity.ReturnObj;
import app.creditapp.inf.client.entity.RuleFact;
import app.creditapp.ln.bo.LnApplyBo;
import app.creditapp.ln.bo.LnApplyMidBo;
import app.creditapp.ln.bo.LnApplyRegBo;
import app.creditapp.ln.entity.LnApply;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.entity.LnApplyReg;
import app.creditapp.proj.bo.ProjMangBo;
import app.creditapp.proj.entity.ProjMang;
import app.creditapp.redis.util.RedisUtil;
import app.creditapp.sys.bo.PrdtBaseBo;
import app.creditapp.sys.entity.PrdtBase;
import app.creditapp.sys.entity.SysOrg;
import app.creditapp.sys.entity.SysPath;
import app.util.syslog.bo.SysExceptionBo;

import com.alibaba.fastjson.JSON;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 6, 2016
 * @描述 业务处理C节点 自动审批处理
 */
public class WorkCforApprove implements Runnable {
	Logger logger = Logger.getLogger(WorkCforApprove.class);
	// private CorpBase corpBase;
	// private CorpBaseBo corpBaseBo;
	private LnApplyMid lnApplyMid;
	private LnApplyReg lnApplyReg;

	public WorkCforApprove(LnApplyMid lnApplyMid) {
		this.lnApplyMid = lnApplyMid;
	}

	public void run() {
		String _auto_app_sts = "02"; // 默认自动审批通过 申请状态[01未处理02通过03否决04产生合同]
		String _up_result = "0";// 更新状态成功标志，默认失败
		String rgsp = "01";
		try {
			if (lnApplyMid == null) {
				logger.error("C任务处理失败,接收到数据为空！");
			} else {
				logger.info("APPID:" + lnApplyMid.getAppId() + " WORK C 处理开始");
				// 推送正式进件消息
				// sendMessage();
				// 调用规则引擎接口进行自动审批[不同的产品规则编号不同]
//				ReturnObj re = appAuto(lnApplyMid);
				String sRe = "0000";
				if (sRe == null || "".equals(sRe)) {
					logger.info("C任务-调用规则引擎失败,re为空！[申请编号AppId="
							+ lnApplyMid.getAppId() + ",合同号PactNo="
							+ lnApplyMid.getPactNo() + ",批次号BatchNo="
							+ lnApplyMid.getBatchNo() + "]");
				} else {
					if ("0000".equals(sRe)) {
						_auto_app_sts = "02";
						 //将resultId插入lnapplyMid表
//						 lnApplyUpdate(lnApplyMid.getAppId(),sRe);
						// 调用存储过程更新状态
						_up_result = lnStage(lnApplyMid.getAppId(), sRe);
						// 将审批失败描述和resultId插入lnapplymid表
						lnApplyMidUpdate(lnApplyMid.getAppId(), sRe);

						if ("1".equals(_up_result)) { // 更新成功的记录
							// 自动审批通过的记录
							if ("02".equals(_auto_app_sts)) {
								String _appr_type = "01"; // 审批类型[01自动02人工]
															// 默认为01自动
								// 调用随机函数判断是否需要人工复核 根据产品号获取人工复核比例
								_appr_type = this.getRandomType(lnApplyMid
										.getProjNo()); // 随机获取人工复核比例
								ReturnObj ro = null;
								// 通过随机函数不需要人工复核的调用人工复核策略
								if ("01".equals(_appr_type)) {
									// 调用规则引擎判断是否需要进行人工复核
									ro = appManual(lnApplyMid);
									if (ro != null) {
										_appr_type = ro.getResponse()
												.getRuleFact().get(0)
												.getAppType();
									} else {
										rgsp = "02";
									}
								}
								if ("01".equals(_appr_type)) {
									logger.info("C任务判断为自动审批-[申请编号AppId="
											+ lnApplyMid.getAppId()
											+ ",合同号PactNo="
											+ lnApplyMid.getPactNo()
											+ ",批次号BatchNo="
											+ lnApplyMid.getBatchNo() + "]");
								} else {
									logger.info("C任务判断为人工审批-[申请编号AppId="
											+ lnApplyMid.getAppId()
											+ ",合同号PactNo="
											+ lnApplyMid.getPactNo()
											+ ",批次号BatchNo="
											+ lnApplyMid.getBatchNo() + "]");
								}
								if ("01".equals(rgsp)) {
									lnApplyMid.setApprType(_appr_type);
									// push入4号消息队列
									Jedis jedis = RedisUtil.getJedis();
									jedis.lpush(RedisUtil.QUEUE4,
											JSON.toJSONString(lnApplyMid));
									jedis.close();
									logger.info("C任务处理成功-[申请编号AppId="
											+ lnApplyMid.getAppId()
											+ ",合同号PactNo="
											+ lnApplyMid.getPactNo()
											+ ",批次号BatchNo="
											+ lnApplyMid.getBatchNo() + "]");
								} else {
									logger.info("C任务处理失败，规则引擎人工审批策略出错-[申请编号AppId="
											+ lnApplyMid.getAppId()
											+ ",合同号PactNo="
											+ lnApplyMid.getPactNo()
											+ ",批次号BatchNo="
											+ lnApplyMid.getBatchNo() + "]");
								}
							} else {
								logger.info("C任务-自动审批否决-[申请编号AppId="
										+ lnApplyMid.getAppId() + ",合同号PactNo"
										+ lnApplyMid.getPactNo()
										+ ",批次号BatchNo="
										+ lnApplyMid.getBatchNo() + "]");
							}
						} else {
							logger.info("C任务-更新状态失败-PKG_LN_APPROVE.PROC_UP_STATUS-[申请编号AppId="
									+ lnApplyMid.getAppId()
									+ ",合同号PactNo="
									+ lnApplyMid.getPactNo()
									+ ",批次号BatchNo="
									+ lnApplyMid.getBatchNo() + "]");
						}
					} else {
						logger.info("C任务-调用规则引擎失败，"
								 + "[申请编号AppId="
								+ lnApplyMid.getAppId() + ",合同号PactNo="
								+ lnApplyMid.getPactNo() + ",批次号BatchNo="
								+ lnApplyMid.getBatchNo() + "]");
					}
				}
			}
		} catch (Exception e) {
			SysExceptionBo sysExceptionBo = (SysExceptionBo) SourceTemplate
					.getSpringContextInstance().getBean("sysExceptionBo");
			sysExceptionBo.insertNewWorkExceptionLog(e, lnApplyMid.getAppId());
			e.printStackTrace();
		}
	}

	/***
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @作者 wangtao
	 * @日期 Jul 12, 2016
	 * @方法说明：调用规则引擎接口进行自动审批[不同的产品规则编号不同]
	 * @返回参数 02通过03否决
	 */
	public ReturnObj appAuto(LnApplyMid ln) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		// 拼接传入数据
		RequestObj requestObj = new RequestObj();
		Request request = new Request();
		RuleFact ruleFact = new RuleFact();

		ruleFact.setPrdtNo(ln.getPrdtNo());
		// ruleFact.setAge(birthToAge(ln.getBirthDay()));
		// ruleFact.setEdu(ln.getEdu());
		// ruleFact.setCifType(ln.getCifType());
		// if(ln.getWorkSts()==null||"".equals(ln.getWorkSts())){
		// ruleFact.setIfWork("99");
		// }else{
		// ruleFact.setIfWork(ln.getWorkSts());
		// }
		// ruleFact.setIfRoom(ln.getIfRoom());
		// ruleFact.setIfBlack(ln.getChkRes());
		// ruleFact.setIfPhone("");
		// if(!(ln.getResTel()==null||"".equals(ln.getResTel()))){
		// ruleFact.setIfPhone(ln.getResTel());
		// }
		// if(!(ln.getPhoneNo()==null||"".equals(ln.getPhoneNo()))){
		// ruleFact.setIfPhone(ln.getPhoneNo());
		// }
		// ruleFact.setAgeRes("");
		// ruleFact.setEduRes("");
		// ruleFact.setCifTypeRes("");
		// ruleFact.setIfWorkRes("");
		// ruleFact.setIfBendiRes("");
		// ruleFact.setIfRoomRes("");
		// ruleFact.setIfBlackRes("");
		// ruleFact.setIfPhoneRes("");
		//
		//
		// ruleFact.setSex(ln.getSex());
		// ruleFact.setWorkYear(dateToYear(ln.getWorkYear())+"");
		// ruleFact.setMarriage(ln.getMarriage());
		// ruleFact.setChildren(ln.getChildren()+"");
		// ruleFact.setIncome(ln.getIncome());
		// ruleFact.setIfBendi("");
		// if((ln.getAppArea().equals(ln.getCifArea()))){
		// ruleFact.setIfBendi("01");
		// }
		// ruleFact.setIfCar(ln.getIfCar());
		// ruleFact.setIfCard(ln.getIfCard());
		// ruleFact.setCardAmt(ln.getCardAmt());
		// ruleFact.setIfCarcred(ln.getIfCarcred());
		// ruleFact.setIfMort(ln.getIfMort());
		// 分拆参数字段并动态赋值
		/*
		 * Field f; if(ln.getArgs().length()>0){ String[]
		 * sArr=(ln.getArgs()).split("@"); for(int i = 0; i < sArr.length; i++){
		 * //args 赋值 f = ruleFact.getClass().getDeclaredField("arg"+(i+1));
		 * f.setAccessible(true); f.set(ruleFact, sArr[i]); //argsRes 赋值 // f =
		 * ruleFact.getClass().getDeclaredField("arg"+(i+1)+"Res"); //
		 * f.setAccessible(true); // f.set(ruleFact, ""); } }
		 */
		ruleFact.setAppRes("");
		List<RuleFact> list = new ArrayList<RuleFact>();
		list.add(0, ruleFact);

		String rulesName = WorkUtils.getRulesNameMap().get(
				lnApplyMid.getPrdtNo());
		if (rulesName == null) {
			PrdtBaseBo prdtBaseBo = (PrdtBaseBo) SourceTemplate
					.getSpringContextInstance().getBean("prdtBaseBo");
			PrdtBase prdtBase = new PrdtBase();
			prdtBase.setPrdtNo(lnApplyMid.getPrdtNo());
			prdtBase = prdtBaseBo.getByPrdtNo(prdtBase);
			rulesName = prdtBase.getRulesName();
			WorkUtils.getRulesNameMap().put(lnApplyMid.getPrdtNo(), rulesName);
		}

		request.setRuleName(rulesName);
		ruleFact.setAge(birthToAge(ln.getBirthDay()));
		request.setRuleFact(list);
		requestObj.setUser("1001");
		requestObj.setPassword("1");
		requestObj.setRequest(request);
		ReturnObj ro = null;
		try {
			// 连接规则引擎
			RulesService rs = (RulesService) SourceTemplate
					.getSpringContextInstance().getBean("RulesService");
			// 调用规则引擎得到返回的字符串
			String str = rs.executeRule(JSON.toJSONString(requestObj));
			// 将返回的字符串转为Object并取出审批结果
			ro = (ReturnObj) JSON.parseObject(str, ReturnObj.class);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("自动审批调用规则引擎失败");
		}
		return ro;
	}

	/***
	 * @作者 wangtao
	 * @日期 Jul 12, 2016
	 * @方法说明：将生日转换为年龄
	 * @返回参数 年龄
	 */
	public int birthToAge(String birth) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(new Date());
		Date birthDate = null;
		Date currentTime = null;
		try {
			birthDate = formatter.parse(birth);
			currentTime = formatter.parse(dateString);
		} catch (Exception e) {
		}
		long day = (currentTime.getTime() - birthDate.getTime())
				/ (24 * 60 * 60 * 1000);
		int year = (int) day / 365;
		return year;
	}

	/***
	 * @作者 wangtao
	 * @日期 Jul 12, 2016
	 * @方法说明：将生日转换为年龄
	 * @返回参数 年龄
	 */
	public int dateToYear(String da) {
		if ("".equals(da) || da == null) {
			return 0;
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			String dateString = formatter.format(new Date());
			int year = Integer.parseInt(dateString) - Integer.parseInt(da);
			return year;
		}
	}

	/***
	 * @作者 wangtao
	 * @日期 Jul 12, 2016
	 * @方法说明：调用规则引擎接口判断是否需要进行人工复核
	 * @返回参数 01自动02人工
	 */
	public ReturnObj appManual(LnApplyMid ln) {
		// 连接规则引擎
		RequestObj requestObj = new RequestObj();
		Request request = new Request();
		RuleFact ruleFact = new RuleFact();
		ruleFact.setAppAmt(ln.getPactAmt());
		ruleFact.setAppType("");
		ruleFact.setVouType(ln.getVouType());
		List<RuleFact> list = new ArrayList<RuleFact>();
		list.add(0, ruleFact);
		request.setRuleName("appManual");
		request.setRuleFact(list);
		requestObj.setUser("1001");
		requestObj.setPassword("1");
		requestObj.setRequest(request);
		// 调用规则引擎得到返回的字符串
		// 将返回的字符串转为Object并取出审批结果
		ReturnObj ro = null;
		try {
			// 连接规则引擎
			RulesService rs = (RulesService) SourceTemplate
					.getSpringContextInstance().getBean("RulesService");
			// 调用规则引擎得到返回的字符串
			String str = rs.executeRule(JSON.toJSONString(requestObj));
			// 将返回的字符串转为Object并取出审批结果
			ro = (ReturnObj) JSON.parseObject(str, ReturnObj.class);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("人工审批策略调用规则引擎失败");
		}
		return ro;
	}

	/**
	 * @作者 DHCC-SONG
	 * @日期 Jul 2, 2016
	 * @方法说明：根据产品编号取人工复核比例生成是否需要人工复核
	 * @返回参数 审批类型[01自动02人工]
	 */
	public String getRandomType(String projNo) {
		Float d = null;
		d = WorkUtils.getRgAppRateMap().get(lnApplyMid.getPrdtNo());
		if (d == null) {
			PrdtBaseBo prdtBaseBo = (PrdtBaseBo) SourceTemplate
					.getSpringContextInstance().getBean("prdtBaseBo");
			PrdtBase prdtBase = new PrdtBase();
			prdtBase.setPrdtNo(lnApplyMid.getPrdtNo());
			prdtBase = prdtBaseBo.getByPrdtNo(prdtBase);
			d = prdtBase.getRgAppRate();
			WorkUtils.getRgAppRateMap().put(lnApplyMid.getPrdtNo(), d);
		}

		int val = (int) (Math.random() * 100);
		if (val < d) {
			return "02";
		} else {
			return "01";
		}
	}

	/***
	 * @作者 wangtao
	 * @日期 Jul 25, 2016
	 * @方法说明：将评级结果更新至ln_apply_mid表中 根据申请ID和合同号更新
	 * @返回参数 无
	 */
	public int lnApplyUpdate(String appId, String resultId) {
		// 设置需要更新的字段值
		LnApply lnApply = new LnApply();
		lnApply.setAppId(appId);
		lnApply.setResultId(resultId);
		// 使用Bo对cifEval表进行更新
		LnApplyBo lnApplyBo = (LnApplyBo) SourceTemplate
				.getSpringContextInstance().getBean("lnApplyBo");
		int result = lnApplyBo.resultIdUpdate(lnApply);
		return result;
	}

	/***
	 * @作者 wangtao
	 * @日期 Jul 25, 2016
	 * @方法说明：将评级结果更新至ln_apply_mid表中 根据申请ID和合同号更新
	 * @返回参数 无
	 */
	public int lnApplyMidUpdate(String appId, String re) {
		int result = 0;
		String appRes = "02";
		String resId = "6bef5ac38b";
		if ("02".equals(appRes)) {
			LnApplyMid lnApplyMid = new LnApplyMid();
			lnApplyMid.setAppId(appId);
			lnApplyMid.setChkRes("01");
			lnApplyMid.setChkDesc("筛查通过,自动审批通过");
			lnApplyMid.setIfFlag(resId);
			// 使用Bo对cifEval表进行更新
			LnApplyMidBo lnApplyMidBo = (LnApplyMidBo) SourceTemplate
					.getSpringContextInstance().getBean("lnApplyMidBo");
			result = lnApplyMidBo.resultIdUpdate(lnApplyMid);

			// 更新lnapplyReg表
			LnApplyReg lnApplyReg = new LnApplyReg();
			lnApplyReg.setAppId(appId);
			lnApplyReg.setApprSts(appRes);
			LnApplyRegBo lnApplyRegBo = (LnApplyRegBo) SourceTemplate
					.getSpringContextInstance().getBean("lnApplyRegBo");
			result = lnApplyRegBo.resultIdUpdate(lnApplyReg);

		} else {
			RuleTrans ru = new RuleTrans();
			String reError = "审批失败";
			// 设置需要更新的字段值
			LnApplyMid lnApplyMid = new LnApplyMid();
			lnApplyMid.setAppId(appId);
			lnApplyMid.setChkRes("16");
			lnApplyMid.setChkDesc(reError);
			lnApplyMid.setIfFlag(resId);
			// 使用Bo对cifEval表进行更新
			LnApplyMidBo lnApplyMidBo = (LnApplyMidBo) SourceTemplate
					.getSpringContextInstance().getBean("lnApplyMidBo");
			result = lnApplyMidBo.resultIdUpdate(lnApplyMid);

			// 更新lnapplyReg表
			LnApplyReg lnApplyReg = new LnApplyReg();
			lnApplyReg.setAppId(appId);
			lnApplyReg.setApprSts("03");
			LnApplyRegBo lnApplyRegBo = (LnApplyRegBo) SourceTemplate
					.getSpringContextInstance().getBean("lnApplyRegBo");
			result = lnApplyRegBo.resultIdUpdate(lnApplyReg);

		}
		return result;
	}

	/***
	 * @作者 wangtao
	 * @日期 Jul 25, 2016
	 * @方法说明：将评级结果更新至ln_apply_mid表中 根据申请ID和合同号更新
	 * @返回参数 无
	 */
	public String lnStage(String appId, String re) {
		String _up_result = null;
		String appRes = "02";
		if ("02".equals(appRes)) {
			_up_result = WorkUtils.getInstance().proc_up_status(
					lnApplyMid.getAppId(), appRes, "自动审批通过");
		} else {
			String reError = "审批否决";
			_up_result = WorkUtils.getInstance().proc_up_status(
					lnApplyMid.getAppId(), appRes, "自动审批否决," + reError);
		}

		/*
		 * RuleTrans ru = new RuleTrans(); String reError =
		 * ru.resultError(re.getResponse().getRuleFact().get(0)); //设置需要更新的字段值
		 * LnApplyMid lnApplyMid = new LnApplyMid(); lnApplyMid.setAppId(appId);
		 * lnApplyMid.setChkRes("16"); lnApplyMid.setChkDesc(reError);
		 * //使用Bo对cifEval表进行更新 LnApplyMidBo lnApplyMidBo = (LnApplyMidBo)
		 * SourceTemplate.getSpringContextInstance().getBean("lnApplyMidBo");
		 * int result = lnApplyMidBo.resultIdUpdate(lnApplyMid);
		 */
		return _up_result;
	}

	public void sendMessage() {

		// 推送正式进件消息
		String s = lnApplyMid.getBrNo();
		CorpBaseBo corpBaseBo = (CorpBaseBo) SourceTemplate
				.getSpringContextInstance().getBean("corpBaseBo");
		CorpBase corpBase = new CorpBase();
		corpBase.setBrNo(s);
		corpBase = corpBaseBo.getById(corpBase);
		// System.out.println("333333333");
		String brname = corpBase.getBrName();
		String a = lnApplyMid.getProjNo();
		// System.out.println(a);
		// List<String> list = new ArrayList<String>();
		// Connection conn=DBUtil.getConnection();
		// Statement stmt=null;
		// ResultSet rs = null;
		// String sql ="select login_id from proj_mang where proj_no="+a;
		// stmt = conn.createStatement();
		// rs = stmt.executeQuery(sql);
		// while(rs.next()){
		// list.add(rs.getString(1));
		// }
		// conn.close();
		// rs.close();
		// stmt.close();
		ProjMang projMang = new ProjMang();
		projMang.setProjNo(a);

		ProjMangBo projMangBo = (ProjMangBo) SourceTemplate
				.getSpringContextInstance().getBean("projMangBo");
		projMangBo.getByIdLoginId(projMang);
		List<ProjMang> projMangList = null;

		projMangList = projMangBo.getByIdLoginId(projMang);
		if (projMangList.equals(null)) {

		} else {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < projMangList.size(); i++) {
				list.add(projMangList.get(i).getLoginId());
			}
			String user[] = (String[]) list.toArray(new String[list.size()]);
			// String[] user = (String[]) list.toArray();
			SendMessageEntity sendMessageEntity = new SendMessageEntity();
			// System.out.println("11111111111111111");
			// System.out.println(user[1]);
			String title = "正式进件";
			String contet = brname + "进件啦";
			sendMessageEntity.setTitle(title);
			sendMessageEntity.setContet(contet);
			sendMessageEntity.setReciveUserNos(user);
			sendMessageEntity.setSendType(SendMessageType.MESSAGE);
			sendMessageEntity
					.setPasSubTypeEntity(PasSubTypeEntity.RewFundMessage);
			try {
				SendMessageMain.sendMessage(sendMessageEntity);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	// 复写父类的方法
	public String toString() {
		String ret = "";
		if (lnApplyMid != null) {
			ret = "业务ID：" + lnApplyMid.getAppId() + ",合同编号："
					+ lnApplyMid.getPactNo();
		}
		return ret;
	}

	// public CorpBase getCorpBase() {
	// return corpBase;
	// }
	//
	// public void setCorpBase(CorpBase corpBase) {
	// this.corpBase = corpBase;
	// }

	// public CorpBaseBo getCorpBaseBo() {
	// return corpBaseBo;
	// }
	//
	// public void setCorpBaseBo(CorpBaseBo corpBaseBo) {
	// this.corpBaseBo = corpBaseBo;
	// }

}
