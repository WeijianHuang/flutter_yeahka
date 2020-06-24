package org.bigbug.flutter_yeahka;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.EventChannel.StreamHandler;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import static org.bigbug.flutter_yeahka.constant.ActionType.CHECK_INSTALL;
import static org.bigbug.flutter_yeahka.constant.ActionType.DOWNLOAD_TMK;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_B_SCAN_C;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_C_SCAN_B_WX;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_C_SCAN_B_YL;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_C_SCAN_B_ZFB;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_REFUND;
import static org.bigbug.flutter_yeahka.constant.ActionType.REPRINT_TICKET;
import static org.bigbug.flutter_yeahka.constant.ActionType.SIGN;
import static org.bigbug.flutter_yeahka.constant.ActionType.SWIPE_CARD_REFUND;
import static org.bigbug.flutter_yeahka.constant.ActionType.SWIPE_CARD_REVOKE;
import static org.bigbug.flutter_yeahka.constant.ActionType.SWIPE_CARD_TRANS;
import static org.bigbug.flutter_yeahka.constant.ActionType.TRANS_QUERY_DETAIL;
import static org.bigbug.flutter_yeahka.constant.ActionType.TRANS_QUERY_LIST;

/**
 * FlutterYeahkaPlugin
 */
public class FlutterYeahkaPlugin implements FlutterPlugin, MethodCallHandler, StreamHandler {

    private String NOT_FOUND_ERROR_CODE = "404";
    private String BAD_PARAMS_ERROR_CODE = "400";
    private String CUSTOM_ORDER_ID = "customOrderId";
    private String ORI_ORDER_ID = "oriOrderId";
    private String AMOUNT = "amount";
    private String REFERENCE_NO = "referenceNo";
    private String ORDER_ID = "orderId";
    private String MERCHANT_ID = "merchantId";
    private String AUTHORIZATION_CODE = "authorizationCode";

    private static FlutterYeahkaModule flutterYeahkaModule = null;
    static EventChannel.EventSink eventSink;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_yeahka");
        FlutterYeahkaPlugin flutterYeahkaPlugin = new FlutterYeahkaPlugin();

