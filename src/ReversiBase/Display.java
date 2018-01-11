package ReversiBase;

public interface Display {
    void printBoard(Board boardToPrint);
    void printPossibleMoves(Pair[] positions, int moves);
    void printPair(Pair p);
    void printString(String string);
    void itsYourMove(Player p);
    void noPossiblePlayerMove(Player p);
}
