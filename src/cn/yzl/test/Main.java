package cn.yzl.test;

import cn.yzl.baidu.TransApi;
import cn.yzl.translate.Modle;
import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by YZL on 2017/7/29.
 */
public class Main {
    public static void main(String[] args) {
        Modle modle = JSON.parseObject(TransApi.getINSTANCE().getTransResult("中国"),Modle.class);
        try {
            System.out.println(modle.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
