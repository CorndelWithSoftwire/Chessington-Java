package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowableMoves = new ArrayList<>();
        List<Coordinates> possibleTo = new ArrayList<>();

        addAvailableSpaces(from, board, possibleTo);
        removeCheckMateLocations(from, board, allowableMoves, possibleTo);

        return allowableMoves;
    }

    private void addAvailableSpaces(Coordinates from, Board board, List<Coordinates> initPossibleTo) {
        int[] incrementArray = {-1, 0, 1};
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        for (int i : incrementArray) {
            int row = fromRow + i;
            for (int j : incrementArray) {
                int col = fromCol + j;

                if (row >= 0 && row <= 7 && col >= 0 && col <= 7) {
                    Coordinates to = new Coordinates(row, col);
                    Piece targetPiece = board.get(to);
                    if (targetPiece == null || targetPiece.getColour() != this.colour) {
                        initPossibleTo.add(to);
                    }
                }

            }
        }
    }

    private void removeCheckMateLocations(Coordinates from, Board board,
                                          List<Move> initAllowableMoves, List<Coordinates> initPossibleTo) {
        List<Coordinates> enemyTo = new ArrayList<>();
        List<Move> filteredListOfMoves = new ArrayList<>();

        // temporarily remove king
        board.setBoardPositionNull(from);

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Coordinates coords = new Coordinates(i, j);
                Piece p = board.get(coords);
                if (p != null && p.getColour() != this.colour && p.getType() != PieceType.KING) {
//                    boardHashMap.put(coords, p);
//                    enemyTeamMoves.addAll(p.getAllowedMoves(coords, board));

                    // if pawn then add back king
                    if (p.getType() == PieceType.PAWN) {
                        board.placePiece(from, this);

                        int pawnDirection = (p.getColour() == PlayerColour.WHITE ? -1 : 1);

                        Coordinates pawnCaptureLeftCoords = coords.plus(pawnDirection, 1);
                        Coordinates pawnCaptureRightCoords = coords.plus(pawnDirection, -1);
                        enemyTo.add(pawnCaptureLeftCoords);
                        enemyTo.add(pawnCaptureRightCoords);
                        p.getAllowedMoves(coords, board).stream().forEach(m -> enemyTo.add(m.getTo()));

                        // remove king
                        board.setBoardPositionNull(from);
                    } else {
                        p.getAllowedMoves(coords, board).stream().forEach(m -> enemyTo.add(m.getTo()));
                    }

                }
            }
        }

        // place back the king
        board.placePiece(from, this);

        // filter our checkout loc
//        for (Move m: initAllowableMoves) {
//            if (enemyTo.contains(m.getTo())) {
//                System.out.println("oh no");
////                filteredListOfMoves.add(m);
//                System.out.println(m.getTo());
//            }
//        }

        for (Coordinates coords: initPossibleTo) {
            if (!enemyTo.contains(coords)) {
                initAllowableMoves.add(new Move(from, coords));
            }
        }

    }
}


