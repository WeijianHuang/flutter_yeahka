package org.bigbug.flutter_yeahka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yeahka.shouyintong.sdk.ISytEventHandler;
import com.yeahka.shouyintong.sdk.action.PosActivate;
import com.yeahka.shouyintong.sdk.action.PosSign;
import com.yeahka.shouyintong.sdk.action.QrPayBScanC;
import com.yeahka.shouyintong.sdk.action.QrPayCScanBWx;
import com.yeahka.shouyintong.sdk.action.QrPayCScanBYl;
import com.yeahka.shouyintong.sdk.action.QrPayCScanBZfb;
import com.yeahka.shouyintong.sdk.action.QrPayRefund;
import com.yeahka.shouyintong.sdk.action.SwipeCardRefund;
import com.yeahka.shouyintong.sdk.action.SwipeCardRevoke;
import com.yeahka.shouyintong.sdk.action.SwipeCardTrans;
import com.yeahka.shouyintong.sdk.action.TransQueryDetail;
import com.yeahka.shouyintong.sdk.action.TransQueryList;
import com.yeahka.shouyintong.sdk.action.base.BaseResp;
import com.yeahka.shouyintong.sdk.api.SytApi;
import com.yeahka.shouyintong.sdk.api.SytFactory;
import com.yeahka.shouyintong.sdk.info.TradeInfo;

import java.util.List;

import io.flutter.plugin.common.EventChannel;

import static org.bigbug.flutter_yeahka.constant.ActionType.DOWNLOAD_TMK;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_B_SCAN_C;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_C_SCAN_B_WX;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_C_SCAN_B_YL;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_C_SCAN_B_ZFB;
import static org.bigbug.flutter_yeahka.constant.ActionType.QRPAY_REFUND;
import static org.bigbug.flutter_yeahka.constant.ActionType.SIGN;
import static org.bigbug.flutter_yeahka.constant.ActionType.SWIPE_CARD_REFUND;
import static org.bigbug.flutter_yeahka.constant.ActionType.SWIPE_CARD_REVOKE;
import static org.bigbug.flutter_yeahka.constant.ActionType.SWIPE_CARD_TRANS;
import static org.bigbug.flutter_yeahka.constant.ActionType.TRANS_QUERY_DETAIL;
import static org.bigbug.flutter_yeahka.constant.ActionType.TRANS_QUERY_LIST;

public class ResultReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        SytApi sytApi = SytFactory.createSytIml(context);
        sytApi.handleAction(intent, new ISytEventHandler() {
            @Override
            public void onResponse(BaseResp baseResp) {
                System.out.println(baseResp);
                System.out.println("121312");
                boolean isSuccess = baseResp.isSuccessful();
                String msg = "";
                String errorCode;
                EventChannel.EventSink eventSink = FlutterYeahkaPlugin.eventSink;
                if (eventSink == null) {
                    System.out.println("EventSink is null");
                    return;
                }

                if (!isSuccess) {
                    msg = baseResp.getMsg();
                    errorCode = baseResp.getCode() + "";
                    Toast.makeText(context, msg + "(" + errorCode + ")", Toast.LENGTH_LONG).show();
                    eventSink.error(errorCode, msg, null);
                    System.out.println("123123123");
                    return;
                }
                if (baseResp instanceof PosActivate.Resp) {
                    msg = "激活成功";
                    eventSink.success(JSON.toJSONString(new RespModel(DOWNLOAD_TMK)));
                    System.out.println("1231344444");
                } else if (baseResp instanceof SwipeCardTrans.Resp) {
                    msg = "刷卡交易成功";
                    TradeInfo tradeInfo = ((SwipeCardTrans.Resp) baseResp).tradeInfo;
                    eventSink.success(JSON.toJSONString(new RespModel(SWIPE_CARD_TRANS, tradeInfo)));
                } else if (baseResp instanceof QrPayBScanC.Resp) {
                    msg = "主扫交易成功";
                    TradeInfo tradeInfo = ((QrPayBScanC.Resp) baseResp).tradeInfo;
                    eventSink.success(JSON.toJSONString(new RespModel(QRPAY_B_SCAN_C, tradeInfo)));
                } else if (baseResp instanceof QrPayCScanBWx.Resp) {
                    msg = "微信支付交易成功";
                    TradeInfo tradeInfo = ((QrPayCScanBWx.Resp) baseResp).tradeInfo;
                    eventSink.success(JSON.toJSONString(new RespModel(QRPAY_C_SCAN_B_WX, tradeInfo)));
                } else if (baseResp instanceof QrPayCScanBYl.Resp) {
                    msg = "银联二维码支付交易成功";
                    TradeInfo tradeInfo = ((QrPayCScanBYl.Resp) baseResp).tradeInfo;
                    eventSink.success(JSON.toJSONString(new RespModel(QRPAY_C_SCAN_B_YL, tradeInfo)));
                } else if (baseResp instanceof QrPayCScanBZfb.Resp) {
                    msg = "支付宝支付交易成功";
                    TradeInfo tradeInfo = ((QrPayCScanBZfb.Resp) baseResp).tradeInfo;
                    eventSink.success(JSON.toJSONString(new RespModel(QRPAY_C_SCAN_B_ZFB, tradeInfo)));
                } else if (baseResp instanceof QrPayRefund.Resp) {
                    msg = "扫码退款成功";
                    eventSink.success(JSON.toJSONString(new RespModel(QRPAY_REFUND)));
                } else if (baseResp instanceof PosSign.Resp) {
                    msg = "签到成功";
                    eventSink.success(JSON.toJSONString(new RespModel(SIGN)));
                } else if (baseResp instanceof TransQueryDetail.Resp) {
                    TradeInfo tradeInfo = ((TransQueryDetail.Resp) baseResp).tradeInfo;
                    eventSink.success(JSON.toJSONString(new RespModel(TRANS_QUERY_DETAIL, tradeInfo)));
                } else if (baseResp instanceof SwipeCardRefund.Resp) {
                    TradeInfo tradeInfo = ((SwipeCardRefund.Resp) baseResp).tradeInfo;
                    eventSink.success(JSON.toJSONString(new RespModel(SWIPE_CARD_REFUND, tradeInfo)));
                } else if (baseResp instanceof SwipeCardRevoke.Resp) {
                    TradeInfo tradeInfo = ((SwipeCardRevoke.Resp) baseResp).tradeInfo;
                    eventSink.success(JSON.toJSONString(new RespModel(SWIPE_CARD_REVOKE, tradeInfo)));
                } else if (baseResp instanceof TransQueryList.Resp) {
                    List<TradeInfo> tradeInfos = ((TransQueryList.Resp) baseResp).tradeInfoList;
                    eventSink.success(JSON.toJSONString(new RespModel(TRANS_QUERY_LIST, tradeInfos)));
                }

                if (!TextUtils.isEmpty(msg)) {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

// 响应模型
class RespModel {
    /**
     * key值
     */
    private String key;
    /**
     * 数据对象
     */
    private TradeInfo data;

    private List<TradeInfo> list;

    public RespModel() {
    }

    public RespModel(String key) {
        this.key = key;
    }

    public RespModel(String key, List<TradeInfo> list) {
        this.key = key;
        this.list = list;
    }

    public RespModel(String key, TradeInfo data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TradeInfo getData() {
        return data;
    }

    public void setData(TradeInfo data) {
        this.data = data;
    }

    public List<TradeInfo> getList() {
        return list;
    }

    public void setList(List<TradeInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RespModel{" +
                "key='" + key + '\'' +
                ", data=" + data +
                ", list=" + list +
                '}';
    }
}
