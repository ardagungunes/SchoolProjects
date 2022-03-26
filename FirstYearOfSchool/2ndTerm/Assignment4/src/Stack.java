import java.util.ArrayList;

public class Stack<E> {
    private ArrayList<E> parts = new ArrayList<E>();


    public void push(E item){
        this.parts.add(item);
    }

    public void pop(){

        if(this.parts.size() != 0) {
            int lastElementIndex = this.parts.size() - 1;
            this.parts.remove(lastElementIndex);
        }
    }


    public ArrayList<E> getItems(){
        return this.parts;
    }


}
