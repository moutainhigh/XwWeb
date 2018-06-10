package  app.creditapp.fund.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.bat.entity.RptPrdtDaily;
import app.creditapp.fund.bo.FundProvProjBo;
import app.creditapp.fund.dao.FundBaseDao;
import app.creditapp.fund.dao.FundProvDao;
import app.creditapp.fund.dao.FundProvProjDao;
import app.creditapp.fund.dao.FundProvServiceDao;
import app.creditapp.fund.dao.FundProvSplitTermDao;
import app.creditapp.fund.dao.FundSplitDao;
import app.creditapp.fund.dao.FundSplitTermDao;
import app.creditapp.fund.entity.FundBase;
import app.creditapp.fund.entity.FundProv;
import app.creditapp.fund.entity.FundProvProj;
import app.creditapp.fund.entity.FundProvService;
import app.creditapp.fund.entity.FundProvSplitTerm;
import app.creditapp.fund.entity.FundSplit;
import app.creditapp.fund.entity.FundSplitTerm;
import app.creditapp.proj.dao.ProjParmDao;
import app.creditapp.proj.entity.ProjParm;
import app.creditapp.sys.dao.FundServiceRateDao;
import app.creditapp.sys.dao.PrdtBaseDao;
import app.creditapp.sys.entity.FundServiceRate;
import app.util.DateUtil;
import app.util.User;
import app.util.toolkit.Ipage;
import flex.messaging.io.ArrayList;
/**
* Title: FundProvProjBoImplImpl.java
* Description:
**/
public class FundProvProjBoImpl extends BaseService implements FundProvProjBo {

	private FundProvProjDao fundProvProjDao;
	private FundProvDao fundProvDao;
	private ProjParmDao projParmDao;
	private FundBaseDao fundBaseDao;
	private FundSplitTermDao fundSplitTermDao;
	private FundSplitDao fundSplitDao;
	private FundServiceRateDao fundServiceRateDao;
	private PrdtBaseDao prdtBaseDao;
	private FundProvSplitTermDao fundProvSplitTermDao;
	private FundProvServiceDao fundProvServiceDao;

