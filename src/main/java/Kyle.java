import paintbots.BoardSquare;
import paintbots.InternalBoardSquare;
import paintbots.MoveRequest;
import paintbots.PaintbotControl;

import java.awt.*;

public class Kyle extends PaintbotControl {
    private static final String name = "Kyle";
    private Color color;

    @Override
    public MoveRequest getMove(BoardSquare[][] shortScan, BoardSquare[][] longScan) {
        MoveRequest.MoveType type = MoveRequest.MoveType.FORWARD;

        if (isBlocked(getBoardSquareInFront(shortScan))){
            BoardSquare right = getBoardSquareToRight(shortScan);
            if (isBlocked(right) || right.getSquareColor().equals(color))
                type = MoveRequest.MoveType.ROTATE_LEFT;
            else
                type = MoveRequest.MoveType.ROTATE_RIGHT;
        }

        return new MoveRequest(type, false, false);
    }

    private boolean isBlocked(BoardSquare square) {
        return isSquareRock(square) || isSquareWall(square) || isSquareFogRock(square);
    }

    private boolean isSquareFogRock(BoardSquare square) {
        return square.getSquareType().equals(InternalBoardSquare.SquareType.FOGROCK);
    }

    private boolean isSquareWall(BoardSquare square) {
        return square.getSquareType().equals(InternalBoardSquare.SquareType.WALL);
    }

    private boolean isSquareRock(BoardSquare square) {
        return square.getSquareType().equals(InternalBoardSquare.SquareType.ROCK);
    }

    public BoardSquare getBoardSquareInFront(BoardSquare[][] scan) {
        int center = scan.length / 2;
        return scan[center - 1][center];
    }

    public BoardSquare getBoardSquareToRight(BoardSquare[][] scan) {
        int center = scan.length / 2;
        return scan[center][center + 1];
    }

    public BoardSquare getBoardSquareToLeft(BoardSquare[][] scan) {
        int center = scan.length / 2;
        return scan[center][center - 1];
    }

    public boolean isOpponentsSquare(BoardSquare square) {
        return square.getSquareColor().equals(getOpponentsColor());
    }

    public Color getOpponentsColor() {
        return color == Color.RED ? Color.BLUE : Color.RED;
    }

    @Override
    public String getRobotName() {
        return Kyle.name;
    }

    @Override
    public String getStudentLastName() {
        return Kyle.name;
    }

    @Override
    public String getStudentID() {
        return Kyle.name;
    }

    @Override
    public boolean tournamentEntry() {
        return false;
    }

    @Override
    public void reset(Color color) {
        this.color = color;
    }
}

