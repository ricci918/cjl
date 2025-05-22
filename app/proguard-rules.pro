# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.

# If you keep the line number information, uncomment this to
# hide the original source file name.

-dontwarn javax.annotation.**
-dontskipnonpubliclibraryclasses
-optimizationpasses 5
-optimizations !code/simplification/cast,!field/*,!class/merging/*
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.conscrypt.ConscryptHostnameVerifier

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

-keepclassmembers class com.vaytree.antic.model.data.**{*;}

-keepattributes SourceFile,LineNumberTable

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.google.android.gms.common.ConnectionResult {
    int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
    com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
    java.lang.String getId();
    boolean isLimitAdTrackingEnabled();
}
-keep public class com.android.installreferrer.**{ *; }
##---------------End: proguard configuration for Gson  ----------

-keepattributes SourceFile,LineNumberTable
-printconfiguration "build/outputs/mapping/configuration.txt"
-keep class com.luck.picture.lib.** { *; }
-keep class com.luck.lib.camerax.** { *; }
	-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }
-keep class cn.hx.plugin.ui.** {*;}

# And if you use AsyncExecutor:
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#Okio
-dontwarn org.codehaus.mojo.animal_sniffer.*

-keep class com.vaytree.antic.ui.widget.** { *; } #自定义控件不参与混淆
-dontshrink
-keep class com.appsflyer.** { *; }
-keep class kotlin.jvm.internal.** { *; }
-keep public class com.android.installreferrer.** { *; }
#trustdecision
-keep class com.trustdecision.**{*;}
-keep class cn.tongdun.**{*;}
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn cn.cloudwalk.jni.WatermarkSdk
-dontwarn cn.cloudwalk.sdk.entity.beauty.BeautyInputImage
-dontwarn cn.cloudwalk.sdk.entity.beauty.BeautyOutputImage
-dontwarn cn.cloudwalk.sdk.gldisplay.CWGLDisplay
-dontwarn cn.cloudwalk.sdk.gldisplay.GLFrameSurface
-dontwarn cn.cloudwalk.sdk.sdk.beauty.BeautySdk
-dontwarn cn.cloudwalk.videorecord.AudioRecorder
-dontwarn cn.cloudwalk.videorecord.IMediaRecorder
-dontwarn cn.cloudwalk.videorecord.jniinterface.FFmpegBridge$FFmpegStateListener
-dontwarn cn.cloudwalk.videorecord.jniinterface.FFmpegBridge
-dontwarn cn.cloudwalk.videorecord.model.MediaObject$MediaPart
-dontwarn cn.cloudwalk.videorecord.model.MediaObject
-dontwarn cn.cloudwalk.videorecord.util.SystemUtil
-dontwarn cn.com.westone.wcspsdk.impl.jni.Utils
-dontwarn cn.keyou.api.utils.Hex
-dontwarn cn.keyou.ssm.client.Algorithm
-dontwarn cn.keyou.ssm.client.AsymmetricKey
-dontwarn cn.keyou.ssm.client.Envelope
-dontwarn com.westone.wvcm.jce.ext.WVCMDataFactory
-dontwarn com.westone.wvcm.jce.key.KeyFactoryExt
-dontwarn com.westone.wvcm.jce.key.WVCMHMACKey
-dontwarn com.westone.wvcm.jce.key.WVCMSessionKey
-dontwarn com.westone.wvcm.jce.skf.WVCMProvider
-dontwarn com.westone.wvcm.jce.skf.spec.WVCMBLOCKCIPHERParameterSpec
-dontwarn com.westone.wvcm.jce.skf.spec.WVCMGenKeySpec
-dontwarn com.westone.wvcm.jce.skf.wrapper.WVCMSKFApi
-dontwarn org.bouncycastle.asn1.x9.ECNamedCurveTable
-dontwarn org.bouncycastle.asn1.x9.X9ECParameters
-dontwarn org.bouncycastle.crypto.AsymmetricCipherKeyPair
-dontwarn org.bouncycastle.crypto.CipherParameters
-dontwarn org.bouncycastle.crypto.CryptoException
-dontwarn org.bouncycastle.crypto.CryptoServicesRegistrar
-dontwarn org.bouncycastle.crypto.Digest
-dontwarn org.bouncycastle.crypto.InvalidCipherTextException
-dontwarn org.bouncycastle.crypto.KeyGenerationParameters
-dontwarn org.bouncycastle.crypto.Signer
-dontwarn org.bouncycastle.crypto.digests.SM3Digest
-dontwarn org.bouncycastle.crypto.engines.SM2Engine$Mode
-dontwarn org.bouncycastle.crypto.engines.SM2Engine
-dontwarn org.bouncycastle.crypto.generators.ECKeyPairGenerator
-dontwarn org.bouncycastle.crypto.macs.HMac
-dontwarn org.bouncycastle.crypto.params.AsymmetricKeyParameter
-dontwarn org.bouncycastle.crypto.params.ECDomainParameters
-dontwarn org.bouncycastle.crypto.params.ECKeyGenerationParameters
-dontwarn org.bouncycastle.crypto.params.ECKeyParameters
-dontwarn org.bouncycastle.crypto.params.ECPrivateKeyParameters
-dontwarn org.bouncycastle.crypto.params.ECPublicKeyParameters
-dontwarn org.bouncycastle.crypto.params.KeyParameter
-dontwarn org.bouncycastle.crypto.params.ParametersWithID
-dontwarn org.bouncycastle.crypto.params.ParametersWithRandom
-dontwarn org.bouncycastle.crypto.signers.DSAEncoding
-dontwarn org.bouncycastle.crypto.signers.DSAKCalculator
-dontwarn org.bouncycastle.crypto.signers.PlainDSAEncoding
-dontwarn org.bouncycastle.crypto.signers.RandomDSAKCalculator
-dontwarn org.bouncycastle.crypto.signers.StandardDSAEncoding
-dontwarn org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey
-dontwarn org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil
-dontwarn org.bouncycastle.jce.provider.BouncyCastleProvider
-dontwarn org.bouncycastle.math.ec.ECAlgorithms
-dontwarn org.bouncycastle.math.ec.ECConstants
-dontwarn org.bouncycastle.math.ec.ECCurve
-dontwarn org.bouncycastle.math.ec.ECFieldElement
-dontwarn org.bouncycastle.math.ec.ECMultiplier
-dontwarn org.bouncycastle.math.ec.ECPoint
-dontwarn org.bouncycastle.math.ec.FixedPointCombMultiplier
-dontwarn org.bouncycastle.pqc.math.linearalgebra.ByteUtils
-dontwarn org.bouncycastle.util.Arrays
-dontwarn org.bouncycastle.util.encoders.Base64
-dontwarn org.bouncycastle.util.encoders.Hex


