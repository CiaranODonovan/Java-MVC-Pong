//Author Ciaran O'Donovan R00238437
package model;

/**
 * Provides an interface for game elements that can be resized along the X and Y axes.
 * Implementing classes can adjust their size or position based on a scaling factor,
 * allowing for flexible layout and scaling behaviors.
 */
public interface Resizable {

    /**
     * Resizes the object along the X axis based on the provided scaling factor.
     * This can involve adjusting the object's width, position, or both.
     *
     * @param factor The scaling factor to apply along the X axis. A factor greater than 1
     *               indicates an enlargement, while a factor less than 1 indicates a reduction.
     */
    void resizeX(double factor);

    /**
     * Resizes the object along the Y axis based on the provided scaling factor.
     * This can involve adjusting the object's height, position, or both.
     *
     * @param factor The scaling factor to apply along the Y axis. A factor greater than 1
     *               indicates an enlargement, while a factor less than 1 indicates a reduction.
     */
    void resizeY(double factor);
}