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
	},
	UNLOGIN {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "用户登陆失效，请刷新浏览器！";
		}
	},
	EXISTLOGINCODE {
		/* (non-Javadoc)
		 * <p>Title: toString</p>
		 * <p>Description: </p>
		 * @return
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return "该账户已经存在！";
		}
	},
	NOPARAMS {
		/* (non-Javadoc)
		 * <p>Title: toString</p>
		 * <p>Description: </p>
		 * @return
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return "缺少参数！";
		}
	},
	EXISTRAWMATERIAL {
		/* (non-Javadoc)
		 * <p>Title: toString</p>
		 * <p>Description: </p>
		 * @return
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return "该原料已经存在！";
		}
	},
	EXISTCONPANYNAME {
		/* (non-Javadoc)
		 * <p>Title: toString</p>
		 * <p>Description: </p>
		 * @return
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "该单位名称已经存在！";
		}
	};
	
}
