import 'package:flutter_yeahka/entitys/trans_info.dart';
import 'package:json_annotation/json_annotation.dart';

part 'resp_data.g.dart';

@JsonSerializable()
class RespData {
  final String key;
  final TransInfo data;
  final List<TransInfo> list;

  RespData(this.key, this.data, this.list);

  factory RespData.fromJson(Map<String, dynamic> json) => _$RespDataFromJson(json);

  Map<String, dynamic> toJson() => _$RespDataToJson(this);

  @override
  String toString() {
    return 'RespData{key: $key, data: $data, list: $list}';
  }
}
