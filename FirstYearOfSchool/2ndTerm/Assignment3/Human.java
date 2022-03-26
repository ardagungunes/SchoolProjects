

public class Human extends Calliance{
    public final int maxHp = Constants.humanHP;
    public Human(String characterName,  int[] position) {
        super(characterName, Constants.humanHP, Constants.humanAP, position);
    }

    @Override
    public boolean moveCheck( String[] moves) {
        return moves.length == Constants.humanMaxMove * 2;
    }





}
