//Author Ciaran O'Donovan R00238437
package controller;

import model.GameState;
import model.Paddle;


/**
 * Manages the logic and state transitions of a game, including moving the ball,
 * detecting collisions, and keeping score.
 */
public class GameController {
    private final GameState game = new GameState();

    /**
     * Retrieves the current game state.
     *
     * @return The current {@link GameState}.
     */
    public GameState getGame() {
        return this.game;
    }

    /**
     * Updates the ball's position based on its current speed.
     */
    public void ballDiscreteMovement() {
        game.getBall().setCenterX(game.getBall().getCenterX() + game.getBall().getXSpeed());
        game.getBall().setCenterY(game.getBall().getCenterY() + game.getBall().getYSpeed());
    }

    /**
     * Checks for collisions between the ball and the top or bottom walls of the game area.
     * If a collision is detected, the ball's Y speed is reversed to simulate a bounce.
     *
     * @param windowHeight The height of the game window which is used to check for bottom wall collisions.
     */
    public void wallCollisionCheck(double windowHeight) {
        if (game.getBall().getCenterY() <= 0 || game.getBall().getCenterY() + game.getBall().getRadius() >= windowHeight) {
            game.getBall().setYSpeed(game.getBall().getYSpeed() * (-1));
        }
    }

    /**
     * Checks if either player has reached the winning score.
     *
     * @return {@code true} if a player has won, {@code false} otherwise.
     */
    public boolean checkIfWon() {
        return game.getPlayer1().getScore() >= game.getWinningScore() || game.getPlayer2().getScore() >= game.getWinningScore();
    }

    /**
     * Determines which player has won the game based on who reached the winning score first.
     *
     * @return {@code true} if Player 1 has won, {@code false} if Player 2 has won.
     */
    public boolean checkWhoWon() {
        if (game.getPlayer1().getScore() >= game.getWinningScore()) {
            resetGameState(game.getBall().getInitialSpeed());
            return true;
        } else if (game.getPlayer2().getScore() >= game.getWinningScore()) {
            resetGameState(game.getBall().getInitialSpeed());
            return false;
        }
        return false;
    }

    /**
     * Helper method to determine if a collision has occurred between the ball and a paddle.
     *
     * @param paddle The paddle to check for a collision with the ball.
     * @return {@code true} if a collision is detected, {@code false} otherwise.
     */
    private boolean paddleCollision(Paddle paddle) {
        return game.getBall().getCenterX() <= paddle.getX() + paddle.getWidth() &&
                game.getBall().getCenterX() + game.getBall().getRadius() >= paddle.getX() &&
                game.getBall().getCenterY() >= paddle.getY() &&
                game.getBall().getCenterY() <= paddle.getY() + paddle.getHeight();
    }

    /**
     * Handles collisions between the ball and a paddle. If a collision is detected,
     * the ball's X speed is reversed and potentially increased based on the number of bounces.
     *
     * @param paddle The paddle to check for a collision with the ball.
     */
    public void paddleCollisionCheck(Paddle paddle) {
        if (paddleCollision(paddle)) {
            game.getBall().setXSpeed(game.getBall().getXSpeed() * (-1));
            game.getBall().setBounces(game.getBall().getBounces() + 1);
            if (game.getBall().getBounces() >= game.getBall().getSpeedIncreaseFrequency()) {
                game.getBall().setXSpeed(game.getBall().getXSpeed() * game.getBall().getSpeedIncreaseScale());
                game.getBall().setYSpeed(game.getBall().getYSpeed() * game.getBall().getSpeedIncreaseScale());
                game.getBall().setBounces(0);
            }
        }
    }

    /**
     * Checks if the ball has crossed either end of the game area, indicating a score.
     *
     * @return 0 if no score, 1 if Player 1 scored, or 2 if Player 2 scored.
     */
    public int checkIfScored() {
        if (game.getBall().getCenterX() - game.getBall().getRadius() > game.getWidth()) {
            game.getPlayer1().setScore(game.getPlayer1().getScore() + 1);
            resetGameState(0);
            return 1;
        } else if (game.getBall().getCenterX() + game.getBall().getRadius() < 0) {
            game.getPlayer2().setScore(game.getPlayer2().getScore() + 1);
            resetGameState(0);
            return 2;
        } else return 0;
    }

    /**
     * Resets the ball's speed to a specified value.
     *
     * @param speed The new speed for the ball.
     */
    public void resetBallSpeed(int speed) {
        game.getBall().setXSpeed(speed);
        game.getBall().setYSpeed(speed);
    }

    /**
     * Resets the game state to the start of a round. This includes resetting the ball and paddle positions,
     * as well as the ball's speed.
     *
     * @param speed The speed to reset the ball to.
     */
    public void resetGameState(int speed) {
        game.resetBallProportions();
        game.resetPaddleProportions();
        resetBallSpeed(speed);
    }
}
