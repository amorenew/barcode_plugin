# barcode_plugin

A flutter plugin for Tablet Alps ax6737 to read barcode.

#### Library Pub link
https://pub.dev/packages/barcode_plugin/

### Getting Started

##### In your Android Manifest add the following in application tag

```xml
    <service android:name="com.scan.service.ScanService">
    <intent-filter>
            <action android:name="com.scan.service" />
            <category android:name="android.intent.category.DEFAULT" />
        </intent-filter>
    </service>
    <service android:name="com.scan.service.Scan1DService">
        <intent-filter>
            <action android:name="com.scan.service" />
            <category android:name="android.intent.category.DEFAULT" />
        </intent-filter>
    </service>
    <receiver android:name="com.scan.service.BootReceiver">
        <intent-filter>
            <action android:name="android.intent.action.BOOT_COMPLETED" />
        </intent-filter>
    </receiver>
```

##### In your Gradle exclude Flutter so files because libary is working in 32bit mode
##### Only use release apk because running from IDE will not exclude 64bit files
    ```ruby
    packagingOptions {
        exclude 'lib/arm64-v8a/libflutter.so'
        exclude 'lib/arm64-v8a/libapp.so'
        exclude 'lib/arm64-v8a/libvudroid.so'
        exclude 'lib/armeabi/libvudroid.so'
        exclude 'lib/mips/libvudroid.so'
        exclude 'lib/x86/libvudroid.so'
        pickFirst 'lib/armeabi-v7a/libdevapi.so'//In case a library conflict with other libs
        }
    ```

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

