package cn.yzl.translate;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by YZL on 2017/4/25.
 */
public class TranslateUtil {

    public static final String url =
            "http://fanyi.youdao.com/openapi.do?keyfrom=Translateyizeliang&key=255126412&type=data&doctype=json&version=1.1&q=";


    /**
     * 翻译为英文
     *
     * @param en
     * @return
     */
    public static Modle translateToEn(String en) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url + en);
        try {
            CloseableHttpResponse execute = client.execute(get);
            if (execute.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(execute.getEntity(), "utf-8");

                Modle modle = JSON.parseObject(result, Modle.class);
                System.out.println(result);
                return modle;
            } else {
                return null;
            }
        } catch (Exception e) {

        }
        return null;
    }


}
