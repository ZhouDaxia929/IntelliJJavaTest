package guessNumber;

/**
 * Created by ggggg on 2016/11/3.
 */
public class Data {
    static final int N = 4; // 排列长度
    static final int M = (10 * 9 * 8 * 7); // 总的可能排列数,5040
    static final int MATCH = (N * 10); // 猜测正确时的judge()返回值，如果是完全正确结果，judge函数会返回40
    static int[][] arrange = new int[M][N]; // 所有可能排列的列表，一行一个排列
    static char[][] table = new char[M][M]; // 各排列之间的关系矩阵
    static int arr_count; // 用于生成所有排列时使用的全局变量
}
