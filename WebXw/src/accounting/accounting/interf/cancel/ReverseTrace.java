package accounting.interf.cancel;

import accounting.domain.sys.AfferentDomain;

/**
 * 冲正交易数据传输对象
 *
 */
public class ReverseTrace extends AfferentDomain {

		private long reverseTraceNo;	//待冲正流水号

		/**
		 * @return 待冲正流水号 
		 */
		public long getReverseTraceNo() {
			return reverseTraceNo;
		}

		/**
		 * @设置     待冲正流水号
		 * @param 待冲正流水号 
		 */
		public void setReverseTraceNo(long reverseTraceNo) {
			this.reverseTraceNo = reverseTraceNo;
		}
		
		
		
}
