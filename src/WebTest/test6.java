package WebTest;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZhouDaxia on 2016-12-15.
 */
public class test6 { //单元测试：获取本周热卖所有商品URL
    public static void main(String[] args) {
        test6.test();
    }
    public static void test(){
        //获取本周热卖网页源码
        String content = SpiderUtils.download("https://sale.jd.com/act/6hd0T3HtkcEmqjpM.html");
        //匹配这个网页所有商品网址
        Pattern compile = Pattern.compile("//item.jd.com/([0-9]+).html");
        //使用正则进行匹配
        Matcher matcher = compile.matcher(content);
        //使用正则进行查找，查找过程中可能会出现重复的URL。所以我们需要存入HashSet从而保证URL唯一
        HashSet<String> hashSet = new HashSet<>();
        while(matcher.find()){
            String goodURL = matcher.group(0);
            hashSet.add(goodURL);
        }
        for(String url : hashSet){
            System.out.println(url);
        }
    }
}
