package cn.yzl.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by YZL on 2017/4/25.
 */
public class FileUtil {
    /**
     * 刷新目录结构
     *
     * @param e
     * @param error 是否带有成功提示
     */
    public static void refreshFiles(AnActionEvent e, boolean error) {
        getCurFile(e).getParent().refresh(true, true, new Runnable() {
            @Override
            public void run() {
                if (error) {
                    MessagesCenter.showMessage("创建成功", "创建成功");
                }
            }
        });
    }

    /**
     * 获取当前File对象
     *
     * @param e
     * @return
     */
    public static VirtualFile getCurFile(AnActionEvent e) {
        return DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
    }

    /**
     * 获取文件扩展名
     *
     * @return
     */
    public static String getFileExtension(AnActionEvent e) {
        VirtualFile file = getCurFile(e);
        return file == null ? null : file.getExtension();
    }
}
