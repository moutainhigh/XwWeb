package  app.creditapp.sys.action;
import java.util.Arrays;
import java.util.List;

import app.util.User;

import org.apache.struts2.ServletActionContext;

import app.util.toolkit.Ipage;
import app.base.quartz.entity.ConExpTransType;
import app.base.quartz.entity.ScheduleJob;
import app.base.quartz.taskUtil.QuartzTaskWork;
import app.base.quartz.taskUtil.TimeToCronUtil;
import app.creditapp.sys.bo.ScheduleTaskBo;
import app.creditapp.sys.bo.TimeControllerBo;
import app.creditapp.sys.entity.ScheduleTask;
import app.creditapp.sys.entity.TimeController;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: TimeControllerAction.java
 * Description:
 **/
public class TimeControllerAction extends BaseFormBean {

	//页面传值
	private TimeController timeController;
	private ScheduleTask scheduleTask;
	private ScheduleJob scheduleJob;
	private List<TimeController> timeControllerList;

	//注入TimeControllerBo
	private ScheduleTaskBo scheduleTaskBo;
	private TimeControllerBo timeControllerBo;

	private String query;
	private String tcName;//策略名称
	private Integer tcId;	
	private Integer stId;
	private FormData formsys0113;
	private FormData formsys0114;
	private QuartzTaskWork taskScheduleUtil;
	private FormService formService = new FormService();
	
	private String tcState;//定时任务状态
	private String trigerType;//定时模式
	private String[] intervalTime;//间隔时间
	private Integer repeatCount;//重复次数
	private String intervalStartTime;//间隔模式开始时间
	private String intervalEndTime;//间隔模式结束时间
	private String timingStartTime;//定时模式开始时间
	private String timingEndTime;//定时模式结束时间
	private String timingMode;//定时模式（分 时 天 周 月 年）
	private String timMin;//分
	private String timHour;//时
	private String hourTime;//时
	private String dayHourTime;//按天定时小时
	private String dayMinTime;//按天定时分钟
	private String timDay;//天
	private String[] week;//周 
	private String timMonth;//月 
	private String[] timYear;//年
	
	public TimeControllerAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0113 = formService.getFormData("sys0113");
		timeController = new TimeController();
		getFormValue(formsys0113);
		setObjValue(formsys0113, timeController);
		timeController.setTcId(tcId);
		timeController.setStId(stId);
		Ipage ipage = this.getIpage();
		timeControllerList = (List) timeControllerBo.findByPage(ipage, timeController).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0114 = formService.getFormData("sys0114");
		return "input";
	}
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		timeController = new TimeController();
		timeController = timing();
		timeControllerBo.insert(timeController);
		this.addActionMessage("定时策略新增成功！");
		return "detail";
	}
	/**
	 * @param timeController
	 * @return
	 */
	public TimeController timing(){
		if("1".equals(trigerType)){//间隔模式
			timeController.setRepeatIntTime(Arrays.toString(intervalTime).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", ""));//间隔时间
			timeController.setStartTime(intervalStartTime);
			timeController.setRepeatCount(repeatCount);
			int intervalArgs[] = new int[intervalTime.length];
			//年月周日时分秒====>日时分秒周月年
			for(int i=3;i<intervalTime.length;i++){
				intervalArgs[i-3] = Integer.parseInt(intervalTime[i]);
			}
			intervalArgs[4] = Integer.parseInt(intervalTime[2]);
			intervalArgs[5] = Integer.parseInt(intervalTime[1]);
			intervalArgs[6] = Integer.parseInt(intervalTime[0]);
			timeController.setRepeatInterval(TimeToCronUtil.intervalToCron(intervalArgs));
		}else if("2".equals(trigerType)){//定时模式
			dayHourTime = timingStartTime.substring(11, 13);
			dayMinTime = timingStartTime.substring(14, 16);
			String[][] dateArrays = new String[7][];
//			dateArrays[1] = new String[]{dayHourTime};//每隔N天小时
//			dateArrays[2] = new String[]{dayMinTime};//每隔N天分钟
			String timeConExpression = "";
			if("0".equals(timingMode)){//按每分钟执行
				dateArrays[2] = new String[]{timMin};
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_TIMES);
			}else if("1".equals(timingMode)){//按小时执行
				dateArrays[1] = new String[]{timHour};//每隔N个小时
				dateArrays[2] = new String[]{hourTime};//每隔N个小时
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_HOUR);
			}else if("2".equals(timingMode)){//按天数执行
				dateArrays[0] = new String[]{timDay};//每隔N天
				dateArrays[1] = new String[]{dayHourTime};//每隔N天小时
				dateArrays[2] = new String[]{dayMinTime};//每隔N天分钟
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_DAY);
			}else if("3".equals(timingMode)){//按周执行
				dateArrays[1] = new String[]{dayHourTime};//每隔N天小时
				dateArrays[2] = new String[]{dayMinTime};//每隔N天分钟
				dateArrays[4] = week;//每周的哪几天
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_WEEK);
			}else if("4".equals(timingMode)){//按月执行
				dateArrays[1] = new String[]{dayHourTime};//每隔N天小时
				dateArrays[2] = new String[]{dayMinTime};//每隔N天分钟
				dateArrays[0] = new String[]{timMonth};//每月的第N天
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_MONTH);
			}else if("5".equals(timingMode)){//按年执行
				dateArrays[0] = new String[]{timYear[1]};//每月的第N天
				dateArrays[5] = new String[]{timYear[0]};//每年的第N个月
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_YEAR);
			}
