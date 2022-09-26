package com.example.test.enums;

import lombok.Getter;

/**
 * 异常的规范
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    USER_STATUS_ERROR(14, "用户状态不正确"),

    USER_UPDATE_FAIL(15, "用户更新失败"),

    ORDER_DETAIL_EMPTY(16, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

    CART_EMPTY(18, "购物车为空"),

    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),

    ADUSER_DELETE_SUCCESS(22, "安卓用户删除成功"),

    ORDER_FINISH_SUCCESS(23, "订单完结成功"),

    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),

    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(26, "登出成功"),
    SELLER_NOT_EXIST(27, "用户不存在"),

    USER_HASEXIST(28, "用户已存在"),
    USER_NOT_XIST(29, "用户不存在"),
    USER_ISNULL(29, "用户为空"),
    LIMIT_NOT_EXIST(30, "该用户在limit表查不到权限"),
    ADUSER_ADD_SUCCESS(31, "安卓用户添加成功"),
    REDLINE_NOT_EXIST(32, "在redline表中查不到未删除信息"),
    FILE_STATUS_ERROR(33, "文件删除状态不正确"),
   FILE_UPDATE_FAIL(34, "文件删除状态更新失败"),
    FILE_DELETE_SUCCESS(35, "红线文件删除成功"),
    ADUSER_BACKPWD_SUCCESS(36, "安卓用户重置密码成功"),
    CATTLE_NOT_EXIST(37, "牛只信息不存在"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
