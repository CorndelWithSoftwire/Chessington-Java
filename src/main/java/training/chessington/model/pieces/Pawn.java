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
        int col = from.getCol();
        if(colour == PlayerColour.WHITE)
        {
            Move move1 = new Move(from, from.plus(-1,  0));
            moves.add(move1);
            if(row == 6)
            {
                moves.add(new Move(from, from.plus( -2,  0)));
            }

            if (row >0 && col >0 && board.get(from.plus(-1,-1))!= null &&(board.get(from.plus(-1,-1)).getColour()== PlayerColour.BLACK))
            {
                diagonalMoves.add(new Move(from, from.plus(-1,-1)));
            }
            if (row >0 && col <7 && board.get(from.plus(-1,1))!= null && (board.get(from.plus(-1,1)).getColour()== PlayerColour.BLACK))
            {
                System.out.println("22");
                diagonalMoves.add(new Move(from, from.plus(-1,1)));
            }

        }
        if(colour == PlayerColour.BLACK)
        {
            Move move1 = new Move(from, from.plus(1,  0));
            moves.add(move1);
            if(row == 1)
            {
                moves.add(new Move(from, from.plus( 2,  0)));
            }
        }
        moves.removeIf(n->outBoundary(n));
        moves.removeIf(move->blocked(move, board));
        System.out.println(moves);
        moves.addAll(diagonalMoves);
        return moves;
    }
    public boolean blocked(Move move, Board board){
        Coordinates to = move.getTo();
        return board.get(to) != null;
    }
    public boolean outBoundary(Move move){
        int row = move.getTo().getRow();
        int col = move.getTo().getCol();
        return(row < 0 || row > 7 || col < 0 || col > 7);
    }

}