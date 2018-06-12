package com.hd.ndklearn;

/**
 * Created by hd on 2018/6/11 .
 */
public class FirstNdkClass {
    static {
        System.loadLibrary("firstndk");
    }

    public native String getCLanguageString();

    public native int addAandB(int a,int b);

    public native float mulAandB(float a,float b);

}
