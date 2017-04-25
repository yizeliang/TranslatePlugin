package cn.yzl.mvp;

import cn.yzl.exception.FileExsitException;
import cn.yzl.utils.MessagesCenter;
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


    public static void createViewInterface(File pathF, String packageName, String className) throws IOException, FileExsitException {
        File targetFile = new File(pathF + File.separator + className + "View.java");
        if (targetFile.exists()) {
            MessagesCenter.showErrorMessage(targetFile.getPath() + "\n文件已存在", "错误");
            throw new FileExsitException(targetFile.getPath() + "\n文件已存在");
        }

        FileWriter fileWriter = new FileWriter(targetFile);
        StringBuffer sb = new StringBuffer();
        sb.append("package " + packageName + ";\n");
        sb.append("import com.qianfan365.android.ebilling.hd.base.mvp.BaseView;\n\n");
        sb.append("public interface " + className + "View extends BaseView" + " {" + "\n");
        sb.append("}");
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void createPresenter(File pathF, String packageName, String className) throws IOException, FileExsitException {
        File targetFile = new File(pathF + File.separator + className + "Presenter.java");

        if (targetFile.exists()) {
            MessagesCenter.showErrorMessage(targetFile.getPath() + "\n文件已存在", "错误");
            throw new FileExsitException(targetFile.getPath() + "\n文件已存在");
        }

        FileWriter fileWriter = new FileWriter(pathF + File.separator + className + "Presenter.java");
        StringBuffer sb = new StringBuffer();
        sb.append("package " + packageName + ";\n\n");

        sb.append("import com.qianfan365.android.ebilling.hd.base.mvp.BasePresenter;\n\n");


        sb.append("public class " + className + "Presenter extends BasePresenter<" + className + "View>" + " {" + "\n\n");

        sb.append("public ###Presenter(###View mView) {\nsuper(mView);\n}\n\n".replace("###", className));

        sb.append("}");
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
