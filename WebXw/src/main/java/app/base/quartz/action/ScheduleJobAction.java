package  app.base.quartz.action;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.quartz.SchedulerException;

import app.base.quartz.bo.ScheduleJobBo;
import app.base.quartz.entity.ConExpTransType;
import app.base.quartz.entity.ScheduleJob;
import app.base.quartz.taskUtil.QuartzTaskWork;
import app.base.quartz.taskUtil.TimeToCronUtil;
import app.creditapp.sys.entity.SysOrg;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: ScheduleJobAction.java
 * Description:
 **/
public class ScheduleJobAction extends BaseFormBean {

	//页面传值
	private ScheduleJob scheduleJob;
	private List<ScheduleJob> scheduleJobList;

	//注入ScheduleJobBo
	private ScheduleJobBo scheduleJobBo;
	private String query;
	private Long jobId;		
	private String jobName;
	private String jobGroup;
	private String jobStatus;
	private FormData formsdj0001;
	private FormData formsdj0002;
	private FormData formsdj0003;
	private FormData formsdj0004;
	private FormService formService = new FormService();
	private String intervalStartTime;//间隔模式开始时间
	private String timingStartTimes;//定时模式开始时间
	private String[] intervalTime;//间隔时间
	private String allTimes;
	private String min;//分
	private String hour;//时
	private String day;//天
	private String[] week;//年
	private String month;//月
	private String[] year;//年
	private Integer repeatCount;//重复次数
	private String mode;//模式
	private String hourTime;//每隔几小时的具体时间
	private QuartzTaskWork taskScheduleUtil;
	
	public ScheduleJobAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsdj0001 = formService.getFormData("sdj0001");
		scheduleJob = new ScheduleJob();
		getFormValue(formsdj0001);
		setObjValue(formsdj0001, scheduleJob);
		Ipage ipage = this.getIpage();
		scheduleJobList = (List) scheduleJobBo.findByPage(ipage, scheduleJob).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsdj0002 = formService.getFormData("sdj0002");
		formsdj0003 = formService.getFormData("sdj0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsdj0003 = formService.getFormData("sdj0002");
		getFormValue(formsdj0003);
		scheduleJob = new ScheduleJob();
		setObjValue(formsdj0003, scheduleJob);
		scheduleJob.setCreateTime(User.getSys_date(this.getHttpRequest()));
		scheduleJob.setUpdateTime(User.getSys_date(this.getHttpRequest()));
		scheduleJob = timing();
		scheduleJobBo.insert(scheduleJob);
		getObjValue(formsdj0003, scheduleJob);
		formsdj0001 = formService.getFormData("sdj0001");
		scheduleJob = new ScheduleJob();
		Ipage ipage = this.getIpage();
		scheduleJobList = (List) scheduleJobBo.findByPage(ipage, scheduleJob).getResult();
		return "list";
	}
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsdj0003 = formService.getFormData("sdj0003");
		getFormValue(formsdj0003);
		scheduleJob = new ScheduleJob();
		setObjValue(formsdj0003, scheduleJob);
		scheduleJob.setJobId(jobId);
		scheduleJob.setUpdateTime(User.getSys_date(ServletActionContext.getRequest()));
		scheduleJob = timing();
		scheduleJobBo.update(scheduleJob);
		getObjValue(formsdj0003, scheduleJob);
		formsdj0001 = formService.getFormData("sdj0001");
		scheduleJob = new ScheduleJob();
		Ipage ipage = this.getIpage();
		scheduleJobList = (List) scheduleJobBo.findByPage(ipage, scheduleJob).getResult();
		return "list";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsdj0001 = formService.getFormData("sdj0001");
		scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(jobId);
		scheduleJobBo.del(scheduleJob);
		this.addActionMessage("删除成功");
		scheduleJob = new ScheduleJob();
		Ipage ipage = this.getIpage();
		scheduleJobList = (List) scheduleJobBo.findByPage(ipage, scheduleJob).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsdj0003 = formService.getFormData("sdj0003");
		scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(jobId);
		scheduleJob = scheduleJobBo.getById(scheduleJob);
		getObjValue(formsdj0003, scheduleJob);
		return "detail";
	}
