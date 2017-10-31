package training.chessington.model;

import training.chessington.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static final int SIZE = 8;
    private final Board board;

    private PlayerColour currentPlayer = PlayerColour.WHITE;

    private boolean isEnded = false;

    public Game(Board board) {
        this.board = board;
    }

    public Piece pieceAt(int row, int col) {
        return board.get(new Coordinates(row, col));
    }

    public List<Move> getAllowedMoves(Coordinates from) {
        if (isEnded) {
            return new ArrayList<>();
        }

        Piece piece = board.get(from);
        if (piece == null || piece.getColour() != currentPlayer) {
            return new ArrayList<>();
        }

        return piece.getAllowedMoves(from, board).stream().filter(move -> !turnEndsInCheck(move)).collect(Collectors.toList());
    }

    private boolean turnEndsInCheck(Move move) {
        Board trialBoard = board.clone();
        trialBoard.move(move.getFrom(), move.getTo());
        return isInCheck(currentPlayer, trialBoard);
    }

    public void makeMove(Move move) throws InvalidMoveException {
        if (isEnded) {
            throw new InvalidMoveException("Game has ended!");
        }

        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();

        Piece piece = board.get(from);
        if (piece == null) {
            throw new InvalidMoveException(String.format("No piece at %s", from));
        }

        if (piece.getColour() != currentPlayer) {
            throw new InvalidMoveException(String.format("Wrong colour piece - it is %s's turn", currentPlayer));
        }

        if (!piece.getAllowedMoves(move.getFrom(), board).contains(move)) {
            throw new InvalidMoveException(String.format("Cannot move piece %s from %s to %s", piece, from, to));
        }

        board.move(from, to);

        if (isInCheck(currentPlayer, board)) {
            throw new InvalidMoveException(currentPlayer + " cannot end their move in check!");
        }
        currentPlayer = currentPlayer.getOpposite();
    }

    public boolean isEnded() {
        return isEnded;
    }

    public String getResult() {
        return null;
    }

    private static boolean isInCheck(PlayerColour player, Board board) {
        List<Coordinates> otherPlayerPositions = board.getAllPiecePositionsForPlayer(player.getOpposite());
        Coordinates playerKingPosition = board.getKingPositionForPlayer(player);

        for (Coordinates piecePosition : otherPlayerPositions) {
            if (board.get(piecePosition).getAllowedMoves(piecePosition, board).contains(new Move(piecePosition, playerKingPosition))) {
                return true;
            }
        }

        return false;
    }
}
