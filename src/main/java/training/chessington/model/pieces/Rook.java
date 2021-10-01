package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Move> allowableMoves = new ArrayList<>();
        addEmptySpaces(from, board, allowableMoves, 1, 0);   // down
        addEmptySpaces(from, board, allowableMoves, -1, 0);   // up
        addEmptySpaces(from, board, allowableMoves, 0, 1);   // right
        addEmptySpaces(from, board, allowableMoves, 0, -1);   // left


        return allowableMoves;
    }

    private void addEmptySpaces(Coordinates from, Board board, List<Move> initAllowableMoves, int vertical, int horizontal) {
        int row = from.getRow() + vertical;
        int col = from.getCol() + horizontal;

        while (row > -1 && row < 8 && col > -1 && col < 8) {
            Coordinates currentLookUpSquareCoords = new Coordinates(row, col);
            // quick exit
            if (board.get(currentLookUpSquareCoords) != null) {

                // if an enemy then allow capture and add to list of allowable moves
                if (board.get(currentLookUpSquareCoords).getColour() != this.colour) {
                    initAllowableMoves.add(new Move(from, currentLookUpSquareCoords));
                }

                break;
            }
            initAllowableMoves.add(new Move(from, currentLookUpSquareCoords));
            row += vertical;
            col += horizontal;
        }
    }
}
