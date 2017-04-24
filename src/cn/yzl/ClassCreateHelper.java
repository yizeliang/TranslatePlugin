package cn.yzl;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 创建class的类
 * Created by wing on 2016/7/23.
 */
public class ClassCreateHelper {

    /**
     * 获取一个文件的路径
     *
     * @param e
     * @param classFullName ex:nihao.java
     * @return
     */
    public static String getCurrentPath(AnActionEvent e, String classFullName) {

        VirtualFile currentFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());

        String path = currentFile.getPath().replace(classFullName, "");
        return path;
    }


    public static void createViewInterface(File pathF, String packageName, String className) throws IOException {

        if (new File(pathF + File.separator + className + "View.java").exists()) {
            MessagesCenter.showErrorMessage("文件已存在", "错误");
            return;
        }

        FileWriter fileWriter = new FileWriter(pathF + File.separator + className + "Presenter.java");
        StringBuffer sb = new StringBuffer();
        sb.append("package " + packageName + ";\n");

        sb.append("public interface " + className + "View extends BaseView" + " {" + "\n");
        sb.append("}");
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void createPresenter(File pathF, String packageName, String className) throws IOException {

        if (new File(pathF + File.separator + className + "Presenter.java").exists()) {
            MessagesCenter.showErrorMessage("文件已存在", "错误");
            return;
        }

        FileWriter fileWriter = new FileWriter(pathF + File.separator + className + "Presenter.java");
        StringBuffer sb = new StringBuffer();
        sb.append("package " + packageName + ";\n");
        sb.append("public class " + className + "Presenter" + " {" + "\n");
        sb.append("}");
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
