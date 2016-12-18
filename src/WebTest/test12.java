package WebTest;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZhouDaxia on 2016-12-15.
 */
public class test12 { //获取商品价格
    public static void main(String[] args) {
        test();
    }
    public static HashSet<String> goodId(){
        //获取本周热卖网页源码
        String content = SpiderUtils.download("https://sale.jd.com/act/6hd0T3HtkcEmqjpM.html");
        //匹配这个网页的所有网址
        Pattern compile = Pattern.compile("//item.jd.com/([0-9]+).html");
        //使用正则进行匹配
        Matcher matcher = compile.matcher(content);
        HashSet<String> hashSet = new HashSet<>();
        while(matcher.find()){
            //获取每个商品对应的URL
            String goodURL = matcher.group(0);
            //把URL添加进HashSet
            hashSet.add(goodURL);
        }
        HashSet<String> ID = new HashSet<>();
        for(String goodURL:hashSet){
            //获取商品页面源码
            String contents = SpiderUtils.download("https:" + goodURL);
            HtmlCleaner htmlCleaner = new HtmlCleaner();
            //获取所有节点
            TagNode tn = htmlCleaner.clean(contents);
            //商品名称的Xpath
            String xpath = ".//*[@id='parameter2']/li[2]";
            Object[] objects = null;
            String id = "";

            try{
                //通过Xpath查找商品名称所在节点
                objects = tn.evaluateXPath(xpath);
            }
            catch(XPatherException e){
                e.printStackTrace();
            }

            if(objects != null && objects.length > 0){
                TagNode node = (TagNode)objects[0];
                //获取ID
                id = node.getText().toString();
                System.out.println(id);
                Pattern compile1 = Pattern.compile("([0-9]+)");
                Matcher matcher1 = compile1.matcher(id);
                while(matcher1.find()){
                    if(ID.size() < 10){
                        //System.out.println(matcher1.group(0));
                        ID.add(matcher1.group(0));
                    }
                    else{
                        return ID;
                    }
                }
            }
        }
        return ID;
    }

    public static void test(){
        //获取所有的ID
        HashSet<String> goodIds = goodId();
        //遍历ID集合，然后通过每个ID拼接JS异步请求的URL，然后获得响应
        for(String goodId : goodIds) {
            //拼接JS异步请求的URL
            //String pricURL = "https://p.3.cn/prices/get?type=1&area=1_72_2799&pdtk=&pduid=1481166803710777284406&pdpin=&pdbp=0&skuid=J_" + goodId;
            String pricURL = "https://p.3.cn/prices/get?type=1&area=1_72_2799&pdtk=&pduid=1340247559&pdpin=&pdbp=0&skuid=J_" + goodId;
            //获取响应的内容
            String con = SpiderUtils.download(pricURL);

            //把JSON格式的字符串格式化为JSON数组
            JSONArray jsonArray = new JSONArray(con);
            //之前获取的结果可用看出只有一个json元素的json数组
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            //获取JSON，并获取其中键为p的值
            String priceStr = jsonObject.getString("p");
            double price = Double.parseDouble(priceStr);
            System.out.println(goodId + " " + price);
        }
    }
}
