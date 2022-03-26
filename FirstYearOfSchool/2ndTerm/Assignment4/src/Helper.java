import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Helper {
    public static void createPartsAndItems(LinkedHashMap<String, Stack<Item>> lhm, Scanner partFile, Scanner itemFile) {

        while(partFile.hasNextLine()){
            String partName = partFile.nextLine();

            lhm.put(partName,new Stack<Item>());
        }

        while (itemFile.hasNextLine()){
            String line = itemFile.nextLine();
            String[] symbolAndPart = line.split(" ");

            for(String part : lhm.keySet()){
                if (symbolAndPart[1].equals(part)){
                    lhm.get(part).push(new Item(symbolAndPart[0], symbolAndPart[1]));
                }
            }
        }
    }

    public static void createTokenBox(Queue<Token> tokenQueue, Scanner tokenFile){

        while (tokenFile.hasNextLine()){
            String line = tokenFile.nextLine();
            String[] commandParts = line.split(" ");
            tokenQueue.enqueue(new Token(commandParts[0], commandParts[1], Integer.parseInt(commandParts[2])));
        }
    }

    public static void doTasks(Queue<Token> tokenBox, LinkedHashMap<String, Stack<Item>> lhm, Scanner taskFile){

        while(taskFile.hasNextLine()){
            String line = taskFile.nextLine();
            String[] commandParts = line.split("\t");

            if(commandParts[0].equals("PUT")){
                for(int i = 1; i < commandParts.length; i++){
                    String[] stacks = commandParts[i].split(",");
                    String parts = stacks[0];
                    String[] symbols = Arrays.copyOfRange(stacks,1,stacks.length);
                    putItems(lhm,parts,symbols);

                }

            }else if(commandParts[0].equals("BUY")){
                for (int i = 1; i < commandParts.length; i ++){
                    String[] stacks = commandParts[i].split(",");
                    String name = stacks[0];
                    int count = Integer.parseInt(stacks[1]);

                    for(int j = 0; j< count; j++){
                        lhm.get(name).pop();
                    }

                    Token token = tokenBox.dequeue(name);

                    while(count != 0){
                        if(token.getValue() >= count){
                            token.changeTokenValue(count);
                            if(token.getValue() != 0){
                            tokenBox.enqueue(token);
                            }

                            count = 0;

                        }else{
                            //System.out.println(token.getSymbol() + " " +token.getValue());
                            count -= token.getValue();
                            token.changeTokenValue(token.getValue());
                            token = tokenBox.dequeue(name);
                        }
                    }


                }
            }
        }
    }
    public static void putItems(LinkedHashMap<String, Stack<Item>> lhm, String part, String[] symbols){
        for(int i = 0; i < symbols.length; i++){
            lhm.get(part).push(new Item(symbols[i], part));
        }
    }

    public static void writeOutput(LinkedHashMap<String, Stack<Item>> lhm, List<Token> tokenList, FileWriter writer) throws IOException {
        for(String part : lhm.keySet()){
            writer.write(part + ":\n");
            ArrayList<Item> itemArrayList = lhm.get(part).getItems();
            Collections.reverse(itemArrayList);
            for(Item item : itemArrayList){
                writer.write(item.getSymbol() + "\n");
            }
            writer.write("---------------\n");
        }
        writer.write("Token Box:\n");
        for(Token token : tokenList){
            writer.write(token.getSymbol() + " " + token.getVendingPart() + " " + token.getValue() + "\n");
        }

    }
}
