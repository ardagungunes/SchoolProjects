import java.util.HashMap;

public class Elf extends Calliance{
    public final int maxHp = Constants.elfHP;
    public Elf(String characterName,   int[] position) {
        super(characterName, Constants.elfHP, Constants.elfAP, position);
    }

    @Override
    public boolean moveCheck(String[] moves) {
        return moves.length == Constants.elfMaxMove*2;
    }

    @Override
    public void rangedAttack(HashMap<String, Characters> charactersHashMap, int horizontal, int vertical) {
        for(int i = horizontal - 2; i <= horizontal + 2; i++){
            for(int j = vertical - 2; j <= vertical + 2; j++){
                for(String name : charactersHashMap.keySet()){
                    if(charactersHashMap.get(name).getPosition()[1] != horizontal || charactersHashMap.get(name).getPosition()[0] != vertical){
                        if(charactersHashMap.get(name).getPosition()[1] == i && charactersHashMap.get(name).getPosition()[0] == j){
                            if(charactersHashMap.get(name) instanceof Zorde){
                                charactersHashMap.get(name).damageTaken(Constants.elfAP);
                            }
                        }
                    }
                }
            }
        }
    }
}
