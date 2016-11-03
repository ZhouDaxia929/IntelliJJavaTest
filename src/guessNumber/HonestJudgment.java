package guessNumber;

/**
 * Created by ggggg on 2016/11/3.
 */
public class HonestJudgment extends Data implements Judgment {
    //private static final int N = 4; // 排列长度
    private int _my_arr[] = new int[N];
    public void init(){}
    // 设置裁判的正确结果
    public void setArrange(int arr[]){
        for(int i = 0; i < N; i++)
            _my_arr[i] = arr[i];
    }
    // 这是一个诚实的裁判，所以会诚实地裁决
    public char doJudge(int arr_index){
        return TestGuessNumber.judge(_my_arr, arrange[arr_index]);
    }
}
