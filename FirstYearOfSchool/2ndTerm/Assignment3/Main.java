import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // line 10 ve 80 82;

        Scanner initials = new Scanner(new File(args[0]));
        FileWriter myWriter = new FileWriter(args[2]);
        HashMap<String, Characters> idOfCharacters = new HashMap<>(); // I decided to use polymorphism in hashmap.


        initials.nextLine(); // To avoid unnecessary command parts.
        int boardSize = Integer.parseInt(initials.nextLine().split("x")[0]);

        String[][] board = new String[boardSize + 2][boardSize + 2];


        while (initials.hasNextLine()) {
            String[] charAttributes = initials.nextLine().split(" ");

            if (charAttributes[0].equals("ELF")) {
                int[] position = new int[2];
                position[0] = Integer.parseInt(charAttributes[2]) + 1;
                position[1] = Integer.parseInt(charAttributes[3]) + 1;
                idOfCharacters.put(charAttributes[1], new Elf(charAttributes[1], position));
            } else if (charAttributes[0].equals("DWARF")) {
                int[] position = new int[2];
                position[0] = Integer.parseInt(charAttributes[2]) + 1;
                position[1] = Integer.parseInt(charAttributes[3]) + 1;
                idOfCharacters.put(charAttributes[1], new Dwarf(charAttributes[1], position));
            } else if (charAttributes[0].equals("HUMAN")) {
                int[] position = new int[2];
                position[0] = Integer.parseInt(charAttributes[2]) + 1;
                position[1] = Integer.parseInt(charAttributes[3]) + 1;
                idOfCharacters.put(charAttributes[1], new Human(charAttributes[1], position));
            } else if (charAttributes[0].equals("GOBLIN")) {
                int[] position = new int[2];
                position[0] = Integer.parseInt(charAttributes[2]) + 1;
                position[1] = Integer.parseInt(charAttributes[3]) + 1;
                idOfCharacters.put(charAttributes[1], new Goblin(charAttributes[1], position));
            } else if (charAttributes[0].equals("TROLL")) {
                int[] position = new int[2];
                position[0] = Integer.parseInt(charAttributes[2]) + 1;
                position[1] = Integer.parseInt(charAttributes[3]) + 1;
                idOfCharacters.put(charAttributes[1], new Troll(charAttributes[1], position));
            } else if (charAttributes[0].equals("ORK")) {
                int[] position = new int[2];
                position[0] = Integer.parseInt(charAttributes[2]) + 1;
                position[1] = Integer.parseInt(charAttributes[3]) + 1;
                idOfCharacters.put(charAttributes[1], new Ork(charAttributes[1], position));
            }
        } // This loop creates HashMap.


        createBoard(board, idOfCharacters);

        //System.out.println(Arrays.deepToString(board));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                myWriter.write(board[i][j]);
            }
            myWriter.write("\n");
        }

        createStatus(idOfCharacters, myWriter);
        myWriter.write("\n");


        Scanner commands = new Scanner(new File(args[1]));

        while (commands.hasNextLine()) {
            String[] partsForErrorCheck = commands.nextLine().split(" ");
            String[] partsForErrorCheck2 = partsForErrorCheck[1].split(";");
            String id = partsForErrorCheck[0];
            String[][] newBoard = new String[boardSize + 2][boardSize + 2];
            //System.out.println(Arrays.asList(partsForErrorCheck2));

            if (!errorCheck2(partsForErrorCheck[0], idOfCharacters, partsForErrorCheck2)) {
                try{
                    throw new MoveCountError();
                }catch (MoveCountError error) {
                    myWriter.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                }

            } else {
                int[][] directions = new int[partsForErrorCheck2.length / 2][2];
                int ct = 0;

                int ctForErrorCheck = 0;

                for (int i = 0; i < partsForErrorCheck2.length / 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        directions[i][j] = Integer.parseInt(partsForErrorCheck2[ct]);
                        ct++;
                    }
                }

                //System.out.println(Arrays.deepToString(directions));

                //System.out.println(Arrays.deepToString(directions));
                int flag = 0;
                int flag2 = 0;
                if (idOfCharacters.get(id).getClass().getSimpleName().equals("Human")) {
                    for (int[] arr : directions) {


                        int a = idOfCharacters.get(id).move(arr, boardSize, idOfCharacters);

                        myWriter.write("\n");

                        if (ctForErrorCheck == 0 && a == -1) {
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                            myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                        }
                            flag++;
                            break;
                        } else if (ctForErrorCheck != 0 && a == -1) {
                            createBoard(newBoard, idOfCharacters);
                            printBoard(newBoard, myWriter);
                            createStatus(idOfCharacters, myWriter);
                            flag2++;
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                            myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                        }
                            break;
                        }

                        ctForErrorCheck++;
                        if (ctForErrorCheck == 3 && a!= 1 && a!= 2) {
                            idOfCharacters.get(id).normalAttack(id, idOfCharacters, idOfCharacters.get(id).getPosition()[1], idOfCharacters.get(id).getPosition()[0]);
                        }if(a == 1 || a == 2){break;}
                    }
                } else if (idOfCharacters.get(id).getClass().getSimpleName().equals("Dwarf")) {
                    for (int[] arr : directions) {


                        int a = idOfCharacters.get(id).move(arr, boardSize, idOfCharacters);

                        myWriter.write("\n");

                        if (ctForErrorCheck == 0 && a == -1) {
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            flag++;
                            break;
                        } else if (ctForErrorCheck != 0 && a == -1) {
                            createBoard(newBoard, idOfCharacters);
                            printBoard(newBoard, myWriter);
                            createStatus(idOfCharacters, myWriter);
                            flag2++;
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            break;
                        }
                        if(a!=1 && a!= 2)
                            idOfCharacters.get(id).normalAttack(id, idOfCharacters, idOfCharacters.get(id).getPosition()[1], idOfCharacters.get(id).getPosition()[0]);
                        ctForErrorCheck++;if(a == 1 || a == 2){break;}
                    }
                } else if (idOfCharacters.get(id).getClass().getSimpleName().equals("Elf")) {
                    for (int[] arr : directions) {


                        int a = idOfCharacters.get(id).move(arr, boardSize, idOfCharacters);

                        myWriter.write("\n");

                        if (ctForErrorCheck == 0 && a == -1) {
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            flag++;
                            break;
                        } else if (ctForErrorCheck != 0 && a == -1) {
                            createBoard(newBoard, idOfCharacters);
                            printBoard(newBoard, myWriter);
                            createStatus(idOfCharacters, myWriter);
                            flag2++;
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            break;
                        }

                        ctForErrorCheck++;

                        if (ctForErrorCheck != 4) {
                            if(a!=1 && a!= 2)
                                idOfCharacters.get(id).normalAttack(id, idOfCharacters, idOfCharacters.get(id).getPosition()[1], idOfCharacters.get(id).getPosition()[0]);
                        } else {
                            if(a!=1 && a!= 2)
                                idOfCharacters.get(id).rangedAttack(idOfCharacters, idOfCharacters.get(id).getPosition()[1], idOfCharacters.get(id).getPosition()[0]);
                        }

                        if(a == 1 || a == 2){break;}
                    }
                } else if (idOfCharacters.get(id).getClass().getSimpleName().equals("Troll")) {
                    for (int[] arr : directions) {


                        int a = idOfCharacters.get(id).move(arr, boardSize, idOfCharacters);

                        myWriter.write("\n");

                        if (ctForErrorCheck == 0 && a == -1) {
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            flag++;
                            break;
                        } else if (ctForErrorCheck != 0 && a == -1) {
                            createBoard(newBoard, idOfCharacters);
                            printBoard(newBoard, myWriter);
                            createStatus(idOfCharacters, myWriter);
                            flag2++;
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            break;
                        }

                        ctForErrorCheck++;
                            if(a!= 1 && a!= 2)
                                idOfCharacters.get(id).normalAttack(id, idOfCharacters, idOfCharacters.get(id).getPosition()[1], idOfCharacters.get(id).getPosition()[0]);

                    }

                } else if (idOfCharacters.get(id).getClass().getSimpleName().equals("Goblin")) {
                    for (int[] arr : directions) {


                        int a = idOfCharacters.get(id).move(arr, boardSize, idOfCharacters);

                        myWriter.write("\n");

                        if (ctForErrorCheck == 0 && a == -1) {
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            flag++;
                            break;
                        } else if (ctForErrorCheck != 0 && a == -1) {
                            createBoard(newBoard, idOfCharacters);
                            printBoard(newBoard, myWriter);
                            createStatus(idOfCharacters, myWriter);
                            flag2++;
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            break;
                        }

                        ctForErrorCheck++;
                        if(a!= 1 && a!=2)
                            idOfCharacters.get(id).normalAttack(id, idOfCharacters, idOfCharacters.get(id).getPosition()[1], idOfCharacters.get(id).getPosition()[0]);
                        if(a == 1 || a == 2){break;}
                    }

                } else if (idOfCharacters.get(id).getClass().getSimpleName().equals("Ork")) {
                    for (int[] arr : directions) {


                        int a = idOfCharacters.get(id).move(arr, boardSize, idOfCharacters);

                        myWriter.write("\n");

                        if (ctForErrorCheck == 0 && a == -1) {
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            flag++;
                            break;
                        } else if (ctForErrorCheck != 0 && a == -1) {
                            createBoard(newBoard, idOfCharacters);
                            printBoard(newBoard, myWriter);
                            createStatus(idOfCharacters, myWriter);
                            flag2++;
                            try {
                                throw new BoardBoundaryException();
                            } catch (BoardBoundaryException exception){
                                myWriter.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                            }
                            break;
                        }

                        idOfCharacters.get(id).heal(idOfCharacters, idOfCharacters.get(id).getPosition()[1] - arr[1], idOfCharacters.get(id).getPosition()[0] - arr[0]);
                        //idOfCharacters.get(id).increaseHealth(10);
                        healCheck(idOfCharacters);

                        ctForErrorCheck++;
                        if(a != 1 && a!= 2)
                            idOfCharacters.get(id).normalAttack(id, idOfCharacters, idOfCharacters.get(id).getPosition()[1], idOfCharacters.get(id).getPosition()[0]);

                    }
                }


                if (flag > 0) {
                    continue;
                }

                int[] stat = isOver(idOfCharacters);

                if(flag2 == 0) {
                    createBoard(newBoard, idOfCharacters);
                    printBoard(newBoard, myWriter);
                    createStatus(idOfCharacters, myWriter);
                }
                //myWriter.write("\n");

                if(stat[0] == 0){
                    myWriter.write("\nGame Finished\nZorde Wins");
                    break;
                }else if(stat[1] == 0){
                    myWriter.write("\nGame Finished\nCalliance Wins");
                    break;
                }


            }


        }


        myWriter.close();
        initials.close();

    }

    public static void createBoard(String[][] board, HashMap<String, Characters> charactersHashMap) {
        //for (String name : charactersHashMap.keySet()) {
         //   if (charactersHashMap.get(name).getCharacterHP() <= 0) {
      //          charactersHashMap.remove(name);
         //   }
        //}


        for (int i = 0; i < board.length; i++) {
            board[i][0] = "*";
            board[i][board.length - 1] = "*";
        }

        for (int i = 1; i < board.length - 1; i++) {
            board[0][i] = "**";
            board[board.length - 1][i] = "**";
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    board[i][j] = "  ";
                }
            }
        }

        for (String id : charactersHashMap.keySet()) {
            if (charactersHashMap.get(id).getCharacterHP() > 0) {
                int[] position = charactersHashMap.get(id).getPosition();

                int vert = position[0];
                int horizontal = position[1];

                board[horizontal][vert] = id;
            }
        }

    }

    public static void createStatus(HashMap<String, Characters> charactersHashMap, FileWriter writer) throws IOException {

        ArrayList<Characters> characters = new ArrayList<>();

        characters.addAll(charactersHashMap.values());

        Collections.sort(characters);

        int maxHp = 0;

        writer.write("\n");


        for (Characters ch : characters) {
            if (ch.getClass().getSimpleName().equals("Elf")) {
                maxHp = Constants.elfHP;
            } else if (ch.getClass().getSimpleName().equals("Human")) {
                maxHp = Constants.humanHP;
            } else if (ch.getClass().getSimpleName().equals("Dwarf")) {
                maxHp = Constants.dwarfHP;
            } else if (ch.getClass().getSimpleName().equals("Ork")) {
                maxHp = Constants.orkHP;
            } else if (ch.getClass().getSimpleName().equals("Troll")) {
                maxHp = Constants.trollHP;
            } else if (ch.getClass().getSimpleName().equals("Goblin")) {
                maxHp = Constants.goblinHP;
            }
            if(ch.getCharacterHP() > 0)
                //System.out.println("Character class " + ch.getClass().getSimpleName() + " Character Hp : " + ch.getCharacterHP());
                writer.write(ch.getCharacterName() + "\t" + ch.getCharacterHP() + "\t" + "(" + maxHp + ")\n");

        }


    }

    public static boolean errorCheck2(String key, HashMap<String, Characters> charactersHashMap, String[] moves) {

        return charactersHashMap.get(key).moveCheck(moves);
    }

    public static void printBoard(String[][] board, FileWriter writer) throws IOException {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                writer.write(board[i][j]);
            }
            writer.write("\n");
        }
    }

    public static void healCheck(HashMap<String, Characters> charactersHashMap) {
        for (String id : charactersHashMap.keySet()) {
            if (charactersHashMap.get(id).getClass().getSimpleName().equals("Troll")) {
                if (charactersHashMap.get(id).getCharacterHP() > Constants.trollHP) {
                    charactersHashMap.get(id).setCharacterHP(150);
                }
            } else if (charactersHashMap.get(id).getClass().getSimpleName().equals("Goblin")) {
                if (charactersHashMap.get(id).getCharacterHP() > Constants.goblinHP) {
                    charactersHashMap.get(id).setCharacterHP(Constants.goblinHP);
                }
            } else if (charactersHashMap.get(id).getClass().getSimpleName().equals("Ork")) {
                if (charactersHashMap.get(id).getCharacterHP() > Constants.orkHP) {
                    charactersHashMap.get(id).setCharacterHP(Constants.orkHP);
                }
            }
        }
    }

    public static int[] isOver(HashMap<String, Characters> charactersHashMap){
        int cal = 0;
        int zor = 0;
        int[] check = new int[2];
        for(String id : charactersHashMap.keySet()){
            if(charactersHashMap.get(id) instanceof Zorde && charactersHashMap.get(id).getCharacterHP() > 0){
                zor++;
            }else if(charactersHashMap.get(id) instanceof Calliance && charactersHashMap.get(id).getCharacterHP() > 0){
                cal++;
            }
        }
        check[0] = cal;
        check[1] = zor;
        return check;
    }
}
