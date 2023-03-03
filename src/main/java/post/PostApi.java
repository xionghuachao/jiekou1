package post;

import config.GetWai;
import config.Proutil1;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostApi {
    Proutil1 proutil1=new Proutil1("application.properties");
    static Logger logger=Logger.getLogger(PostApi.class);
    GetWai getWai=new GetWai();
    Sheet sheet= getWai.getc(0,"D:\\test\\data1.xlsx");

@Test
    public void testPostMethod() {
    PropertyConfigurator.configure("log4j.properties");
    //获取第二行工作簿
    Row row=sheet.getRow(1);
        String url=row.getCell(6).toString();
    PostMethod postMethod = null;
    postMethod = new PostMethod(url) ;//添加请求头数据
    postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;
    //参数设置，需要注意的就是里边不能传NULL，要传空字符串
    NameValuePair[] data = {
            new NameValuePair("name",row.getCell(10).toString()),
            new NameValuePair("sex",row.getCell(9).toString())
    };

    postMethod.setRequestBody(data);

    org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
    String result = null;
    try {
        int response = httpClient.executeMethod(postMethod); // 执行POST方法
        result = postMethod.getResponseBodyAsString();
        System.out.println(result);
        Assert.assertEquals(result,row.getCell(11).toString());
    } catch (IOException e) {
        e.printStackTrace();
    }


        }

    }