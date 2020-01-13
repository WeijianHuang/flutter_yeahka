import 'dart:convert';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_yeahka/flutter_yeahka.dart';
import 'package:flutter_yeahka/entitys/resp_data.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
    _initListener();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = "Unknow";
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  void _sign(){
     FlutterYeahka.sign();
  }

  void _swipeCardTrans(){
    FlutterYeahka.swipeCardTrans(1, customOrderId: "123123");
  }

  void _unipayRevoke(){
    FlutterYeahka.swipeCardRevoke("165003013957");
  }

  void _unipayDetail(){
    FlutterYeahka.transQueryDetail("165003013957");
  }

  void _unipayList(){
    FlutterYeahka.transQueryList("000003917016585");
  }

  void _initListener(){
    FlutterYeahka.eventChannel.receiveBroadcastStream().listen( (data){
      RespData respData = RespData.fromJson(jsonDecode(data));
      print(respData);

    },onError: (err){
      if(err is PlatformException){
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              Text(
                  'Running on: $_platformVersion\n'
              ),
              const SizedBox(height: 30),
              RaisedButton(
                onPressed: _sign,
                child: const Text(
                    '签到',
                    style: TextStyle(fontSize: 20)
                ),
              ),
              RaisedButton(
                onPressed: _swipeCardTrans,
                child: const Text(
                    '刷卡支付',
                    style: TextStyle(fontSize: 20)
                ),
              ),
              RaisedButton(
                onPressed: _unipayRevoke,
                child: const Text(
                    '刷卡撤销',
                    style: TextStyle(fontSize: 20)
                ),
              ),
              RaisedButton(
                onPressed: _unipayDetail,
                child: const Text(
                    '查询详情',
                    style: TextStyle(fontSize: 20)
                ),
              ),
              RaisedButton(
                onPressed: _unipayList,
                child: const Text(
                    '查询列表',
                    style: TextStyle(fontSize: 20)
                ),
              ),
              const SizedBox(height: 30),
            ],
          ),
        ),
      ),
    );
  }
}

