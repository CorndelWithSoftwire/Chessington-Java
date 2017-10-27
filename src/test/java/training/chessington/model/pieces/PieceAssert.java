package training.chessington.model.pieces;

import org.assertj.core.api.AbstractAssert;
import training.chessington.model.PlayerColour;

public class PieceAssert extends AbstractAssert<PieceAssert, Piece> {

    public PieceAssert(Piece actual) {
        super(actual, PieceAssert.class);
    }

    public static PieceAssert assertThat(Piece actual) {
        return new PieceAssert(actual);
    }

    public PieceAssert isPiece(Piece.PieceType type) {
        isNotNull();

        if (actual.getType() != type) {
            failWithMessage("Expected piece to be <%s> but was <%s>", type, actual.getType());
        }

        return this;
    }

    public PieceAssert isColour(PlayerColour colour) {
        isNotNull();

        if (actual.getColour() != colour) {
            failWithMessage("Expected piece colour to be <%s> but was <%s>", colour, actual.getColour());
        }

        return this;
    }
}
