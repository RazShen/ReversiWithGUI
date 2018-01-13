package ReversiBase;

public class Pair {
    private int row, column;
    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow()  {
        return this.row;
    }

    public int getCol()  {
        return this.column;
    }
}
