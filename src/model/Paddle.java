//Author Ciaran O'Donovan R00238437
package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a paddle in the game. A paddle is a rectangular object used by a player to interact with the game,
 * such as hitting a ball. This class extends {@link Rectangle} and provides additional functionality for resizing.
 */
public class Paddle extends Rectangle implements Resizable {

    /**
     * Constructs a new paddle with default position, size, and color.
     * The default paddle is white, positioned at the origin (0,0), with a width of 15 and a height of 100.
     */
    public Paddle() {
        super(0, 0, 15, 100);
        super.setFill(Color.WHITE);
    }

    /**
     * Constructs a new paddle with specified position, size, and sets the default color to white.
     *
     * @param x The X coordinate of the paddle's upper-left corner.
     * @param y The Y coordinate of the paddle's upper-left corner.
     * @param paddleWidth The width of the paddle.
     * @param paddleHeight The height of the paddle.
     */
    public Paddle(double x, double y, double paddleWidth, double paddleHeight) {
        super(x, y, paddleWidth, paddleHeight);
        super.setFill(Color.WHITE);
    }

    /**
     * Resizes the paddle along the X axis by a specified factor. The paddle's X position and width are
     * multiplied by this factor to scale the paddle horizontally.
     *
     * @param factor The factor by which to scale the paddle's width and X position.
     */
    public void resizeX(double factor) {
        this.setX(this.getX() * factor);
        this.setWidth(this.getWidth() * factor);
    }

    /**
     * Resizes the paddle along the Y axis by a specified factor. The paddle's Y position and height are
     * multiplied by this factor to scale the paddle vertically.
     *
     * @param factor The factor by which to scale the paddle's height and Y position.
     */
    public void resizeY(double factor) {
        this.setY(this.getY() * factor);
        this.setHeight(this.getHeight() * factor);
    }

}
