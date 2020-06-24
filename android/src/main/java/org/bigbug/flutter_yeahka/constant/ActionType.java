package org.bigbug.flutter_yeahka.constant;

public class ActionType {
    public static final String SIGN = "sign"; // 签到
    public static final String CHECK_INSTALL = "checkInstall"; // 检查下载
    public static final String DOWNLOAD_TMK = "downloadTMK"; // 下载TMK
    public static final String SWIPE_CARD_TRANS = "swipeCardTrans"; // 刷卡支付
    public static final String QRPAY_B_SCAN_C = "qrPayBScanC"; // 二维码支付B扫C
    public static final String QRPAY_C_SCAN_B_WX = "qrPayCScanBWx"; //微信二维码支付C扫B
    public static final String QRPAY_C_SCAN_B_YL = "qrPayCScanBYl"; //银联二维码支付C扫B
    public static final String QRPAY_C_SCAN_B_ZFB = "qrPayCScanBZfb"; //支付宝二维码支付C扫B
    public static final String QRPAY_REFUND = "qrPayRefund"; // 二维码扫码退款
    public static final String TRANS_QUERY_DETAIL = "transQueryDetail"; // 订单详情获取
    public static final String SWIPE_CARD_REFUND = "swipeCardRefund"; // 刷卡退款
    public static final String SWIPE_CARD_REVOKE = "swipeCardRevoke"; // 刷卡撤销
    public static final String TRANS_QUERY_LIST = "transQueryList"; // 订单列表获取
    public static final String REPRINT_TICKET = "reprintTicket"; // 重打印
}
