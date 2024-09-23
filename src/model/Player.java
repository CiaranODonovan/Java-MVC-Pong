//Author Ciaran O'Donovan R00238437
package model;

/**
 * Represents a player in the game, including their score, name, and paddle control.
 */
public class Player {
    private int score = 0;
    private final Paddle paddle;
    private String playerName;

    /**
     * Constructs a new Player with the specified name. Initializes the player's paddle.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.playerName = name;
        this.paddle = new Paddle();
    }

    /**
     * Returns the paddle associated with the player.
     *
     * @return The paddle object controlled by this player.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Returns the current score of the player.
     *
     * @return The score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the name of the player.
     *
     * @param newPlayerName The new name of the player.
     */
    public void setPlayerName(String newPlayerName) {
        this.playerName = newPlayerName;
    }

    /**
     * Sets the score of the player.
     *
     * @param newScore The new score to set for the player.
     */
    public void setScore(int newScore) {
        this.score = newScore;
    }

    /**
     * Sets the height of the player's paddle.
     *
     * @param y The new height for the paddle.
     */
    public void setPaddleHeight(double y) {
        paddle.setY(y);
    }

    /**
     * Sets the width of the player's paddle.
     *
     * @param x The new width for the paddle.
     */
    public void setPaddleWidth(double x) {
        paddle.setX(x);
    }

}
