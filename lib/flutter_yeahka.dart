import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_yeahka/constant/action_type.dart';

class FlutterYeahka {
  static const MethodChannel _channel = const MethodChannel('flutter_yeahka');
  static const EventChannel _eventChannel = const EventChannel('flutter_yeahka_event');

  static EventChannel get eventChannel => _eventChannel;

  static Future<void> sign() async {
    await _channel.invokeMethod(SIGN);
  }

  static Future<bool> checkInstall() async {
   return await _channel.invokeMethod(CHECK_INSTALL);
  }

  static Future<String> downloadTMK(String authorizationCode) async {
    await _channel.invokeMethod(DOWNLOAD_TMK, {"authorizationCode": authorizationCode});
  }

  static Future<String> swipeCardTrans(int amount, String customOrderId) async {
    await _channel.invokeMethod(SWIPE_CARD_TRANS, {"amount": amount, "customOrderId": customOrderId});
  }

  static Future<String> qrPayBScanC(int amount, String customOrderId) async {
    await _channel.invokeMethod(QRPAY_B_SCAN_C, {"amount": amount, "customOrderId": customOrderId});
  }

  static Future<String> qrPayCScanBWx(int amount, String customOrderId) async {
    await _channel.invokeMethod(QRPAY_C_SCAN_B_WX, {"amount": amount, "customOrderId": customOrderId});
  }

  static Future<String> qrPayCScanBYl(int amount, String customOrderId) async {
    await _channel.invokeMethod(QRPAY_C_SCAN_B_YL, {"amount": amount, "customOrderId": customOrderId});
  }

  static Future<String> qrPayCScanBZfb(int amount, String customOrderId) async {
    await _channel.invokeMethod(QRPAY_C_SCAN_B_ZFB, {"amount": amount, "customerOrderId": customOrderId});
  }

  static Future<String> qrPayRefund(int amount, String referenceNo, {String oriOrderId}) async {
    await _channel.invokeMethod(QRPAY_REFUND, {"amount": amount, "referenceNo": referenceNo, "oriOrderId": oriOrderId});
  }

  static Future<String> transQueryDetail(String referenceNo, {String oriOrderId}) async {
    await _channel.invokeMethod(TRANS_QUERY_DETAIL, {"referenceNo": referenceNo, "oriOrderId": oriOrderId});
  }

  static Future<String> swipeCardRefund(int amount, String referenceNo, {String oriOrderId}) async {
    await _channel
        .invokeMethod(SWIPE_CARD_REFUND, {"amount": amount, "referenceNo": referenceNo, "oriOrderId": oriOrderId});
  }

  static Future<String> swipeCardRevoke(String referenceNo, {String oriOrderId}) async {
    await _channel.invokeMethod(SWIPE_CARD_REVOKE, {"referenceNo": referenceNo, "oriOrderId": oriOrderId});
  }

  static Future<String> transQueryList(String merchantId) async {
    await _channel.invokeMethod(TRANS_QUERY_LIST, {"merchantId": merchantId});
  }

  static Future<String> reprintTicket(String orderId,{String referenceNo}) async {
    await _channel.invokeMethod(REPRINT_TICKET, {"orderId":orderId, "referenceNo": referenceNo});
  }
}
