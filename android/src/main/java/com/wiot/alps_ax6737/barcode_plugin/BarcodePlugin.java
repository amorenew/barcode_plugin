package com.wiot.alps_ax6737.barcode_plugin;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * BarcodePlugin
 */
public class BarcodePlugin implements FlutterPlugin, MethodCallHandler {

    //    private static final String CHANNEL_Init = "barcode_plugin/init";
//    private static final String CHANNEL_Is1DimenModule = "barcode_plugin/is1DimenModule";
//    private static final String CHANNEL_Set1DimenModule = "barcode_plugin/set1DimenModule";
    private static final String CHANNEL_StartScanning = "startScanning";
    private static final String CHANNEL_IsOpen = "isOpen";
    private static final String CHANNEL_Open = "open";
    private static final String CHANNEL_Close = "close";
//    private static final String CHANNEL_IsVoice = "barcode_plugin/isVoice";
//    private static final String CHANNEL_SetVoice = "barcode_plugin/setVoice";
//    private static final String CHANNEL_IsTab = "barcode_plugin/isTab";
//    private static final String CHANNEL_SetTab = "barcode_plugin/setTab";
//    private static final String CHANNEL_IsEnter = "barcode_plugin/isEnter";
//    private static final String CHANNEL_SetEnter = "barcode_plugin/setEnter";
//    private static final String CHANNEL_SetPrefix = "barcode_plugin/setPrefix";
//    private static final String CHANNEL_SetSurfix = "barcode_plugin/setSurfix";
//    private static final String CHANNEL_InitTimeOut = "barcode_plugin/initTimeOut";
    /**
     * private final int CodeSuccess = 1001;
     * private final int CodeFailed = 1002;
     * private final int CodeTimeout = 1003;
     *
     * @Override public void onResult(int code, String barcode) {
     * if (code == CodeSuccess) {
     * Toast.makeText(context, "Result Code: Success", Toast.LENGTH_LONG).show();
     * Toast.makeText(context, "Barcode: " + barcode, Toast.LENGTH_LONG).show();
     * } else if (code == CodeFailed) {
     * Toast.makeText(context, "Result Code: Failed", Toast.LENGTH_LONG).show();
     * } else if (code == CodeTimeout) {
     * Toast.makeText(context, "Result Code: Timeout", Toast.LENGTH_LONG).show();
     * } else {
     * Toast.makeText(context, "Result Code: " + code, Toast.LENGTH_LONG).show();
     * }
     * }
     * }
     */
    private static PublishSubject<Integer> scannerStatus = PublishSubject.create();
    private static PublishSubject<String> barcodeStatus = PublishSubject.create();
    private static final String CHANNEL_ScannerStatus = "ScannerStatus";
    private static final String CHANNEL_BarcodeStatus = "BarcodeStatus";

    //private FlutterPluginBinding flutterPluginBinding;
    // private static Registrar registrar;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "barcode_plugin");
        initScannerEvent(flutterPluginBinding.getBinaryMessenger());
        initBarcodeEvent(flutterPluginBinding.getBinaryMessenger());
        channel.setMethodCallHandler(new BarcodePlugin());
        //  this.flutterPluginBinding = flutterPluginBinding;
        BarcodeHelper.getInstance().init(flutterPluginBinding.getApplicationContext());
        BarcodeHelper.getInstance().setBarcodeListener(new BarcodeListener() {
            @Override
            public void onResult(int code, String barcode) {
                scannerStatus.onNext(code);
                barcodeStatus.onNext(barcode);
            }
        });
        BarcodeHelper.getInstance().set1DimenModule(true);
        BarcodeHelper.getInstance().initTimeOut();
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "barcode_plugin");
        initScannerEvent(registrar.messenger());
        initBarcodeEvent(registrar.messenger());
        channel.setMethodCallHandler(new BarcodePlugin());
        //  BarcodePlugin.registrar = registrar;
        BarcodeHelper.getInstance().init(registrar.context());
        BarcodeHelper.getInstance().setBarcodeListener(new BarcodeListener() {
            @Override
            public void onResult(int code, String barcode) {
                scannerStatus.onNext(code);
                barcodeStatus.onNext(barcode);
            }
        });
        BarcodeHelper.getInstance().set1DimenModule(true);
        BarcodeHelper.getInstance().initTimeOut();
    }

//    Context getContext() {
//        if (flutterPluginBinding != null)
//            return flutterPluginBinding.getApplicationContext();
//        if (BarcodePlugin.registrar != null)
//            return BarcodePlugin.registrar.context();
//        return null;
//    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        handleMethods(call.method, result);
    }

    private void handleMethods(String method, Result result) {
        switch (method) {
            case "getPlatformVersion":
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            case CHANNEL_StartScanning:
                BarcodeHelper.getInstance().startScanning();
                result.success(true);
                break;
            case CHANNEL_IsOpen:
                result.success(BarcodeHelper.getInstance().isOpen());
                break;
            case CHANNEL_Open:
                BarcodeHelper.getInstance().open();
                result.success(true);
                break;
            case CHANNEL_Close:
                BarcodeHelper.getInstance().close();
                result.success(true);
                break;
            default:
                result.notImplemented();
        }
//    private static final String CHANNEL_Init = "barcode_plugin/init";
        ///private static final String CHANNEL_ = "barcode_plugin/BarcodeListener";
//    private static final String CHANNEL_Is1DimenModule = "barcode_plugin/is1DimenModule";
//    private static final String CHANNEL_Set1DimenModule = "barcode_plugin/set1DimenModule";
//      private static final String CHANNEL_StartScanning = "barcode_plugin/startScanning";
//      private static final String CHANNEL_IsOpen = "barcode_plugin/isOpen";
//      private static final String CHANNEL_Open = "barcode_plugin/open";
//      private static final String CHANNEL_Close = "barcode_plugin/close";
//    private static final String CHANNEL_IsVoice = "barcode_plugin/isVoice";
//    private static final String CHANNEL_SetVoice = "barcode_plugin/setVoice";
//    private static final String CHANNEL_IsTab = "barcode_plugin/isTab";
//    private static final String CHANNEL_SetTab = "barcode_plugin/setTab";
//    private static final String CHANNEL_IsEnter = "barcode_plugin/isEnter";
//    private static final String CHANNEL_SetEnter = "barcode_plugin/setEnter";
//    private static final String CHANNEL_SetPrefix = "barcode_plugin/setPrefix";
//    private static final String CHANNEL_SetSurfix = "barcode_plugin/setSurfix";
//    private static final String CHANNEL_InitTimeOut = "barcode_plugin/initTimeOut";

    }

    private static void initScannerEvent(BinaryMessenger messenger) {
        final EventChannel scannerEventChannel = new EventChannel(messenger, CHANNEL_ScannerStatus);
        scannerEventChannel.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object o, final EventChannel.EventSink eventSink) {
                scannerStatus
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer resultCode) {
                        eventSink.success(resultCode);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }

            @Override
            public void onCancel(Object o) {

            }
        });
    }    private static void initBarcodeEvent(BinaryMessenger messenger) {
        final EventChannel scannerEventChannel = new EventChannel(messenger, CHANNEL_BarcodeStatus);
        scannerEventChannel.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object o, final EventChannel.EventSink eventSink) {
                barcodeStatus
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String barcode) {
                        eventSink.success(barcode);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }

            @Override
            public void onCancel(Object o) {

            }
        });
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
