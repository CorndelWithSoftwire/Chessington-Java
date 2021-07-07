package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<Move>();

        addMoveByDirection(board, from, -1,0, moves);
        addMoveByDirection(board, from, 1,0, moves);
        addMoveByDirection(board, from, 0,-1, moves);
        addMoveByDirection(board, from, 0,1, moves);

        System.out.println(moves);
        return moves;
    }
    public boolean outBoundary(Coordinates coor){
        int row = coor.getRow();
        int col = coor.getCol();
        return(row < 0 || row > 7 || col < 0 || col > 7);
    }

    public void addMoveByDirection(Board board,Coordinates from, int directionX, int directionY, ArrayList<Move> moves){
        Coordinates current = from.plus(directionX,directionY);
        while(!outBoundary(current) && board.get(current)== null)
        {
            moves.add(new Move(from, current));
            current = current.plus(directionX, directionY);
        }
        if (!outBoundary(current) && board.get(current) !=null) {
            System.out.println("Hello");
            if (board.get(current).getColour() == opponentColour(colour))
                moves.add(new Move(from, current));
        }
    }

    public PlayerColour opponentColour(PlayerColour colour){
        if(colour == PlayerColour.WHITE) return PlayerColour.BLACK;
        if(colour == PlayerColour.BLACK) return PlayerColour.WHITE;
        return null;
    }


}
