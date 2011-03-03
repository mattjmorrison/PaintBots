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
        Position position = new Position(shortScan, color);
        MoveRequest.MoveType type = getLevelOneMove(position);

        if (type == null)
            type = getLevelTwoMove(position);

        if (type == null)
            type = getLevelThreeMove(position);

        if (type == null)
            type = MoveRequest.MoveType.ROTATE_RIGHT;

        return new MoveRequest(type, false, false);
    }

    public MoveRequest.MoveType getLevelOneMove(Position position) {
        MoveRequest.MoveType type = null;

        if (!position.inFront().isBlocked() && position.inFront().isOpponentsSquare())
            type = MoveRequest.MoveType.FORWARD;
        else if (!position.onLeft().isBlocked() && position.onLeft().isOpponentsSquare())
            type = MoveRequest.MoveType.ROTATE_LEFT;
        else if (!position.onRight().isBlocked() && position.onRight().isOpponentsSquare())
            type = MoveRequest.MoveType.ROTATE_RIGHT;

        return type;
    }

    public MoveRequest.MoveType getLevelTwoMove(Position position) {
        MoveRequest.MoveType type = null;

        if (!position.inFront().isBlocked() && !position.inFront().isMySquare())
            type = MoveRequest.MoveType.FORWARD;
        else if(!position.onLeft().isBlocked() && !position.onLeft().isMySquare())
            type = MoveRequest.MoveType.ROTATE_LEFT;
        else if (!position.onRight().isBlocked() && !position.onRight().isMySquare())
            type = MoveRequest.MoveType.ROTATE_RIGHT;

        return type;
    }    

    public MoveRequest.MoveType getLevelThreeMove(Position position) {
        MoveRequest.MoveType type = null;

        if (!position.inFront().isBlocked())
            type = MoveRequest.MoveType.FORWARD;
        else if (!position.onRight().isBlocked())
            type = MoveRequest.MoveType.ROTATE_RIGHT;
        else if (!position.onLeft().isBlocked())
            type = MoveRequest.MoveType.ROTATE_LEFT;

        return type;        
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

