package List;

public class DoubleNode {

    private DoubleNode prev;
    private DoubleNode next;
    private Box box;

    public DoubleNode(Box box){
        this(null, box, null);
    }

    public DoubleNode(DoubleNode p, Box box, DoubleNode n){
        this.prev = p;
        this.box = box;
        this.next = n;
    }

    public Box getBox() {
        return this.box;
    }

    public DoubleNode getPrev() {
        return prev;
    }

    public void setPrev(DoubleNode prev) {
        this.prev = prev;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }
}
