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

JNIEXPORT jint JNICALL Java_com_hd_ndklearn_FirstNdkClass_addAandB
              (JNIEnv *env, jobject obj, jint a, jint b){
              return (a+b);
              }

JNIEXPORT jfloat JNICALL Java_com_hd_ndklearn_FirstNdkClass_mulAandB
                (JNIEnv *env, jobject obj, jfloat af, jfloat bf){
return (af+bf);
                }
