package enums;

/**
 * @ClassName: Gender
 * @Description: TODO 性别枚举
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午3:16:29
 *
 */
public enum Gender {
    MAN{
        /* (non-Javadoc)
         * <p>Title: toString</p> 
         * <p>Description: </p> 
         * @return 
         * @see java.lang.Enum#toString() 
         */
        @Override
        public String toString() {
            return "男";
        }
	},WOMEN{
        /* (non-Javadoc)
         * <p>Title: toString</p> 
         * <p>Description: </p> 
         * @return 
         * @see java.lang.Enum#toString() 
         */
        @Override
        public String toString() {
            return "女";
        }

	};
}