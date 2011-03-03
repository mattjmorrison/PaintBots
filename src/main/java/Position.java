import paintbots.BoardSquare;
import paintbots.InternalBoardSquare;

import java.awt.*;
import java.util.*;

public class Position {
    BoardSquare[][] grid;
    Color color;

    public static class Square {
        private BoardSquare square;
        private Color color;
        public Square(BoardSquare square, Color color){
            this.square = square;
            this.color = color;
        }

        public boolean isBlocked() {
            return     isSquareRock()
                    || isSquareWall()
                    || isSquareFogRock()
                    || square.redRobotPresent()
                    || square.blueRobotPresent();
        }

        public boolean isSquareFogRock() {
            return square.getSquareType().equals(InternalBoardSquare.SquareType.FOGROCK);
        }

        public boolean isSquareWall() {
            return square.getSquareType().equals(InternalBoardSquare.SquareType.WALL);
        }

        public boolean isSquareRock() {
            return square.getSquareType().equals(InternalBoardSquare.SquareType.ROCK);
        }

        public boolean isOpponentsSquare() {
            return square.getSquareColor().equals(getOpponentsColor());
        }

        public boolean isMySquare(){
            return square.getSquareColor().equals(color);
        }

        public Color getOpponentsColor() {
            return color == Color.RED ? Color.BLUE : Color.RED;
        }        

        public BoardSquare getSquare(){
            return square;
        }
    }

    public static class SurroundingSquares {
        Square front;
        Square right;
        Square behind;
        Square left;

        public SurroundingSquares(Square front, Square right, Square behind, Square left){
            this.front = front;
            this.right = right;
            this.behind = behind;
            this.left = left;
        }

        public java.util.List<BoardSquare> getSquares(){
            return Arrays.asList(front.getSquare(), right.getSquare(), behind.getSquare(), left.getSquare());
        }

        public boolean areMine() {
            return front.isMySquare() && behind.isMySquare() && left.isMySquare() && right.isMySquare();
        }
    }

    public Position(BoardSquare[][] grid, Color color){
        this.grid = grid;
        this.color = color;
    }

    public Square onRight() {
        int center = grid.length / 2;
        return new Square(grid[center][center + 1], color);
    }

    public Square onLeft() {
        int center = grid.length / 2;
        return new Square(grid[center][center - 1], color);
    }

    public Square inFront() {
        int center = grid.length / 2;
        return new Square(grid[center - 1][center], color);
    }

    public Square behind() {
        int center = grid.length / 2;
        return new Square(grid[center + 1][center], color);
    }

    public SurroundingSquares allSurrounding() {
        return new SurroundingSquares(inFront(), onLeft(), behind(), onRight());
    }


}
