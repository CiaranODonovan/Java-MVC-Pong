//Author Ciaran O'Donovan R00238437
package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.Ball;
import model.Paddle;
import model.GameState;
import model.Player;

/**
 * Renders the visual representation of the game state on a canvas.
 * This includes drawing the game background, ball, paddles, and player details such as scores and names.
 */
public class GameView extends Canvas {

    GraphicsContext gc = this.getGraphicsContext2D();

    /**
     * Constructs a GameView with specified height and width.
     *
     * @param height The height of the canvas.
     * @param width The width of the canvas.
     */
    public GameView(double height, double width) {
        super(width, height);
    }

    /**
     * Draws the ball on the canvas.
     *
     * @param gc The graphics context of the canvas.
     * @param ball The ball to be drawn.
     */
    private void drawBall(GraphicsContext gc, Ball ball) {
        gc.setFill(Color.YELLOWGREEN);
        gc.fillOval(ball.getCenterX(), ball.getCenterY(), ball.getRadius(), ball.getRadius());
    }

    /**
     * Draws a paddle on the canvas.
     *
     * @param gc The graphics context of the canvas.
     * @param paddle The paddle to be drawn.
     */
    private void drawPaddle(GraphicsContext gc, Paddle paddle) {
        gc.setFill(paddle.getFill());
        gc.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
    }

    /**
     * Draws the background of the game.
     *
     * @param gc The graphics context of the canvas.
     */
    private void drawBackground(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    /**
     * Renders player details, including the name and score, at specified canvas coordinates.
     *
     * @param gc The graphics context of the canvas.
     * @param player The player whose details are to be drawn.
     * @param x The x-coordinate where the details start.
     * @param y The y-coordinate where the details start.
     */
    private void drawTitle(GraphicsContext gc, Player player, double x, double y) {
        gc.setFill(Color.WHITE);
        Font font = Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 30);
        gc.setFont(font);
        gc.fillText(player.getPlayerName() + " " + player.getScore(), x, y);
    }

    /**
     * Displays a message indicating which player scored. This method also triggers a canvas reset
     * to reflect any potential changes in the game state size.
     *
     * @param scored The player number who scored.
     * @param game The current state of the game.
     * @return True if a player has scored, false otherwise.
     */
    public boolean showingScoredMessage(int scored, GameState game) {
        resetSize(game);
        String message = "";
        if (scored == 0) { return false; }
        else if (scored == 1) { message = game.getPlayer1().getPlayerName() + " has Scored!"; }
        else if (scored == 2) { message = game.getPlayer2().getPlayerName() + " has Scored!"; }
        gc.setFill(Color.WHITE);
        Font font = Font.font("Comic Sans MS", FontWeight.BOLD, 30);
        gc.setFont(font);
        gc.fillText(message, gc.getCanvas().getWidth() / 2 - 100, gc.getCanvas().getHeight() / 2 - 100);
        return true;
    }

    /**
     * Displays a message indicating which player has won the game. This method also triggers a canvas reset
     * to reflect any potential changes in the game state size.
     *
     * @param bool Indicates if Player 1 has won. If false, Player 2 is assumed to have won.
     * @param game The current state of the game.
     */
    public void showingWonMessage(boolean bool, GameState game) {
        resetSize(game);
        String message;
        if (bool) { message = game.getPlayer1().getPlayerName() + " has won! Enter to restart."; }
        else { message = game.getPlayer2().getPlayerName() + " has won!"; }
        gc.setFill(Color.WHITE);
        Font font = Font.font("Comic Sans MS", FontWeight.BOLD, 30);
        gc.setFont(font);
        gc.fillText(message, gc.getCanvas().getWidth() / 2 - 100, gc.getCanvas().getHeight() / 2 - 100);
    }

    /**
     * Adjusts the size of the canvas to match the current game state dimensions.
     *
     * @param game The current state of the game.
     */
    private void resetSize(GameState game) {
        this.setWidth(game.getWidth());
        this.setHeight(game.getHeight());
    }

    /**
     * Renders the entire game state, including background, ball, paddles, and player details.
     *
     * @param game The current state of the game to be rendered.
     */
    public void drawGame(GameState game) {
        resetSize(game);
        drawBackground(gc);
        drawTitle(gc, game.getPlayer1(), game.getWidth() * 0.25, game.getHeight() * 0.1);
        drawTitle(gc, game.getPlayer2(), game.getWidth() * 0.75, game.getHeight() * 0.1);
        drawBall(gc, game.getBall());
        drawPaddle(gc, game.getPlayer1().getPaddle());
        drawPaddle(gc, game.getPlayer2().getPaddle());
    }
}







