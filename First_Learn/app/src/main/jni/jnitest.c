//
// Created by HUDI on 2018/6/11.
//
#include <string.h>
#include <jni.h>
#include "com_hd_ndklearn_FirstNdkClass.h"

JNIEXPORT jstring JNICALL Java_com_hd_ndklearn_FirstNdkClass_getCLanguageString
            (JNIEnv *env, jobject obj){
            return (*env)->NewStringUTF(env,"好绝望");
}