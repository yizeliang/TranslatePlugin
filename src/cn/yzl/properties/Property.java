package cn.yzl.properties;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by YZL on 2017/4/25.
 */
public class Property implements Configurable {
    private JTextArea textArea1;
    private JPanel panel1;

    @Nls
    @Override
    public String getDisplayName() {
        return "测试设置";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        //帮助信息
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {

        //返回form中的跟布局就好
        return panel1;
    }

    @Override
    public boolean isModified() {
        //是否修改了?
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
//        应用设置

    }

    @Override
    public void reset() {
//        重置
    }
}
