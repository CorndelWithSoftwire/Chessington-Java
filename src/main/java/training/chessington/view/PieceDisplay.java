package training.chessington.view;

import training.chessington.model.pieces.Piece;
import training.chessington.model.PlayerColour;

import java.util.HashMap;
import java.util.Map;

public class PieceDisplay {
    private static final Map<PlayerColour, Map<Piece.PieceType, String>> SYMBOLS = new HashMap<>();

    static {
        Map<Piece.PieceType, String> whiteSymbols = new HashMap<>();
        whiteSymbols.put(Piece.PieceType.PAWN, "\u2659");
        whiteSymbols.put(Piece.PieceType.KNIGHT, "\u2658");
        whiteSymbols.put(Piece.PieceType.BISHOP, "\u2657");
        whiteSymbols.put(Piece.PieceType.ROOK, "\u2656");
        whiteSymbols.put(Piece.PieceType.QUEEN, "\u2655");
        whiteSymbols.put(Piece.PieceType.KING, "\u2654");

        Map<Piece.PieceType, String> blackSymbols = new HashMap<>();
        blackSymbols.put(Piece.PieceType.PAWN, "\u265F");
        blackSymbols.put(Piece.PieceType.KNIGHT, "\u265E");
        blackSymbols.put(Piece.PieceType.BISHOP, "\u265D");
        blackSymbols.put(Piece.PieceType.ROOK, "\u265C");
        blackSymbols.put(Piece.PieceType.QUEEN, "\u265B");
        blackSymbols.put(Piece.PieceType.KING, "\u265A");

        SYMBOLS.put(PlayerColour.WHITE, whiteSymbols);
        SYMBOLS.put(PlayerColour.BLACK, blackSymbols);
    }

    public static String displayFor(Piece piece) {
        return piece == null ? "" : SYMBOLS.get(piece.getColour()).get(piece.getType());
    }
}
