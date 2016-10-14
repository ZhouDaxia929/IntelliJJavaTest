package Test2;
public class TestJava{
    /**
     * 对运算符排列
     * @param a
     * @param cur
     */
    public void fenPei(int[] a,int cur){
        if(cur == 9){
            check(a);
            return;
        }
        for(int i = 0;i < 3; i++){
            a[cur] = i;
            fenPei(a,cur+1);
        }
    }
    /**
     * 检查及输出
     * @param a
     */
    public void check(int[] a){
        int sum=0,shu,pre = 9,k;
        for(int i = 8;i >=0; i--){
            switch(a[i]){
                case 1:
                    shu = 0;
                    for(k = 1;pre> i;pre--,k*=10){
                        shu += pre*k;
                    }
                    sum -= shu;
                    break;
                case 2:
                    shu = 0;
                    for(k = 1;pre > i;pre--,k*=10){
                        shu += pre*k;
                    }
                    sum += shu;
                    break;
                default:break;
            }
        }
        /**
         * 输出
         */
        if(sum == 110)
        {
            for(int i = 1;i <9;i++){
                System.out.print(i);
                if(a[i] == 1)
                    System.out.print('-');
                if(a[i] == 2)
                    System.out.print('+');
            }
            System.out.println("9=110");
        }
    }
    /**
     * main方法
     * @param args
     */
    public static void main(String[] args){
        int[] a = new int[9];
        a[0] = 2;
        new TestJava().fenPei(a,1);
    }
}
