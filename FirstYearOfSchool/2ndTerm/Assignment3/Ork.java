import java.util.HashMap;

public class Ork extends Zorde {
    public final int maxHp = Constants.orkHP;

    public Ork(String characterName, int[] position) {
        super(characterName, Constants.orkHP, Constants.orkAP, position);
    }

    @Override
    public boolean moveCheck(String[] moves) {
        return moves.length == Constants.orkMaxMove * 2;
    }

    @Override
    public void heal(HashMap<String, Characters> charactersHashMap, int horizontal, int vertical) {
        for(int i = horizontal - 1; i <= horizontal + 1; i++){
            for(int j = vertical - 1; j <= vertical + 1; j++){
                for(String name : charactersHashMap.keySet()){
                    if(charactersHashMap.get(name).getPosition()[1] != horizontal || charactersHashMap.get(name).getPosition()[0] != vertical){
                        if(charactersHashMap.get(name).getPosition()[1] == i && charactersHashMap.get(name).getPosition()[0] == j){
                            if((charactersHashMap.get(name) instanceof Zorde) && (charactersHashMap.get(name).getCharacterHP() > 0) ){
                                charactersHashMap.get(name).increaseHealth(10);
                            }
                        }
                    }
                }
            }
        }
    }
}
