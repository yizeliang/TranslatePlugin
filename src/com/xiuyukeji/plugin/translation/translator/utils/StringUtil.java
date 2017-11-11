package com.xiuyukeji.plugin.translation.translator.utils;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YZL on 2017/4/25.
 */
public class StringUtil {

    /**
     * 替换文字
     *
     * @param text
     */
    public static void changeSelectText(Editor editor, String text) {
        if (editor == null ||
                TextUtils.isEmpty(text)) {
            return;
        }
        Document document = editor.getDocument();
        SelectionModel selectionModel = editor.getSelectionModel();

        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//            }
//        };
        text = text.replaceAll("\\r\\n", "");
        text = text.replaceAll("\\n", "");
        document.replaceString(start, end, text);
//        selectionModel.removeSelection();
    }

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
     * @param firstUpperCase 第一个字母是否大写,大写是针对类名
     * @return
     */
    public static String upDataString(String string, boolean firstUpperCase) {
        if (string.contains(" ")) {
            String[] split = string.split(" ");
            String result = "";
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    if (firstUpperCase) {
                        result += upDataFirstChar(split[i]);
                    } else {
                        result += lowDataFirstChar(split[i]);
                    }
                } else {
                    result += upDataFirstChar(split[i]);
                }
            }
            return result;
        } else {
            if (firstUpperCase) {
                return upDataFirstChar(string);
            } else {
                return lowDataFirstChar(string);
            }
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

    /**
     * 针对英文
     * 将一个翻译结果组装成一个数据模型
     *
     * @param model
     * @param result
     */
    public static void setResultType(DefaultListModel model, String result) {
        if (model == null) {
            return;
        }
        //驼峰成员变量
        model.addElement(upDataString(result, false));
        model.addElement(upDataString(result, true));
        //全局变量
        model.addElement(result.replaceAll(" ", "_").toUpperCase());
        //原句 针对注释
        //这里不再处理任何大小写
        model.addElement(result);
        //扩展
        //KEY,RESULT
        model.addElement("KEY_" + result.replaceAll(" ", "_").toUpperCase());
        model.addElement("RESULT_" + result.replaceAll(" ", "_").toUpperCase());
        model.addElement("TYPE_" + result.replaceAll(" ", "_").toUpperCase());
    }
}
