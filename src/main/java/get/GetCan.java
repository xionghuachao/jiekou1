package get;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetCan {
    public  static  String url="http://apis.juhe.cn/sxpd/query";
    public  static  String key="5baeccd771e0624fae38bd038ca6e001";
    static Logger logger=Logger.getLogger(GetCan.class);
    public  static  String men="蛇";
    public  static  String women="羊";

    @Test
    public void  xingzuopeiduiSuccess(){
        PropertyConfigurator.configure("log4j.properties");
        URI uri = null;
        logger.debug("传入参数");
        try {
            uri = new URIBuilder(url)

                    .setParameter("key",key)
                    .setParameter("men",men)
                    .setParameter("women",women).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet get=new HttpGet(uri);
        logger.debug("设置请求头");
        get.setHeader("content-type","application/x-www-form-urlencoded");
        logger.debug("实例化httpClient对象");
        DefaultHttpClient httpClient=new DefaultHttpClient();

        try {
            HttpResponse response= httpClient.execute(get);
            String result= EntityUtils.toString(response.getEntity());
            System.out.println(result);
            JSONObject jsonObject=new JSONObject(result);
           String reason=jsonObject.getString("reason");
           int error_code= (int) jsonObject.getInt("error_code");
            logger.debug("断言reason=Seccess");
            Assert.assertEquals(reason,"success");
            logger.debug("断言error_code=0");
            Assert.assertEquals(error_code,0);
            System.out.println(jsonObject.getJSONObject("result"));
            //第一种方法
//           String actual=jsonObject.getJSONObject("result").toString();
//           JSONObject expect=new JSONObject();
//            expect.put("data","良缘佳偶，她那丰富的幻想力，能够把你深深吸引住。");
//            expect.put("men","蛇");
//            expect.put("women","羊");
//            String expect_1=expect.toString();
//            logger.debug("断言result");
//            Assert.assertEquals(actual,expect_1);
//第二种方法
         String Data=jsonObject.getJSONObject("result").getString("data");
         String men=jsonObject.getJSONObject("result").getString("men");
            String women=jsonObject.getJSONObject("result").getString("women");
          Assert.assertEquals(Data,"良缘佳偶，她那丰富的幻想力，能够把你深深吸引住。");
          Assert.assertEquals(men,"蛇");
            Assert.assertEquals(women,"羊");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
