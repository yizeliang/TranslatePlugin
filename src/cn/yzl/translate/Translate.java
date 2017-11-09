package cn.yzl.translate;

import cn.yzl.baidu.TransApi;
import cn.yzl.utils.FileUtil;
import cn.yzl.utils.MessagesCenter;
import com.alibaba.fastjson.JSON;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;

import java.awt.*;

/**
 * 中文自动翻译为英文插件
 * Created by YZL on 2017/4/25.
 */
public class Translate extends AnAction {

    Editor editor;
    private String selectedText;
    @Override
    public void actionPerformed(AnActionEvent event) {
        try {
            editor = event.getData(PlatformDataKeys.EDITOR);
            selectedText = editor.getSelectionModel().getSelectedText();

            if (selectedText == null || selectedText.equals("")) {
                return;
            }
            Modle modle = JSON.parseObject(TransApi.getINSTANCE().getTransResult(selectedText), Modle.class);

            if (!modle.haveData()) {
                MessagesCenter.showErrorMessage("翻译失败", "插件错误");
                return;
            }

            String resultText = modle.trans_result.get(0).dst;
//
//            editor.getDocument().getText().replace(selectedText, resultText);
//
//            FileUtil.getCurFile(event).refresh(false, false);

//            MessagesCenter.showErrorMessage(resultText, "成功");
            if (StringUtil.isContainChinese(selectedText)) {
                resultText = StringUtil.upDataString(resultText);
                changeSelectText(event, resultText);
            } else {
                JBPopupFactory jbPopupFactory = JBPopupFactory.getInstance();
                jbPopupFactory.createHtmlTextBalloonBuilder(modle.getStringResult(),
                        null,
                        new JBColor(new Color(186, 238, 186),
                                new Color(73, 117, 73)),
                        null)
                        .setFadeoutTime(5000)
                        .createBalloon()
                        .show(jbPopupFactory.guessBestPopupLocation(editor),
                                Balloon.Position.below);
            }
        } catch (NullPointerException e) {
            MessagesCenter.showErrorMessage("翻译失败", "插件错误");
        } catch (Exception e1) {
            e1.printStackTrace();
            MessagesCenter.showErrorMessage("翻译失败", "插件错误");
        }
    }

    /**
     * 替换文字
     *
     * @param event
     * @param text
     */
    private void changeSelectText(AnActionEvent event, String text) {
        Editor mEditor = event.getData(PlatformDataKeys.EDITOR);
        Project mProject = event.getData(PlatformDataKeys.PROJECT);
        Document document = mEditor.getDocument();
        SelectionModel selectionModel = mEditor.getSelectionModel();

        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                document.replaceString(start, end, text);
            }
        };
        WriteCommandAction.runWriteCommandAction(mProject, runnable);
        selectionModel.removeSelection();
    }
}
