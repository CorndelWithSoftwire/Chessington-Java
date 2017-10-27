package training.chessington.view;

import javafx.beans.binding.NumberBinding;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import training.chessington.model.Coordinates;
import training.chessington.model.pieces.Piece;


public class Square extends Parent {

    private final Coordinates coordinates;

    private Text text;

    public enum SquareColour {
        WHITE(Color.WHITE), BLACK(Color.DARKGRAY);

        private final Color color;

        SquareColour(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    private Rectangle background;

    public Square(int row, int col) {
        this.coordinates = new Coordinates(row, col);
        createBackground();
        createText();
        getChildren().addAll(background, text);
    }

    private void createBackground() {
        SquareColour colour = (((coordinates.getRow() + coordinates.getCol()) % 2) == 0) ? SquareColour.WHITE : SquareColour.BLACK;
        background = new Rectangle(50, 50, colour.getColor());
        background.setStrokeType(StrokeType.INSIDE);
        background.setStrokeWidth(3);
    }

    private void createText() {
        text = new Text();
        text.setTranslateX(10);
        text.setY(40);
        text.setFont(new Font(30));
    }

    public void resetHighlighting() {
        background.setStroke(Color.TRANSPARENT);
    }

    public void showAsSelected() {
        background.setStroke(Color.PURPLE);
    }

    public void showAsMoveOption() {
        background.setStroke(Color.CHARTREUSE);
    }

    public void setPiece(Piece piece) {
        text.setText(PieceDisplay.displayFor(piece));
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
