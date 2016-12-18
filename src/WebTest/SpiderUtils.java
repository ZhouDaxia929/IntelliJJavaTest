package WebTest;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
/**
 * Created by ZhouDaxia on 2016-12-14.
 */
public class SpiderUtils {
   //下载给定URL的网页实体
    public static String download(String url){
        //构建HTTP请求
        HttpClientBuilder builder = HttpClients.custom();
        CloseableHttpClient client = builder.build();
        HttpGet request = new HttpGet(url);
        String str = "";
        try{
            //执行请求
            CloseableHttpResponse response = client.execute(request);
            //获取网页实体
            HttpEntity entity = response.getEntity();
            //返回网页实体字符串
            str = EntityUtils.toString(entity);
        }
        catch(ClientProtocolException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return str;
    }
}
