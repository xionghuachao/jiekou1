package post;


import config.Proutil1;
import get.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.DatabaseUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class GetCanWaibu {

    Proutil1 proutil1=new Proutil1("application.properties");
    static Logger logger=Logger.getLogger(GetCanWaibu.class);

@Test
//正确输入数据
    public void xingzuopeiduiSuccess() {
    PropertyConfigurator.configure("log4j.properties");

    String url=proutil1.GetPro("test.url");
    System.out.println(url);
    URI uri = null;
                    logger.debug("传入参数");
                    Proutil1 param=new Proutil1("parameter.properties");
            System.out.println(param.GetPro("key"));
            System.out.println(param.GetPro("men"));
            System.out.println(param.GetPro("women"));
                    try {

                        uri = new URIBuilder(url)

                                .setParameter("key",param.GetPro("key"))
                                .setParameter("men",param.GetPro("men"))
                                .setParameter("women",param.GetPro("women")).build();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }


                    HttpGet get = new HttpGet(uri);
                    logger.debug("设置请求头");
                    //get.setHeader("content-type", "application/x-www-form-urlencoded");
                    logger.debug("实例化httpClient对象");
                    DefaultHttpClient httpClient = new DefaultHttpClient();

                    try {

                        try {
                            String actual = null;
                            HttpResponse response = httpClient.execute(get);
                            actual = EntityUtils.toString(response.getEntity());
                            System.out.println("result--------");
                            System.out.println(actual);
                            JSONObject jsonObject=new JSONObject(actual);
                         String reason=jsonObject.getString("reason");
                         int error_code=jsonObject.getInt("error_code");
                     String   result=jsonObject.getJSONObject("result").toString();
                     String expect="{\"data\":\"良缘佳偶，她那丰富的幻想力，能够把你深深吸引住。\",\"men\":\"蛇\",\"women\":\"羊\"}";
                          Assert.assertEquals(result,expect);
                            System.out.println(expect);
                            System.out.println(result);
Assert.assertEquals(reason,param.GetPro("reason"));
                            Assert.assertEquals(error_code,Integer.parseInt(param.GetPro("error_code")));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }



    }
    @Test
//women=null
    public void womannull() {
        PropertyConfigurator.configure("log4j.properties");

        String url=proutil1.GetPro("test.url");
        System.out.println(url);
        URI uri = null;
        logger.debug("传入参数");
        Proutil1 param=new Proutil1("parameter.properties");
        System.out.println(param.GetPro("key"));
        System.out.println(param.GetPro("men"));
        System.out.println(param.GetPro("womennull"));
        try {

            uri = new URIBuilder(url)

                    .setParameter("key",param.GetPro("key"))
                    .setParameter("men",param.GetPro("men"))
                    .setParameter("women",param.GetPro("womennull")).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        HttpGet get = new HttpGet(uri);
        logger.debug("设置请求头");
        //get.setHeader("content-type", "application/x-www-form-urlencoded");
        logger.debug("实例化httpClient对象");
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {

            try {
                String actual = null;
                HttpResponse response = httpClient.execute(get);
                actual = EntityUtils.toString(response.getEntity());
                System.out.println("result--------");
                System.out.println(actual);
                JSONObject jsonObject=new JSONObject(actual);
                String reason=jsonObject.getString("reason");
                int error_code=jsonObject.getInt("error_code");
                System.out.println(jsonObject.get("result"));
              Assert.assertEquals(jsonObject.get("result").toString(),"null");
              Assert.assertEquals(reason,param.GetPro("reasonnull"));
                Assert.assertEquals(error_code,Integer.parseInt(param.GetPro("error_codenull")));

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    }
