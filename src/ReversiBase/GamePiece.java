package ReversiBase;


import javafx.scene.paint.Color;

public class GamePiece {
    private Color color;
    private boolean isEmpty;

    public boolean isEmpty() {
        return isEmpty;
    }

    GamePiece() {
        this.isEmpty = true;
    }

    public Color getColor() {
        if (this.isEmpty) {
            return Color.BISQUE;
        }
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.isEmpty = false;
    }
}
