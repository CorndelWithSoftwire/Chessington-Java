package training.chessington.model;

import training.chessington.model.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Board implements Cloneable {

    public static final int BOARD_SIZE = 8;

    private Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

    private Board() {
    }

    public static Board forNewGame() {
        Board board = new Board();
        board.setBackRow(0, PlayerColour.BLACK);
        board.setBackRow(7, PlayerColour.WHITE);

        for (int col = 0; col < BOARD_SIZE; col++) {
            board.board[1][col] = new Pawn(PlayerColour.BLACK);
            board.board[6][col] = new Pawn(PlayerColour.WHITE);
        }

        return board;
    }

    public static Board empty() {
        return new Board();
    }

    private void setBackRow(int rowIndex, PlayerColour colour) {
        board[rowIndex][0] = new Rook(colour);
        board[rowIndex][1] = new Knight(colour);
        board[rowIndex][2] = new Bishop(colour);
        board[rowIndex][3] = new Queen(colour);
        board[rowIndex][4] = new King(colour);
        board[rowIndex][5] = new Bishop(colour);
        board[rowIndex][6] = new Knight(colour);
        board[rowIndex][7] = new Rook(colour);
    }

    public Piece get(Coordinates coords) {
        return board[coords.getRow()][coords.getCol()];
    }

    public boolean inRange(Coordinates coordinates) {
        return coordinates.getCol() < BOARD_SIZE && coordinates.getCol() >= 0
                && coordinates.getRow() < BOARD_SIZE && coordinates.getRow() >= 0;
    }

    public boolean hasPieceOfColourAt(Coordinates coords, PlayerColour colour) {
        return get(coords) != null && get(coords).getColour() == colour;
    }

    public void move(Coordinates from, Coordinates to) {
        Piece piece = board[from.getRow()][from.getCol()];
        board[to.getRow()][to.getCol()] = piece;
        board[from.getRow()][from.getCol()] = null;
        piece.setMoved();
    }

    public void placePiece(Coordinates coords, Piece piece) {
        board[coords.getRow()][coords.getCol()] = piece;
    }

    public List<Coordinates> getAllPiecePositionsForPlayer(PlayerColour colour) {
        List<Coordinates> positions = new ArrayList<>();
        forEachPiece((coords, piece) -> {
            if (piece.getColour() == colour) {
                positions.add(coords);
            }
        });
        return positions;
    }

    public Coordinates getKingPositionForPlayer(PlayerColour colour) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = board[row][col];
                if (piece != null && piece.getColour() == colour && piece.getType() == Piece.PieceType.KING) {
                    return new Coordinates(row, col);
                }
            }
        }
        return null;
    }

    @Override
    public Board clone() {
        Board other = new Board();
        forEachPiece(((coords, piece) -> other.placePiece(coords, piece.duplicate())));
        return other;
    }

    private void forEachPiece(BiConsumer<Coordinates, Piece> function) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    function.accept(new Coordinates(row, col), piece);
                }
            }
        }
    }
}
