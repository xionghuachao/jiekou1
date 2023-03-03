package get;

import com.google.gson.JsonObject;

import config.Proutil1;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.DatabaseUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GetCanShuJuKu {

   Proutil1 proutil1=new Proutil1("application.properties");
    static Logger logger=Logger.getLogger(GetCanShuJuKu.class);
    String url=proutil1.GetPro("test.url");
@Test
    public void xingzuopeiduiSuccess() {
    PropertyConfigurator.configure("log4j.properties");

        try {
            SqlSession session = null;
            try {
                session = DatabaseUtil.getSqlSession();
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<User> user = session.selectList("const");

            for (int i = 0; i < user.size(); i++) {

                if (user.get(i).getMen().equals("蛇") && (user.get(i).getWomen().equals("羊"))) {

                    URI uri = null;
                    logger.debug("传入参数");
                    try {
                        uri = new URIBuilder(url)

                                .setParameter("key","5baeccd771e0624fae38bd038ca6e001")
                                .setParameter("men", user.get(i).getMen())
                                .setParameter("women", user.get(i).getWomen()).build();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }


                    HttpGet get = new HttpGet(uri);
                    logger.debug("设置请求头");
                    get.setHeader("content-type", "application/x-www-form-urlencoded");
                    logger.debug("实例化httpClient对象");
                    DefaultHttpClient httpClient = new DefaultHttpClient();

                    try {

                        try {
                            String actual = null;
                            HttpResponse response = httpClient.execute(get);
                            actual = EntityUtils.toString(response.getEntity());
                            System.out.println("result--------");
                            System.out.println(actual);
                            JSONObject expect=new JSONObject(user.get(i));
                         JSONObject  actual1=new JSONObject(actual);
                            System.out.println(expect.toString());
                       Assert.assertEquals(actual1.get("reason"),expect.get("reason"));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    //如果没有找到数据，跑一个异常
                    Reporter.log("这是我们自己写的日志");

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
@Test
    public void teshuzifu() {
        PropertyConfigurator.configure("log4j.properties");

            SqlSession session = null;
            try {
                session = DatabaseUtil.getSqlSession();
            } catch (IOException e) {
                e.printStackTrace();
            }

            User user = session.selectOne("se");
            System.out.println(user);

            URI uri = null;
            logger.debug("传入参数");
            try {
                uri = new URIBuilder(url)

                        .setParameter("key", "5baeccd771e0624fae38bd038ca6e001")
                        .setParameter("men", "s")
                        .setParameter("women", "羊").build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }


            HttpGet get = new HttpGet(uri);
            logger.debug("设置请求头");
            get.setHeader("content-type", "application/x-www-form-urlencoded");
            logger.debug("实例化httpClient对象");
            DefaultHttpClient httpClient = new DefaultHttpClient();

            try {
                String actual = null;
                HttpResponse response = httpClient.execute(get);
                actual = EntityUtils.toString(response.getEntity());
                System.out.println("==============");
                System.out.println(actual);

                JSONObject expect=new JSONObject(user);
                JSONObject actual1=new JSONObject(actual);
                System.out.println(expect.toString());
                Assert.assertEquals(expect.get("reason"),actual1.get("reason"));
                Assert.assertEquals(expect.getInt("error_code"),actual1.getInt("error_code"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
