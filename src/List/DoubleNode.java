package List;

public class DoubleNode {

    private DoubleNode prev;
    private DoubleNode next;
    private Square square;

    /**
     * Constructor
     * @param p previous DoubleNode
     * @param square a Square for this DoubleNode
     * @param n next DoubleNode
     */

    public DoubleNode(DoubleNode prev, Square square, DoubleNode next){
        this.prev = prev;
        this.square = square;
        this.next = next;
    }

    /**
     * @return this DoubleNode's Square
     */
    public Square getSquare() {
        return this.square;
    }

    /**
     * @return previous DoubleNode if it exists, null otherwise
     */
    public DoubleNode getPrev() {
        return prev;
    }

    /**
     * @param prev DoubleNode to be placed before this one
     */
    public void setPrev(DoubleNode prev) {
        this.prev = prev;
    }

    /**
     * @return next DoubleNode if it exists, null otherwise
     */
    public DoubleNode getNext() {
        return next;
    }

    /**
     * @param next DoubleNode to be placed after this one
     */
    public void setNext(DoubleNode next) {
        this.next = next;
    }
}
