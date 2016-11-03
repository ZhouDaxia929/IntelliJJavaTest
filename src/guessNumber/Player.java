package guessNumber;

/**
 * Created by ggggg on 2016/11/3.
 */
public interface Player {
    public void init();
    public int getNextGuess();
    void setGuessResult(int arr_index, char result);
}
