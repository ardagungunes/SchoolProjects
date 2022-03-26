

public class Goblin extends Zorde{
    public final int maxHp = Constants.goblinHP;
    public Goblin(String characterName,   int[] position) {
        super(characterName, Constants.goblinHP, Constants.goblinAP, position);
    }

    @Override
    public boolean moveCheck( String[] moves) {
        return moves.length == Constants.goblinMaxMove * 2;
    }


}
