package org.bigbug.flutter_yeahka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

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

import java.io.Serializable;

public class ResultReceiver extends BroadcastReceiver {

//    private static final String EXTRA_TRADE_INFO_LIST = "extra_trade_info_list";

    @Override
    public void onReceive(final Context context, Intent intent) {
        SytApi sytApi = SytFactory.createSytIml(context);
        sytApi.handleAction(intent, new ISytEventHandler() {
            @Override
            public void onResponse(BaseResp baseResp) {
                boolean isSuccess = baseResp.isSuccessful();
                String msg = "";
                String errorCode = "";
                if (!isSuccess) {
                    msg = baseResp.getMsg();
                    errorCode = baseResp.getCode() + "";
                }

                if (baseResp instanceof PosActivate.Resp) {
                    if (isSuccess) {
                        msg = "激活成功";
                    }
                } else if (baseResp instanceof SwipeCardTrans.Resp) {
                    if (isSuccess) {
                        msg = "刷卡交易成功";
                        TradeInfo tradeInfo=((SwipeCardTrans.Resp) baseResp).tradeInfo;
//                        startDetailActivity(tradeInfo,context);
                    }
                } else if (baseResp instanceof QrPayBScanC.Resp) {
                    if (isSuccess) {
                        msg = "主扫交易成功";
                        TradeInfo tradeInfo=((QrPayBScanC.Resp) baseResp).tradeInfo;
//                        startDetailActivity(tradeInfo,context);
                    }
                } else if (baseResp instanceof QrPayCScanBWx.Resp) {
                    if (isSuccess) {
                        msg = "微信支付交易成功";
                        TradeInfo tradeInfo=((QrPayCScanBWx.Resp) baseResp).tradeInfo;
//                        startDetailActivity(tradeInfo,context);
                    }
                } else if (baseResp instanceof QrPayCScanBYl.Resp) {
                    if (isSuccess) {
                        msg = "银联二维码支付交易成功";
                        TradeInfo tradeInfo=((QrPayCScanBYl.Resp) baseResp).tradeInfo;
//                        startDetailActivity(tradeInfo,context);
                    }
                } else if (baseResp instanceof QrPayCScanBZfb.Resp) {
                    if (isSuccess) {
                        msg = "支付宝支付交易成功";
                        TradeInfo tradeInfo=((QrPayCScanBZfb.Resp) baseResp).tradeInfo;
//                        startDetailActivity(tradeInfo,context);
                    }
                } else if (baseResp instanceof QrPayRefund.Resp) {
                    if (isSuccess) {
                        msg = "扫码退款成功";
                    }
                } else if (baseResp instanceof PosSign.Resp) {
                    if (isSuccess) {
                        msg = "签到成功";
                    }
                    //商户名称
                    String merchantName=((PosSign.Resp) baseResp).merchantName;
                    //商户id
                    String merchantId=((PosSign.Resp) baseResp).merchantId;
                    //批次号
                    String batchNo=((PosSign.Resp) baseResp).batchNo;
                    //终端号
                    String terminalId=((PosSign.Resp) baseResp).terminalId;
                    //TODO 自行保存
//                }if (baseResp instanceof TransQueryDetail.Resp) {
//                    if(isSuccess) {
//                        TradeInfo tradeInfo = ((TransQueryDetail.Resp) baseResp).tradeInfo;
//                        startDetailActivity(tradeInfo,context);
//                    }
//                }  if (baseResp instanceof SwipeCardRefund.Resp) {
//                    if(isSuccess) {
//                        TradeInfo tradeInfo = ((SwipeCardRefund.Resp) baseResp).tradeInfo;
//                        startDetailActivity(tradeInfo,context);
//                    }
//                } else if (baseResp instanceof SwipeCardRevoke.Resp) {
//                    if(isSuccess) {
//                        TradeInfo tradeInfo = ((SwipeCardRevoke.Resp) baseResp).tradeInfo;
//                        startDetailActivity(tradeInfo,context);
//                    }
//                }else if (baseResp instanceof TransQueryList.Resp) {
//                    Intent listIntent=new Intent(context,TradeInfoActivity.class);
//                    listIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    // mAdapter.setNewData(((TransQueryList.Resp) baseResp).tradeInfoList);
//                    listIntent.putExtra(EXTRA_TRADE_INFO_LIST, (Serializable) ((TransQueryList.Resp) baseResp).tradeInfoList);
//                    context.startActivity(listIntent);
                }

                if (isSuccess) {
                    if(!TextUtils.isEmpty(msg)) {
                        Toast.makeText(context, msg + "(main)", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, msg + "(" + errorCode + ")", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

//    private void startDetailActivity(TradeInfo tradeInfo,Context context){
//        Intent intent = new Intent(context, TradeDetailActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        String EXTRA_TRADE_INFO = "extra_trade_info";
//        intent.putExtra(EXTRA_TRADE_INFO, tradeInfo);
//        context.startActivity(intent);
//    }
}
