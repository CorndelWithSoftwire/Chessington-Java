package training.chessington.model.pieces;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;
import java.util.ArrayList;
import java.util.List;
public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }
    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<Move>();
        int row = from.getRow();
        int colourInt = 1;
        if(colour == PlayerColour.WHITE) colourInt = -1; //colourInt = -1 when piece is white; 1 when colour is black
        addValid(moves, board, from, 1, 1, colourInt);
        addValid(moves, board, from, 1, -1, colourInt);
        addValid(moves, board, from, -1, 1, colourInt);
        addValid(moves, board, from, -1, -1, colourInt);
        addValid(moves, board, from, 1, 0, colourInt);
        addValid(moves, board, from, 0, 1, colourInt);
        addValid(moves, board, from, -1, 0, colourInt);
        addValid(moves, board, from, 0, -1, colourInt);
        return moves;
    }
    public void addValid(ArrayList<Move> moves, Board board, Coordinates coor, int rowDir, int colDir, int colour)
    {
        Coordinates target = new Coordinates (coor.getRow()+rowDir, coor.getCol()+colDir);
        while(!outBoundary(target) && board.get(target) == null)
        {
            moves.add(new Move(coor, target));
            target = new Coordinates (target.getRow()+rowDir, target.getCol()+colDir);
        }
        if (outBoundary(target)) return;
        if ((board.get(target).getColour() == PlayerColour.WHITE && colour ==1) || (board.get(target).getColour() == PlayerColour.BLACK && colour ==-1))
        {
            moves.add(new Move(coor,target));
        }
    }
    public boolean outBoundary(Coordinates coor)
    {
        int row = coor.getRow();
        int col = coor.getCol();
        return(row < 0 || row > 7 || col < 0 || col > 7);
    }
}