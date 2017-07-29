package cn.yzl.test;

import cn.yzl.translate.Modle;
import cn.yzl.translate.TranslateUtil;

/**
 * Created by YZL on 2017/7/29.
 */
public class Main {
    public static void main(String[] args) {
        Modle zh = TranslateUtil.translateToEn("中文");
        zh.getWeb();
    }
}
