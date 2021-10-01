package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowableMoves = new ArrayList<>();

        addAvailableSpaces(from, board, allowableMoves);

        return allowableMoves;
    }

    private void addAvailableSpaces(Coordinates from, Board board, List<Move> initAllowableMoves) {
        int[] incrementArray = {-1, 0, 1};
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        for (int i: incrementArray) {
            int row = fromRow + i;
            for (int j: incrementArray) {
                int col = fromCol + j;

                if (row >= 0 && row <= 7 && col >= 0 && col <= 7) {
                    Coordinates to = new Coordinates(row, col);
                    Piece targetPiece = board.get(to);
                    if (targetPiece == null || targetPiece.getColour() != this.colour) {
                        initAllowableMoves.add(new Move(from, to));
                    }
                }

            }
        }
    }
}
