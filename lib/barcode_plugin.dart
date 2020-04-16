import 'dart:async';

import 'package:flutter/services.dart';

class BarcodePlugin {
  static const MethodChannel _channel = const MethodChannel('barcode_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static const EventChannel scannerStatusStream = EventChannel('ScannerStatus');
  static const EventChannel barcodeStatusStream = EventChannel('BarcodeStatus');

  static Future<bool> get startScanning async {
    return _channel.invokeMethod('startScanning');
  }

  static Future<bool> get isOpen async {
    return _channel.invokeMethod('isOpen');
  }

  static Future<bool> get open async {
    return _channel.invokeMethod('open');
  }

  static Future<bool> get close async {
    return _channel.invokeMethod('close');
  }
}
