public class Token {
    private String symbol;
    private String vendingPart;
    private int value;

    public Token(String symbol, String vendingPart, int value){
        this.symbol = symbol;
        this.vendingPart = vendingPart;
        this.value = value;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getVendingPart() {
        return this.vendingPart;
    }

    public  int getValue() {
        return this.value;
    }

    public void changeTokenValue(int count){
        this.value -= count;
    }


}