//			dateArrays[3] = null;//秒
			timeController.setJobMode(timingMode);
			timeController.setCronExpression(timeConExpression);
			timeController.setRepeatCount(0);
			timeController.setRepeatInterval(null);
			StringBuilder timingTiming = new StringBuilder();
			//年（月，日）+月+日+时+分+每小时几分，用于返显
			timeController.setRepeatIntTime(timingTiming.append(arrayToStr(timYear)).append(",").append(timMonth).append(",").append(timDay).append(",").append(timHour).append(",").append(timMin).append(",").append(hourTime).toString());
			timeController.setWeek(arrayToStr(week));
			
			timeController.setStartTime(timingStartTime);
		}
		timeController.setTcState(tcState);//启动状态
		timeController.setStId(stId);
		timeController.setTcName(tcName);
		timeController.setTriggerType(trigerType);//定时模式
//		this.addActionMessage("新增定时策略成功！");
		return timeController;
	}
	/**
	 * 数组转换为字符串
	 * @param arr
	 * @return
	 */
	public String arrayToStr(String[] arr){
		return Arrays.toString(arr).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
	}
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		timeController = new TimeController();
		timeController = timing();
		timeController.setTcId(tcId);
		timeControllerBo.update(timeController);
		timeController = timeControllerBo.getById(timeController);
		this.addActionMessage("定时策略修改成功！");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0113 = formService.getFormData("sys0113");
		timeController = new TimeController();
		timeController.setTcId(tcId);
		timeControllerBo.del(timeController);
		this.addActionMessage("定时策略删除成功");
		timeController = new TimeController();
		Ipage ipage = this.getIpage();
		timeControllerList = (List) timeControllerBo.findByPage(ipage, timeController).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		timeController = new TimeController();
		timeController.setTcId(tcId);
		timeController = timeControllerBo.getById(timeController);
		return "detail";
	}
	/**
	 * 启动定时策略
	 * @return
	 * @throws Exception
	 */
	public String start()throws Exception {
		timeController = new TimeController();
		timeController.setTcId(tcId);
		timeController = timeControllerBo.getById(timeController);
		if(timeController.getStId()==null){//定时策略是否关联预设任务
			this.addActionMessage("请关联预设任务之后再启动定时策略");
		}else{
			scheduleTask = new ScheduleTask();
			scheduleTask.setSeId(timeController.getStId());
			scheduleTask = scheduleTaskBo.getById(scheduleTask);
			if("0".equals(scheduleTask.getJobStatus())){//预设任务是否启用
				this.addActionMessage("请先启用预设任务之后再启动定时策略");
			}else{
				scheduleJob = new ScheduleJob();
				scheduleJob.setJobName(String.valueOf(timeController.getTcId()));
				scheduleJob.setJobTaskName(scheduleTask.getJobName());
				scheduleJob.setJobGroup(scheduleTask.getJobGroup());
				scheduleJob.setBeanClass(scheduleTask.getBeanClass());
				scheduleJob.setSpringId(scheduleTask.getSpringId());
				scheduleJob.setArgumentsStr(scheduleTask.getArgumentsStr());
				scheduleJob.setMethodName(scheduleTask.getMethodName());
				scheduleJob.setTriggerType(timeController.getTriggerType());
				scheduleJob.setCronExpression(timeController.getCronExpression());
				scheduleJob.setRepeatCount(timeController.getRepeatCount());
				scheduleJob.setRepeatInterval(timeController.getRepeatInterval());
				scheduleJob.setJobStatus("1");
				scheduleJob.setStartTime(timeController.getStartTime());
				scheduleJob.setEndTime(timeController.getEndTime());
				scheduleJob.setDescription(scheduleTask.getDescription());
				taskScheduleUtil.addOrUpdateJob(scheduleJob);//新增或更新定时任务
				timeController.setTcState("1");//把策略状态设为启动
				timeControllerBo.startTimeSts(timeController);
			}
		}
//		findByPage();
		formsys0113 = formService.getFormData("sys0113");
		TimeController timeController1 = new TimeController();
		getFormValue(formsys0113);
		setObjValue(formsys0113, timeController1);
		Ipage ipage = this.getIpage();
		timeControllerList = (List) timeControllerBo.findByPage(ipage, timeController1).getResult();
		return "start";
	}
	/**
	 * 停止定时策略
	 * @return
	 * @throws Exception
	 */
	public String stop()throws Exception {
		timeController = new TimeController();
		timeController.setTcId(tcId);
		timeController = timeControllerBo.getById(timeController);
		scheduleTask = new ScheduleTask();
		scheduleTask.setSeId(timeController.getStId());
		scheduleTask = scheduleTaskBo.getById(scheduleTask);
		scheduleJob = new ScheduleJob();
		scheduleJob.setJobName(String.valueOf(timeController.getTcId()));//策略ID当做名称，防止名称重复
		scheduleJob.setJobGroup(scheduleTask.getJobGroup());//预设任务group
		taskScheduleUtil.deleteTaskJob(scheduleJob);//删除定时任务
		timeController.setTcState("0");//把策略状态设为停止
		timeControllerBo.startTimeSts(timeController);
		timeController = new TimeController();
		timeController.setTcId(tcId);
//		findByPage();
		formsys0113 = formService.getFormData("sys0113");
		TimeController timeController1 = new TimeController();
		getFormValue(formsys0113);
		setObjValue(formsys0113, timeController1);
		Ipage ipage = this.getIpage();
		timeControllerList = (List) timeControllerBo.findByPage(ipage, timeController1).getResult();
		return "stop";
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0114 = formService.getFormData("sys0114");
		 getFormValue(formsys0114);
		 System.out.println(timingStartTime.length());
		 if(!"".equals(timingStartTime) ||timingStartTime != null){
			 String[] arr1 = timingStartTime.split(" ");
			 String[] arr = arr1[1].split(":");
			 if(Integer.parseInt(arr[0]) >=24){
				 this.addActionError("开始时间：小时应小于24");
			 }
			 if(Integer.parseInt(arr[1]) >=60){
				 this.addActionError("开始时间：秒数应小于60");
			 }
		 }
		 if(timingStartTime.length()== 16 || intervalStartTime.length() == 16){
			 
		 }else {
			 this.addActionError("请输入正确的开始时间");
		 }
		 validateFormData(formsys0114);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0114 = formService.getFormData("sys0114");
		 getFormValue(formsys0114);
		 if(!"".equals(timingStartTime) ||timingStartTime != null){
			 String[] arr1 = timingStartTime.split(" ");
			 String[] arr = arr1[1].split(":");
			 if(Integer.parseInt(arr[0]) >=24){
				 this.addActionError("开始时间：小时应小于24");
			 }
			 if(Integer.parseInt(arr[1]) >=60){
				 this.addActionError("开始时间：秒数应小于60");
			 }
		 }
		 if(timingStartTime.length()== 16 || intervalStartTime.length() == 16){
			 
		 }else {
			 this.addActionError("请输入正确的开始时间");
		 }
		 validateFormData(formsys0114);
  	}
	public TimeController getTimeController() {
		return timeController;
	}
	public void setTimeController(TimeController  timeController) {
		this.timeController = timeController;
	}
	public List<TimeController> getTimeControllerList() {
		return timeControllerList;
	}
	public void setTimeControllerList(List<TimeController> timeControllerList) {
		this.timeControllerList = timeControllerList;
	}
	public TimeControllerBo getTimeControllerBo() {
		return timeControllerBo;
	}
	public void setTimeControllerBo(TimeControllerBo timeControllerBo) {
		this.timeControllerBo = timeControllerBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsys0114() {
		return formsys0114;
	}
	public void setFormsys0114(FormData formsys0114) {
		this.formsys0114 = formsys0114;
	}
	public FormData getFormsys0113() {
		return formsys0113;
	}
	public void setFormsys0113(FormData formsys0113) {
		this.formsys0113 = formsys0113;
	}
	public void setTcId(Integer tcId){
		this.tcId = tcId;
	}		
	public Integer getTcId(){
		return tcId;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public String getTcState() {
		return tcState;
	}
	public void setTcState(String tcState) {
		this.tcState = tcState;
	}
	public String getTrigerType() {
		return trigerType;
	}
	public void setTrigerType(String trigerType) {
		this.trigerType = trigerType;
	}
	public String[] getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(String[] intervalTime) {
		this.intervalTime = intervalTime;
	}
	public Integer getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}
	public String getIntervalStartTime() {
		return intervalStartTime;
	}
	public void setIntervalStartTime(String intervalStartTime) {
		this.intervalStartTime = intervalStartTime;
	}
	public String getIntervalEndTime() {
		return intervalEndTime;
	}
	public void setIntervalEndTime(String intervalEndTime) {
		this.intervalEndTime = intervalEndTime;
	}
	public String getTimingStartTime() {
		return timingStartTime;
	}
	public void setTimingStartTime(String timingStartTime) {
		this.timingStartTime = timingStartTime;
	}
	public String getTimingEndTime() {
		return timingEndTime;
	}
	public void setTimingEndTime(String timingEndTime) {
		this.timingEndTime = timingEndTime;
	}
	public String getTimingMode() {
		return timingMode;
	}
	public void setTimingMode(String timingMode) {
		this.timingMode = timingMode;
	}
	public String getTimMin() {
		return timMin;
	}
	public void setTimMin(String timMin) {
		this.timMin = timMin;
	}
	public String getTimHour() {
		return timHour;
	}
	public void setTimHour(String timHour) {
		this.timHour = timHour;
	}
	public String getHourTime() {
		return hourTime;
	}
	public void setHourTime(String hourTime) {
		this.hourTime = hourTime;
	}
	public String getTimDay() {
		return timDay;
	}
	public void setTimDay(String timDay) {
		this.timDay = timDay;
	}
	public String[] getWeek() {
		return week;
	}
	public void setWeek(String[] week) {
		this.week = week;
	}
	public String getTimMonth() {
		return timMonth;
	}
	public void setTimMonth(String timMonth) {
		this.timMonth = timMonth;
	}
	public String[] getTimYear() {
		return timYear;
	}
	public void setTimYear(String[] timYear) {
		this.timYear = timYear;
	}
	public ScheduleTask getScheduleTask() {
		return scheduleTask;
	}
	public void setScheduleTask(ScheduleTask scheduleTask) {
		this.scheduleTask = scheduleTask;
	}
	public ScheduleTaskBo getScheduleTaskBo() {
		return scheduleTaskBo;
	}
	public void setScheduleTaskBo(ScheduleTaskBo scheduleTaskBo) {
		this.scheduleTaskBo = scheduleTaskBo;
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
	public String getTcName() {
		return tcName;
	}
	public Integer getStId() {
		return stId;
	}
	public void setStId(Integer stId) {
		this.stId = stId;
	}
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	public String getDayHourTime() {
		return dayHourTime;
	}
	public void setDayHourTime(String dayHourTime) {
		this.dayHourTime = dayHourTime;
	}
	public String getDayMinTime() {
		return dayMinTime;
	}
	public void setDayMinTime(String dayMinTime) {
		this.dayMinTime = dayMinTime;
	}
}