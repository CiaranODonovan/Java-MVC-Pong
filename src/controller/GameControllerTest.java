//AUTHOR: Ciaran O'Donovan R00238437
package controller;

import model.GameState;
import model.Paddle;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link GameController} class that cover collision detection and response handling.
 */
public class GameControllerTest {
    private final GameController gameController = new GameController();
    private final GameState gameState = gameController.getGame();

    /**
     * Tests the collision detection and speed change handling when the ball hits the top wall of the game screen.
     * The ball is positioned at the top of the screen and set to move upwards. After collision detection logic,
     * the ball's Y speed should become positive, indicating a downward movement.
     */
    @Test
    public void testWallCollisionTop() {
        gameState.getBall().setYSpeed(-1);
        gameState.getBall().setCenterY(0); // Position the ball at the very top.
        gameController.wallCollisionCheck(gameState.getHeight());
        assertTrue("The ball's Y speed should be positive after hitting the top wall",
                gameState.getBall().getYSpeed() > 0);
    }

    /**
     * Tests the collision detection and speed change handling when the ball hits the bottom wall of the game screen.
     * The ball is positioned at the bottom of the screen and set to move downwards. After collision detection logic,
     * the ball's Y speed should become negative, indicating an upward movement.
     */
    @Test
    public void testWallCollisionBottom() {
        gameState.getBall().setYSpeed(1);
        gameState.getBall().setCenterY(gameState.getHeight()); // Position the ball at the very bottom.
        gameController.wallCollisionCheck(gameState.getHeight());
        assertTrue("The ball's Y speed should be negative after hitting the bottom wall",
                gameState.getBall().getYSpeed() < 0);
    }

    /**
     * Tests the collision detection and speed change handling when the ball collides with Player 1's paddle.
     * The ball is moving towards Player 1's paddle and positioned to collide with it. After collision detection logic,
     * the ball's X speed should become positive, indicating a rightward movement.
     */
    @Test
    public void testPaddleCollision() {
        gameState.getBall().setXSpeed(-1); // Ball moving towards the paddle
        Paddle paddle = gameState.getPlayer1().getPaddle();
        gameState.getBall().setCenterX(paddle.getX() + paddle.getWidth()); // Position for collision
        gameState.getBall().setCenterY(paddle.getY() + paddle.getHeight() / 2);
        gameController.paddleCollisionCheck(paddle);
        assertTrue("The ball's X speed should be positive after colliding with the paddle",
                gameState.getBall().getXSpeed() > 0);
    }
}
