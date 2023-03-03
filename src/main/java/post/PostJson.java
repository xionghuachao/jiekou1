package post;

import config.Proutil1;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostJson {
    Proutil1 proutil1=new Proutil1("application.properties");
    static Logger logger=Logger.getLogger(PostApi.class);
   @Test
    public void testPostMethod(){
       PropertyConfigurator.configure("log4j.properties");
        String url1="http://localhost:8899/postdemo";
        HttpPost httpPost=new HttpPost(url1);

        //声明一个client对象
        DefaultHttpClient httpClient=new DefaultHttpClient();
        try {

            //添加参数
            JSONObject param=new JSONObject();
            param.put("name","haha");
            param.put("sex","man");
            //设置请求头信息 设置header
            httpPost.setHeader("content-type","application/json;utf-8");
            //将参数信息添加到方法中
            StringEntity entity=new StringEntity(param.toString());
            httpPost.setEntity(entity);
            //声明一个对象来进行响应结果的存储
            String result;
            //设置cookie信息

            //执行post方法
            HttpResponse httpResponse=httpClient.execute(httpPost);
            //获取响应结果
            result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("result--------");
            System.out.println(result);
          Assert.assertEquals(result,"这是我的第一个post请求");







        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
