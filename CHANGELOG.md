# Release notes
## 2020-05-31
### Release version 1.0.0

Initial release using World Country Data library version 1.4

In this release, developer can play around the search field and understand the capabilities of the library.

This app is open source and release under MIT Licence. Feel free to fork it and develop it further to suite your case.
Attribution is not mandatory but it will be appreciated.

## 2020-12-14
### version 1.0.1

Initial release using World Country Data library version 1.4

Setting `minifyEnabled true` in `build.gradle`

Adds some proguard rules
```groovy 
-keep class com.blongho.** {*;}
-keep interface com.blongho.**
```