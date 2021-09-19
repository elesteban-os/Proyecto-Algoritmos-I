package List;

public class DoubleNode {

    private DoubleNode prev;
    private DoubleNode next;
    private String type;

    public DoubleNode(String type){
        this(null, type, null);
    }

    public DoubleNode(DoubleNode p, String type, DoubleNode n){
        this.prev = p;
        this.type = type;
        this.next = n;
    }

    public String getBoxType() {
        return type;
    }

    public void setBoxType(String type) {
        this.type = type;
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
