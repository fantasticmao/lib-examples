package cn.fantasticmao.demo.java.apache.rocketmq.orderedmsg;

/**
 * OrderStep
 * <p>
 * 订单顺序：创建 -> 支付 -> 完成
 *
 * @author maomao
 * @since 2020-11-11
 */
public class OrderStep {
    public static final String DESC_CREATE = "created";
    public static final String DESC_PAID = "paid";
    public static final String DESC_FINISH = "finished";

    final Long orderId;
    final String stepDesc;

    public OrderStep(Long orderId, String stepDesc) {
        this.orderId = orderId;
        this.stepDesc = stepDesc;
    }
}
