package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Pawn extends AbstractPiece {

    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Move> allowableMoves = new ArrayList<>();
        int direction = (this.getColour() == PlayerColour.WHITE ? -1: 1);

        // check if at end of boundary TOP and BOTTOM
        boolean atEndOfBoard = boundaryCheck(from.getRow());
        if (atEndOfBoard) { return allowableMoves; }

        // check for nearby enemies to capture
        captureNearbyEnemy(from, board, direction,allowableMoves);

        // check for obstruction directly in front
        boolean pieceInfrontExists = pieceInfront(from, board, direction);
        if (pieceInfrontExists) { return allowableMoves; }


        //  ########     standard move move place forward

        // make the new coordinate object
        Coordinates to = from.plus(direction, 0);
        // add move object to list of move objects
        allowableMoves.add(new Move(from, to));

        //  ########      end of standard move one place forward

        // check if it can move two spaces
        boolean itMoved = hasItMovedAtAll(from.getRow(), direction);
        if (!itMoved) {
            to = from.plus(direction * 2, 0);
            // check if 2 squares away has a piece, only add to list of moves if loc is null
            if (board.get(to) == null) {
                allowableMoves.add(new Move(from, to));
            }
        }

        return allowableMoves;
    }

    public boolean hasItMovedAtAll(int fromRow, int direction) {
        int initRowBlack = 1;
        int initRowWhite = 6;

        // quick exit
        // black and row isnt 1  OR white and row isnt 6 then it moved
        if ( (direction == 1 && fromRow != initRowBlack) | (direction == -1 && fromRow != initRowWhite) ) {
            return true;
        }
        return false;
    }

    private boolean pieceInfront(Coordinates from, Board board, int direction) {
        // if black and one space below is occupied then cannot move
        // if white and one space above is occupied then cannot move
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        Coordinates coordsPieceInFront = new Coordinates(fromRow + direction, fromCol);

        Piece pieceInFront = board.get(coordsPieceInFront);

        if (pieceInFront == null) {
            return false;
        }
        return true;
    }

    private boolean boundaryCheck(int fromRow) {
        if (fromRow == 0 | fromRow == 7) {
            return true;
        }
        return false;
    }

    private void captureNearbyEnemy(Coordinates from, Board board, int direction, List<Move> initAllowableMoves) {
        List<Coordinates> listOfCoords = new ArrayList<>();

        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int forwardRow = fromRow + direction;

        int[] enemyCol = {fromCol - 1, fromCol + 1};

        // add to list of coordinates to check for enemy
        Arrays.stream(enemyCol)
                .filter(c -> (c > -1 && c < 8) )
                .forEach(f -> listOfCoords.add(new Coordinates(forwardRow, f)));

        // if the checked loc is NOT null and of different COLOUR then add it to the allowable moves list
        listOfCoords.stream()
                .filter(c -> (board.get(c) != null && this.getColour() != board.get(c).getColour()))
                .forEach(f -> {initAllowableMoves.add(new Move(from, f));});
    }

}
