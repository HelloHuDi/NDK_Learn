package com.hd.third_learn;

/**
 * Created by hd on 2018/6/13 .
 */
public class TransferJNI {

    static {
        //System.loadLibrary("avcodec");
        //System.loadLibrary("avdevice");
        //System.loadLibrary("avfilter");
        //System.loadLibrary("avformat");
        //System.loadLibrary("avutil");
        //System.loadLibrary("swresample");
        //System.loadLibrary("swscale");
        System.loadLibrary("transfer");
    }

    public native int transfer(String[]commands);
}
