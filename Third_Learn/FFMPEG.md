## 编译环境环境

```
Mac : macOS High Sierra 10.13.4
FFmpeg : 4.0
NDK : android-ndk-r14b
```

## [下载FFmpeg][0],网上提到可以用brew指令下载，如果只是需要编译出so，可以直接从官网下载
[0]: https://www.ffmpeg.org/

## 修改配置,这是一波基本操作，目的将编译出来的库后缀 .so.xx 改成 .xx.so

```
# 将 configure 文件中的：
SLIBNAME_WITH_MAJOR='$(SLIBNAME).$(LIBMAJOR)' 
LIB_INSTALL_EXTRA_CMD='$$(RANLIB) "$(LIBDIR)/$(LIBNAME)"' 
SLIB_INSTALL_NAME='$(SLIBNAME_WITH_VERSION)' 
SLIB_INSTALL_LINKS='$(SLIBNAME_WITH_MAJOR) $(SLIBNAME)'

#替换为：
SLIBNAME_WITH_MAJOR='$(SLIBPREF)$(FULLNAME)-$(LIBMAJOR)$(SLIBSUF)'
LIB_INSTALL_EXTRA_CMD='$$(RANLIB) "$(LIBDIR)/$(LIBNAME)"'
SLIB_INSTALL_NAME='$(SLIBNAME_WITH_MAJOR)'
SLIB_INSTALL_LINKS='$(SLIBNAME)'
```

## 编译脚本 android_build.sh，将该脚本放在FFmpeg目录下即可
### 注意：脚本的目的是对FFmpeg功能进行裁剪，去除不需要的功能来缩减so大小（编译完整so库大小可达到60M+），因此**脚本内容并不是固定不变的**,可以根据实际情况调整或添加相应的部分
### **懵逼点：因为每个人的编译环境及版本各有差异，当你发现使用某个脚本无法编译出东西或者出现N种迷之问题的时候，不要放弃，可使用其他脚本尝试**
### 以下脚本内容为编译出可操作mp4转换gif的so

```
#!/bin/bash
PLATFORM=/Users/hd/Library/Android/android-ndk-r14b/platforms/android-14/arch-arm/
TOOLCHAIN=/Users/hd/Library/Android/android-ndk-r14b/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64
PREFIX=./android_new_build
function build_one
{
./configure \
--prefix=$PREFIX \
--target-os=android \
--enable-cross-compile \
--cross-prefix=$TOOLCHAIN/bin/arm-linux-androideabi- \
--arch=arm \
--sysroot=$PLATFORM \
--extra-cflags="-I$PLATFORM/usr/include" \
--cc=$TOOLCHAIN/bin/arm-linux-androideabi-gcc \
--nm=$TOOLCHAIN/bin/arm-linux-androideabi-nm \
--enable-shared \
--disable-static \
--disable-doc \
--disable-programs \
--disable-encoders  \
--enable-encoder=mjpeg \
--enable-encoder=gif  \
--disable-decoders   \
--enable-decoder=mjpeg \
--enable-decoder=h264 \
--enable-decoder=mpeg4  \
--disable-muxers  \
--enable-muxer=gif  \
--enable-muxer=mjpeg     \
--enable-muxer=image2     \
--disable-demuxers           \
--enable-demuxer=mpegvideo   \
--enable-demuxer=image2   \
--enable-demuxer=mjpeg   \
--enable-demuxer=h264   \
--enable-demuxer=mov    \
--disable-parsers        \
--enable-parser=mjpeg       \
--enable-parser=mpegvideo   \
--enable-parser=h264    \
--extra-cflags="-I$ASM -isysroot $ISYSROOT -Os -fpic -marm" \
--extra-ldflags="-marm" \
$ADDITIONAL_CONFIGURE_FLAG
    make clean
    make j8
    make install
}
# arm v7vfp
CPU=arm
OPTIMIZE_CFLAGS="-mfloat-abi=softfp -mfpu=vfp -marm -march=$CPU "
ADDI_CFLAGS="-marm"
build_one
```

### 部分参数解释

```
––enable-cross-compile 允许交叉编译，默认不允许
––cross-prefix= 指定编译工具的前缀，默认不指定
––target-os=OS 编译目标操作系统，我们的目标是android系统，使用linux内核，要指定为linux
––arch=ARCH 目标cpu架构
––sysroot=PATH 指定编译过程中需要引用的库，头文件所在的逻辑目录。比如编译器通常会在 /usr/include 和 /usr/lib 中搜索头文件和库，如果指定了sysroot选项则会去$PATH/usr/include 和 $PATH/usr/lib 目录中搜索。
––prefix=PREFIX 在PREFIX目录下安装FFmpeg，默认在/usr/local目录下
––disable-static 不要编译静态库，默认会编译静态库
––enable-shared 编译动态库，默认不会编译动态库
––disable-programs 不编译命令行应用程序，默认会编译命令行应用程序，如：ffmpeg，ffplay等
––disable-doc 不编译文档，默认会编译文档
––disable-encoders 禁用所有编码器，与下面的–enable-encoder=NAME配合可开放指定的编码器
––enable-encoder=NAME 开放指定的编码器，可以有多个
––disable-decoder=NAME 禁用所有解码器，与下面的–enable-decoder=NAME配合可开放指定的编码器
––enable-decoder=NAME 开放指定的解码器，可以有多个
––disable-muxers 禁用所有的复用器，与下面的–enable-muxer=NAME配合可开放指定的复用器
––enable-muxer=NAME 开放指定的复用器，可以有多个
––disable-demuxers 禁用所有的解复用器，与下面的–enable-demuxer=NAME配合可开放指定的解复用器
––enable-demuxer=NAME 开放指定的解复用器，可以有多个
––disable-parsers 禁用所有的解析器，与下面的–enable-parser=NAME配合可开放指定的解析器
––enable-parser=NAME 开放指定的解解析器, 可以有多个
```

## 执行脚本,终端依次输入如下指令

```
cd FFmpeg目录

chmod 777 android_build.sh

./android_build.sh
```

## 经过一段**迷之编译**，最终在指定目录android_new_build下可以查看到结果，至此Mac下FFmpeg so文件已经编译出来了，个中辛酸，无奈，懵逼，可自行体会
