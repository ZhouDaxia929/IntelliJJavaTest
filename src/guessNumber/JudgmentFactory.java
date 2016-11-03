package guessNumber;

/**
 * Created by ggggg on 2016/11/4.
 */
public class JudgmentFactory {
    public static Judgment makeJudgment(){
        return new HonestJudgment();
    }
}
