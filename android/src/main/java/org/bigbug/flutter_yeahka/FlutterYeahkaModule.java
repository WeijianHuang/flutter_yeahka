package org.bigbug.flutter_yeahka;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.yeahka.shouyintong.sdk.action.PosActivate;
import com.yeahka.shouyintong.sdk.action.PosSign;
import com.yeahka.shouyintong.sdk.action.QrPayCScanBYl;
import com.yeahka.shouyintong.sdk.api.SytApi;
import com.yeahka.shouyintong.sdk.api.SytFactory;

public class FlutterYeahkaModule {

    private SytApi sytApi;

    @SuppressLint({"WrongConstant", "ShowToast"})
    public void initBase(Context context) {
        //初始化收银通sdk
        sytApi = SytFactory.createSytIml(context);
        //需要先判断收银通是否已经安装，已经安装才可以进行
        boolean installed = sytApi.isSytInstalled();
        if (!installed) {
            Toast.makeText(context,"请先安装收银通",3000);
            //提示未安装
        }
        //初始化请求
//        PosActivate.Req req = new PosActivate.Req();
//        req.authorizeCode = authorizationCode;
        //返回 true 表示请求发送成功，false 则表示参数有问题，需检查
//        boolean sent = sytApi.sendReq(req);
    }

    public boolean sign() {
        PosSign.Req req = new PosSign.Req();
        return sytApi.sendReq(req);
    }

    public boolean downloadTMK(String authorizationCode) {
        PosActivate.Req req = new PosActivate.Req();
        req.authorizeCode = authorizationCode;
        return sytApi.sendReq(req);
    }

    public void unipay(String customerOrderId, int amountOfPenny) {
        QrPayCScanBYl.Req qrUnion = new QrPayCScanBYl.Req();
        qrUnion.amount = amountOfPenny;
        qrUnion.customOrderId = customerOrderId;
        sytApi.sendReq(qrUnion);
    }
}