	public void del(FundProvProj fundProvProj) throws ServiceException {
		try {
			fundProvProjDao.del(fundProvProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundProvProj fundProvProj)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundProvProjDao
					.getCount(fundProvProj) });// 初始化分页类
			fundProvProj.setStartNumAndEndNum (ipg);
			ipg.setResult(fundProvProjDao.findByPage(fundProvProj));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public FundProvProj getById(FundProvProj fundProvProj) throws ServiceException {
		try {
			fundProvProj = fundProvProjDao.getById(fundProvProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundProvProj;
	}
	
	public List<FundProvProj> findAll(FundProvProj fundProvProj) throws ServiceException {
		List<FundProvProj> list = null;
		try {
			list = fundProvProjDao.findAll(fundProvProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-22
	 * @方法说明：收益计提计算
	 * @返回参数: 收益计提项目汇总表FundProvProj对象
	 */
	public FundProvProj insert(FundProvProj fundProvProj) throws ServiceException {
		try {
			//获取id
			String provProjNo = fundProvProjDao.getProvProjNo();
			//项目收益成本
			BigDecimal finCost = DoDcm(0.00);
			//项目受益人总收益
			BigDecimal payAmt = DoDcm(0.00);
			//项目管理费(优先级，次级)
			BigDecimal managerFee = DoDcm(0.00);
			BigDecimal managerFee_super = DoDcm(0.00);
			BigDecimal managerFee_less = DoDcm(0.00);
			//项目管理费基数(优先级，次级)
			BigDecimal managerFee_super_parm = DoDcm(0.00);
			BigDecimal managerFee_less_parm = DoDcm(0.00);
			//放贷服务费
			BigDecimal serviceFee = DoDcm(0.00) ;
			//计提起始日期
			String begDate = fundProvProj.getBegDate();
			String endDate = fundProvProj.getEndDate();
			//获取项目配置表中的项目收费种类（融资报酬，信托管理费，放贷服务费3中的几种）
			ProjParm projParm = new ProjParm();			
			projParm.setProjNo(fundProvProj.getProjNo());
			projParm = projParmDao.getById(projParm);
			//项目所收费种类
			String payType;
			
			if(projParm==null ){

			}
			payType = projParm.getTruPayType();
			//获取信托管理费率
			BigDecimal mangFeerate = new BigDecimal(Float.toString(projParm.getMangFeerate()));		
	/**************************放贷服务费计算方法start******************************************************/
//			if( "1".equals(payType.substring(2, 3))){
			if(payType.contains("3")){
				//计提统计起始日前一天日期
				String before_Date = DateUtil.addByDay(fundProvProj.getBegDate(), -1);
		        //放贷服务费方式（固定，阶梯）以及费率获取
				FundServiceRate fundServiceRate = new FundServiceRate();
				fundServiceRate.setProjNo(fundProvProj.getProjNo());
				//统计费率条数：0-没有；1-固定费率；>1阶梯费率
				int fundServiceRate_count = (Integer) fundServiceRateDao.getCount(fundServiceRate);
				Ipage ipage = new Ipage();
				ipage.setPageSize(fundServiceRate_count);	
				fundServiceRate.setStartNumAndEndNum(ipage);
				List<FundServiceRate> fundServiceRateList = fundServiceRateDao.findByPage(fundServiceRate);
				RptPrdtDaily rptPrdtDaily= new RptPrdtDaily();
				rptPrdtDaily.setProjNo(fundProvProj.getProjNo());
				if(fundServiceRate_count == 0){
					serviceFee = DoDcm(0.00);
				}else if(fundServiceRate_count == 1){
					//统计区间内，项目中需统计产品分类的放贷额；不同产品对应的折扣率不同
					//获取放贷服务费率
					fundServiceRate = fundServiceRateList.get(0);
					//获取按产品的放贷量 (计提到日期累计放贷量-计提开始前一天的放贷量)
					//阶梯内放贷服务费累计
					String startdate=fundProvProj.getBegDate();
               	    while(fundProvProj.getEndDate().compareTo(startdate) > 0){
               	    	BigDecimal day_amt = new BigDecimal("0.00");
               	    	rptPrdtDaily.setRptDate(startdate);
               	    	day_amt = get_dayamt(rptPrdtDaily,fundServiceRate.getSvRate());
               	    	//日期加1
               	    	startdate = DateUtil.addByDay(startdate, 1);
               	        //阶梯内开始日期到结束日期的放贷服务费
               	    	serviceFee = serviceFee.add(day_amt);
               	    }  
               	    //插入表收益计提放贷服务费明细表FUND_PROV_SERVICE
               	    rptPrdtDaily.setRptDate(DateUtil.addByDay(begDate, -1));
               	    //rptPrdtDaily.setRptDate(begDate);
               	    //设置到期日期
               	    rptPrdtDaily.setPrdtNo(endDate);
        		    List<RptPrdtDaily> rptPrdtDailyList = fundProvProjDao.RptRrdtDailygetByprdtno( rptPrdtDaily);
        		    BigDecimal Rate = FoDcm(fundServiceRate.getSvRate());
        		    BigDecimal amt = new BigDecimal("0.00");
        	        for(int i=0;i<rptPrdtDailyList.size();i++){
        		    //按产品分类放贷服务费
        	        BigDecimal Prdt_amt = new BigDecimal("0.00");
        		    rptPrdtDaily = rptPrdtDailyList.get(i);
        		    //一个产品的累计放贷服务费
        		    BigDecimal AmtTot = DoDcm(rptPrdtDaily.getAmtTot());
        		    BigDecimal OffRate = FoDcm(rptPrdtDaily.getOffRate());
        		    Prdt_amt = AmtTot.multiply(Rate).multiply(OffRate);
        		    amt = amt.add(Prdt_amt);
        		    
                   	FundProvService fundProvService = new FundProvService();
                   	fundProvService.setProvProjNo(provProjNo);
                   	fundProvService.setProjNo(fundProvProj.getProjNo());
                   	fundProvService.setStepNo(fundServiceRate.getStepNo());
                   	fundProvService.setMaxAmt(fundServiceRate.getMaxAmt());
                   	fundProvService.setMinAmt(fundServiceRate.getMinAmt());
                   	fundProvService.setSvRate(fundServiceRate.getSvRate());
                   	fundProvService.setJtBegDate(begDate);
                   	fundProvService.setJtEndDate(endDate);
                   	fundProvService.setPrdtNo(rptPrdtDaily.getPrdtNo());
                   	fundProvService.setPrdtDueAmt(rptPrdtDaily.getAmtTot());
                   	fundProvService.setPrdtName("");
                   	fundProvService.setPrdtServicefee(Prdt_amt.doubleValue());
                   	fundProvService.setServicefee(amt.doubleValue());
                   	fundProvService.setOffRate(rptPrdtDaily.getOffRate());
                   	fundProvService.setOpNo(fundProvProj.getOpNo());
                   	fundProvService.setTxDate((User.getTime().replace("-", "")).substring(0, 8));

                   	fundProvServiceDao.insert(fundProvService);
        	         }                  	    
					}else{
					//历史放贷规模
					rptPrdtDaily.setRptDate(before_Date);
					Double before_amt = fundProvProjDao.RptRrdtDailygetByProjNoamt(rptPrdtDaily);
					//计提结束日对应放贷规模
					rptPrdtDaily.setRptDate(fundProvProj.getEndDate());
					Double end_amt = fundProvProjDao.RptRrdtDailygetByProjNoamt(rptPrdtDaily);					
					//历史放贷规模阶梯编号
					String min_stepNo = null;
					//计提结束日放贷规模阶梯编号
					String now_stepNo = null;
					//1.获取累计到计提开始日之前的放贷额，再遍历放贷服务费率的费率阶段(n1)；获取累计到计提日的累计放贷额，再遍历放贷服务费率的费率阶段(nn)
					FundServiceRate min_fundServiceRate = null;
					FundServiceRate max_fundServiceRate = null;
					for(int i=0;i<fundServiceRateList.size();i++){
						FundServiceRate fundServiceRate1 = fundServiceRateList.get(i);
						if((fundServiceRate1.getMinAmt()<before_amt && before_amt<=fundServiceRate1.getMaxAmt())||
								(fundServiceRate1.getMinAmt()<before_amt && fundServiceRate1.getMaxAmt()== 0)){
							min_stepNo = fundServiceRate1.getStepNo();
							min_fundServiceRate = fundServiceRate1;
							
						}
						if((fundServiceRate1.getMinAmt()<end_amt && end_amt<=fundServiceRate1.getMaxAmt())||
								(fundServiceRate1.getMinAmt()<end_amt && fundServiceRate1.getMaxAmt()== 0)){
							now_stepNo = fundServiceRate1.getStepNo();
							max_fundServiceRate = fundServiceRate1;
						}
					}
                    //2.判断n1跟nn是否在一个阶段:
					if(min_fundServiceRate == max_fundServiceRate){
						//3.1.n1=nn不考虑跨阶段
						//获取按产品的放贷量 (计提到日期累计放贷量-计提开始前一天的放贷量)
    						//阶梯内放贷服务费累计
						     String now_date = begDate;
                       	    while(fundProvProj.getEndDate().compareTo(now_date) >= 0){
                       	    	BigDecimal day_amt ;
                       	    	rptPrdtDaily.setRptDate(now_date);
                       	    	day_amt = get_dayamt(rptPrdtDaily,min_fundServiceRate.getSvRate());
                       	    	//日期加1
                       	    	now_date = DateUtil.addByDay(now_date, 1);
                       	        //阶梯内开始日期到结束日期的放贷服务费
                       	    	serviceFee = serviceFee.add(day_amt);
                       	    }    
                       	 //插入表收益计提放贷服务费明细表FUND_PROV_SERVICE
                       	    rptPrdtDaily.setRptDate(DateUtil.addByDay(begDate, -1));
                       	    //设置到期日期
                       	    rptPrdtDaily.setPrdtNo(endDate);
                		    List<RptPrdtDaily> rptPrdtDailyList = fundProvProjDao.RptRrdtDailygetByprdtno( rptPrdtDaily);
                		    BigDecimal Rate = FoDcm(min_fundServiceRate.getSvRate());
                		    BigDecimal amt = new BigDecimal("0.00");
                	        for(int i=0;i<rptPrdtDailyList.size();i++){
                		    //按产品分类放贷服务费
                	        BigDecimal Prdt_amt = new BigDecimal("0.00");
                		    rptPrdtDaily = rptPrdtDailyList.get(i);
                		    //一个产品的累计放贷服务费
                		    BigDecimal AmtTot = DoDcm(rptPrdtDaily.getAmtTot());
                		    BigDecimal OffRate = FoDcm(rptPrdtDaily.getOffRate());
                		    Prdt_amt = AmtTot.multiply(Rate).multiply(OffRate);
                		    amt = amt.add(Prdt_amt);
                		    
                           	FundProvService fundProvService = new FundProvService();

                           	fundProvService.setProjNo(fundProvProj.getProjNo());
                           	fundProvService.setProvProjNo(provProjNo);
                           	fundProvService.setStepNo(min_fundServiceRate.getStepNo());
                           	fundProvService.setMaxAmt(min_fundServiceRate.getMaxAmt());
                           	fundProvService.setMinAmt(min_fundServiceRate.getMinAmt());
                           	fundProvService.setSvRate(min_fundServiceRate.getSvRate());
                           	fundProvService.setJtBegDate(begDate);
                           	fundProvService.setJtEndDate(endDate);
                           	fundProvService.setPrdtNo(rptPrdtDaily.getPrdtNo());
                           	fundProvService.setPrdtDueAmt(rptPrdtDaily.getAmtTot());
                           	fundProvService.setPrdtName("");
                           	fundProvService.setPrdtServicefee(Prdt_amt.doubleValue());
                           	fundProvService.setServicefee(amt.doubleValue());
                           	fundProvService.setOffRate(rptPrdtDaily.getOffRate());
                           	fundProvService.setOpNo(fundProvProj.getOpNo());
                           	fundProvService.setTxDate((User.getTime().replace("-", "")).substring(0, 8));

                           	fundProvServiceDao.insert(fundProvService);
                	         }          
					}else{
						//3.2.nn <> n1；nn跟n1之间的时间节点（累计到每阶段最大值时的日期n1阶段date_1,...,n(n-1)阶段date_(n-1)）
						
						fundServiceRate = min_fundServiceRate;
						//开始时间
						String begin_date = begDate;
 					    //所用参数--插入表收益计提放贷服务费明细表FUND_PROV_SERVICE
						List<FundProvService> fundProvServicelist = new ArrayList() ;
						List<FundProvService> fundProvServicelist_next = new ArrayList() ;
						//判断当前阶梯的最大值是否大于最大阶梯的最大值
                         while(fundServiceRate.getMaxAmt()<=max_fundServiceRate.getMaxAmt()){
                        	 //累计贷款不超过阶梯上限的最大日期
                        	String stage_max_date ;
                        	//累计到阶梯节点的日期
     						String date = null;
     						//定义下一阶段的服务费率实体类
    						FundServiceRate next_fundServiceRate = new FundServiceRate();
  					    	next_fundServiceRate.setProjNo(fundProvProj.getProjNo());
 					    	next_fundServiceRate.setMinAmt(fundServiceRate.getMaxAmt());
 					    	next_fundServiceRate = fundServiceRateDao.getByProjAndMinamt(next_fundServiceRate);
                        	//获取当前阶梯上限对应的累计放贷量小于阶梯上限的日期：stage_max_date
 					    	RptPrdtDaily rptPrdtDaily1 = new RptPrdtDaily();
 					    	rptPrdtDaily1.setProjNo(fundProvProj.getProjNo());
 					    	rptPrdtDaily1.setAmtTot(fundServiceRate.getMaxAmt());
 					    	rptPrdtDaily1 = fundProvProjDao.RptRrdtDailygetBymaxDate(rptPrdtDaily1);
 					    	//判断阶梯内最大日期（如果是最后阶梯，最大日期就是计提结束日期）
 					    	Double reult = fundServiceRate.getMaxAmt()-max_fundServiceRate.getMaxAmt();
 					    	if(reult == 0 ){
 					    		stage_max_date = fundProvProj.getEndDate();	
 					    		date = DateUtil.addByDay(stage_max_date, 1);
 					    	}else{
 					    		stage_max_date = rptPrdtDaily1.getRptDate();	
 	                       	//------start累计到阶梯上限的日期计算方法start---------
         						//上限日期
         						date = DateUtil.addByDay(stage_max_date, 1);
         						//上限日期前一天的累计放贷金额
         						Double amt = fundProvProjDao.RptRrdtDailygetByProjNoamt(rptPrdtDaily1);
         						BigDecimal less_date_amt = DoDcm(amt);
         						//计算到节点还差多少金额
         						BigDecimal less_amt = DoDcm(fundServiceRate.getMaxAmt()).subtract(less_date_amt);
         						//获取上限日的当日产品发生额
         						rptPrdtDaily.setRptDate(date);
    						   
         						List<RptPrdtDaily> rptPrdtDailyList = fundProvProjDao.RptRrdtDailygetByPrdtNoamt(rptPrdtDaily);
         				        for(int i=0;i<rptPrdtDailyList.size();i++){
         					    //在上一阶梯放贷服务费
         				        BigDecimal PrdtNoamt_down = DoDcm(0.00);
         					    //在下一阶梯放贷服务费
         				        BigDecimal PrdtNoamt_up = DoDcm(0.00);
         					    //单次计算放贷服务费
         				        BigDecimal PrdtNoamt = DoDcm(0.00);
         					    RptPrdtDaily rptPrdtDaily2 = rptPrdtDailyList.get(i);
     					    
         					    if(less_amt.compareTo(DoDcm(rptPrdtDaily2.getAmtTot()))>=0){
         						    //当天一个产品放贷服务费       					    	
         						    PrdtNoamt = DoDcm(rptPrdtDaily2.getAmtTot()).multiply(FoDcm(fundServiceRate.getSvRate())).multiply(FoDcm(rptPrdtDaily2.getOffRate()));
         						    PrdtNoamt_down = PrdtNoamt_down.add(PrdtNoamt);
         						    less_amt= less_amt.subtract(DoDcm(rptPrdtDaily2.getAmtTot()));
         						    
             					    //所用参数--插入表收益计提放贷服务费明细表FUND_PROV_SERVICE
         						    FundProvService fundProvService = new FundProvService();
         						    fundProvService.setProjNo(fundProvProj.getProjNo());
         						    fundProvService.setPrdtNo(rptPrdtDaily2.getPrdtNo());
         						    fundProvService.setPrdtDueAmt(rptPrdtDaily2.getAmtTot());
         						    fundProvService.setStepNo(fundServiceRate.getStepNo());
         						    fundProvService.setSvRate(fundServiceRate.getSvRate());
         						    fundProvService.setOffRate(rptPrdtDaily2.getOffRate());
         						    fundProvServicelist.add(fundProvService);
         						   
         					    }else{
         					    	//当天一个产品中在下一阶梯部分规模
         					    	BigDecimal less_prdtbal = DoDcm(rptPrdtDaily2.getAmtTot()).subtract(less_amt);
         					    	
         					    	//所用参数--插入表收益计提放贷服务费明细表FUND_PROV_SERVICE
         					    	if(less_prdtbal.doubleValue()>0){
             						    FundProvService fundProvService = new FundProvService();
             						    fundProvService.setProjNo(fundProvProj.getProjNo());
             						    fundProvService.setPrdtNo(rptPrdtDaily2.getPrdtNo());
             						    fundProvService.setPrdtDueAmt(less_prdtbal.doubleValue());
             						    fundProvService.setStepNo(next_fundServiceRate.getStepNo());
             						    fundProvService.setOffRate(rptPrdtDaily2.getOffRate());
             						    fundProvService.setSvRate(next_fundServiceRate.getSvRate());
             						    fundProvServicelist_next.add(fundProvService);
         					    	}
         					    	//当天一个产品在下一阶梯部分放贷服务费
         					    	PrdtNoamt = less_prdtbal.multiply(FoDcm(next_fundServiceRate.getSvRate())).multiply(FoDcm(rptPrdtDaily2.getOffRate()));
         					    	PrdtNoamt_up = PrdtNoamt_up.add(PrdtNoamt);
         					    	//当天一个产品在上一阶梯部分放贷服务费
         					    	PrdtNoamt = less_amt.multiply(FoDcm(fundServiceRate.getSvRate())).multiply(FoDcm(rptPrdtDaily2.getOffRate()));
         					    	
         					    	//所用参数--插入表收益计提放贷服务费明细表FUND_PROV_SERVICE
         					    	if(less_amt.doubleValue()>0){
             						    FundProvService fundProvService = new FundProvService();
             						    fundProvService.setProjNo(fundProvProj.getProjNo());
             						    fundProvService.setPrdtNo(rptPrdtDaily2.getPrdtNo());
             						    fundProvService.setPrdtDueAmt(less_amt.doubleValue());
             						    fundProvService.setOffRate(rptPrdtDaily2.getOffRate());
             						    fundProvService.setStepNo(fundServiceRate.getStepNo());
             						    fundProvService.setSvRate(fundServiceRate.getSvRate());
             						    fundProvServicelist.add(fundProvService);
         					    	}
         					    	PrdtNoamt_down = PrdtNoamt_down.add(PrdtNoamt);
         					    	less_amt = DoDcm(0.00);
         					    }
         					    serviceFee = serviceFee.add(PrdtNoamt_down.add(PrdtNoamt_up));
         				       }
         				      //------阶梯节点日计算方法end---------
 					    	}
     				     
                        //测算不是阶梯点日期的放贷服务费	 
 					    	
 					    	if(stage_max_date.compareTo(begin_date)>=0){
 					    		rptPrdtDaily.setRptDate(DateUtil.addByDay(begin_date, -1));
 					    	//插入表收益计提放贷服务费明细表FUND_PROV_SERVICE--start
                     	    //设置到期日期
                     	    rptPrdtDaily.setPrdtNo(stage_max_date);
              		        List<RptPrdtDaily> rptPrdtDailyList = fundProvProjDao.RptRrdtDailygetByprdtno(rptPrdtDaily);
              		        BigDecimal Rate = FoDcm(fundServiceRate.getSvRate());
              		        BigDecimal amt = new BigDecimal("0.00");
              	            for(int i=0;i<rptPrdtDailyList.size();i++){
              		        //按产品分类放贷服务费
              	            BigDecimal Prdt_amt = new BigDecimal("0.00");
              	            RptPrdtDaily rptPrdtDaily3 = rptPrdtDailyList.get(i);
              		        //一个产品的累计放贷服务费
              		        BigDecimal amtTot = DoDcm(rptPrdtDaily3.getAmtTot());
              		        BigDecimal OffRate = FoDcm(rptPrdtDaily3.getOffRate());
              		       
              		        //循环在上限日期date时不同产品在本阶梯内金额
              		          for(int j=0;j<fundProvServicelist.size();j++){
              		        	 if(rptPrdtDaily3.getPrdtNo().equals(fundProvServicelist.get(j).getPrdtNo())){
              		        		amtTot = amtTot.add(DoDcm(fundProvServicelist.get(j).getPrdtDueAmt()));
              		        		Prdt_amt = amtTot.multiply(Rate).multiply(OffRate);
                       		        amt = amt.add(Prdt_amt);
              		        	 }
              		          }
            		         
                         	FundProvService fundProvService = new FundProvService();
                         	fundProvService.setProvProjNo(provProjNo);
                           	fundProvService.setProjNo(fundProvProj.getProjNo());
                           	fundProvService.setStepNo(fundServiceRate.getStepNo());
                           	fundProvService.setMaxAmt(fundServiceRate.getMaxAmt());
                           	fundProvService.setMinAmt(fundServiceRate.getMinAmt());
                           	fundProvService.setSvRate(fundServiceRate.getSvRate());
                           	fundProvService.setJtBegDate(begDate);
                           	fundProvService.setJtEndDate(endDate);
                           	fundProvService.setPrdtNo(rptPrdtDaily3.getPrdtNo());
                        	fundProvService.setPrdtName("");
                           	fundProvService.setPrdtDueAmt(amtTot.doubleValue());
                           	fundProvService.setPrdtServicefee(Prdt_amt.doubleValue());
                           	fundProvService.setServicefee(amt.doubleValue());
                           	fundProvService.setOffRate(rptPrdtDaily3.getOffRate());
                           	fundProvService.setOpNo(fundProvProj.getOpNo());
                           	fundProvService.setTxDate((User.getTime().replace("-", "")).substring(0, 8));

                           	fundProvServiceDao.insert(fundProvService);
                           	//添加项目整体的服务费
              	            }
                           }else{
                        	 //插入表收益计提放贷服务费明细表FUND_PROV_SERVICE--start 
                        	   BigDecimal amt = new BigDecimal("0.00");
                        	   for(int j=0;j<fundProvServicelist.size();j++){    
                 		        	fundProvServicelist.get(j).getPrdtNo();
                      		        //一个产品的累计放贷服务费
                 		        	 BigDecimal Prdt_amt = DoDcm(fundProvServicelist.get(j).getPrdtDueAmt()).multiply(FoDcm(fundServiceRate.getSvRate())).multiply(FoDcm(fundProvServicelist.get(j).getOffRate()));
                 		        	 amt = amt.add(Prdt_amt);
                      		        
                            	FundProvService fundProvService = new FundProvService();
                            	fundProvService.setProvProjNo(provProjNo);
                               	fundProvService.setProjNo(fundProvProj.getProjNo());
                               	fundProvService.setStepNo(fundServiceRate.getStepNo());
                               	fundProvService.setMaxAmt(fundServiceRate.getMaxAmt());
                               	fundProvService.setMinAmt(fundServiceRate.getMinAmt());
                               	fundProvService.setSvRate(fundServiceRate.getSvRate());
                               	fundProvService.setJtBegDate(begDate);
                               	fundProvService.setJtEndDate(endDate);
                               	fundProvService.setPrdtNo(fundProvServicelist.get(j).getPrdtNo());
                            	fundProvService.setPrdtName("");
                               	fundProvService.setPrdtDueAmt(fundProvServicelist.get(j).getPrdtDueAmt());
                               	fundProvService.setPrdtServicefee(Prdt_amt.doubleValue());
                               	fundProvService.setServicefee(amt.doubleValue());
                               	fundProvService.setOffRate(fundProvServicelist.get(j).getOffRate());
                               	fundProvService.setOpNo(fundProvProj.getOpNo());
                               	fundProvService.setTxDate((User.getTime().replace("-", "")).substring(0, 8));

                               	fundProvServiceDao.insert(fundProvService);
                        	   }
                           };
                          //插入表收益计提放贷服务费明细表FUND_PROV_SERVICE--end
              	            
    						//阶梯内放贷服务费累计
                       	    while(stage_max_date.compareTo(begin_date) >= 0){
                       	    	BigDecimal day_amt = DoDcm(0.00);
                       	    	rptPrdtDaily.setRptDate(begin_date);
                       	    	day_amt = get_dayamt(rptPrdtDaily,fundServiceRate.getSvRate());
                       	    	//日期加1
                       	    	begin_date = DateUtil.addByDay(begin_date, 1);
                       	        //阶梯内开始日期到结束日期的放贷服务费
                       	    	serviceFee = serviceFee.add(day_amt);
                       	    }
                       	  //判断最大档下一档为空的情况处理  
                       	  if(fundProvServicelist_next.size()<1){
                       		fundServiceRate.setMaxAmt(fundServiceRate.getMaxAmt()+1); 
                       	  }else{
                       		fundProvServicelist.clear();
                       		fundProvServicelist.addAll(fundProvServicelist_next);
                       		fundProvServicelist_next.clear();
                       		fundServiceRate = next_fundServiceRate;
                       	  }
       				      begin_date = DateUtil.addByDay(date, 1);
                         }						
					}
				}
			}
	/**************************放贷服务费计算方法end******************************************************/
			
	/**************************融资报酬计算方法end*******************************************************/
				 //从资金主表中获取项目对应的资金信息
				FundBase fundBase = new FundBase();
				fundBase.setProjNo(fundProvProj.getProjNo());
				    //获得资金总条数
				int fund_base_count = fundBaseDao.getCount(fundBase);
				Ipage ipage = new Ipage();
				ipage.setPageSize(fund_base_count);		
				fundBase.setStartNumAndEndNum(ipage);
				  //获得所有资金数据
				List<FundBase> fundBaseList= fundBaseDao.findByPage(fundBase);
				 //计算每条资金数据
				for(int i=0;i<fundBaseList.size();i++){
					  //资金融资成本
					BigDecimal fund_fineCost =  DoDcm(0.00);
					  // 资金存续期内融资规模、天数积
					BigDecimal fund_fineCost_parm = DoDcm(0.00);
					  //资金维度受益人收益
					BigDecimal fund_payAmt =  DoDcm(0.00);
					  //获取资金相关信息
					fundBase = fundBaseList.get(i);
					//计算融资成本
					BigDecimal financerate = FoDcm(fundBase.getFinanceRate());
					  //从分配方案存续表中获取数据
					FundSplitTerm fundSplitTerm = new FundSplitTerm();
					//资金编号赋值
					fundSplitTerm.setFundNo(fundBase.getFundNo());

				    //获得资金总条数
					int fund_plitterm_count = fundSplitTermDao.getCount(fundSplitTerm);
					Ipage ipage1 = new Ipage();
					ipage1.setPageSize(fund_plitterm_count);
					fundSplitTerm.setStartNumAndEndNum(ipage1);
					List<FundSplitTerm> fundSplitTermList= fundSplitTermDao.findByPage(fundSplitTerm);
 					for(int j=0;j<fundSplitTermList.size();j++){
						// 最大开始日期
						String maxbegDate;
						// 最小结束日期
						String minendDate;
						//存续期内融资规模、天数积
						BigDecimal term_fineCost_parm ;
						//存续期内受益人收益规模
						BigDecimal term_payAmt;						
						
						fundSplitTerm=fundSplitTermList.get(j);
						String begtermDate = fundSplitTerm.getBegDate(); 
						String endtermDate = fundSplitTerm.getEndDate();

						//判断两个日期时间段是否存在交集，如存在，找出最大开始日期，最小结束日期
						if(((begtermDate.compareTo(begDate) <=0) && begDate.compareTo(endtermDate) < 0) || 
							((begtermDate.compareTo(begDate) > 0) && (begtermDate.compareTo(endDate)) < 0)	){
							if(begtermDate.compareTo(begDate) > 0){
								maxbegDate = begtermDate ;
							}else {
								maxbegDate = begDate ;
							}
							if(endtermDate.compareTo(endDate) > 0 ){
								minendDate = endDate;
							}else{
								minendDate = endtermDate;
							}
							//计算一个存续记录在一个清算周期内的天数
							int days=DateUtil.getBetweenDays(DateUtil.changeToShow(maxbegDate), DateUtil.changeToShow(minendDate));
							//计算该笔对应的融资成本=金额*融资利率*天数/年天数（资金的）
							//term_fineCost=(fundSplitTerm.getTermAmt())*(fundBase.getFinanceRate())*days/(fundBase.getYearDays());
							//存续期内融资规模、天数积
                            term_fineCost_parm=DoDcm(fundSplitTerm.getTermAmt()).multiply(IoDcm(days)).divide(IoDcm(fundBase.getYearDays()),3,BigDecimal.ROUND_HALF_UP);
							//从分配方案获取年天数
							FundSplit fundSplit = new FundSplit();
							fundSplit.setPartNo(fundSplitTerm.getPartNo());
							fundSplit = fundSplitDao.getById(fundSplit);
	
							//计算该笔对应的受益人收益payAmt=金额*收益率*天数/年天数（分配方案的）
							BigDecimal invRate = FoDcm(fundSplitTerm.getInvRate());
							BigDecimal termAmt = DoDcm(fundSplitTerm.getTermAmt());
							term_payAmt=(invRate.multiply(termAmt)).multiply(IoDcm(days)).divide(IoDcm(fundBase.getYearDays()),3,BigDecimal.ROUND_HALF_UP);
						    
							//插入收益计提融资报酬，管理费明细登记表FUND_PROV_SPLIT_TERM
							FundProvSplitTerm fundProvSplitTerm = new FundProvSplitTerm();
							fundProvSplitTerm.setProvProjNo(provProjNo);
							fundProvSplitTerm.setProjNo(fundProvProj.getProjNo());
							fundProvSplitTerm.setFundNo(fundBase.getFundNo());
							fundProvSplitTerm.setPartNo(fundSplitTerm.getPartNo());
							fundProvSplitTerm.setTermNo(fundSplitTerm.getTermNo());
							fundProvSplitTerm.setTermAmt(fundSplitTerm.getTermAmt());
							fundProvSplitTerm.setBegDate(fundSplitTerm.getBegDate());
							fundProvSplitTerm.setEndDate(fundSplitTerm.getEndDate());
							fundProvSplitTerm.setJtBegDate(begDate);
							fundProvSplitTerm.setJtEndDate(endDate);
							fundProvSplitTerm.setDays(days);
							fundProvSplitTerm.setYearDays(fundBase.getYearDays());
							fundProvSplitTerm.setFinanceRate(fundBase.getFinanceRate());
							fundProvSplitTerm.setFinCost(financerate.multiply(term_fineCost_parm).doubleValue());
							fundProvSplitTerm.setInvRate(fundSplitTerm.getInvRate());
							fundProvSplitTerm.setPayAmt(term_payAmt.doubleValue());
							fundProvSplitTerm.setFinlIncome(financerate.multiply(term_fineCost_parm).subtract(term_payAmt).doubleValue());
							fundProvSplitTerm.setManagerRate(mangFeerate.floatValue());
							fundProvSplitTerm.setManagerFee(mangFeerate.multiply(term_fineCost_parm).doubleValue());
							fundProvSplitTerm.setOpNo(fundProvProj.getOpNo());
							fundProvSplitTerm.setTxDate((User.getTime().replace("-", "")).substring(0, 8));
							fundProvSplitTermDao.insert(fundProvSplitTerm);
						}else{
							term_fineCost_parm = DoDcm(0.00);
							term_payAmt = DoDcm(0.00);
						}
						fund_fineCost_parm =fund_fineCost_parm.add(term_fineCost_parm);
						fund_payAmt =fund_payAmt.add(term_payAmt);
					}
    /************************************************************************************************/ 					
 	/**##########判断资金性质为优先级资金有融资报酬############ **/
					if("01".equals(fundBase.getFdType())){
	/**##########融资报酬计算方法############ **/
//						if("1".equals(payType.substring(3, 4))){
						if(payType.contains("4")){
							BigDecimal fundfineCostparm = fund_fineCost_parm;
							fund_fineCost = financerate.multiply(fundfineCostparm);
							//资金维度的融资成本或者资金维度的受益人收益不为零
                            if(fund_fineCost.doubleValue() != 0 || fund_payAmt.doubleValue()!=0){
    							FundProv fundProv = new FundProv();		

    							fundProv.setProvProjNo(provProjNo);
    							fundProv.setProjNo(fundProvProj.getProjNo());	
    							fundProv.setFundNo(fundBase.getFundNo());
    							fundProv.setFundName(fundBase.getFundName());
    							fundProv.setBegDate(begDate);
    							fundProv.setEndDate(endDate);
    							fundProv.setFdAmt(fundBase.getFdAmt());
    							fundProv.setFinRate(fundBase.getFinanceRate());
    							fundProv.setFineCost(fund_fineCost.doubleValue());
    							fundProv.setPayAmt(fund_payAmt.doubleValue());
    							fundProv.setFinIncome(fund_fineCost.subtract(fund_payAmt).doubleValue());
    							fundProv.setOpNo(fundProvProj.getOpNo());
    							fundProv.setTxDate((User.getTime().replace("-", "")).substring(0, 8));					
    							//插入资金的融资报酬计提表
    							fundProvDao.insert(fundProv);                            	
                            }

						}
						//项目累计融资成本，受益人收益率
						finCost=finCost.add(fund_fineCost);
						payAmt=payAmt.add(fund_payAmt);
						//项目累计计算信托管理费的基数
						managerFee_super_parm=managerFee_super_parm.add(fund_fineCost_parm);
					}else{
						managerFee_less_parm=managerFee_less_parm.add(fund_fineCost_parm);
					}
					
				}
	/*********************************计算项目管理费用***************************************************/ 	
			   //2.计算项目管理费用：managerFee_parm*mangFeerate
//				if( "1".equals(payType.substring(0, 1))){
				if(payType.contains("1")){
					//2.1信托管理费计算方法-优先级
					managerFee_super = managerFee_super_parm.multiply(mangFeerate);
				}
//				if( "1".equals(payType.substring(1, 2))){
				if(payType.contains("2")){
					//2.2信托管理费计算方法-次级
					managerFee_less = managerFee_less_parm.multiply(mangFeerate);
				}
				managerFee=managerFee_super.add(managerFee_less);
	/*********************************计算项目管理费用***************************************************/ 	
	/********************汇总各费用插入计提收益汇总表*********************/ 

			   //总金额drawingAmt
			Double drawingAmt = managerFee.add(serviceFee).add(finCost.subtract(payAmt)).doubleValue();
			fundProvProj.setProvProjNo(provProjNo);
			fundProvProj.setProjName(projParm.getProjName());
			fundProvProj.setFinCost(finCost.doubleValue());
			fundProvProj.setPayAmt(payAmt.doubleValue());
			fundProvProj.setFinlIncome(finCost.subtract(payAmt).doubleValue());
			fundProvProj.setManagerFee(managerFee.doubleValue());
			fundProvProj.setServiceFee(serviceFee.doubleValue());
			fundProvProj.setDrawingAmt(drawingAmt);
			fundProvProj.setTxDate((User.getTime().replace("-", "")).substring(0, 8));
			fundProvProjDao.insert(fundProvProj);
			fundProvProj=fundProvProjDao.getById(fundProvProj);

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundProvProj;
	}
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-27
	 * @方法说明：一天放贷服务费
	 * @返回参数 BigDecimal
	 */
	public BigDecimal get_dayamt(RptPrdtDaily rptPrdtDaily,Float SvRate)throws ServiceException {
		BigDecimal amt = new BigDecimal("0.00") ;
		BigDecimal Rate = FoDcm(SvRate);
       try {
		    List<RptPrdtDaily> rptPrdtDailyList = fundProvProjDao.RptRrdtDailygetByPrdtNoamt( rptPrdtDaily);
	        for(int i=0;i<rptPrdtDailyList.size();i++){
		    //按产品分类放贷服务费
	        BigDecimal Prdt_amt = new BigDecimal("0.00");
		    rptPrdtDaily = rptPrdtDailyList.get(i);
		    //一个产品的累计放贷服务费
		    BigDecimal AmtTot = DoDcm(rptPrdtDaily.getAmtTot());
		    BigDecimal OffRate = FoDcm(rptPrdtDaily.getOffRate());
		    Prdt_amt = AmtTot.multiply(Rate).multiply(OffRate);
		    amt = amt.add(Prdt_amt);
	       }
         } catch (Exception e) {
	         throw new ServiceException(e.getMessage());
        }
            return amt;
    }
	/**
	 * 
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-27
	 * @方法说明：float转 decimal
	 * @返回参数 BigDecimal
	 */
	public  BigDecimal FoDcm(Float value)throws ServiceException {
		BigDecimal decimal = new BigDecimal(Float.toString(value));
       return decimal;
    }
	/**
	 * 
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-27
	 * @方法说明：Double转 decimal
	 * @返回参数 BigDecimal
	 */
	public static BigDecimal DoDcm(Double value)throws ServiceException {
		BigDecimal decimal = new BigDecimal(Double.toString(value));
       return decimal;
    }
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-28
	 * @方法说明：int 转decimal
	 * @返回参数 BigDecimal
	 */
	public static BigDecimal IoDcm(Integer value)throws ServiceException {
		BigDecimal decimal = new BigDecimal(Integer.toString(value));
       return decimal;
    }	
	public void update(FundProvProj fundProvProj) throws ServiceException {
		try {
			fundProvProjDao.update(fundProvProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String getProjNameByProjNo(String projNo) throws ServiceException {
		String projName="";
		try {
			projName = fundProvProjDao.getProjNameByProjNo(projNo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projName;
	}
	public FundProvProjDao getFundProvProjDao() {
		return fundProvProjDao;
	}

	public void setFundProvProjDao(FundProvProjDao fundProvProjDao) {
		this.fundProvProjDao = fundProvProjDao;
	}
	//引入
	public FundProvDao getFundProvDao() {
		return fundProvDao;
	}
	public void setFundProvDao(FundProvDao fundProvDao) {
		this.fundProvDao = fundProvDao;
	}
	public ProjParmDao getProjParmDao() {
		return projParmDao;
	}
	public void setProjParmDao(ProjParmDao projParmDao) {
		this.projParmDao = projParmDao;
	}
	public FundBaseDao getFundBaseDao() {
		return fundBaseDao;
	}
	public void setFundBaseDao(FundBaseDao fundBaseDao) {
		this.fundBaseDao = fundBaseDao;
	}
	public FundSplitTermDao getFundSplitTermDao() {
		return fundSplitTermDao;
	}
	public void setFundSplitTermDao(FundSplitTermDao fundSplitTermDao) {
		this.fundSplitTermDao = fundSplitTermDao;
	}
	public FundSplitDao getFundSplitDao() {
		return fundSplitDao;
	}
	public void setFundSplitDao(FundSplitDao fundSplitDao) {
		this.fundSplitDao = fundSplitDao;
	}
	public FundServiceRateDao getFundServiceRateDao() {
		return fundServiceRateDao;
	}
	public void setFundServiceRateDao(FundServiceRateDao fundServiceRateDao) {
		this.fundServiceRateDao = fundServiceRateDao;
	}
	public PrdtBaseDao getPrdtBaseDao() {
		return prdtBaseDao;
	}
	public void setPrdtBaseDao(PrdtBaseDao prdtBaseDao) {
		this.prdtBaseDao = prdtBaseDao;
	}
	public FundProvSplitTermDao getFundProvSplitTermDao() {
		return fundProvSplitTermDao;
	}
	public void setFundProvSplitTermDao(FundProvSplitTermDao fundProvSplitTermDao) {
		this.fundProvSplitTermDao = fundProvSplitTermDao;
	}
	public FundProvServiceDao getFundProvServiceDao() {
		return fundProvServiceDao;
	}
	public void setFundProvServiceDao(FundProvServiceDao fundProvServiceDao) {
		this.fundProvServiceDao = fundProvServiceDao;
	}

	
}