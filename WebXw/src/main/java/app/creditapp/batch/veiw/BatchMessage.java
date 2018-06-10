package app.creditapp.batch.veiw;

/**
 *  批处理作业消息对象 单例类
 */
public class BatchMessage {
	private static StringBuilder msg = null; // 静态共享内存区域
	private static boolean running = false;

	public static StringBuilder getInstance() {
		if (msg == null) {
			msg = new StringBuilder();
		}
		return msg;
	}

	public static boolean isRunning() {
		return running;
	}

	public static void setRunning(boolean running) {
		BatchMessage.running = running;
	}
}
