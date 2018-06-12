package com.hd.second_learn;

/**
 * Created by hd on 2018/6/12 .
 */
public class SecondNdkClass {
    static {
        System.loadLibrary("secondndk");
    }

    public native String getCMakeString();
}
