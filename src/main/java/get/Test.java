package get;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;

public class Test {
    public static void main(String[] args) {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet1=new HttpGet("http://apis.juhe.cn/sxpd/query?man=羊&woman=牛");
        URI uri = null;


            try {
                uri = new URIBuilder().setScheme("http")
                        .setHost("localhost")
                        .setPort(8080).setPath("/sxpd/query")
                        .setParameter("key","羊")
                        .build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
}
