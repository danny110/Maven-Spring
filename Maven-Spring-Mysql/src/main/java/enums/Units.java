package enums;

/**
 * @ClassName: Units
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午4:24:49
 *
 */
public enum Units {
	KG {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "Kg";
		}
	},
	TAO {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "套";
		}
	},
	ZHI {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "只";
		}
	},
	GE {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "个";
		}
	},
	JIAN {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "件";
		}
	},
	UNKNOWN {
		/* (non-Javadoc)
		 * <p>Title: toString</p> 
		 * <p>Description: </p> 
		 * @return 
		 * @see java.lang.Enum#toString() 
		 */
		@Override
		public String toString() {
			return "缺省";
		}
	}
}
