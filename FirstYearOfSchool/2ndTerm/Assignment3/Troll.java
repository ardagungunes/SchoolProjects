

public class Troll extends Zorde{
    public final int maxHp = Constants.trollHP;
    public Troll(String characterName,   int[] position) {
        super(characterName, Constants.trollHP, Constants.trollAP, position);
    }

    @Override
    public boolean moveCheck(String[] moves) {
        return moves.length == Constants.trollMaxMove * 2;
    }


}
