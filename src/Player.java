import java.awt.*;

public interface Player {
    Pair getMove(Pair positions[], int moves, Color opponentStat, Display display);
    void noMove(Display display);
    boolean isStarter();
    Color getColor();
    void setColor();

}
