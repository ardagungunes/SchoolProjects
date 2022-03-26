import java.util.Arrays;
import java.util.HashMap;

public abstract class Characters implements Comparable<Characters> {

    private String characterName;
    private int characterHP;
    private int characterAP;
    private int[] position = new int[2];

    public Characters(String characterName,int characterHP, int characterAP, int[] position) {
        this.characterName = characterName;
        this.characterHP = characterHP;
        this.characterAP = characterAP;
        this.position = position;
    }


    public String getCharacterName() {
        return characterName;
    }

    public int getCharacterHP() {
        return characterHP;
    }

    public int getCharacterAP() {
        return characterAP;
    }

    public int[] getPosition() {
        return position;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setCharacterHP(int characterHP) {
        this.characterHP = characterHP;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void damageTaken(int damage){
        if(damage > 0)
            this.characterHP -= damage;
    }

    @Override
    public int compareTo(Characters o) {
        return this.characterName.compareTo(o.characterName);
    }

    public abstract boolean moveCheck(String[] moves);

    //
    public void fightToDeath(String attacker, String defender, HashMap<String, Characters> charactersHashMap){
        int attackerHp = charactersHashMap.get(attacker).getCharacterHP();
        int attackerAp = charactersHashMap.get(attacker).getCharacterAP();

        charactersHashMap.get(defender).damageTaken(attackerAp);
        int defenderHp = charactersHashMap.get(defender).getCharacterHP();

        if(attackerHp == defenderHp){
            charactersHashMap.get(attacker).setCharacterHP(0);
            charactersHashMap.get(defender).setCharacterHP(0);
        }else if(attackerHp > defenderHp){
            charactersHashMap.get(attacker).damageTaken(defenderHp);
            charactersHashMap.get(defender).setCharacterHP(0);
        }else{
            charactersHashMap.get(attacker).setCharacterHP(0);
            charactersHashMap.get(defender).damageTaken(attackerHp);
        }

        //for(String id : charactersHashMap.keySet()){
           // if(charactersHashMap.get(id).getCharacterHP() <= 0){
           //     charactersHashMap.remove(id);
           // }
        //}


    }

    

    public void normalAttack(String id, HashMap<String, Characters> charactersHashMap, int horizontal, int vertical){
        for(int i = horizontal - 1; i <= horizontal + 1; i++){
            for(int j = vertical - 1; j <= vertical + 1; j++){
                for(String name : charactersHashMap.keySet()){
                    if(charactersHashMap.get(name).getPosition()[1] != horizontal || charactersHashMap.get(name).getPosition()[0] != vertical){
                        if(charactersHashMap.get(name).getPosition()[1] == i && charactersHashMap.get(name).getPosition()[0] == j){
                            if((charactersHashMap.get(name) instanceof Calliance && charactersHashMap.get(id) instanceof Zorde) || (charactersHashMap.get(name) instanceof Zorde && charactersHashMap.get(id) instanceof Calliance)){
                                charactersHashMap.get(name).damageTaken(this.characterAP);
                            }
                        }
                    }
                }
            }
        }
    }

    public void increaseHealth(int hp){
        this.characterHP += hp;
    }

    public void heal(HashMap<String, Characters> charactersHashMap, int horizontal, int vertical){

    }

    public void rangedAttack(HashMap<String, Characters> charactersHashMap, int horizontal, int vertical){

    }

    public int move(int[] direction, int boardSize, HashMap<String, Characters> charactersHashMap) {
        int ct = 0;
        int oldHorizontal = this.position[1];
        int oldVertical = this.position[0];

        int newHorizontal = oldHorizontal + direction[1];
        int newVertical = oldVertical + direction[0];

        int[] newPosition = {newVertical, newHorizontal};

        if (newHorizontal != 0 && newHorizontal != boardSize + 1 && newVertical != 0 && newVertical != boardSize + 1){



            for (String id : charactersHashMap.keySet()) {
                if (Arrays.equals(charactersHashMap.get(id).getPosition(), newPosition) && !(charactersHashMap.get(id).getCharacterHP() <= 0) ) {
                    if ((this instanceof Calliance && charactersHashMap.get(id) instanceof Calliance) ||
                            (this instanceof Zorde && charactersHashMap.get(id) instanceof Zorde)) {
                        newHorizontal = oldHorizontal;
                        newVertical = oldVertical;
                        return 1;
                    } else if ((this instanceof Calliance && charactersHashMap.get(id) instanceof Zorde) ||
                            (this instanceof Zorde && charactersHashMap.get(id) instanceof Calliance)) {
                        if (charactersHashMap.get(id).getCharacterHP() > 0 && this.getCharacterHP() > 0) {
                            fightToDeath(this.characterName, id, charactersHashMap);
                            if(charactersHashMap.get(this.characterName).getCharacterHP() > 0){
                                charactersHashMap.get(this.characterName).setPosition(newPosition);
                            }
                            return 2;
                        }
                    }
                }
            }
            charactersHashMap.get(this.characterName).setPosition(newPosition);
            return 3;
    }

        return -1;

    }
}










