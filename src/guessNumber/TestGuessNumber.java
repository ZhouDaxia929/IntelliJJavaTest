package guessNumber;

/**
 * Created by ggggg on 2016/11/3.
 * http://www.cppblog.com/qinzuoyan/archive/2009/08/14/93306.html
 */
public class TestGuessNumber extends Data{

    public static void swap(int a[], int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // 使用递归方法计算排列，就是从0~9中挑选4个不同的数，这里用递归不好，记得改
    public static void arr(int a[], int start){
        if(start == N){
            for(int i = 0; i < N; i++)
                arrange[arr_count][i] = a[i];
            arr_count++;
            return;
        }
        for(int i = start; i <= 9; i++){
            swap(a, start, i);
            arr(a, start + 1);
            swap(a, start, i);
        }
    }

    // 初始化所有可能排列情况的列表
    public static void init_arrange(){
        int[] a = new int[10];
        arr_count = 0;
        for(int i = 0; i <= 9; i++)
            a[i] = i;
        arr(a, 0);
    }

    // 判断两个排列之间的关系
    // 返回结果为“A*10+B”；譬如结果为“1A2B”，则返回值为 (char)12
    public static char judge(int v1[], int v2[]){
        int a = 0;
        int b = 0;
        for(int i = 0; i < N; i++){
            if(v1[i] == v2[i])
                a++;
            for(int j = 0; j < N; j++){
                if(v1[i] == v2[j])
                    b++;
            }
        }
        b -= a; //因为b中含有a中的部分情况
        return (char)(a * 10 + b);
    }

    // 初始化各个排列间的关系矩阵
    public static void init_table(){
        for(int i = 0; i < M; i++)
            for(int j = 0; j <= i; j++)
                table[i][j] = table[j][i] = judge(arrange[i], arrange[j]);
    }

    // 按照“[0 1 2 3]”的形式打印排列
    public static void print_arrange(int arr_index){
        System.out.print("[");
        for(int i = 0; i < N; i++){
            if(i != 0)
                System.out.print(" ");
            System.out.print(arrange[arr_index][i]);
        }
        System.out.print("]");
    }

    // 按照类似“0A0B”的形式打印裁判的结果
    public static void print_result(char result){
        System.out.println((int)result / 10 + "A" + (int)result % 10 + "B");
    }

    // 打印一次猜测与结果的信息
    public static void print_guess_info(int arr_index, char result){
        print_arrange(arr_index);
        System.out.print(" --> ");
        print_result(result);
        System.out.print("\n");
    }

    // 玩一次游戏，返回猜测次数
    public static int play(Player p, Judgment j){
        int time = 0;
        p.init();
        j.init();
        while(true){
            int guess = p.getNextGuess();
            char result = j.doJudge(guess);
            //print_guess_info(guess, result);
            time++;
            if(result == (char)MATCH)
                break;
            p.setGuessResult(guess, result);
        }
        return time;
    }

    public static void main(String[] args) {
        Judgment jp = JudgmentFactory.makeJudgment();
        Player pp = PlayerFactory.makePlayer();
        int[] time_stat = new int[M];// 次数统计表
        // 初始化次数统计表
        for(int i = 0; i < M; i++)
            time_stat[i] = 0;
        // 初始化排列列表和关系矩阵
        init_arrange();
        init_table();
        // 每一种排列情况都玩一次
        for(int i = 0; i < M; i++){
            ((HonestJudgment)jp).setArrange(arrange[i]);
            int t = play(pp, jp);
            print_arrange(i);
            System.out.println(" : " + t);
            time_stat[t]++;
        }
        // 统计结果
        int total = 0;
        System.out.println("-------------");
        for(int i = 0; i < M; i++){
            if(time_stat[i] > 0){
                System.out.println(i + "\t" + time_stat[i]);
                total += time_stat[i] * i;
            }
        }
        System.out.println("Average: " + total + "/" + M + " = " + (float)total / M);
        System.out.println("-------------");
    }
}
