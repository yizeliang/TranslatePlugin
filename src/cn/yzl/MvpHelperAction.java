package cn.yzl;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiJavaFile;

import java.io.File;

/**
 * Created by YZL on 2017/4/24.
 */
public class MvpHelperAction extends AnAction {

    private Editor _editor;
    private ClassModel _classModel;
    private String _content;

    @Override
    public void actionPerformed(AnActionEvent e) {
        if (e == null) {
            return;
        }

//        init(e);
//        getClassModel();

        try {
            PsiJavaFile javaFile = (PsiJavaFile) e.getData(CommonDataKeys.PSI_FILE);
//            MessagesCenter.showMessage(javaFile.getPackageName() + "\n" + javaFile.getName() + "\n" + javaFile.getText(), "title");

            String className = javaFile.getName();
            className = className.replace(".java", "");
            if (className.contains("Activity")) {
                className = className.replace("Activity", "");
            } else if (className.contains(".Fragment")) {
                className = className.replace("Fragment", "");
            }

            //F:\a\cn\yzl\eee.java
            String path = ClassCreateHelper.getCurrentPath(e, className + ".java").replace(File.separator + className + ".java", "");
            File pathF = new File(path);

            ClassCreateHelper.createPresenter(pathF,javaFile.getPackageName(),className);

            MessagesCenter.showMessage("请刷新目录", "创建成功");
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }

    }


    private void getClassModel() {
        _content = _editor.getDocument().getText();
        MessagesCenter.showMessage(_content, "内容");
        System.out.print(_content);
        String[] words = _content.split(" ");
        for (String word : words) {
            if (word.contains("Contract")) {
                String className = word.substring(0, word.indexOf("Contract"));
                _classModel.set_className(className);
                _classModel.set_classFullName(word);
                MessagesCenter.showDebugMessage(className, "class name");
//                mode = MODE_CONTRACT;
            } else if (word.contains("Presenter")) {
                String className = word.substring(0, word.indexOf("Presenter"));
                _classModel.set_className(className);
                _classModel.set_classFullName(word);
//                mode = MODE_PRESENTER;
                MessagesCenter.showDebugMessage(className, "class name");
            }
        }
        if (null == _classModel.get_className()) {
            MessagesCenter.showErrorMessage("Create failed ,Can't found 'Contract' or 'Presenter' in your class name,your class name must contain 'Contract' or 'Presenter'", "error");
//            canCreate = false;
        }
    }

    private void init(AnActionEvent e) {
        _editor = e.getData(PlatformDataKeys.EDITOR);
        _classModel = new ClassModel();
    }
}
