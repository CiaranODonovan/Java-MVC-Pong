//Author Ciaran O'Donovan R00238437
package model;

/**
 * Represents the state of the game, including the dimensions of the play area, the players,
 * the ball, and the winning score necessary to end the game.
 */
public class GameState implements Resizable {
    private double width = 1000;
    private double height = 500;
    private int winningScore = 3;

    private final Ball ball = new Ball();
    private final Player player1 = new Player("Player1");
    private final Player player2 = new Player("Player2");

    /**
     * Constructs a new GameState with default dimensions, players, and ball. Initializes the game
     * by setting the initial positions of the paddles and the ball.
     */
    public GameState() {
        resetPaddleProportions();
        resetBallProportions();
    }

    /**
     * Gets the width of the game area.
     *
     * @return The width of the game area.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the game area.
     *
     * @return The height of the game area.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the ball used in the game.
     *
     * @return The ball object.
     */
    public Ball getBall() {
        return this.ball;
    }

    /**
     * Gets the first player in the game.
     *
     * @return The first player object.
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * Gets the second player in the game.
     *
     * @return The second player object.
     */
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * Gets the winning score for the game.
     *
     * @return The winning score.
     */
    public int getWinningScore() {
        return winningScore;
    }

    /**
     * Sets the width of the game area.
     *
     * @param width The new width of the game area.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets the height of the game area.
     *
     * @param height The new height of the game area.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Sets the winning score for the game.
     *
     * @param winningScore The new winning score.
     */
    public void setWinningScore(int winningScore) {
        this.winningScore = winningScore;
    }

    /**
     * Resizes all game elements along the X axis by a specified factor.
     *
     * @param factor The factor by which to scale the game elements' X dimension.
     */
    public void resizeX(double factor) {
        player1.getPaddle().resizeX(factor);
        player2.getPaddle().resizeX(factor);
        ball.resizeX(factor);
    }

    /**
     * Resizes all game elements along the Y axis by a specified factor.
     *
     * @param factor The factor by which to scale the game elements' Y dimension.
     */
    public void resizeY(double factor) {
        player1.getPaddle().resizeY(factor);
        player2.getPaddle().resizeY(factor);
        ball.resizeY(factor);
    }

    /**
     * Resets the paddle positions based on the current game area dimensions, ensuring they are
     * proportionally placed within the play area.
     */
    public void resetPaddleProportions() {
        player1.getPaddle().setY((height - player1.getPaddle().getHeight()) / 2);
        player2.getPaddle().setX((width - player2.getPaddle().getWidth()));
        player2.getPaddle().setY((height - player2.getPaddle().getHeight()) / 2);
    }

    /**
     * Resets the ball's position to the center of the game area, ensuring it starts in a neutral
     * location at the beginning of the game or after a score.
     */
    public void resetBallProportions() {
        ball.setCenterX(width / 2);
        ball.setCenterY(height / 2);
    }
}
