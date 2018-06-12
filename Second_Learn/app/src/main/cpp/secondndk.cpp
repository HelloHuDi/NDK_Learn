//
// Created by HUDI on 2018/6/12.
//

#include <jni.h>
#include <string>
#include "com_hd_ndklearn_FirstNdkClass.h"
#include "android_log.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_hd_second_1learn_SecondNdkClass_getCMakeString(JNIEnv *env, jobject instance) {
    jstring firStr = Java_com_hd_ndklearn_FirstNdkClass_getCLanguageString(env, instance);
    LOGD("我要打印一波");
    return firStr;
}
