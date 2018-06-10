package app.creditapp.entity;

import app.base.BaseDomain;
      
public class WorkCalendar extends BaseDomain implements java.io.Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public WorkCalendar(){}
//变量定义
private String make_date;            //记事日期
private String make_man;             //记事人
private String title;                //标题
private String event_type;           //事件类型（'1':'工作计划','2':'日程安排','3':'工作记事','4':'备忘录','5':'个人日程'）
private String event_date;           //事件日期
private String event_time;           //事件时间
private String event_desc;           //事件说明
private String warn_setup;           //提醒设置（'1':'不提醒','2':'当天提醒'）
private String warn_date;            //提醒日期
private String warn_time;            //提醒时间
private String warn_stop;            //提醒终止
private String end_sts;              //完成状态（1：完成，2：未完成）
private String filler;               //备注
private String calendar_no;          //日程编号
private String day;					 //当前日期 
private String event_lev;            //事件等级 1:紧急 2:重要 3:一般
public String getMake_date() {
	return make_date;
}
public void setMake_date(String make_date) {
	this.make_date = make_date;
}
public String getMake_man() {
	return make_man;
}
public void setMake_man(String make_man) {
	this.make_man = make_man;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getEvent_type() {
	return event_type;
}
public void setEvent_type(String event_type) {
	this.event_type = event_type;
}
public String getEvent_date() {
	return event_date;
}
public void setEvent_date(String event_date) {
	this.event_date = event_date;
}
public String getEvent_time() {
	return event_time;
}
public void setEvent_time(String event_time) {
	this.event_time = event_time;
}
public String getEvent_desc() {
	return event_desc;
}
public void setEvent_desc(String event_desc) {
	this.event_desc = event_desc;
}
public String getWarn_setup() {
	return warn_setup;
}
public void setWarn_setup(String warn_setup) {
	this.warn_setup = warn_setup;
}
public String getWarn_date() {
	return warn_date;
}
public void setWarn_date(String warn_date) {
	this.warn_date = warn_date;
}
public String getWarn_time() {
	return warn_time;
}
public void setWarn_time(String warn_time) {
	this.warn_time = warn_time;
}
public String getWarn_stop() {
	return warn_stop;
}
public void setWarn_stop(String warn_stop) {
	this.warn_stop = warn_stop;
}
public String getEnd_sts() {
	return end_sts;
}
public void setEnd_sts(String end_sts) {
	this.end_sts = end_sts;
}
public String getFiller() {
	return filler;
}
public void setFiller(String filler) {
	this.filler = filler;
}
public String getCalendar_no() {
	return calendar_no;
}
public void setCalendar_no(String calendar_no) {
	this.calendar_no = calendar_no;
}
public static long getSerialVersionUID() {
	return serialVersionUID;
}
public String getDay() {
	return day;
}
public void setDay(String day) {
	this.day = day;
}
public String getEvent_lev() {
	return event_lev;
}
public void setEvent_lev(String event_lev) {
	this.event_lev = event_lev;
}

}
