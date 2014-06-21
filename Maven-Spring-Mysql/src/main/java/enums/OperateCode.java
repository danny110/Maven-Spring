package enums;

/**
 * @ClassName: OperateCode
 * @Description: TODO 返回值枚举
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 上午10:27:40
 *
 */
public enum OperateCode {
	SUCCESS {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "操作成功！";
		}
	},
	ERROR {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "操作失败！";
		}
	};
	
}
