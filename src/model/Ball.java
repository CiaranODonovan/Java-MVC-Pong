//Author Ciaran O'Donovan R00238437
package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

/**
 * Represents a ball in the game, characterized by its speed, direction, and behavior upon collisions.
 * The ball's movement is defined by its speed along the X and Y axes.
 */
public class Ball extends Circle implements Resizable {
    private final Random random = new Random();
    private int initialSpeed = 1;
    private double xSpeed = random.nextBoolean() ? initialSpeed :  -initialSpeed;
    private double ySpeed = random.nextBoolean() ? initialSpeed :  -initialSpeed;
    private double speedIncreaseScale = 1.1;
    private int speedIncreaseFrequency = 2;
    private int bounces = 0;

    /**
     * Constructs a new Ball with a default position at the origin (0,0), a radius of 15, and a random initial speed and direction.
     */
    public Ball() {
        super(0, 0, 15);
        super.setFill(Color.BLACK);
    }

    /**
     * Constructs a new Ball at a specified position with a radius of 15 and a random initial speed and direction.
     *
     * @param x The X coordinate of the ball's position.
     * @param y The Y coordinate of the ball's position.
     */
    public Ball(double x, double y) {
        super(x, y, 15);
        super.setFill(Color.BLACK);
    }

    // Getters

    /**
     * Returns the current speed of the ball along the X axis.
     *
     * @return The ball's current X speed.
     */
    public double getXSpeed() {
        return xSpeed;
    }

    /**
     * Returns the current speed of the ball along the Y axis.
     *
     * @return The ball's current Y speed.
     */
    public double getYSpeed() {
        return ySpeed;
    }

    /**
     * Returns the scale factor by which the ball's speed is increased after a certain number of bounces.
     *
     * @return The ball's speed increase scale.
     */
    public double getSpeedIncreaseScale() {
        return speedIncreaseScale;
    }

    /**
     * Returns the number of bounces the ball has made.
     *
     * @return The ball's bounce count.
     */
    public int getBounces() {
        return bounces;
    }

    /**
     * Provides access to the random number generator used for initial speed and direction.
     *
     * @return The Random instance used by this ball.
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Returns the frequency at which the ball's speed is increased after a certain number of bounces.
     *
     * @return The frequency of speed increase.
     */
    public int getSpeedIncreaseFrequency() {
        return speedIncreaseFrequency;
    }

    /**
     * Returns the initial speed of the ball.
     *
     * @return The ball's initial speed.
     */
    public int getInitialSpeed() {
        return initialSpeed;
    }

    // Setters

    /**
     * Sets the scale factor by which the ball's speed is to be increased.
     *
     * @param speedIncreaseScale The new speed increase scale.
     */
    public void setSpeedIncreaseScale(double speedIncreaseScale) {
        this.speedIncreaseScale = speedIncreaseScale;
    }

    /**
     * Sets the frequency at which the ball's speed should increase.
     *
     * @param speedIncreaseFrequency The new speed increase frequency.
     */
    public void setSpeedIncreaseFrequency(int speedIncreaseFrequency) {
        this.speedIncreaseFrequency = speedIncreaseFrequency;
    }

    /**
     * Sets the initial speed of the ball.
     *
     * @param initialSpeed The new initial speed.
     */
    public void setInitialSpeed(int initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    /**
     * Sets the bounce count of the ball.
     *
     * @param bounces The new bounce count.
     */
    public void setBounces(int bounces) {
        this.bounces = bounces;
    }

    /**
     * Sets the speed of the ball along the X axis.
     *
     * @param xSpeed The new X speed.
     */
    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * Sets the speed of the ball along the Y axis.
     *
     * @param ySpeed The new Y speed.
     */
    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    // Additional methods

    /**
     * Resizes the ball along the X axis by a specified factor, adjusting both its position and radius.
     *
     * @param factor The factor by which to scale the ball's X position and radius.
     */
    public void resizeX(double factor) {
        this.setCenterX(this.getCenterX() * factor);
        this.setRadius(this.getRadius() * factor);
    }

    /**
     * Resizes the ball along the Y axis by a specified factor, adjusting both its position and radius.
     *
     * @param factor The factor by which to scale the ball's Y position and radius.
     */
    public void resizeY(double factor) {
        this.setCenterY(this.getCenterY() * factor);
        this.setRadius(this.getRadius() * factor);
    }
}
