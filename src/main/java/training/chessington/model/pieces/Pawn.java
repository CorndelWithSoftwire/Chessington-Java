package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Pawn extends AbstractPiece {
    private int direction;
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
        this.direction = (this.getColour() == PlayerColour.WHITE ? -1: 1);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowableMoves = new ArrayList<>();

        // check if at end of boundary TOP and BOTTOM
        boolean atEndOfBoard = boundaryCheck(from.getRow());
        if (atEndOfBoard) { return allowableMoves; }

        // check for nearby enemies to capture
        captureNearbyEnemy(from, board, allowableMoves);

        // check for obstruction directly in front
        boolean pieceInfrontExists = pieceInfront(from, board);
        if (pieceInfrontExists) { return allowableMoves; }


        //  ########     standard move one place forward

        // make the new coordinate object
        Coordinates to = from.plus(direction, 0);
        // add move object to list of move objects
        allowableMoves.add(new Move(from, to));

        //  ########      end of standard move one place forward

        // check if it can move two spaces
        hasItMovedAtAll(from, allowableMoves, board);

        return allowableMoves;
    }

    public void hasItMovedAtAll(Coordinates from, List<Move> initAllowableMoves, Board board) {

        int initRowBlack = 1;
        int initRowWhite = 6;
        int fromRow = from.getRow();

        // quick exit
        // black and row isnt 1  OR white and row isnt 6 then it has moved before
        if ( (direction == 1 && fromRow != initRowBlack) | (direction == -1 && fromRow != initRowWhite) ) {
            return;
        }

        Coordinates to = from.plus(direction * 2, 0);
        // check if 2 squares away has a piece, only add to list of moves if loc is null
        if (board.get(to) == null) { initAllowableMoves.add(new Move(from, to)); }


    }

    private boolean pieceInfront(Coordinates from, Board board) {
        // if black and one space below is occupied then cannot move forward
        // if white and one space above is occupied then cannot move forward
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        Coordinates coordsPieceInFront = new Coordinates(fromRow + direction, fromCol);

        Piece pieceInFront = board.get(coordsPieceInFront);

        return pieceInFront != null;
    }

    private boolean boundaryCheck(int fromRow) {
        return fromRow == 0 | fromRow == 7;
    }

    private void captureNearbyEnemy(Coordinates from, Board board, List<Move> initAllowableMoves) {
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
