package org.bigbug.flutter_yeahka;

import android.content.IntentFilter;
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.EventChannel.StreamHandler;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterYeahkaPlugin
 */
public class FlutterYeahkaPlugin implements FlutterPlugin, MethodCallHandler, StreamHandler {

    private static FlutterYeahkaModule flutterYeahkaModule = null;

    private String SIGN = "sign"; // 签到
    private String UNIPAY = "unipay"; // 银联支付
    private String DOWNLOAD_TMK = "downloadTMK"; // 下载TMK
    private String action = "org.bigbug.flutter_yeahka.com.yeahka.L3.RESULT";


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_yeahka");
        channel.setMethodCallHandler(new FlutterYeahkaPlugin());
        flutterYeahkaModule = new FlutterYeahkaModule();
        flutterYeahkaModule.initBase(flutterPluginBinding.getApplicationContext());
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
        System.out.println(call.method);
        if (call.method.equals(SIGN)) {
            boolean sign = flutterYeahkaModule.sign();
            if (!sign) {
                result.error("提示", "签到失败", null);
            }
        } else if (call.method.equals(DOWNLOAD_TMK)) {
            if (call.hasArgument("authorizationCode")) {
                boolean downloadTMK = flutterYeahkaModule.downloadTMK((String) call.argument("authorizationCode"));
                if (!downloadTMK) {
                    result.error("提示", "授权码错误", null);
                }
            } else {
                result.error("提示", "authorizationCode 不能为空", null);
            }
        } else if (call.method.equals(UNIPAY)) {
            String customerOrderId = call.argument("customerOrderId");
            String amount = call.argument("amount");
            if (amount != null && !amount.equals("")) {
                flutterYeahkaModule.unipay(customerOrderId, Integer.parseInt(amount));
            } else {
                result.error("提示", "amount 不能为空", null);
            }
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        System.out.println("Listener /........");
    }

    @Override
    public void onCancel(Object arguments) {

    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
