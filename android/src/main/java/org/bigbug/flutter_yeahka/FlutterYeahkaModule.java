package org.bigbug.flutter_yeahka;

import com.yeahka.shouyintong.sdk.action.PosActivate;
import com.yeahka.shouyintong.sdk.action.PosSign;
import com.yeahka.shouyintong.sdk.action.QrPayCScanBYl;
import com.yeahka.shouyintong.sdk.api.SytApi;

public class FlutterYeahkaModule {

    private SytApi sytApi;

    public void sign() {
        PosSign.Req req = new PosSign.Req();
        sytApi.sendReq(req);
    }

    public void downloadTMK(String authorizationCode) {
        PosActivate.Req req = new PosActivate.Req();
        req.authorizeCode = authorizationCode;
        sytApi.sendReq(req);
    }



    public void readCard(String customerOrderId, int amountOfPenny) {
        QrPayCScanBYl.Req qrUnion = new QrPayCScanBYl.Req();
        qrUnion.amount = amountOfPenny;
        qrUnion.customOrderId = customerOrderId;
        sytApi.sendReq(qrUnion);
    }
}
