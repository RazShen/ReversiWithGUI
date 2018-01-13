package ReversiBase;


import javafx.scene.paint.Color;

public class RegularGameLogic extends GameLogic {


    public RegularGameLogic(Board board, Color startingColor, Color notStartingColor) {
        this.board = board;
        this.notStartingColor = notStartingColor;
        this.startingColor = startingColor;
    }

    @Override
    public boolean validMove(Pair p, GameLogic.ScanDirection scanD, Color opponentP, Color player) {
        int x = p.getRow();
        int y = p.getCol();
        int size = this.getBoardSize();
        switch (scanD) {
            case NorthWest:
                for (; x >= 0 && y >= 0; x--, y--) {
                    if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                        continue;
                    } else return
                            this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString());
                }
                return false;
            case North:
                for (; x >= 0 && y >= 0; x--) {
                    if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                        continue;
                    } else return
                            this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString());
                }
                return false;
            case NorthEast:
                for (; x >= 0 && y < size; x--, y++) {
                    if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                        continue;
                    } else return
                            this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString());
                }
                return false;
            case West:
                for (; x >= 0 && y >= 0; y--) {
                    if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                        continue;
                    } else return
                            this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString());
                }
                return false;
            case East:
                for (; x >= 0 && y < size; y++) {
                    if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                        continue;
                    } else return
                            this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString());
                }
                return false;
            case SouthWest:
                for (; x < size && y >= 0; x++, y--) {
                    if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                        continue;
                    } else return
                            this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString());
                }
                return false;
            case South:
                for (; x < size && y >= 0; x++) {
                    if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                        continue;
                    } else return
                            this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString());
                }
                return false;
            case SouthEast:
                for (; x < size && y < size; x++, y++) {
                    if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                        continue;
                    } else return
                            this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString());
                }
                return false;
        }
        return false;
    }

    @Override
    public int possibleMoves(Pair pairArr[], int index, Color player) {
        int size = this.getBoardSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board.getCellStatus(new Pair(i, j)).isEmpty()) {
                    if (player.toString().equals(this.startingColor.toString())) {
                        if (checkCell(new Pair(i, j), this.notStartingColor, this.startingColor)) {
                            pairArr[index] = new Pair(i, j);
                            index++;
                        }
                    } else {
                        if (checkCell(new Pair(i, j), this.startingColor, this.notStartingColor)) {
                            pairArr[index] = new Pair(i, j);
                            index++;
                        }
                    }
                }
            }
        }
        return index;
    }

    @Override
    public boolean checkCell(Pair p, Color opponentP, Color player) {
        int size = this.getBoardSize();
        if (p.getRow() > 0 && p.getCol() > 0 &&
                this.board.getCellStatus(new Pair(p.getRow() - 1, p.getCol() - 1))
                        .getColor().toString().equals(opponentP.toString())) {
            //scan north-west
            if (validMove(new Pair(p.getRow() - 1, p.getCol() - 1), ScanDirection.NorthWest, opponentP, player)) {
                return true;
            }
        }
        if (p.getRow() > 0 &&
                this.board.getCellStatus(new Pair(p.getRow() - 1, p.getCol()))
                        .getColor().toString().equals(opponentP.toString())) {
            //scan north
            if (validMove(new Pair(p.getRow() - 1, p.getCol()), ScanDirection.North, opponentP, player)) {
                return true;
            }
        }
        if (p.getRow() > 0 && p.getCol() < size - 1 &&
                this.board.getCellStatus(new Pair(p.getRow() - 1, p.getCol() + 1))
                        .getColor().toString().equals(opponentP.toString())) {
            //scan north-east
            if (validMove(new Pair(p.getRow() - 1, p.getCol() + 1), ScanDirection.NorthEast, opponentP, player)) {
                return true;
            }
        }
        if (p.getCol() > 0 && this.board.getCellStatus(new Pair(p.getRow(), p.getCol() - 1))
                .getColor().toString().equals(opponentP.toString())) {
            //scan west
            if (validMove(new Pair(p.getRow(), p.getCol() - 1), ScanDirection.West, opponentP, player)) {
                return true;
            }
        }
        if (p.getCol() < size - 1 && this.board.getCellStatus(new Pair(p.getRow(), p.getCol() + 1))
                .getColor().toString().equals(opponentP.toString())) {
            //scan east
            if (validMove(new Pair(p.getRow(), p.getCol() + 1), ScanDirection.East, opponentP, player)) {
                return true;
            }
        }
        if (p.getRow() < size - 1 && p.getCol() > 0 &&
                this.board.getCellStatus(new Pair(p.getRow() + 1, p.getCol() - 1))
                        .getColor().toString().equals(opponentP.toString())) {
            //scan south-west
            if (validMove(new Pair(p.getRow() + 1, p.getCol() - 1), ScanDirection.SouthWest, opponentP, player)) {
                return true;
            }
        }
        if (p.getRow() < size - 1 && this.board.getCellStatus(new Pair(p.getRow() + 1, p.getCol()))
                .getColor().toString().equals(opponentP.toString())) {
            //scan south
            if (validMove(new Pair(p.getRow() + 1, p.getCol()), ScanDirection.South, opponentP, player)) {
                return true;
            }
        }
        if (p.getRow() < size - 1 && p.getCol() < size - 1 &&
                this.board.getCellStatus(new Pair(p.getRow() + 1, p.getCol() + 1))
                        .getColor().toString().equals(opponentP.toString())) {
            //scan south-east
            if (validMove(new Pair(p.getRow() + 1, p.getCol() + 1), ScanDirection.SouthEast, opponentP, player)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void flipCell(Pair p, Color opponentP, Color player) {
        this.board.changeStatus(new Pair(p.getRow() - 1, p.getCol() - 1), player);
        flipAllDirections(p,opponentP, player);
    }

    @Override
    public boolean checkInput(Pair p, Pair arr[], int count) {
        for (int i = 0; i < count; i++) {
            if (p.getRow() - 1 == arr[i].getRow() && p.getCol() - 1 == arr[i].getCol()) {
                return true;
            }
        }
        return false;
    }

    private void flipDirection(Pair p, Color opponentP, Color player, int xDirection, int yDirection) {
        int size = this.getBoardSize();
        Pair maybeFlipArr[] = new Pair[size];
        int x = p.getRow() - 1;
        int y = p.getCol() - 1;
        int count = -1;
        while (x < size && y < size && x >= 0 && y >= 0) {
            if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(opponentP.toString())) {
                count++;
                maybeFlipArr[count] = new Pair(x, y);
            } else if (this.board.getCellStatus(new Pair(x, y)).getColor().toString().equals(player.toString())) {
                for (int i = 0; i <= count; i++) {
                    this.board.changeStatus(new Pair(maybeFlipArr[i].getRow(), maybeFlipArr[i].getCol()), player);
                }
                if (count != -1) {
                    break;
                }
            } else {
                break;
            }
            x = x + xDirection;
            y = y + yDirection;
        }
    }

    private void flipAllDirections(Pair p, Color opponentP, Color player) {
        flipDirection(p, opponentP, player, 1, 1);
        flipDirection(p, opponentP, player, 1, 0);
        flipDirection(p, opponentP, player, 1, -1);
        flipDirection(p, opponentP, player, 0, -1);
        flipDirection(p, opponentP, player, 0, 1);
        flipDirection(p, opponentP, player, -1, -1);
        flipDirection(p, opponentP, player, -1, 1);
        flipDirection(p, opponentP, player, -1, 0);
    }
}
