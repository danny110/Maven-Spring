package cn.live.enums;

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
	},
	USERSUCCESS {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "用户登陆成功！";
		}
	},
	USERERROR {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "用户登陆失败！";
		}
	},
	USEREXCEPTION {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "用户信息存在异常，请联系管理员！";
		}
	};
	
}
