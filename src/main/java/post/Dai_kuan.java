package post;

import config.Proutil1;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Dai_kuan {
      Proutil1 proutil1=new Proutil1("parameter.properties");
    static Logger logger=Logger.getLogger(GetCanWaibu.class);
    String url="http://apis.juhe.cn/fapig/loanCalc/loan";
    //输入正确金额和年份
    @Test

    public void dai_success(){
        PropertyConfigurator.configure("log4j.properties");
        for(int i=5;i<=30;i+=5){
           PostMethod postMethod = new PostMethod(url);
            //httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;

            //dff98220f294f4c7c4f92f2e2cacf644
            NameValuePair[] data = {
                    new NameValuePair("key","dff98220f294f4c7c4f92f2e2cacf644"),
                    new NameValuePair("money",proutil1.GetPro("money")),
                    new NameValuePair("year",Integer.toString(i))
            };
            System.out.println(i);
            postMethod.setRequestBody(data);
           HttpClient httpClient=new HttpClient();
            try {
                String result;
               int code=httpClient.executeMethod(postMethod);
               Assert.assertEquals(code,200);
result=postMethod.getResponseBodyAsString();
              JSONObject jsonObject=new JSONObject(result);
         String reason=jsonObject.getString("reason");
         int error_code=jsonObject.getInt("error_code");
         Assert.assertEquals(reason,"success");
         Assert.assertEquals(error_code,0);
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        }
        //年份为空
    @Test
public void yearnull(){
    PostMethod postMethod = new PostMethod(url);
    //httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
    postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;

    //dff98220f294f4c7c4f92f2e2cacf644
    NameValuePair[] data = {
            new NameValuePair("key","dff98220f294f4c7c4f92f2e2cacf644"),
            new NameValuePair("money",proutil1.GetPro("money")),
            new NameValuePair("year","")
    };

    postMethod.setRequestBody(data);
    HttpClient httpClient=new HttpClient();
    try {
        String result;
        int code=httpClient.executeMethod(postMethod);
        result=postMethod.getResponseBodyAsString();
        JSONObject jsonObject=new JSONObject(result);
       String reason=jsonObject.getString("reason");
       int error_code=jsonObject.getInt("error_code");
       Assert.assertEquals(reason,"请输入正确的年限");
       Assert.assertEquals(error_code,257101);
    } catch (IOException e) {
        e.printStackTrace();
    }

}
//贷款金额为空
    @Test
public void moneynull(){
    PostMethod postMethod = new PostMethod(url);
    //httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
    postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;

    //dff98220f294f4c7c4f92f2e2cacf644
    NameValuePair[] data = {
            new NameValuePair("key","dff98220f294f4c7c4f92f2e2cacf644"),
            new NameValuePair("money",""),
            new NameValuePair("year","25")
    };

    postMethod.setRequestBody(data);
    HttpClient httpClient=new HttpClient();
    try {
        String result;
        int code=httpClient.executeMethod(postMethod);
        result=postMethod.getResponseBodyAsString();
        JSONObject jsonObject=new JSONObject(result);
        String reason=jsonObject.getString("reason");
        int error_code=jsonObject.getInt("error_code");
        Assert.assertEquals(reason,"请输入正确的金额");
        Assert.assertEquals(error_code,257101);
    } catch (IOException e) {
        e.printStackTrace();
    }


}
@Test
//年份填写不正确-26
public void yearWrong(){
    PostMethod postMethod = new PostMethod(url);
    //httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
    postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;

    //dff98220f294f4c7c4f92f2e2cacf644
    NameValuePair[] data = {
            new NameValuePair("key","dff98220f294f4c7c4f92f2e2cacf644"),
            new NameValuePair("money",proutil1.GetPro("money")),
            new NameValuePair("year","26")
    };

    postMethod.setRequestBody(data);
    HttpClient httpClient=new HttpClient();
    try {
        String result;
        int code=httpClient.executeMethod(postMethod);
        result=postMethod.getResponseBodyAsString();
        JSONObject jsonObject=new JSONObject(result);
        String reason=jsonObject.getString("reason");
        int error_code=jsonObject.getInt("error_code");
        Assert.assertEquals(reason,"请输入正确的年限");
        Assert.assertEquals(error_code,257101);
    } catch (IOException e) {
        e.printStackTrace();
    }


}
@Test
    //贷款金额填写不正确-550
    public void moneyWrong(){
        PostMethod postMethod = new PostMethod(url);
        //httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;

        //dff98220f294f4c7c4f92f2e2cacf644
        NameValuePair[] data = {
                new NameValuePair("key","dff98220f294f4c7c4f92f2e2cacf644"),
                new NameValuePair("money","550"),
                new NameValuePair("year","25")
        };

        postMethod.setRequestBody(data);
        HttpClient httpClient=new HttpClient();
        try {
            String result;
            int code=httpClient.executeMethod(postMethod);
            result=postMethod.getResponseBodyAsString();
            JSONObject jsonObject=new JSONObject(result);
            String reason=jsonObject.getString("reason");
            int error_code=jsonObject.getInt("error_code");
            Assert.assertEquals(reason,"请输入正确的金额");
            Assert.assertEquals(error_code,257101);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //填写-非必填：贷款利率
    public void active(){
        PropertyConfigurator.configure("log4j.properties");
        for(int i=5;i<=30;i+=5){
            PostMethod postMethod = new PostMethod(url);
            //httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;

            //dff98220f294f4c7c4f92f2e2cacf644
            NameValuePair[] data = {
                    new NameValuePair("key","dff98220f294f4c7c4f92f2e2cacf644"),
                    new NameValuePair("money",proutil1.GetPro("money")),
                    new NameValuePair("year",Integer.toString(i)),
                    new NameValuePair("active",proutil1.GetPro("active"))
            };
            System.out.println(i);
            postMethod.setRequestBody(data);
            HttpClient httpClient=new HttpClient();
            try {
                String result;
                int code=httpClient.executeMethod(postMethod);
                Assert.assertEquals(code,200);
                result=postMethod.getResponseBodyAsString();
                JSONObject jsonObject=new JSONObject(result);
                String reason=jsonObject.getString("reason");
                int error_code=jsonObject.getInt("error_code");
                Assert.assertEquals(reason,"success");
                Assert.assertEquals(error_code,0);
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }


    }}
    }

