package org.bigbug.flutter_yeahka;

import android.content.Context;
import android.widget.Toast;

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
import com.yeahka.shouyintong.sdk.api.SytApi;
import com.yeahka.shouyintong.sdk.api.SytFactory;

import static android.widget.Toast.LENGTH_LONG;

public class FlutterYeahkaModule {

    private SytApi sytApi;
    private Context context;

    void initBase(Context context) {
        //初始化收银通sdk
        sytApi = SytFactory.createSytIml(context);
        this.context = context;
    }

    boolean sign() {
        PosSign.Req req = new PosSign.Req();
        return sytApi.sendReq(req);
    }

    boolean downloadTMK(String authorizationCode) {
        PosActivate.Req req = new PosActivate.Req();
        req.authorizeCode = authorizationCode;
        return sytApi.sendReq(req);
    }

    boolean swipeCardTrans(int amount, String customOrderId) {
        SwipeCardTrans.Req swipeCardTrans = new SwipeCardTrans.Req();
        swipeCardTrans.amount = amount;
        swipeCardTrans.customOrderId = customOrderId;
        return sytApi.sendReq(swipeCardTrans);
    }

    boolean qrPayBScanC(int amount, String customOrderId) {
        QrPayBScanC.Req qrPayBScanCReq = new QrPayBScanC.Req();
        qrPayBScanCReq.amount = amount;
        qrPayBScanCReq.customOrderId = customOrderId;
        return sytApi.sendReq(qrPayBScanCReq);
    }

    boolean qrPayCScanBWx(int amount, String customOrderId) {
        QrPayCScanBWx.Req qrPayCScanBWxReq = new QrPayCScanBWx.Req();
        qrPayCScanBWxReq.amount = amount;
        qrPayCScanBWxReq.customOrderId = customOrderId;
        return sytApi.sendReq(qrPayCScanBWxReq);
    }

    boolean qrPayCScanBYl(int amount, String customOrderId) {
        QrPayCScanBYl.Req qrPayCScanBYlReq = new QrPayCScanBYl.Req();
        qrPayCScanBYlReq.amount = amount;
        qrPayCScanBYlReq.customOrderId = customOrderId;
        return sytApi.sendReq(qrPayCScanBYlReq);
    }

    boolean qrPayCScanBZfb(int amount, String customOrderId) {
        QrPayCScanBZfb.Req qrPayCScanBZfbReq = new QrPayCScanBZfb.Req();
        qrPayCScanBZfbReq.amount = amount;
        qrPayCScanBZfbReq.customOrderId = customOrderId;
        return sytApi.sendReq(qrPayCScanBZfbReq);
    }

    boolean qrPayRefund(int amount, String oriOrderId, String referenceNo) {
        QrPayRefund.Req qrPayRefundReq = new QrPayRefund.Req();
        qrPayRefundReq.amount = amount;
        qrPayRefundReq.oriOrderId = oriOrderId;
        qrPayRefundReq.referenceNo = referenceNo;
        return sytApi.sendReq(qrPayRefundReq);
    }

    boolean transQueryDetail(String orderId, String referenceNo) {
        TransQueryDetail.Req transQueryDetailReq = new TransQueryDetail.Req();
        transQueryDetailReq.orderId = orderId;
        transQueryDetailReq.referenceNo = referenceNo;
        return sytApi.sendReq(transQueryDetailReq);
    }

    boolean swipeCardRefund(int amount, String oriOrderId, String referenceNo) {
        SwipeCardRefund.Req swipeCardRefundReq = new SwipeCardRefund.Req();
        swipeCardRefundReq.amount = amount;
        swipeCardRefundReq.oriOrderId = oriOrderId;
        swipeCardRefundReq.referenceNo = referenceNo;
        return sytApi.sendReq(swipeCardRefundReq);
    }

    boolean swipeCardRevoke(String oriOrderId, String referenceNo) {
        SwipeCardRevoke.Req swipeCardRevokeReq = new SwipeCardRevoke.Req();
        swipeCardRevokeReq.oriOrderId = oriOrderId;
        swipeCardRevokeReq.referenceNo = referenceNo;
        return sytApi.sendReq(swipeCardRevokeReq);
    }

    boolean transQueryList(String merchantId) {
        TransQueryList.Req transQueryListReq = new TransQueryList.Req();
        transQueryListReq.merchantId = merchantId;
        return sytApi.sendReq(transQueryListReq);
    }

    boolean checkInstall(){
        boolean installed = sytApi.isSytInstalled();
        if (!installed) {
            Toast.makeText(context, "请先安装收银通", LENGTH_LONG).show();
        }
        return  installed;
    }
}
