#import "BarcodePlugin.h"
#if __has_include(<barcode_plugin/barcode_plugin-Swift.h>)
#import <barcode_plugin/barcode_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "barcode_plugin-Swift.h"
#endif

@implementation BarcodePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftBarcodePlugin registerWithRegistrar:registrar];
}
@end
