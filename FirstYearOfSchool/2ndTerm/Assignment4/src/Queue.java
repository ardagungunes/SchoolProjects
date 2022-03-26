import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Queue<T> {
    private List<Token> tokens = new ArrayList<Token>();


    public void enqueue(Token token){
        this.tokens.add((Token)token);
        this.tokens = tokens.stream().sorted(Comparator.comparing(Token::getValue).reversed()).collect(Collectors.toList());

    }


    public Token dequeue(String part){
        for(Token token : tokens){
            if(token.getVendingPart().equals(part)){
                tokens.remove(token);
                return token;
            }
        }
        return null;
    }

    public List<Token> getTokenBox(){
        return this.tokens;
    }
}
