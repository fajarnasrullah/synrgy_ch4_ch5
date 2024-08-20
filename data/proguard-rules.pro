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
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn java.lang.invoke.StringConcatFactory
-keep class com.jer.ch4_ch5.data.datasource.local.room.UserNote { *; }
-keep class com.jer.ch4_ch5.data.datasource.remote.retrofit.art.** { *; }
-keep class com.jer.ch4_ch5.data.repository.students.NoteStudentsRepository { *; }

-keep class com.jer.ch4_ch5.data.datasource.LoginLocalSource { *; }
-keep class com.jer.ch4_ch5.data.datasource.LoginRemoteSource { *; }
-keep class com.jer.ch4_ch5.data.datasource.local.DataStoreFactKt { *; }
-keep class com.jer.ch4_ch5.data.datasource.local.ImplementLoginLocal { *; }
-keep class com.jer.ch4_ch5.data.datasource.remote.ImplementLoginRemote { *; }
-keep class com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ApiClientLoginKt { *; }
-keep class com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ReqresService { *; }
-keep class com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository { *; }

-dontwarn com.jer.ch4_ch5.data.datasource.LoginLocalSource
-dontwarn com.jer.ch4_ch5.data.datasource.LoginRemoteSource
-dontwarn com.jer.ch4_ch5.data.datasource.local.DataStoreFactKt
-dontwarn com.jer.ch4_ch5.data.datasource.local.ImplementLoginLocal
-dontwarn com.jer.ch4_ch5.data.datasource.remote.ImplementLoginRemote
-dontwarn com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ApiClientLoginKt
-dontwarn com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ReqresService
-dontwarn com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository