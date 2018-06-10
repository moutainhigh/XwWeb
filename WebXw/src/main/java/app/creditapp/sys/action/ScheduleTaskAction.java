package  app.creditapp.sys.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.base.quartz.entity.ScheduleJob;
import app.base.quartz.taskUtil.QuartzTaskWork;
import app.creditapp.sys.bo.ScheduleTaskBo;
import app.creditapp.sys.bo.TimeControllerBo;
import app.creditapp.sys.bo.impl.ScheduleTaskBoImpl;
import app.creditapp.sys.entity.ScheduleTask;
import app.creditapp.sys.entity.TimeController;
import app.creditapp.sys.entity.TransContr;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: ScheduleTaskAction.java
 * Description:
 **/
public class ScheduleTaskAction extends BaseFormBean {

	//页面传值
	private ScheduleTask scheduleTask;
	private TimeController timeController;
	private ScheduleJob scheduleJob;
	private List<ScheduleTask> scheduleTaskList;
	private List<TimeController> timeControllerList;
	private QuartzTaskWork taskScheduleUtil;
	//注入ScheduleTaskBo
	private ScheduleTaskBo scheduleTaskBo;
	private TimeControllerBo timeControllerBo;
	private String query;
	private Integer seId;	
	private Integer tcId;//策略ID序列
	private FormData formsys0115;
	private FormData formsys0116;
	private FormService formService = new FormService();
	private Object[] arguments;
	
