package  app.creditapp.fund.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import app.creditapp.fund.ReadExecl;
import app.creditapp.fund.bo.FundBaseBo;
import app.creditapp.fund.bo.FundDetailBo;
import app.creditapp.fund.entity.FundBase;
import app.creditapp.fund.entity.FundDetail;
import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;
import app.util.User;
import app.util.message.conversion.transfer.TransferHandler;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: FundDetailAction.java
 * Description:
 **/
public class FundDetailAction extends BaseFormBean {

	//页面传值
	private FundDetail fundDetail;
	private List<FundDetail> fundDetailList;

	private TransferHandler transferHandler;
	private File upload;
	private String uploadFileName;	
	private String uploadContentType;	
	private String downloadFile;
	
	//注入FundDetailBo
	private FundDetailBo fundDetailBo;
	private FundBaseBo fundBaseBo;

	private String query;
	private String detailId;		
	private String fundNo;	
	private String fdType;
	private String projNo;
	private FormData formfddetail0001;
	private FormData formfddetail0002;
	private FormService formService = new FormService();
	
	public FundDetailAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfddetail0001 = formService.getFormData("fddetail0001");
		fundDetail = new FundDetail();
		getFormValue(formfddetail0001);
		setObjValue(formfddetail0001, fundDetail);
		Ipage ipage = this.getIpage();
		fundDetailList = (List) fundDetailBo.findByPage(ipage, fundDetail).getResult();
//		for(int i = 0; i<fundDetailList.size();i++){
//			fundDetailList.get(i).setQuery(query);
//		}
		
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfddetail0002 = formService.getFormData("fddetail0002");
		fundDetail = new FundDetail();
		fundDetail.setFundNo(fundNo);
		getObjValue(formfddetail0002, fundDetail);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfddetail0002 = formService.getFormData("fddetail0002");
		getFormValue(formfddetail0002);
		fundDetail = new FundDetail();
		setObjValue(formfddetail0002, fundDetail);
		fundDetail.setOpNo(User.getLoginid(this.getHttpRequest()));
		fundDetailBo.insert(fundDetail);
		