//	public String getdetail() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		formsdj0004 = formService.getFormData("sdj0004");
//		scheduleJob = new ScheduleJob();
//		scheduleJob.setJobId(jobId);
//		scheduleJob = scheduleJobBo.getById(scheduleJob);
//		getObjValue(formsdj0004, scheduleJob);
//		return "detail";
//	}
	public ScheduleJob timing(){
		if("1".equals(scheduleJob.getTriggerType())){//间隔模式
			scheduleJob.setAllTime(Arrays.toString(intervalTime).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", ""));//间隔时间
			scheduleJob.setStartTime(intervalStartTime);
			scheduleJob.setRepeatCount(repeatCount);
			int intervalArgs[] = new int[intervalTime.length];
			//年月周日时分秒====>日时分秒周月年
			for(int i=3;i<intervalTime.length;i++){
				intervalArgs[i-3] = Integer.parseInt(intervalTime[i]);
			}
			intervalArgs[4] = Integer.parseInt(intervalTime[2]);
			intervalArgs[5] = Integer.parseInt(intervalTime[1]);
			intervalArgs[6] = Integer.parseInt(intervalTime[0]);
			scheduleJob.setRepeatInterval(TimeToCronUtil.intervalToCron(intervalArgs));
		}else{//定时模式
			String[][] dateArrays = new String[7][];
			String timeConExpression = "";
			if("0".equals(mode)){//按每分钟执行
				dateArrays[2] = new String[]{min};
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_TIMES);
			}else if("1".equals(mode)){//按小时执行
				dateArrays[1] = new String[]{hour};//每隔N个小时
				dateArrays[2] = new String[]{hourTime};//每隔N个小时
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_HOUR);
//				timeConExpression = TimeToCronUtil
			}else if("2".equals(mode)){//按天数执行
				dateArrays[0] = new String[]{day};//每隔N天
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_DAY);
			}else if("3".equals(mode)){//按周执行
				dateArrays[4] = week;//每周的哪几天
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_WEEK);
			}else if("4".equals(mode)){//按月执行
				dateArrays[0] = new String[]{month};//每月的第N天
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_MONTH);
			}else if("5".equals(mode)){//按年执行
				dateArrays[0] = new String[]{year[1]};//每月的第N天
				dateArrays[5] = new String[]{year[0]};//每年的第N个月
				timeConExpression = TimeToCronUtil.transConExpression(dateArrays, ConExpTransType.BY_YEAR);
			}else{
				//这里
			}
			dateArrays[3] = null;//秒
			scheduleJob.setMode(mode);
			scheduleJob.setCronExpression(timeConExpression);
			scheduleJob.setRepeatCount(0);
			scheduleJob.setRepeatInterval(0);
			StringBuilder timingTiming = new StringBuilder();
			//年（月，日）+月+日+时+分+每小时几分，用于返显
			scheduleJob.setTimingTime(timingTiming.append(Arrays.toString(year).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "")).append(",").append(month).append(",").append(day).append(",").append(hour).append(",").append(min).append(",").append(hourTime).toString());
			scheduleJob.setWeek(Arrays.toString(week).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", ""));
			scheduleJob.setStartTime(timingStartTimes);
		}
		scheduleJob.setOpNo(User.getLoginid(this.getHttpRequest()));
		return scheduleJob;
	}
	/**
	 * 启动
	 * @return
	 */
	public String chaStart(){
		try {
			scheduleJob = new ScheduleJob();
			scheduleJob.setJobId(jobId);
			scheduleJob = scheduleJobBo.getById(scheduleJob);
			scheduleJob.setJobStatus("1");
			taskScheduleUtil.addOrUpdateJob(scheduleJob);
			scheduleJobBo.chaStart(scheduleJob);
			formsdj0001 = formService.getFormData("sdj0001");
			scheduleJob = new ScheduleJob();
			Ipage ipage = this.getIpage();
			scheduleJobList = (List) scheduleJobBo.findByPage(ipage, scheduleJob).getResult();
			return "list";
		} catch (SchedulerException e1) {
			this.addActionMessage("启动失败");
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	/**
	 * 停止
	 * @return
	 */
	public String chaStop(){
		
		try {
			scheduleJob = new ScheduleJob();
			scheduleJob.setJobId(jobId);
			scheduleJob = scheduleJobBo.getById(scheduleJob);
			scheduleJob.setJobStatus("0");
			taskScheduleUtil.addOrUpdateJob(scheduleJob);
			scheduleJobBo.chaStop(scheduleJob);
			taskScheduleUtil.deleteTaskJob(scheduleJob);
			formsdj0001 = formService.getFormData("sdj0001");
			scheduleJob = new ScheduleJob();
			Ipage ipage = this.getIpage();
			scheduleJobList = (List) scheduleJobBo.findByPage(ipage, scheduleJob).getResult();
			return "list";
		} catch (SchedulerException e1) {
			this.addActionMessage("停止失败");
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsdj0002 = formService.getFormData("sdj0002");
		 getFormValue(formsdj0002);
		 validateFormData(formsdj0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsdj0003 = formService.getFormData("sdj0003");
		 getFormValue(formsdj0003);
		 validateFormData(formsdj0003);
  	}
	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}
	public void setScheduleJob(ScheduleJob  scheduleJob) {
		this.scheduleJob = scheduleJob;
	}
	public List<ScheduleJob> getScheduleJobList() {
		return scheduleJobList;
	}
	public void setScheduleJobList(List<ScheduleJob> scheduleJobList) {
		this.scheduleJobList = scheduleJobList;
	}
	public ScheduleJobBo getScheduleJobBo() {
		return scheduleJobBo;
	}
	public void setScheduleJobBo(ScheduleJobBo scheduleJobBo) {
		this.scheduleJobBo = scheduleJobBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsdj0002() {
		return formsdj0002;
	}
	public void setFormsdj0002(FormData formsdj0002) {
		this.formsdj0002 = formsdj0002;
	}
	public FormData getFormsdj0001() {
		return formsdj0001;
	}
	public void setFormsdj0001(FormData formsdj0001) {
		this.formsdj0001 = formsdj0001;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public FormData getFormsdj0003() {
		return formsdj0003;
	}
	public void setFormsdj0003(FormData formsdj0003) {
		this.formsdj0003 = formsdj0003;
	}
	public FormData getFormsdj0004() {
		return formsdj0004;
	}
	public void setFormsdj0004(FormData formsdj0004) {
		this.formsdj0004 = formsdj0004;
	}
	public QuartzTaskWork getTaskScheduleUtil() {
		return taskScheduleUtil;
	}
	public void setTaskScheduleUtil(QuartzTaskWork taskScheduleUtil) {
		this.taskScheduleUtil = taskScheduleUtil;
	}
	public String getAllTimes() {
		return allTimes;
	}
	public void setAllTimes(String allTimes) {
		this.allTimes = allTimes;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String[] getYear() {
		return year;
	}
	public void setYear(String[] year) {
		this.year = year;
	}
	public Integer getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}
	public String[] getWeek() {
		return week;
	}
	public void setWeek(String[] week) {
		this.week = week;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String[] getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(String[] intervalTime) {
		this.intervalTime = intervalTime;
	}
	public String getIntervalStartTime() {
		return intervalStartTime;
	}
	public void setIntervalStartTime(String intervalStartTime) {
		this.intervalStartTime = intervalStartTime;
	}
	public String getTimingStartTimes() {
		return timingStartTimes;
	}
	public void setTimingStartTimes(String timingStartTimes) {
		this.timingStartTimes = timingStartTimes;
	}
	public String getHourTime() {
		return hourTime;
	}
	public void setHourTime(String hourTime) {
		this.hourTime = hourTime;
	}
}