public class Item {
    private String symbol;
    private String vendingPart;

    public Item(String symbol, String vendingPart){
        this.symbol = symbol;
        this.vendingPart = vendingPart;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getVendingPart() {
        return this.vendingPart;
    }
}
