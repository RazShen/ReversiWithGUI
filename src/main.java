public class main {
    public static void main(String[] args) {
        Game game;
        Display display = new ConsoleDisplay();
        Menu menu = new Menu();
        menu.parseMenuFromText();
        GameLogic gameLogic = new RegularGameLogic(menu.getBoardSize(), menu.getBlackPlayerGameColor(),
                menu.getWhitePlayerGameColor());
        if (menu.isBlackStart()) {
            game = new Game(gameLogic, display, menu.getBlackPlayerGameColor(), menu.getWhitePlayerGameColor());
        } else {
            game = new Game(gameLogic, display, menu.getWhitePlayerGameColor(), menu.getBlackPlayerGameColor());
        }
        game.run();

    }
}
