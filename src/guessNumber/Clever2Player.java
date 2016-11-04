package guessNumber;

/**
 * Created by ZhouDaxia on 2016-11-04.
 */
public class Clever2Player extends Data implements Player {
    private boolean _cand[] = new boolean[M];
    private int _cand_count;
    public void init(){
        for(int i = 0; i < M; i++)
            _cand[i] = true;
        _cand_count = M;
    }
    public int getNextGuess(){
        //第一次猜测总是使用第一个排列
        if(_cand_count == M)
            return 0;
        if(_cand_count == 1){
            for(int i = 0; i < M; i++)
                if(_cand[i])
                    return i;
        }
        /*
        与Clever1Player的区别：下一次猜测的排列不局限于可行排列
         */
        int res_count[] = new int[MATCH + 1];
        int min_max = M + 1;
        int min_max_index = 0;
        for(int i = 0; i < M; i++){
            //对于所有排列Ri
            int cur_max = 0;
            for(int j = 0; j <= MATCH; j++)
                res_count[j] = 0;
            for(int j = 0; j < M; j++){
                if(_cand[j])
                    res_count[(int)table[i][j]]++;
            }
            for(int j = 0; j <= MATCH; j++)
                if(res_count[j] > cur_max)
                    cur_max = res_count[j];
            if(cur_max < min_max){
                min_max = cur_max;
                min_max_index = i;
            }
        }
        return min_max_index;
    }
    public void setGuessResult(int arr_index, char result){
        for(int j = 0; j < M; j++){
            if(_cand[j] && table[arr_index][j] != result){
                _cand[j] = false;
                _cand_count--;
            }
        }
    }
}
