package cn.yzl.translate;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by YZL on 2017/4/25.
 */
public class Modle {
    /**
     * from : zh
     * to : en
     * trans_result : [{"src":"中国","dst":"China"}]
     */

    public String from;
    public String to;
    public List<TransResultBean> trans_result;

    public static class TransResultBean {
        /**
         * src : 中国
         * dst : China
         */
        public String src;
        public String dst;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getStringResult() {
        StringBuffer sb = new StringBuffer();
        for (TransResultBean item : trans_result) {
            sb.append("\n" + item.dst);
        }
        return sb.toString();
    }

    public boolean haveData() {
        return trans_result != null
                && trans_result.size() > 0
                && trans_result.get(0) != null;
    }
}
