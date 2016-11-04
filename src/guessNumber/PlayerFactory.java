package guessNumber;

/**
 * Created by ggggg on 2016/11/4.
 */
public class PlayerFactory {
    public static Player makePlayer(){
        //return new NaivePlayer();
        //return  new Clever1Player();
        return new Clever2Player();
    }
}
