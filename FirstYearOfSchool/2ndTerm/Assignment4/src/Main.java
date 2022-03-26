import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedHashMap<String, Stack<Item>> lhm = new LinkedHashMap<String, Stack<Item>>(); // This hashmap stores parts and their corresponding items.
        Queue<Token> tokenBox = new Queue<>();
        Scanner partsFile = new Scanner(new File(args[0]));
        Scanner itemsFile = new Scanner(new File(args[1]));
        Scanner tokensFile = new Scanner(new File(args[2]));
        Scanner tasksFile = new Scanner(new File(args[3]));
        FileWriter myWriter = new FileWriter(args[4]);

        Helper.createPartsAndItems(lhm,partsFile,itemsFile);//This method creates stacks in LinkedHashmap
        Helper.createTokenBox(tokenBox, tokensFile);
        Helper.doTasks(tokenBox,lhm,tasksFile);

        List<Token> tokenBoxForWrite = tokenBox.getTokenBox();
        Collections.reverse(tokenBoxForWrite);

        Helper.writeOutput(lhm,tokenBoxForWrite,myWriter);



        myWriter.close();
    }

}
