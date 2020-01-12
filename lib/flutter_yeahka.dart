import 'dart:async';

import 'package:flutter/services.dart';

class FlutterYeahka {
  static const MethodChannel _channel = const MethodChannel('flutter_yeahka');
  static const EventChannel eventChannel = EventChannel('flutter_yeahka_event');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> get sign async {
    await _channel.invokeMethod('sign');
  }

  static Future<String> get downloadTMK async {
    await _channel.invokeMethod('downloadTMK', {"authorizationCode": "222223"});
  }

  static Future<void> get listener async {
    eventChannel.receiveBroadcastStream().listen( (data){
      print(123123123);
      print(data);
    });
  }
}