	public ScheduleTaskAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0115 = formService.getFormData("sys0115");
		scheduleTask = new ScheduleTask();
		getFormValue(formsys0115);
		setObjValue(formsys0115, scheduleTask);
		scheduleTask.setSeId(seId);
		Ipage ipage = this.getIpage();
		scheduleTaskList = (List) scheduleTaskBo.findByPage(ipage, scheduleTask).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0116 = formService.getFormData("sys0116");
		scheduleTask = new ScheduleTask();
		scheduleTask.setCreateTime(User.getSys_date(getHttpRequest()));
		scheduleTask.setUpdateTime(User.getSys_date(getHttpRequest()));
		getObjValue(formsys0116, scheduleTask);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0116 = formService.getFormData("sys0116");
		getFormValue(formsys0116);
		scheduleTask = new ScheduleTask();
		setObjValue(formsys0116, scheduleTask);
		try {
			scheduleTask.setArguments(arguments);
			scheduleTask.getArguments();
		} catch (Exception e) {
			this.addActionError("新增失败，参数格式错误");
			e.printStackTrace();
			return "detail";
		}
		scheduleTask.setOpNo(User.getTlrno(this.getHttpRequest()));
		scheduleTask.setCreateTime(User.getSys_date(getHttpRequest()));
		scheduleTask.setUpdateTime(User.getSys_date(getHttpRequest()));
		scheduleTask.setJobStatus("0");
		scheduleTaskBo.insert(scheduleTask);
		getObjValue(formsys0116, scheduleTask);
		this.addActionError("新增定时任务成功！");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0116 = formService.getFormData("sys0116");
		getFormValue(formsys0116);
		scheduleTask = new ScheduleTask();
		setObjValue(formsys0116, scheduleTask);
		try {
			scheduleTask.setArguments(arguments);
			scheduleTask.getArguments();
		} catch (Exception e) {
			this.addActionError("修改失败，参数格式错误");
			e.printStackTrace();
			return "detail";
		}
		scheduleTask.setUpdateTime(User.getSys_date(getHttpRequest()));
		scheduleTaskBo.update(scheduleTask);
		getObjValue(formsys0116, scheduleTask);
		return "detail";
	}
	/**
	 * 启用预设任务
	 */
	public String start()throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0115 = formService.getFormData("sys0115");
		Ipage ipage = this.getIpage();
		scheduleTask = new ScheduleTask();
		scheduleTask.setJobStatus("1");
		scheduleTask.setSeId(seId);
		scheduleTaskBo.updateTaskSts(scheduleTask);
		scheduleTaskList = (List) scheduleTaskBo.findByPage(ipage, new ScheduleTask()).getResult();
		return "start";
	}
	/**
	 * 停用预设任务
	 */
	public String stop()throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0115 = formService.getFormData("sys0115");
		Ipage ipage = this.getIpage();
		scheduleTask = new ScheduleTask();
		scheduleTask.setJobStatus("0");
		scheduleTask.setSeId(seId);
		scheduleTaskBo.updateTaskSts(scheduleTask);
		
		timeController = new TimeController();
		timeController.setStId(seId);
		timeController.setTcState("0");
		timeControllerBo.updateTimeSts(timeController);
		timeController = new TimeController();
		scheduleTask = new ScheduleTask();
		scheduleTask.setSeId(seId);
		scheduleTask = scheduleTaskBo.getById(scheduleTask);
		timeController.setStId(seId);
		timeControllerList = timeControllerBo.getByStId(timeController);
		scheduleJob = new ScheduleJob();
		scheduleJob.setJobGroup(scheduleTask.getJobGroup());
		String tcName = "";
		for(int i=0;i<timeControllerList.size();i++){//停用预设任务的所有定时策略
			tcName = timeControllerList.get(i).getTcName();
			scheduleJob.setJobName(tcName);
			taskScheduleUtil.deleteTaskJob(scheduleJob);
		}
		scheduleTaskList = (List) scheduleTaskBo.findByPage(ipage, new ScheduleTask()).getResult();
		return "stop";
		
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0115 = formService.getFormData("sys0115");
		scheduleTask = new ScheduleTask();
		scheduleTask.setSeId(seId);
		scheduleTask = scheduleTaskBo.getById(scheduleTask);
		if(scheduleTask.getTemplateName()==null){//判断是否为系统功能
			scheduleTaskBo.del(scheduleTask);
			timeController = new TimeController();
			timeController.setStId(seId);
			timeControllerList = timeControllerBo.getByStId(timeController);
			for(int i=0;i<timeControllerList.size();i++){//删除预设任务时更新关联的策略
				timeController = new TimeController();
				timeController = timeControllerList.get(i);
				timeController.setStId(null);
				timeControllerBo.update(timeController);
			}
			this.addActionMessage("删除成功");
		}else{
			this.addActionMessage("此部分为系统功能无法删除");
		}
		scheduleTask = new ScheduleTask();
		Ipage ipage = this.getIpage();
		scheduleTaskList = (List) scheduleTaskBo.findByPage(ipage, scheduleTask).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0116 = formService.getFormData("sys0116");
		scheduleTask = new ScheduleTask();
		scheduleTask.setSeId(seId);
		scheduleTask = scheduleTaskBo.getById(scheduleTask);
		getObjValue(formsys0116, scheduleTask);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0116 = formService.getFormData("sys0116");
		 getFormValue(formsys0116);
		 validateFormData(formsys0116);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0116 = formService.getFormData("sys0116");
		 getFormValue(formsys0116);
		 validateFormData(formsys0116);
  	}
	public ScheduleTask getScheduleTask() {
		return scheduleTask;
	}
	public void setScheduleTask(ScheduleTask  scheduleTask) {
		this.scheduleTask = scheduleTask;
	}
	public List<ScheduleTask> getScheduleTaskList() {
		return scheduleTaskList;
	}
	public void setScheduleTaskList(List<ScheduleTask> scheduleTaskList) {
		this.scheduleTaskList = scheduleTaskList;
	}
	public ScheduleTaskBo getScheduleTaskBo() {
		return scheduleTaskBo;
	}
	public void setScheduleTaskBo(ScheduleTaskBo scheduleTaskBo) {
		this.scheduleTaskBo = scheduleTaskBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsys0116() {
		return formsys0116;
	}
	public void setFormsys0116(FormData formsys0116) {
		this.formsys0116 = formsys0116;
	}
	public FormData getFormsys0115() {
		return formsys0115;
	}
	public void setFormsys0115(FormData formsys0115) {
		this.formsys0115 = formsys0115;
	}
	public void setSeId(Integer seId){
		this.seId = seId;
	}		
	public Integer getSeId(){
		return seId;
	}
	public Integer getTcId() {
		return tcId;
	}
	public void setTcId(Integer tcId) {
		this.tcId = tcId;
	}
	public TimeControllerBo getTimeControllerBo() {
		return timeControllerBo;
	}
	public void setTimeControllerBo(TimeControllerBo timeControllerBo) {
		this.timeControllerBo = timeControllerBo;
	}
	public TimeController getTimeController() {
		return timeController;
	}
	public void setTimeController(TimeController timeController) {
		this.timeController = timeController;
	}
	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}
	public void setScheduleJob(ScheduleJob scheduleJob) {
		this.scheduleJob = scheduleJob;
	}
	public QuartzTaskWork getTaskScheduleUtil() {
		return taskScheduleUtil;
	}
	public void setTaskScheduleUtil(QuartzTaskWork taskScheduleUtil) {
		this.taskScheduleUtil = taskScheduleUtil;
	}
	public List<TimeController> getTimeControllerList() {
		return timeControllerList;
	}
	public void setTimeControllerList(List<TimeController> timeControllerList) {
		this.timeControllerList = timeControllerList;
	}
	public Object[] getArguments() {
		return arguments;
	}
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
}