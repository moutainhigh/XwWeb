package app.base.quartz.job.test;

import java.util.Date;

public class DoMyWork {
	public void doWork(){
		System.out.println("快去干活快去干活快去干活别光玩！！！！"+new Date().toString());
	}
	
	public void doWork2(String name,int number){
		System.out.println("这是您的名字："+name+",这是您的数字："+number);
	}
}
