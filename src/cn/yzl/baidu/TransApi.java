package cn.yzl.baidu;

import cn.yzl.setting.Setting;
import cn.yzl.translate.StringUtil;
import cn.yzl.utils.MessagesCenter;
import com.intellij.ide.util.PropertiesComponent;
import org.apache.http.util.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private static String appid;
    private static String securityKey;

    public static TransApi INSTANCE;

    public static TransApi getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (TransApi.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TransApi();
                }
            }
        }
        return INSTANCE;
    }

    public void resetAppId() {
        appid = PropertiesComponent.getInstance().getValue(Setting.KEY_APP_ID);
        securityKey = PropertiesComponent.getInstance().getValue(Setting.KEY_APP_KEY);
    }

    /**
     * @param query 文字
     * @return
     */
    public String getTransResult(String query) {
        if (TextUtils.isEmpty(appid)
                || TextUtils.isEmpty(securityKey)) {
            MessagesCenter.showErrorMessage("请先设置百度翻译appid和appkey", "插件错误");
            return null;
        }
        if (StringUtil.isContainChinese(query)) {
            return getTransResult(query, "zh", "en");
        } else {
            return getTransResult(query, "en", "zh");
        }
    }

    /**
     * @param query 文字
     * @param from  语言
     * @param to    到什么语言
     * @return
     */
    private String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
