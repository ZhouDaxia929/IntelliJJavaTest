package Test2;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
//import java.io.File;
//import java.io.FileInputStream;

/**
 * Created by ggggg on 2016/9/28.
 */
public class TestJava {
    public static void main(String[] args) {

        /*
        Charset cset = new Charset.forName("ISO-8859-1");

        Set<String> aliases = cset.aliases();
        for(String alias : aliases)
            System.out.println(alias);
        */
        Map<String, Charset> charsets = Charset.availableCharsets();
        for(String name : charsets.keySet())
            System.out.println(name);

        System.out.println(System.getProperty("user.dir"));
        System.out.println(File.separator);
        try{
            DataInputStream din = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("employee.dat")
                    )
            );
            int i = din.read();
            System.out.println(i);
        }
        catch(FileNotFoundException e){
            System.out.println("未找到");
            System.exit(0);
        }
        catch(IOException e) {
            System.out.println("发生错误");
            System.exit(0);
        }
    }
}
