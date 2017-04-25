package cn.yzl.translate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YZL on 2017/4/25.
 */
public class StringUtil {
    /**
     * 是否包含中文
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
