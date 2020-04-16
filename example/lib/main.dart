import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:barcode_plugin/barcode_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  bool _isOpen = false;
  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await BarcodePlugin.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }
    BarcodePlugin.scannerStatusStream.receiveBroadcastStream().listen(updateData);
    BarcodePlugin.barcodeStatusStream.receiveBroadcastStream().listen(updateData);

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  List<String> _data = ['--'];
  void updateData(dynamic  result) {
    setState(() {
      _data.add('$result');
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
            children: <Widget>[
              Text('Running on: $_platformVersion'),
              RaisedButton(
                  child: Text('Call isOpen'),
                  onPressed: () async {
                    bool isOpen = await BarcodePlugin.isOpen;
                    setState(() {
                      this._isOpen = isOpen;
                    });
                  }),
              Text('Barcode Scanner isOpen:$_isOpen'),
              RaisedButton(
                  child: Text('Call Open'),
                  onPressed: () async {
                    await BarcodePlugin.open;
                    bool isOpen = await BarcodePlugin.isOpen;
                    setState(() {
                      this._isOpen = isOpen;
                    });
                  }),
              RaisedButton(
                  child: Text('Call Close'),
                  onPressed: () async {
                    await BarcodePlugin.close;
                    bool isOpen = await BarcodePlugin.isOpen;
                    setState(() {
                      this._isOpen = isOpen;
                    });
                  }),
              RaisedButton(
                  child: Text('Call Start Scanning'),
                  onPressed: () async {
                    await BarcodePlugin.startScanning;
                    bool isOpen = await BarcodePlugin.isOpen;
                    setState(() {
                      this._isOpen = isOpen;
                    });
                  }),
              ..._data.map((text) =>Text('Status - Barcode: $text')),
            ],
          ),
        ),
      ),
    );
  }
}
