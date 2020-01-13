
import 'package:json_annotation/json_annotation.dart';

part 'trans_info.g.dart';

@JsonSerializable()
class TransInfo{
  final int transactionType;
  final int payType;
  final int cardType;
  final String cardNo;
  final String batchNo;
  final String flowId;
  final int amount;
  final String answerCode;
  final String requestMsg;
  final String responseMsg;
  final String referenceNo;
  final String oldFlowId;
  final String oldBatchNo;
  final String oldReferenceNo;
  final String date;
  final String time;
  final String tradeHandleCode;
  final String bankName;
  final String merchantId;
  final int reverseStatus;
  final int reverseCount;
  final String merchantName;
  final int displayFlag;
  final String cardValidity;
  final String icSeqNo;
  final String serverInputCode;
  final String authorizedResponseCode;
  final String billDate;
  final String orderId;
  final String qrCodeUrl;

  TransInfo(this.transactionType, this.payType, this.cardType, this.cardNo, this.batchNo, this.flowId, this.amount, this.answerCode, this.requestMsg, this.responseMsg, this.referenceNo, this.oldFlowId, this.oldBatchNo, this.oldReferenceNo, this.date, this.time, this.tradeHandleCode, this.bankName, this.merchantId, this.reverseStatus, this.reverseCount, this.merchantName, this.displayFlag, this.cardValidity, this.icSeqNo, this.serverInputCode, this.authorizedResponseCode, this.billDate, this.orderId, this.qrCodeUrl);

  factory TransInfo.fromJson(Map<String, dynamic> json) => _$TransInfoFromJson(json);

  Map<String, dynamic> toJson() => _$TransInfoToJson(this);

  @override
  String toString() {
    return 'TransInfo{transactionType: $transactionType, payType: $payType, cardType: $cardType, cardNo: $cardNo, batchNo: $batchNo, flowId: $flowId, amount: $amount, answerCode: $answerCode, requestMsg: $requestMsg, responseMsg: $responseMsg, referenceNo: $referenceNo, oldFlowId: $oldFlowId, oldBatchNo: $oldBatchNo, oldReferenceNo: $oldReferenceNo, date: $date, time: $time, tradeHandleCode: $tradeHandleCode, bankName: $bankName, merchantId: $merchantId, reverseStatus: $reverseStatus, reverseCount: $reverseCount, merchantName: $merchantName, displayFlag: $displayFlag, cardValidity: $cardValidity, icSeqNo: $icSeqNo, serverInputCode: $serverInputCode, authorizedResponseCode: $authorizedResponseCode, billDate: $billDate, orderId: $orderId, qrCodeUrl: $qrCodeUrl}';
  }
}
