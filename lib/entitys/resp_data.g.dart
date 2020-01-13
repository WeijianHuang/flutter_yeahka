// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'resp_data.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

RespData _$RespDataFromJson(Map<String, dynamic> json) {
  return RespData(
    json['key'] as String,
    json['data'] == null
        ? null
        : TransInfo.fromJson(json['data'] as Map<String, dynamic>),
    (json['list'] as List)
        ?.map((e) =>
            e == null ? null : TransInfo.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$RespDataToJson(RespData instance) => <String, dynamic>{
      'key': instance.key,
      'data': instance.data,
      'list': instance.list,
    };
