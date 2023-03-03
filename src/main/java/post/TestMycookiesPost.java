package post;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestMycookiesPost {
    private String url;
    private ResourceBundle bundle;
    private String getCookie;
    private CookieStore store;
   // @BeforeTest
    public void beforetest(){



    }
   // @Test
    public void testGetCookies(){

        String result;
        getCookie=bundle.getString("getCookies.uri");
        String testUrl=this.url+this.getCookie;
        HttpGet get=new HttpGet(testUrl);
        DefaultHttpClient httpClient=new DefaultHttpClient();

        try {
            HttpResponse response=httpClient.execute(get);
            result= EntityUtils.toString(response.getEntity(),"utf-8");

            System.out.println(result);
            //获取cookies信息
            store=httpClient.getCookieStore();
            List<Cookie> list=store.getCookies();
            for(Cookie cookie:list){
                String name=cookie.getName();
                String value=cookie.getValue();
                System.out.println(name+value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //@Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod(){
      String uri1=bundle.getString("test.post.uri");
      String url1=this.url+uri1;
      HttpPost httpPost=new HttpPost(url1);
      //声明一个client对象
      DefaultHttpClient httpClient=new DefaultHttpClient();
        try {

           //添加参数
            JSONObject param=new JSONObject();
            param.put("name","huhansan");
            param.put("age",18);
            //设置请求头信息 设置header
            httpPost.setHeader("content-type","application/json");
            //将参数信息添加到方法中
            StringEntity entity=new StringEntity(param.toString(),"utf-8");
            httpPost.setEntity(entity);
            //声明一个对象来进行响应结果的存储
            String result;
            //设置cookie信息
            httpClient.setCookieStore(this.store);
            //执行post方法
            HttpResponse httpResponse=httpClient.execute(httpPost);
            //获取响应结果
            result=EntityUtils.toString(httpResponse.getEntity());
            System.out.println(result);
            //处理结果，判断返回结果是否符合预期
            //将返回的响应结果字符串转化为json对象
            JSONObject resultJson=new JSONObject(result);
            //具体的判断返回结果的值
            //获取到结果值
           String success=(String) resultJson.get("huhansan");
          int code=(int) resultJson.get("status");
           //具体获得的结果值
            System.out.println(success);
            System.out.println(code);
            Assert.assertEquals(success,"success");
            Assert.assertEquals(code,1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testPost(){

        //HttpPost httpPost=new HttpPost("https://api.uomg.com/api/icp");
        HttpGet httpGet = new HttpGet("https://api.uomg.com/api/icp?domain=www.baidu.com");

        //声明一个client对象
        HttpClient httpClient1=new DefaultHttpClient();
        //将参数信息添加到方法中
      httpGet.setHeader("content-type","application/json");

            //声明一个对象来进行响应结果的存储
            String result;

            //执行post方法
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient1.execute(httpGet);


        //获取响应结果
            result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            System.out.println(result);
            JSONObject resultJson=new JSONObject(result);
            //具体的判断返回结果的值
            //获取到结果值
            String domain=(String) resultJson.get("domain");
            int code=(int) resultJson.get("code");
            String icp=(String)resultJson.get("icp");
            //具体获得的结果值
            System.out.println(domain);
            System.out.println(code);
            System.out.println(icp);
            Assert.assertEquals(domain,"www.baidu.com");
            Assert.assertEquals(code,1);
            Assert.assertEquals(icp,"未备案");


            //处理结果，判断返回结果是否符合预期
            //将返回的响应结果字符串转化为json对象
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
