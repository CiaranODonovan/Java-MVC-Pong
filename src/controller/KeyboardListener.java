//Author Ciaran O'Donovan R00238437

package controller;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.GameState;
import model.Paddle;

/**
 * Handles keyboard input for controlling paddle movement, pausing, and restarting the game.
 * Binds specific keys to actions within the game, allowing for real-time interaction.
 */
public class KeyboardListener  implements EventHandler<KeyEvent> {
    private final GameState game;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;

    private boolean gamePaused = false;

    private boolean gameRestart = false;

    private boolean wasPaused = false;

    /**
     * Constructs a KeyboardListener with a reference to the game state.
     *
     * @param game The current game state, used for accessing paddle objects and game dimensions.
     */
    public KeyboardListener(GameState game) {
        this.game = game;

    }
    /**
     * Processes key press and release events to update control flags and manage game states.
     * Supports controlling two paddles with 'W', 'S', and 'UP', 'DOWN' keys, respectively.
     * Also manages game pause and restart actions with 'SPACE' and 'ENTER'.
     *
     * @param keyEvent The event triggered by key actions.
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        boolean keyState = keyEvent.getEventType() == KeyEvent.KEY_PRESSED;
        switch (keyEvent.getCode()) {
            case UP:
                upPressed = keyState;
                break;
            case DOWN:
                downPressed = keyState;
                break;
            case W:
                wPressed = keyState;
                break;
            case S:
                sPressed = keyState;
                break;
            case SPACE:
                if (keyState && !gamePaused) {
                    gamePaused = true;

                } else if (keyState) {
                    gamePaused = false;


                }
                break;
            case ENTER:
                if (keyState) {
                    gameRestart = true;

                }
                break;

            default:
                break;

        }}
    /**
     * Checks if the game is currently paused.
     *
     * @return True if the game is paused, false otherwise.
     */
    public boolean isGamePaused(){
        return gamePaused;
    }
    /**
     * Checks if there's a request to restart the game.
     *
     * @return True if a restart request was made, false otherwise.
     */
    public boolean isGameRestart() {
        return gameRestart;
    }
    /**
     * Checks if the game was paused before the current state.
     *
     * @return True if the game was previously paused, false otherwise.
     */
    public boolean isWasPaused(){
        return wasPaused;
    }
    /**
     * Sets the game restart flag, used to indicate a request to restart the game.
     *
     * @param gameRestart True to request a restart, false otherwise.
     */
    public void setGameRestart(boolean gameRestart) {
        this.gameRestart = gameRestart;
    }
    /**
     * Sets the flag indicating whether the game was paused before the current state.
     *
     * @param paused True if the game was paused, false otherwise.
     */
    public void setWasPaused(boolean paused){
        this.wasPaused = paused;
    }


    /**
     * Initiates paddle movement based on the current key press states.
     * Moves the paddles up or down within the game boundaries.
     */
    public void paddleMovement() {
            if (upPressed) movePaddleUp(game.getPlayer2().getPaddle());
            if (downPressed) movePaddleDown(game.getPlayer2().getPaddle());
            if (wPressed) movePaddleUp(game.getPlayer1().getPaddle());
            if (sPressed) movePaddleDown(game.getPlayer1().getPaddle());

    }
    /**
     * Moves the specified paddle up if it is not at the top boundary.
     *
     * @param paddle The paddle to move up.
     */
    public void movePaddleUp(Paddle paddle){

        if(paddle.getY() > 0){paddle.setY(paddle.getY() - 10);}
    }
    /**
     * Moves the specified paddle down if it is not at the bottom boundary.
     *
     * @param paddle The paddle to move down.
     */
    public void movePaddleDown(Paddle paddle){
        if(paddle.getY() + paddle.getHeight()< game.getHeight()){paddle.setY(paddle.getY() + 10);}
    }
}




