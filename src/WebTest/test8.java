package WebTest;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by ZhouDaxia on 2016-12-15.
 */
public class test8 { //获取ID
    public static void main(String[] args) {
        test8.test();
    }
    public static void test(){
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
            }
        }
    }
}
