package cn.yzl.translate;

import org.apache.http.util.TextUtils;

import java.util.List;

/**
 * Created by YZL on 2017/4/25.
 */
public class Modle {

    /**
     * translation : ["test"]
     * basic : {"phonetic":"cè shì","explains":["[试验] test","measurement"]}
     * query : 测试
     * errorCode : 0
     * web : [{"value":["Test","test","TST test"],"key":"测试"},{"value":["Testing Conditions","test condition","Condition"],"key":"测试条件"},{"value":["Integration testing","integration testing","Integrated Testing"],"key":"集成测试"}]
     */

    private BasicBean basic;
    private String query;
    private int errorCode;
    private List<String> translation;
    private List<WebBean> web;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public static class BasicBean {
        /**
         * phonetic : cè shì
         * explains : ["[试验] test","measurement"]
         */

        private String phonetic;
        private List<String> explains;

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            if (TextUtils.isEmpty(phonetic)) {
                return "";
            }
            sb.append("\n" + phonetic + ":\n");

            for (int i = 0; i < explains.size(); i++) {
                sb.append(explains.get(i) + "\n");
            }
            return sb.toString();
        }
    }

    public static class WebBean {
        /**
         * value : ["Test","test","TST test"]
         * key : 测试
         */

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }


        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();

            sb.append(key + "\n");

            for (int i = 0; i < value.size(); i++) {
                sb.append(value.get(i));

                if (i != value.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (translation == null) {
            return "翻译失败";
        }
        //第一解释
        for (int i = 0; i < translation.size(); i++) {
            sb.append(translation.get(i));
            if (i != translation.size() - 1) {
                sb.append(",");
            }
        }

        if (getBasic() != null) {
            sb.append(getBasic().toString());
        }
        if (getWeb() != null) {

            for (int i = 0; i < getWeb().size(); i++) {
                if (i == 0) {
                    sb.append("\n\n网络释义");
                }
                sb.append("\n"+(i+1) + "." + getWeb().get(i).toString());
            }
        }
        return sb.toString();
    }
}
