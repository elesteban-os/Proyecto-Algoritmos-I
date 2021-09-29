package List;

public class SquareFactory {
    
    private StringBuilder boardID;
    private DoubleLinkedList board;

    /**
     * Creates a string with the kinds of Squares that will be added
     */
    public String generateBoard() {
        this.boardID = new StringBuilder();
        int traps = (int) (Math.random() * 7);
        int tunnels = 8 - traps;
        int challenges = 8;
        for (int i = 0; i < 16; i++) {
            String kind = "";
            if (i == 0 || i == 15) {
                kind = "Goal";
                tunnels --;
            } else {
                boolean generated = false;
                while (!generated) {
                    int square = (int) (Math.random() * 3) + 1;
                    if (square == 1 && tunnels > 1) {
                        kind = "Tunnel";
                        tunnels--;
                        generated = true;
                    } else if (square == 2 && traps > 0) {
                        kind = "Trap";
                        traps--;
                        generated = true;
                    } else if (challenges > 0) {
                        kind = "Challenge";
                        challenges--;
                        generated = true;
                    }
                }
            }
            if (i < 15) {
                this.boardID.append(kind).append(",");
            } else {
                this.boardID.append(kind);
            }
        }
        return this.boardID.toString();
    }

    /**
     * @param id String with the kinds of Squares to be added
     * @return DoubleLinkedList with the specified Squares
     */
    public DoubleLinkedList createBoard(String id) {
        this.board = new DoubleLinkedList();
        String[] boxArray = id.split(",");
        for (int i = 0; i < boxArray.length; i++) {
            int x;
            if (i < 4 || (8 <= i && i < 12)) {
                x = 70 + ((i % 4) * 92);
            } else {
                x = 346 - ((i % 4) * 92);
            }
            int y = 130 + ((i / 4) * 92);
            this.board.addSquare(new Square(boxArray[i], x, y));
        }
        return this.board;
    }
}
