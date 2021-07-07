package training.chessington.model.pieces;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }
    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<Move>();
        ArrayList<Move> diagonalMoves = new ArrayList<Move>();
        int row = from.getRow();
        int colourInt = 1;
        if(colour == PlayerColour.WHITE) colourInt = -1; //colourInt = -1 when piece is white; 1 when colour is black
        Move move1 = new Move(from, from.plus(colourInt,  0));
        moves.add(move1);
        if(row == 3.5 - 2.5*colourInt) // 7 for white, 1 for black
        {
            moves.add(new Move(from, from.plus( 2*colourInt,  0)));
        }
        capture(from, 1, colourInt, board, diagonalMoves);
        capture(from, -1, colourInt, board, diagonalMoves);
        moves.removeIf(n->outBoundary(n.getTo()));
        moves.removeIf(move->blocked(move, board));
        moves.addAll(diagonalMoves);
        return moves;
    }
    public boolean blocked(Move move, Board board){
        Coordinates to = move.getTo();
        return board.get(to) != null;
    }
    public boolean outBoundary(Coordinates coor){
        int row = coor.getRow();
        int col = coor.getCol();
        return(row < 0 || row > 7 || col < 0 || col > 7);
    }
    public void capture(Coordinates coor, int direction, int colour, Board board, ArrayList<Move> moves){
        Coordinates target = new Coordinates (coor.getRow()+colour, coor.getCol()+direction);
        if (outBoundary(target) || board.get(target) == null) return;
        if ((board.get(target).getColour() == PlayerColour.WHITE && colour ==1)
            || (board.get(target).getColour() == PlayerColour.BLACK && colour ==-1))
        {
            moves.add(new Move(coor,target));
        }
    }
}