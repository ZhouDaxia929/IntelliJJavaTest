package WebTest;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

/**
 * Created by ZhouDaxia on 2016-12-18.
 */
public class test10 { //获取商品介绍详情
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        //获取商品页面源码
        String contents = SpiderUtils.download("https://item.jd.com/3702211.html");
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        //获取所有节点
        TagNode tn = htmlCleaner.clean(contents);
        //商品名称的XPath
        String xpath = ".//*[@id='parameter2']/li";
        Object[] objects = null;
        try{
            objects = tn.evaluateXPath(xpath);
        }
        catch(XPatherException e){
            e.printStackTrace();
        }
        for(Object obj : objects){
            TagNode node = (TagNode)obj;
            String val = node.getText().toString();
            System.out.println(val);
        }
    }
}