		this.addActionMessage("明细添加成功");
		fundDetail = fundDetailBo.getById(fundDetail);
		query="query";
		return "detail1";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfddetail0002 = formService.getFormData("fddetail0002");
		getFormValue(formfddetail0002);
		fundDetail = new FundDetail();
		setObjValue(formfddetail0002, fundDetail);
		fundDetailBo.update(fundDetail);
		this.addActionMessage("修改成功");
		query="query";
		getObjValue(formfddetail0002, fundDetail);
		return "detail";
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfddetail0001 = formService.getFormData("fddetail0001");
		fundDetail = new FundDetail();
		fundDetail.setDetailId(detailId);
		fundDetailBo.del(fundDetail);
		this.addActionMessage("删除成功");
		fundDetail = new FundDetail();
		fundDetail.setFundNo(fundNo);
		Ipage ipage = this.getIpage();
		fundDetailList = (List) fundDetailBo.findByPage(ipage, fundDetail).getResult();
		return "list";
	}
	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfddetail0002 = formService.getFormData("fddetail0002");
		fundDetail = new FundDetail();
		fundDetail.setDetailId(detailId);
		fundDetail = fundDetailBo.getById(fundDetail);
		getObjValue(formfddetail0002, fundDetail);
		ServletActionContext.getRequest().setAttribute("fundNo", fundDetail.getFundNo());
		this.changeFormProperty(formfddetail0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfddetail0002 = formService.getFormData("fddetail0002");
		 getFormValue(formfddetail0002);
		 validateFormData(formfddetail0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfddetail0002 = formService.getFormData("fddetail0002");
		 getFormValue(formfddetail0002);
		 validateFormData(formfddetail0002);
  	}
	
	/**
	 * 文件上传页面
	 * @return
	 */
	public String uploadInput() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());	
		return "uploadInput";
	}	
	/**
	 * 导入
	 * @return
	 * @throws Exception 
	 */
	public String upload() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		FileOutputStream fos = null;
		FileInputStream fis = null;
		String opNo = User.getLoginid(this.getHttpRequest());
		if(this.uploadFileName==null || "".equals(this.uploadFileName) ){
			this.addActionError("文件未上传，或文件存在错误！");
			return "input";
		}
		String ftype = this.uploadFileName.substring(this.uploadFileName.indexOf("."));
		if(!(".xls".equals(ftype)||".xlsx".equals(ftype))){
			this.addActionError("上传文件格式错误，系统支持上传.xls和.xlsx文件！");
			return "input";
		}
		//获取上传目标路径
		Map<String,String> sysPathMap =(Map<String,String>)MBaseCache.getCache().
				getBeanCache(CachecodeUtil.SYS_PATH_STR, CachecodeUtil.SYS_PATH);
		String tmppath = sysPathMap.get("101")+"/"+this.uploadFileName;
		/*String tmppath = UploadConfigurationRead.getInstance().getConfigItem("tmpuploadpath")+"/"
				+java.util.UUID.randomUUID()+ftype;*/		
		try {
			fos = new FileOutputStream(tmppath);
			fis = new FileInputStream(this.getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=fis.read(buffer))>0){
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			this.addActionError("上传文件不存在！");
			return "input";
		} catch (IOException e) {
			e.printStackTrace();
			this.addActionError("上传文件读取过程中出现错误！");
			return "input";
		}finally{
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ReadExecl rex = new ReadExecl();
		List<FundDetail> fdlist = null;
		try {
			fdlist = rex.readXls(this.getUpload());
		} catch (IOException e) {
			e.printStackTrace();
			this.addActionError("上传文件读取过程中出现错误！");
			return "input";
		}
		
		FundBase fundBase = new FundBase();
		fundBase.setFundNo(fundNo);
		fundBase = fundBaseBo.getById(fundBase);
		double fdAmt = fundBase.getFdAmt();
		boolean vali = true;
		int tx = 0;
		for(int i=0; i<fdlist.size(); i++){
			int j = i+1;
			try{
				if(tx > Integer.parseInt(fdlist.get(i).getTxDate())){
					vali = false;
					this.addActionError("第"+j+"条数据：交易日期未按照顺序排列！");
					return "input";
				}else{
					if(i==0){
						fdlist.get(0).setFundNo(fundNo);
						tx = Integer.parseInt(fdlist.get(0).getTxDate());
						if(tx<Integer.parseInt(fundDetailBo.getMaxDate(fdlist.get(0)))){
							vali = false;
							this.addActionError("第"+1+"条数据：交易日期早于数据库中最晚日期！");
							return "input";
						}
					}
				}
			}catch(NumberFormatException e){
				vali = false;
				this.addActionError("第"+j+"条数据：交易日期格式异常！");
				return "input";
			}
			String val = validate(fdlist.get(i));
			if(val != null&&!"".equals(val)){
				vali = false;
				this.addActionError("第"+j+"条数据："+val);
				return "input";
			}
			String _vl = fundDetailBo.validate(fdlist.get(i));
			if(_vl!=null&&!"".equals(_vl)){
				vali = false;
				_vl = _vl.replace("txAmt字段", "发生金额（元）");
				_vl = _vl.replace("txDate字段", "交易日期");
				_vl = _vl.replace("filler字段", "备注");
				this.addActionError("第"+j+"条数据："+_vl);
				return "input";
			}
			fdAmt += fdlist.get(i).getTxAmt();
			if(fdAmt<0){
				vali = false;
				this.addActionError("第"+j+"条数据：操作将超出当前资金规模！");
				return "input";
			}
		}
		if(vali){
			for(FundDetail fd : fdlist){
				fd.setFundNo(fundNo);
				fd.setOpNo(User.getLoginid(this.getHttpRequest()));
				try{
					fundDetailBo.insert(fd);
				}catch(Exception e){
					e.printStackTrace();
					this.addActionError("导入数据库失败，请稍后再试或与管理员联系！");
					return "input";
				}
			}
			this.addActionError("数据导入成功！");
		}
		return findByPage();	
	}
	
	/**
	 * 下载
	 * @return
	 */
	public String download() {		
		return "download";
	}
	public String getDownfName() throws UnsupportedEncodingException {
		return new String("资金明细导入模版.xls".getBytes(), "ISO8859-1");
	}
	public InputStream getTargetFile() {
		Map<String,String> sysPathMap =(Map<String,String>)MBaseCache.getCache().
				getBeanCache(CachecodeUtil.SYS_PATH_STR, CachecodeUtil.SYS_PATH);
		downloadFile = sysPathMap.get("001");
		return this.getServletContest().getResourceAsStream(downloadFile);
	}
	
	
	public String validate(FundDetail fd){
		String val = null;
		if(!"01".equals(fd.getTradeType())&&!"02".equals(fd.getTradeType())){
			val = "错误的交易类型";
		}else if("01".equals(fd.getTradeType())&&fd.getTxAmt()<0){
			val = "追加时，发生金额应大于0";
		}else if("02".equals(fd.getTradeType())){
			if(fd.getTxAmt()==null||fd.getTxAmt()==0||"".equals(fd.getTxAmt())){
				val = "兑付时，发生金额不能为空或0！";
			}else if(fd.getTxAmt()>0){
				val = "兑付时，发生金额应小于0";
			}
		}
		return val;
	}
	
	public FundDetail getFundDetail() {
		return fundDetail;
	}
	
	public void setFundDetail(FundDetail  fundDetail) {
		this.fundDetail = fundDetail;
	}
	public List<FundDetail> getFundDetailList() {
		return fundDetailList;
	}
	public void setFundDetailList(List<FundDetail> fundDetailList) {
		this.fundDetailList = fundDetailList;
	}
	public FundDetailBo getFundDetailBo() {
		return fundDetailBo;
	}
	public void setFundDetailBo(FundDetailBo fundDetailBo) {
		this.fundDetailBo = fundDetailBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfddetail0002() {
		return formfddetail0002;
	}
	public void setFormfddetail0002(FormData formfddetail0002) {
		this.formfddetail0002 = formfddetail0002;
	}
	public FormData getFormfddetail0001() {
		return formfddetail0001;
	}
	public void setFormfddetail0001(FormData formfddetail0001) {
		this.formfddetail0001 = formfddetail0001;
	}
	public void setDetailId(String detailId){
		this.detailId = detailId;
	}		
	public String getDetailId(){
		return detailId;
	}
	public String getFundNo() {
		return fundNo;
	}
	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}
	public String getFdType() {
		return fdType;
	}
	public void setFdType(String fdType) {
		this.fdType = fdType;
	}
	public TransferHandler getTransferHandler() {
		return transferHandler;
	}
	public void setTransferHandler(TransferHandler transferHandler) {
		this.transferHandler = transferHandler;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public FundBaseBo getFundBaseBo() {
		return fundBaseBo;
	}
	public void setFundBaseBo(FundBaseBo fundBaseBo) {
		this.fundBaseBo = fundBaseBo;
	}
	
}