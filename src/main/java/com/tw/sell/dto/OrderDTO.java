package com.tw.sell.dto;

import com.tw.sell.enums.OrderStatusEnum;
import com.tw.sell.enums.PayStatusEnum;
import com.tw.sell.model.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    /** 订单id */
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单总金额*/
    private BigDecimal orderAmount;

    /** 订单状态,默认0新下单 */
    private Integer orderStatus;

    /** 支付状态,默认0未支付 */
    private Integer payStatus;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /**  */
    private List<OrderDetail> detailList;

}
