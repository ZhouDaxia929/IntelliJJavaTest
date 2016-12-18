package WebTest;

import org.htmlcleaner.XPatherException;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZhouDaxia on 2016-12-18.
 */
public class Spider {
    public static void main(String[] args) throws XPatherException{
        Spider spider = new Spider();
        spider.start();
    }
    public void start() throws XPatherException{
        System.out.println("Begin!!!");
        //爬取本周热卖
        String content = SpiderUtils.download("https://sale.jd.com/act/6hd0T3HtkcEmqjpM.html");
        //匹配这个网页的所有商品网址
        Pattern compile = Pattern.compile("//item.jd.com/([0-9]+).html");
        //使用正则进行匹配
        Matcher matcher = compile.matcher(content);
        HashSet<String> hashSet = new HashSet<>();
        String goodId = "";
        while(matcher.find()){
            String goodURL = matcher.group(0);
            hashSet.add(goodURL);
        }
        for(String goodUrl:hashSet){
            parsePage npp = new parsePage("http:" + goodUrl);
            Thread th = new Thread(npp);
            th.start();
            //System.out.println(hashSet.size());
        }
    }
}
