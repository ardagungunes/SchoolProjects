

public class Dwarf extends Calliance{
    public final int maxHp = Constants.dwarfHP;
    public Dwarf(String characterName,   int[] position) {
        super(characterName, Constants.dwarfHP, Constants.dwarfAP, position);
    }

    @Override
    public boolean moveCheck( String[] moves) {
        return moves.length == Constants.dwarfMaxMove * 2;
    }



    }

