package WebTest;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZhouDaxia on 2016-12-18.
 */
public class parsePage implements Runnable {
    private String url;
    public parsePage(String url){
        this.url = url;
    }

    //将每个页面的业务逻辑放在Runnable接口的run()方法中，这样可以调用多线程爬去每个页面
    public void run(){
        //通过构造函数插入的URL，然后获取该URL的响应结果
        String contents = SpiderUtils.download(url);
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        //获取所有节点
        TagNode tn = htmlCleaner.clean(contents);
        //插入商品信息到数据库
        insertGoodInfo(tn);
    }

    //插入商品信息到数据库
    private void insertGoodInfo(TagNode tn){
        //获取商品ID、名称、价格、URL、图片URL、详情、插入日期、插入到数据库
        String goodsId = "jd_" + goodsId(tn);
        String goodsName = goodsName(tn);
        String goodsPrice = goodsPrice(tn);
        String goodsUrl = url;
        String goodsPicUrl = goodsPicUrl(tn);
        String goodsDetils = goodsDetils(tn);
        if(!goodsPrice.equals("null"))
            System.out.println("ID:" + goodsId + " Name:" + goodsName + " Price:" + goodsPrice + " URL:" + goodsUrl
                    + " PicURL:" + goodsPicUrl + " Detils:" + goodsDetils);
        //再加条属性，当前时间
        //插入函数以后再写
    }

    //获取商品ID
    private String goodsId(TagNode tn){
        String xpath = ".//*[@id='parameter2']/li[2]";
        Object[] objects = null;
        String id = "";
        try{
            objects = tn.evaluateXPath(xpath);
        }
        catch (XPatherException e){
            e.printStackTrace();
        }
        if(objects != null && objects.length > 0){
            TagNode node = (TagNode)objects[0];
            String temp = "";
            temp = node.getText().toString();
            Pattern compile1 = Pattern.compile("([0-9]+)");
            Matcher matcher1 = compile1.matcher(temp);
            while(matcher1.find()){
                return matcher1.group(0);
            }
        }
        return "";
    }

    //获取商品名称
    private String goodsName(TagNode tn){
        //商品名称的XPath
        String xpath = ".//*[@id='name']/h1";
        Object[] objects = null;
        String name = "";
        try{
            objects = tn.evaluateXPath(xpath);
        }
        catch(XPatherException e){
            e.printStackTrace();
        }
        if(objects != null && objects.length > 0){
            TagNode node = (TagNode)objects[0];
            name = node.getText().toString();
        }
        return name;
    }

    //获取商品价格
    private String goodsPrice(TagNode tn){
        String id = goodsId(tn);
        if(id.equals("")){
            return "null";
        }
        else{
            //System.out.println(goodsId(tn));
            String pricURL = "https://p.3.cn/prices/get?type=1&area=1_72_2799&pdtk=&pduid=1340247559&pdpin=&pdbp=0&skuid=J_" + goodsId(tn);
            String con = SpiderUtils.download(pricURL);
            //System.out.println(con);
            JSONArray jsonArray = new JSONArray(con);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String priceStr = jsonObject.getString("p");
            return priceStr;
        }
    }

    //获取商品详情
    private String goodsDetils(TagNode tn){
        String xpath = ".//*[@id='parameter2']/li";
        StringBuilder info = new StringBuilder();
        Object[] objects = null;
        try{
            objects = tn.evaluateXPath(xpath);
        }
        catch(XPatherException e){
            e.printStackTrace();
        }

        for(Object obj : objects){
            TagNode node = (TagNode)obj;
            String goodInfo = node.getText().toString();
            info.append(goodInfo);
            info.append("--");
        }
        return info.toString();
    }

    //获取商品图片URL
    private String goodsPicUrl(TagNode tn){
        //商品图片的XPath
        String xpath = "//*[@id=\"spec-n1\"]/img";
        Object[] objects = null;
        String picUrl = "";
        try{
            objects = tn.evaluateXPath(xpath);
        }
        catch(XPatherException e){
            e.printStackTrace();
        }
        if(objects != null && objects.length > 0){
            TagNode node = (TagNode)objects[0];
            picUrl = node.getAttributeByName("src");
        }
        return "http:" + picUrl;
    }
}
