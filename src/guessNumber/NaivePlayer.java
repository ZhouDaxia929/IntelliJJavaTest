package guessNumber;

/**
 * Created by ggggg on 2016/11/4.
 */
public class NaivePlayer extends Data implements Player {
    private boolean _cand[] = new boolean[M];
    private int _cand_count;
    public void init(){
        for(int i = 0; i < M; i++)
            _cand[i] = true;
        _cand_count = M;
    }
    public int getNextGuess(){
        // 第一次猜测总是使用第一个排列
        if(_cand_count == M)//排列次数用完结束
            return 0;
        // 返回遇到的第一个可行排列
        for(int i = 0; i < M; i++)
            if(_cand[i])
                return i;
        return -1;
    }
    public void setGuessResult(int arr_index, char result){
        // 依据返回结果进一步排除已有的可行排列
        for(int j = 0; j < M; j++){
            if(_cand[j] && table[arr_index][j] != result)
                _cand[j] = false;
            _cand_count--;
        }
    }
}
