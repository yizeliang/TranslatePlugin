package cn.yzl.translate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YZL on 2017/4/25.
 */
public class StringUtil {
    /**
     * 是否包含中文
     *
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

    /**
     * 转换一个句子 为驼峰式
     *
     * @return
     */
    public static String upDataString(String string) {
        if (string.contains(" ")) {
            String[] split = string.split(" ");
            String result = "";
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    result += lowDataFirstChar(split[i]);
                } else {
                    result += upDataFirstChar(split[i]);
                }
            }
            return result;
        } else {
            return lowDataFirstChar(string);
        }
    }

    /**
     * 将第一个字母转为小写
     *
     * @param word
     * @return
     */
    public static String lowDataFirstChar(String word) {
        String start = word.substring(0, 1);
        String end = word.substring(1, word.length());
        word = start.toLowerCase() + end;
        return word;
    }

    /**
     * 将第一个字母转为大写
     *
     * @param word
     * @return
     */
    public static String upDataFirstChar(String word) {
        String start = word.substring(0, 1);
        String end = word.substring(1, word.length());
        word = start.toUpperCase() + end;
        return word;
    }
}
