// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'trans_info.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

TransInfo _$TransInfoFromJson(Map<String, dynamic> json) {
  return TransInfo(
    json['transactionType'] as int,
    json['payType'] as int,
    json['cardType'] as int,
    json['cardNo'] as String,
    json['batchNo'] as String,
    json['flowId'] as String,
    json['amount'] as int,
    json['answerCode'] as String,
    json['requestMsg'] as String,
    json['responseMsg'] as String,
    json['referenceNo'] as String,
    json['oldFlowId'] as String,
    json['oldBatchNo'] as String,
    json['oldReferenceNo'] as String,
    json['date'] as String,
    json['time'] as String,
    json['tradeHandleCode'] as String,
    json['bankName'] as String,
    json['merchantId'] as String,
    json['reverseStatus'] as int,
    json['reverseCount'] as int,
    json['merchantName'] as String,
    json['displayFlag'] as int,
    json['cardValidity'] as String,
    json['icSeqNo'] as String,
    json['serverInputCode'] as String,
    json['authorizedResponseCode'] as String,
    json['billDate'] as String,
    json['orderId'] as String,
    json['qrCodeUrl'] as String,
  );
}

Map<String, dynamic> _$TransInfoToJson(TransInfo instance) => <String, dynamic>{
      'transactionType': instance.transactionType,
      'payType': instance.payType,
      'cardType': instance.cardType,
      'cardNo': instance.cardNo,
      'batchNo': instance.batchNo,
      'flowId': instance.flowId,
      'amount': instance.amount,
      'answerCode': instance.answerCode,
      'requestMsg': instance.requestMsg,
      'responseMsg': instance.responseMsg,
      'referenceNo': instance.referenceNo,
      'oldFlowId': instance.oldFlowId,
      'oldBatchNo': instance.oldBatchNo,
      'oldReferenceNo': instance.oldReferenceNo,
      'date': instance.date,
      'time': instance.time,
      'tradeHandleCode': instance.tradeHandleCode,
      'bankName': instance.bankName,
      'merchantId': instance.merchantId,
      'reverseStatus': instance.reverseStatus,
      'reverseCount': instance.reverseCount,
      'merchantName': instance.merchantName,
      'displayFlag': instance.displayFlag,
      'cardValidity': instance.cardValidity,
      'icSeqNo': instance.icSeqNo,
      'serverInputCode': instance.serverInputCode,
      'authorizedResponseCode': instance.authorizedResponseCode,
      'billDate': instance.billDate,
      'orderId': instance.orderId,
      'qrCodeUrl': instance.qrCodeUrl,
    };
