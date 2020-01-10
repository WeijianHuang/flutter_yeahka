#import "FlutterYeahkaPlugin.h"
#if __has_include(<flutter_yeahka/flutter_yeahka-Swift.h>)
#import <flutter_yeahka/flutter_yeahka-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_yeahka-Swift.h"
#endif

@implementation FlutterYeahkaPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterYeahkaPlugin registerWithRegistrar:registrar];
}
@end
