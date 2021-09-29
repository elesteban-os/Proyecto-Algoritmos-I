package List;

public class DoubleLinkedList {

    private DoubleNode head;
    private DoubleNode tail;

    /**
     * Constructor that creates an empty DoubleLinkedList with null head and tail
     */
    public DoubleLinkedList(){
        head = null;
        tail = null;
    }

    /**
     * @return true if the DoubleLinkedList is empty, false if it isn't
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * @param square a Square to add to the DoubleLinkedList
     */
    public void addSquare(Square square){
        DoubleNode n = new DoubleNode(tail, square, null);
        if (isEmpty()){
            head = tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
    }

    /**
     * @return the DoubleLinkedList's head
     */
    public DoubleNode getHead(){
        return this.head;
    }
}
