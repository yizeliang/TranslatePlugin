package cn.yzl.setting;

import cn.yzl.baidu.TransApi;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by YZL on 2017/11/9.
 */
public class Setting implements Configurable {

    public static final String KEY_APP_ID = "cn_yzl_bd_translate_app_id";
    public static final String KEY_APP_KEY = "cn_yzl_bd_translate_app_key";

    private JTextField etAppid;
    private JTextField etAppKey;
    private JPanel myPan;
    private JTextField httpApiFanyiBaiduTextField;

    @Nls
    @Override
    public String getDisplayName() {
        return "BaiDuTranslate";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        reset();
        return myPan;
    }

    /**
     * 激活apply按钮
     *
     * @return
     */
    @Override
    public boolean isModified() {
        return true;
    }

    /**
     * 点击【apply】、【OK】时，调用 2017/3/20 14:12
     *
     * @throws ConfigurationException
     */
    @Override
    public void apply() throws ConfigurationException {
        PropertiesComponent.getInstance().setValue(KEY_APP_ID, etAppid.getText());
        PropertiesComponent.getInstance().setValue(KEY_APP_KEY, etAppKey.getText());
        TransApi.getINSTANCE().resetAppId();
    }

    @Override
    public void reset() {
        String id = PropertiesComponent.getInstance().getValue(KEY_APP_ID, "");
        String key = PropertiesComponent.getInstance().getValue(KEY_APP_KEY, "");
        etAppid.setText(id);
        etAppKey.setText(key);
    }

    /**
     * 一定要实现，否则在Android Studio中会报错 2017/4/4 17:40
     */
    @Override
    public void disposeUIResources() {
        reset();
    }
}
