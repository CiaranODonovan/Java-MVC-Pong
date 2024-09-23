//Author Ciaran O'Donovan R00238437
package controller;

import model.GameState;

/**
 * Handles the configuration of game settings from the menu,
 * updating the {@link GameState} based on user inputs.
 */
public class MenuController {
    private final GameState game;

    /**
     * Constructs a MenuController with a specific {@link GameState}.
     *
     * @param game The game state to be manipulated by this controller.
     */
    public MenuController(GameState game) {
        this.game = game;
    }

    /**
     * Sets a new name for Player 1.
     *
     * @param name The new name to be set for Player 1.
     */
    public void setNewPlayer1Name(String name) {
        game.getPlayer1().setPlayerName(name);
    }

    /**
     * Sets a new name for Player 2.
     *
     * @param name The new name to be set for Player 2.
     */
    public void setNewPlayer2Name(String name) {
        game.getPlayer2().setPlayerName(name);
    }

    /**
     * Sets a new height for the paddles of both players.
     *
     * @param height The new paddle height.
     */
    public void setNewPaddleHeight(double height) {
        game.getPlayer1().getPaddle().setHeight(height);
        game.getPlayer2().getPaddle().setHeight(height);
        game.resetBallProportions();
    }

    /**
     * Sets a new width for the paddles of both players.
     *
     * @param width The new paddle width.
     */
    public void setNewPaddleWidth(double width) {
        game.getPlayer1().getPaddle().setWidth(width);
        game.getPlayer2().getPaddle().setWidth(width);
        game.resetPaddleProportions();
    }

    /**
     * Sets a new frequency at which the ball's speed will increase.
     *
     * @param frequency The frequency of speed increase.
     */
    public void setNewBallSpeedFrequency(int frequency) {
        game.getBall().setSpeedIncreaseFrequency(frequency);
    }

    /**
     * Sets the initial speed for the ball, applying it directly to both the X and Y speeds.
     *
     * @param initialSpeed The initial speed of the ball.
     */
    public void setNewInitialBall(int initialSpeed) {
        game.getBall().setInitialSpeed(initialSpeed);
        game.getBall().setXSpeed(game.getBall().getRandom().nextBoolean() ? initialSpeed : -initialSpeed);
        game.getBall().setYSpeed(game.getBall().getRandom().nextBoolean() ? initialSpeed : -initialSpeed);
    }

    /**
     * Sets a new radius for the ball.
     *
     * @param radius The new ball radius.
     */
    public void setNewBallRadius(double radius) {
        game.getBall().setRadius(radius);
        game.resetBallProportions();
    }

    /**
     * Sets a new winning score for the game.
     *
     * @param maxScore The score required to win the game.
     */
    public void setNewWinningScore(int maxScore) {
        game.setWinningScore(maxScore);
    }
}