        channel.setMethodCallHandler(flutterYeahkaPlugin);
        flutterYeahkaModule = new FlutterYeahkaModule();
        flutterYeahkaModule.initBase(flutterPluginBinding.getApplicationContext());
        final EventChannel eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_yeahka_event");
        eventChannel.setStreamHandler(flutterYeahkaPlugin);
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_yeahka");
        channel.setMethodCallHandler(new FlutterYeahkaPlugin());
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method != CHECK_INSTALL && !flutterYeahkaModule.checkInstall(true)) {
            result.error(NOT_FOUND_ERROR_CODE, "收银通未安装", null);
            return;
        }

        String customOrderId = call.argument(CUSTOM_ORDER_ID);
        String oriOrderId = call.argument(ORI_ORDER_ID);
        switch (call.method) {
            case SIGN:
                flutterYeahkaModule.sign();
                break;
            case DOWNLOAD_TMK:
                String authorizationCode = getAuthorizationCode(call, result);
                if (authorizationCode != null) {
                    result.success(flutterYeahkaModule.downloadTMK(authorizationCode));
                }
                break;
            case CHECK_INSTALL:
                result.success(flutterYeahkaModule.checkInstall(false));
                break;
            case SWIPE_CARD_TRANS: {
                Integer amount = getAmount(call, result);
                if (amount != null) {
                    result.success(flutterYeahkaModule.swipeCardTrans(amount, customOrderId));
                }
                break;
            }
            case QRPAY_B_SCAN_C: {
                Integer amount = getAmount(call, result);
                if (amount != null) {
                    result.success(flutterYeahkaModule.qrPayBScanC(amount, customOrderId));
                }
                break;
            }
            case QRPAY_C_SCAN_B_WX: {
                Integer amount = getAmount(call, result);
                if (amount != null) {
                    result.success(flutterYeahkaModule.qrPayCScanBWx(amount, customOrderId));
                }
                break;
            }
            case QRPAY_C_SCAN_B_YL: {
                Integer amount = getAmount(call, result);
                if (amount != null) {
                    result.success(flutterYeahkaModule.qrPayCScanBYl(amount, customOrderId));
                }
                break;
            }
            case QRPAY_C_SCAN_B_ZFB: {
                Integer amount = getAmount(call, result);
                if (amount != null) {
                    result.success(flutterYeahkaModule.qrPayCScanBZfb(amount, customOrderId));
                }
                break;
            }
            case QRPAY_REFUND: {
                String referenceNo = getReferenceNo(call, result);
                Integer amount = getAmount(call, result);
                if (amount != null && referenceNo != null) {
                    result.success(flutterYeahkaModule.qrPayRefund(amount, oriOrderId, referenceNo));
                }
                break;
            }
            case TRANS_QUERY_DETAIL: {
                String referenceNo = getReferenceNo(call, result);
                if (referenceNo != null) {
                    result.success(flutterYeahkaModule.transQueryDetail(oriOrderId, referenceNo));
                }
                break;
            }
            case SWIPE_CARD_REFUND: {
                String referenceNo = getReferenceNo(call, result);
                Integer amount = getAmount(call, result);
                if (amount != null && referenceNo != null) {
                    result.success(flutterYeahkaModule.swipeCardRefund(amount, oriOrderId, referenceNo));
                }
                break;
            }
            case SWIPE_CARD_REVOKE: {
                String referenceNo = getReferenceNo(call, result);
                if (referenceNo != null) {
                    result.success(flutterYeahkaModule.swipeCardRevoke(oriOrderId, referenceNo));
                }
                break;
            }
            case TRANS_QUERY_LIST:
                String merchantId = getMerchantId(call, result);
                if (merchantId != null) {
                    result.success(flutterYeahkaModule.transQueryList(merchantId));
                }
                break;
            case REPRINT_TICKET:
                String referenceNo = call.argument(REFERENCE_NO);
                String orderId = getOrderId(call, result);
                result.success(flutterYeahkaModule.reprintTicket(orderId, referenceNo));
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        System.out.println("Listener start");
        eventSink = events;
    }

    @Override
    public void onCancel(Object arguments) {
        eventSink = null;
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }

    private Integer getAmount(MethodCall call, Result result) {
        Integer amount = call.argument(AMOUNT);
        if (amount == null || amount <= 0) {
            result.error(BAD_PARAMS_ERROR_CODE, "amount不能为空或者不能小于等于零", null);
            return null;
        }
        return amount;
    }

    private String getReferenceNo(MethodCall call, Result result) {
        String referenceNo = call.argument(REFERENCE_NO);
        if (referenceNo == null || referenceNo.equals("")) {
            result.error(BAD_PARAMS_ERROR_CODE, "referenceNo参考号不能为空", null);
            return null;
        }
        return referenceNo;
    }

    private String getOrderId(MethodCall call, Result result) {
        String orderId = call.argument(ORDER_ID);
        if (orderId == null || orderId.equals("")) {
            result.error(BAD_PARAMS_ERROR_CODE, "orderId不能为空", null);
            return null;
        }
        return orderId;
    }

    private String getMerchantId(MethodCall call, Result result) {
        String merchantId = call.argument(MERCHANT_ID);
        if (merchantId == null || merchantId.equals("")) {
            result.error(BAD_PARAMS_ERROR_CODE, "merchantId不能为空", null);
            return null;
        }
        return merchantId;
    }

    private String getAuthorizationCode(MethodCall call, Result result) {
        String authorizationCode = call.argument(AUTHORIZATION_CODE);
        if (authorizationCode == null || authorizationCode.equals("")) {
            result.error(BAD_PARAMS_ERROR_CODE, "authorizationCode不能为空", null);
            return null;
        }
        return authorizationCode;
    }
}
