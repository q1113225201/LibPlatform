# LibPlatform
依赖包平台库，包含Activity和Fragment基类以及常用工具类，已添加RxJava2.0、Retrofit2.0、Glide、Gson等常用依赖。

## 依赖
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```
	dependencies {
	        implementation 'com.github.q1113225201:LibPlatform:1.0.6'
	}
```

[最新](https://github.com/q1113225201/LibPlatform/releases/latest)

## 说明

### 初始化->[App.java](/app/src/main/java/com/sjl/libplatform/App.java)

初始化[PlatformInit](/libplatform/src/main/java/com/sjl/libplatform/PlatformInit.java)传入Application，后续使用的上下文等都不再需要传入。

### 基类->[base](/libplatform/src/main/java/com/sjl/libplatform/base)

封装有基类[PlatformActivity.java](/libplatform/src/main/java/com/sjl/libplatform/base/PlatformActivity.java)、
基类[PlatformFragment.java](/libplatform/src/main/java/com/sjl/libplatform/base/PlatformFragment.java)(支持懒加载)。

### 常用工具类->[util](/libplatform/src/main/java/com/sjl/libplatform/util)

加密工具类->[encrypt](/libplatform/src/main/java/com/sjl/libplatform/util/encrypt)

* AES加解密->[AESUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/encrypt/AESUtil.java)
* DES加解密->[DESUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/encrypt/DESUtil.java)
* RSA加解密->[RSAUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/encrypt/RSAUtil.java)
* MD5加密->[MD5Util.java](/libplatform/src/main/java/com/sjl/libplatform/util/encrypt/MD5Util.java)
* Base64加解密->[Base64Util.java](/libplatform/src/main/java/com/sjl/libplatform/util/encrypt/Base64Util.java)

Activity工具类->[ActivityUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/ActivityUtil.java)

图像操作工具类->[BitmapUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/BitmapUtil.java)

二进制工具类->[ByteUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/ByteUtil.java)

剪切板工具类->[ClipboardUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/ClipboardUtil.java)

密度转换工具类->[DensityUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/DensityUtil.java)

文件操作工具类->[FileUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/FileUtil.java)

IO工具类->[IOUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/IOUtil.java)

Json工具类->[JsonUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/JsonUtil.java)

软键盘工具类->[KeyboardUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/KeyboardUtil.java)

网络相关工具类->[NetworkUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/NetworkUtil.java)

权限请求工具类->[PermissionUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/PermissionUtil.java)

手机设备相关工具类->[PhoneUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/PhoneUtil.java)

常用正则工具类->[RegexUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/RegexUtil.java)

SharedPreferences工具类->[SPUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/SPUtil.java)

常用系统功能工具类->[SystemUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/SystemUtil.java)

时间工具类->[TimeUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/TimeUtil.java)

土司工具类->[ToastUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/ToastUtil.java)

Uri转换工具类->[UriUtil.java](/libplatform/src/main/java/com/sjl/libplatform/util/UriUtil.java)

工具类->[Util.java](/libplatform/src/main/java/com/sjl/libplatform/util/Util.java)







