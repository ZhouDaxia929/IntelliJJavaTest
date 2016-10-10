package Test;
import java.util.*;
public class t{
    public static void main(String[] args){
        /*
        List<String> setting = Collections.nCopies(100,"DEFAULT");
        ListIterator<String> pos = setting.listIterator();

        while(pos.hasNext()){
            pos.next();
            if(pos.hasNext()){
                pos.next();
                pos.remove();
            }
        }

        pos.next();
        pos.set("ZhouDaxia");
        System.out.println(setting.size());
        System.out.println(setting);
        */

        List<String> list2 = new LinkedList<>();
        for(int i = 0; i < 100; i++){
            list2.add("HA! ");
        }
        ListIterator<String> p2 = list2.listIterator();
        while(p2.hasNext()){
            p2.next();
            p2.set("Zhou");
            if(p2.hasNext()){
                p2.next();
                p2.remove();
            }
        }
        System.out.println(list2.size());
        System.out.println(list2);
        List<String> list3 = list2.subList(10,20);
        String[] values = list3.toArray(new String[list3.size()]);
        for(int i = 0; i < values.length; i++){

            if(i == 2)
                values[2] = "huanghui";
            System.out.println(values[i]);
        }

        System.out.println(list3);
        //list3.add(22);
        //List safeList = Collections.checkedList(list3, String.class);
        //List<String> safeList = list3;
        //safeList.add("33");
        //safeList.add(33);
        //System.out.println(safeList.get(10));

        //System.out.println(list3);
    }
}


