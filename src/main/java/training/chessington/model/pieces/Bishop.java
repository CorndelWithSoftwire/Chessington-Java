package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowableMoves = new ArrayList<>();

        addTheDiagonal(from, board, allowableMoves);

        return allowableMoves;
    }

    private void addTheDiagonal(Coordinates from, Board board, List<Move> initAllowableMoves) {
        int[] incrementArray = {-1, 1};
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        for (int i: incrementArray) {
            for (int j: incrementArray) {
                int row = fromRow + i;
                int col = fromCol + j;
                while (row >= 0 && row <= 7 && col >= 0 && col <= 7) {
                    Coordinates to = new Coordinates(row, col);
                    Piece targetPiece = board.get(to);

                    if (targetPiece != null) {
                        if (targetPiece.getColour() != this.colour) { initAllowableMoves.add(new Move(from, to)); }
                        break;
                    }

                    initAllowableMoves.add(new Move(from, to));
                    row += i;
                    col += j;
                }
            }
        }
    }
}
