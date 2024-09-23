//Author Ciaran O'Donovan R00238437
package controller;

/**
 * Manages the ball's movement and collision detection in a separate thread.
 * This allows the ball to be updated independently of the user interface or other game logic,
 * ensuring smooth gameplay.
 */
public class BallManager implements Runnable {

    private final GameController controller;

    // Flag to control the running state of the ball movement thread
    private volatile boolean running = true;

    /**
     * Constructs a BallManager with a reference to the GameController.
     * This allows the BallManager to invoke game logic methods.
     *
     * @param controller The GameController that contains the game logic.
     */
    public BallManager(GameController controller) {
        this.controller = controller;
    }

    /**
     * Pauses the ball's movement by setting the running flag to false.
     */
    public void pause() {
        this.running = false;
    }

    /**
     * Resumes the ball's movement by setting the running flag to true.
     */
    public void resume() {
        this.running = true;
    }

    /**
     * Returns the current running state of the ball movement.
     *
     * @return true if the ball movement is active, false otherwise.
     */
    public boolean getRunning() {
        return this.running;
    }

    /**
     * The entry point for the ball movement thread.
     * Continuously updates the ball's position and checks for collisions
     * as long as the thread is running.
     */
    @Override
    public void run() {
        while (true) {
            while (running) {
                try {
                    // Introduces a small delay to manage the update frequency
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Update the ball's position and check for collisions
                controller.ballDiscreteMovement();
                controller.wallCollisionCheck(controller.getGame().getHeight());
                controller.paddleCollisionCheck(controller.getGame().getPlayer1().getPaddle());
                controller.paddleCollisionCheck(controller.getGame().getPlayer2().getPaddle());
            }
        }
    }
}
