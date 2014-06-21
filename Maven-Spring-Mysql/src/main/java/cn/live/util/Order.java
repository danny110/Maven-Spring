package cn.live.util;

import java.io.Serializable;

/**
 * @ClassName: Order
 * @Description: TODO 排序类
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午2:00:41
 *
 */
public class Order implements Serializable {
    public enum Direction {
        asc, desc;

        public static Direction fromString(String value) {
            return valueOf(value.toLowerCase());
        }
    }

    private static final long serialVersionUID = -3078342809727773232L;
    private static final Order.Direction defaultDirection = Order.Direction.desc;
    private String property;
    private Order.Direction direction = defaultDirection;

    public Order() {
    }

    public Order(String property, Order.Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    public static Order asc(String property) {
        return new Order(property, Order.Direction.asc);
    }

    public static Order desc(String property) {
        return new Order(property, Order.Direction.desc);
    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Order.Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Order.Direction direction) {
        this.direction = direction;
    }


}