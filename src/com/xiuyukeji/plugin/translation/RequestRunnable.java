package com.xiuyukeji.plugin.translation;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.popup.PopupFactoryImpl;
import com.xiuyukeji.plugin.translation.translator.impl.GoogleTranslator;
import com.xiuyukeji.plugin.translation.translator.trans.Language;
import com.xiuyukeji.plugin.translation.translator.ui.ResultPicker;
import com.xiuyukeji.plugin.translation.translator.utils.StringUtil;

/**
 * 请求
 *
 * @author Created by jz on 2017/10/24 14:45
 */
class RequestRunnable implements Runnable {
    private GoogleTranslator mGoogleTranslator;
    private Editor mEditor;
    private String mQuery;

    RequestRunnable(GoogleTranslator translator, Editor editor, String query) {
        this.mEditor = editor;
        this.mQuery = query;
        this.mGoogleTranslator = translator;
    }

    @Override
    public void run() {
        String text;
        boolean flag = isChinese(mQuery);
        if (flag) {
            text = mGoogleTranslator.translation(Language.ZH, Language.EN, mQuery);
        } else {
            text = mGoogleTranslator.translation(Language.EN, Language.ZH, mQuery);
        }
        if (text == null) {
            showPopupBalloon("翻译出错！");
        }
        if (flag) {
            ResultPicker dialog = new ResultPicker(mEditor,text);
            dialog.pack();
            dialog.setVisible(true);
        } else {
            showPopupBalloon("翻译：" + text);
        }
    }

    private void chageText(String pickerString) {
        try {
            StringUtil.changeSelectText(mEditor, pickerString);
        } catch (Exception e) {

        }
    }

    private boolean isChinese(String strName) {
        char[] cs = strName.toCharArray();
        for (char c : cs) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    private void showPopupBalloon(final String result) {
        ApplicationManager.getApplication().invokeLater(() -> {
            mEditor.putUserData(PopupFactoryImpl.ANCHOR_POPUP_POSITION, null);//解决因为TranslationPlugin而导致的泡泡显示错位问题
            JBPopupFactory factory = JBPopupFactory.getInstance();
            factory.createHtmlTextBalloonBuilder(result, null, new JBColor(Gray._242, Gray._0), null)
                    .createBalloon()
                    .show(factory.guessBestPopupLocation(mEditor), Balloon.Position.below);
        });
    }

}
