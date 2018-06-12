LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

TARGET_PLATFORM := android-14
LOCAL_MODULE := firstndk
LOCAL_SRC_FILES := jnitest.c
LOCAL_LDLIBS    := -llog

include $(BUILD_SHARED_LIBRARY)