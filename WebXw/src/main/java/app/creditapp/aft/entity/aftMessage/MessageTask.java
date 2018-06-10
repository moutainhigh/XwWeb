package app.creditapp.aft.entity.aftMessage;

public class MessageTask {
	private String taskId;
	private String taskName;
	private int taskCount;
	private String content;
	private String url;
	private String taskType;// 01 待审批任务 02资金未处理任务

	public MessageTask(String taskName, int taskCount, String content, String url) {
		super();
		this.taskName = taskName;
		this.taskCount = taskCount;
		this.content = content;
		this.url = url;
	}

	public MessageTask(String taskId, String taskName, int taskCount, String content, String url, String taskType) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskCount = taskCount;
		this.content = content;
		this.url = url;
		this.taskType = taskType;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
