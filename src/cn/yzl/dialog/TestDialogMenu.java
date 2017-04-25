package cn.yzl.dialog;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by YZL on 2017/4/25.
 */
public class TestDialogMenu extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        TestDialog testDialog = new TestDialog();
        testDialog.setVisible(true);
    }
}
