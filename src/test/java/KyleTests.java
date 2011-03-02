import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import paintbots.BoardSquare;
import paintbots.InternalBoardSquare;
import paintbots.MoveRequest;

import java.awt.*;
import java.lang.reflect.Method;

public class KyleTests {
    private Kyle kyle;
    private BoardSquare normal = new TestBoardSquare(InternalBoardSquare.SquareType.NORMAL);
    private BoardSquare rock = new TestBoardSquare(InternalBoardSquare.SquareType.ROCK);
    private BoardSquare wall = new TestBoardSquare(InternalBoardSquare.SquareType.WALL);
    private BoardSquare fogRock = new TestBoardSquare(InternalBoardSquare.SquareType.FOGROCK);
    private BoardSquare opponents = new TestBoardSquare(Color.BLUE);
    private BoardSquare mine = new TestBoardSquare(Color.RED);

    private MoveRequest.MoveType getMoveType(MoveRequest request){
        try {
            Method m = request.getClass().getDeclaredMethod("getMovetype");
            m.setAccessible(true);
            return (MoveRequest.MoveType)m.invoke(request);
        }
        catch (Throwable t){
            return null;
        }
    }

    @Before
    public void setUp(){
        kyle = new Kyle();
        kyle.reset(Color.RED);
    }

    @Test
    public void shouldMoveFoward(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal,normal,normal},
            {normal,normal,normal},
            {normal,normal,normal}
        };
        MoveRequest request = kyle.getMove(scan, null);
        MoveRequest.MoveType type = this.getMoveType(request);
        assertEquals(type, MoveRequest.MoveType.FORWARD);
    }

    @Test
    public void shouldTurnRightWhenForwardIsBlockedByRock(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal,rock,normal},
            {normal,normal,normal},
            {normal,normal,normal}
        };
        MoveRequest request = kyle.getMove(scan, null);
        MoveRequest.MoveType type = this.getMoveType(request);
        assertEquals(MoveRequest.MoveType.ROTATE_RIGHT, type);
    }

    @Test
    public void shouldTurnRightWhenForwardIsBlockedByWall(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal,wall,normal},
            {normal,normal,normal},
            {normal,normal,normal}
        };
        MoveRequest request = kyle.getMove(scan, null);
        MoveRequest.MoveType type = this.getMoveType(request);
        assertEquals(MoveRequest.MoveType.ROTATE_RIGHT, type);
    }

    @Test
    public void shouldTurnRightWhenForwardIsBlockedByFogRock(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal,fogRock,normal},
            {normal,normal,normal},
            {normal,normal,normal}
        };
        MoveRequest request = kyle.getMove(scan, null);
        MoveRequest.MoveType type = this.getMoveType(request);
        assertEquals(MoveRequest.MoveType.ROTATE_RIGHT, type);
    }

    @Test
    public void shouldTurnLeftWhenForwardAndRightAreBlocked(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal,rock,normal},
            {normal,normal,rock},
            {normal,normal,normal}
        };
        MoveRequest request = kyle.getMove(scan, null);
        MoveRequest.MoveType type = this.getMoveType(request);
        assertEquals(MoveRequest.MoveType.ROTATE_LEFT, type);
    }

    @Test
    public void shouldGetBoardSquareInFrontOfCurrentPosition(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal, rock, normal},
            {normal, normal, normal},
            {normal, normal, normal}
        };

        assertEquals(rock, kyle.getBoardSquareInFront(scan));
    }

    @Test
    public void shouldGetBoardSquareToRightOfCurrentPosition(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal, normal, normal},
            {normal, normal, rock},
            {normal, normal, normal}
        };

        assertEquals(rock, kyle.getBoardSquareToRight(scan));
    }

    @Test
    public void shouldGetBoardSquareToLeftOfCurrentPosition(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal, normal, normal},
            {rock, normal, normal},
            {normal, normal, normal}
        };

        assertEquals(rock, kyle.getBoardSquareToLeft(scan));
    }

    @Test
    public void shouldGetBoardSquareInFrontOfCurrentPositionWithLargerGrid(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {normal, normal, normal, normal, normal},
            {normal, normal, rock, normal, normal},
            {normal, normal, normal, normal, normal},
            {normal, normal, normal, normal, normal},
            {normal, normal, normal, normal, normal},
        };

        assertEquals(rock, kyle.getBoardSquareInFront(scan));
    }

    @Test
    public void shouldReturnTrueWhenSquareHasOpponentsColor(){
        assertTrue(kyle.isOpponentsSquare(opponents));
    }

    @Test
    public void shouldReturnFalseWhenSquareHasMyColor(){
        assertFalse(kyle.isOpponentsSquare(mine));
    }

    @Test
    public void shouldGetOpponentsColor(){
        assertEquals(Color.BLUE, kyle.getOpponentsColor());
    }

    @Test
    public void shouldMoveToLeftWhenRightHasOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {rock, rock, rock},
            {normal, normal, mine},
            {normal, normal, normal}
        };
        MoveRequest request = kyle.getMove(scan, null);
        MoveRequest.MoveType type = this.getMoveType(request);
        assertEquals(MoveRequest.MoveType.ROTATE_LEFT, type);
    }

    @Test
    public void shouldMoveToRightWhenLeftHasOwnColor(){
        BoardSquare[][] scan = new BoardSquare[][] {
            {rock, rock, rock},
            {mine, normal, normal},
            {normal, normal, normal}
        };
        MoveRequest request = kyle.getMove(scan, null);
        MoveRequest.MoveType type = this.getMoveType(request);
        assertEquals(MoveRequest.MoveType.ROTATE_RIGHT, type);
    }
}
