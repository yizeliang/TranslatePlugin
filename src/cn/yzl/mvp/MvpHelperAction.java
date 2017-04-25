package cn.yzl.mvp;

import cn.yzl.exception.FileExsitException;
import cn.yzl.utils.MessagesCenter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiJavaFile;

import java.io.File;

import static cn.yzl.utils.FileUtil.refreshFiles;

/**
 * Created by YZL on 2017/4/24.
 */
public class MvpHelperAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        if (event == null) {
            return;
        }

        try {
            PsiJavaFile javaFile = (PsiJavaFile) event.getData(CommonDataKeys.PSI_FILE);

            String className = javaFile.getName();
            className = className.replace(".java", "");
            if (className.contains("Activity")) {
                className = className.replace("Activity", "");
            } else if (className.contains(".Fragment")) {
                className = className.replace("Fragment", "");
            }

            //F:\a\cn\yzl\eee.java
            String path = ClassCreateHelper.getCurrentPath(event, className + ".java").replace(File.separator + className + ".java", "");

            File pathF = new File(path);

            ClassCreateHelper.createViewInterface(pathF, javaFile.getPackageName(), className);

            ClassCreateHelper.createPresenter(pathF, javaFile.getPackageName(), className);

            refreshFiles(event, true);
        } catch (FileExsitException fileExsitException) {
            refreshFiles(event, false);
        } catch (Exception exception) {
            exception.printStackTrace();
            MessagesCenter.showMessage("请确认当前文件是不是java文件", "创建失败");
            return;
        }
    }

//    /**
//     * 只有是java文件的时候才显示
//     *
//     * @param event
//     */
//    @Override
//    public void update(AnActionEvent event) {
//        //在Action显示之前,根据选中文件扩展名判定是否显示此Action
//        String extension = getFileExtension(event);
//        this.getTemplatePresentation().setEnabled(extension != null && "java".equals(extension));
//    }
//
//    @Override
//    public void beforeActionPerformedUpdate(@NotNull AnActionEvent e) {
//        super.beforeActionPerformedUpdate(e);
//        String extension = getFileExtension(e);
//        this.getTemplatePresentation().setEnabled(extension != null && "java".equals(extension));
//    }
}
