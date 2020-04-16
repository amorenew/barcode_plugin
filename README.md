# barcode_plugin

A flutter plugin for Tablet Alps ax6737 to read barcode.

#### Library Pub link
https://pub.dev/packages/barcode_plugin/

### Getting Started

##### In your Gradle exclude Flutter so files because libary is working in 32bit mode
##### Only use release apk because running from IDE will not exclude 64bit files
    `packagingOptions {
        exclude 'lib/arm64-v8a/libflutter.so'
        exclude 'lib/arm64-v8a/libapp.so'
    }`

- Import the library:
   `import 'package:barcode_plugin/barcode_plugin.dart';`

- Open connection to the barcode reader

    `await BarcodePlugin.open;`

- Check if connection is opened

    `await BarcodePlugin.isOpen;`

- Start scanning barcode labels

    `await BarcodePlugin.startScanning;`

- Close connection to the barcode reader

    `await BarcodePlugin.close;`

- Listen to scanner connection status

   `BarcodePlugin.scannerStatusStream.receiveBroadcastStream().listen(updateData);`

- Listen to barcode labels

   `BarcodePlugin.barcodeStatusStream.receiveBroadcastStream().listen(updateData);`

