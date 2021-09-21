package List;

public class DoubleLinkedList {

    private DoubleNode head;
    private DoubleNode tail;

    public DoubleLinkedList(){
        head = null;
        tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void addBox(Box box){
        DoubleNode n = new DoubleNode(tail, box, null);
        if (isEmpty()){
            head = tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
    }

    public DoubleNode getHead(){
        return this.head;
    }
}
