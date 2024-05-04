/**
 * MuscleFiber class represents each fiber residing in the each individual patches.
 * 
 * The muscle fiber will have their maximum limit to the fiber size. The fiber can grow
 * or shirink depending on the anabolic and catabolic hormone level of the patch which
 * the fiber resides in.
 * 
 * @author Dong Hyeog Jang (582238)
 * @author Junheng Chen (1049540)
 * @author Ning Wang (1468286)
 * 
 * @date 4 May 2024
 */

package exteded;

import java.util.Random;

public class MuscleFiber {

    private static final Configuration config = Configuration.getInstance();
    private static int nextId = 1;

    private Random random = new Random();

    private int id;             // id of the muscle fiber
    private int maxSize;        // maximum size limit for the muscle fiber
    private double fiberSize;   // current size of muscle fiber

    /**
     * Constructs a MuscleFiber object with a unique ID, and initializes its size parameters.
     * The maximum size is determined through a stochastic process influenced by the percentage
     * of slow twitch fibers.
     */
    public MuscleFiber() {
        this.id = nextId++;
        this.maxSize = sproutMuscleFiber();
        this.fiberSize = initFiberSize();
        regulateMuscleFiber();
    }

    /**
     * Determines the initial maximum size of the muscle fiber using a random process.
     * The size is increased based on the probability determined by the percentage of
     * slow twitch fibers.
     * @return the sprouted maximum size of the muscle fiber.
     */
    private int sproutMuscleFiber() {
        int size = 4;
        for (int i = 0; i < 20; i++) {
            if (random.nextDouble() * 100 > config.getSlowTwitchFibersPercentage()) {
                size += 1;
            }
        }
        return size;
    }

    /**
     * Initializes the current size of the muscle fiber as a fraction of its maximum size.
     * @return the initial size of the muscle fiber.
     */
    private double initFiberSize() {
        return (0.2 + random.nextDouble() * 0.4) * maxSize;
    }

    /**
     * Simulates the growth of the muscle fiber based on the levels of hormones.
     * The size decrease with catabolic hormone level and increase with respect to 
     * anabolic and catabolic hormone level.
     * @param anabolicHormone current level of anabolic hormones.
     * @param catabolicHormone current level of catabolic hormones.
     */
    public void grow(double anabolicHormone, double catabolicHormone) {
        // decrease size based on catabolic hormone.
        fiberSize -= 0.2 * Math.log10(catabolicHormone);

        // increase size based on anabolic hormone, capped by a factor of the catabolic hormone.
        fiberSize += 0.2 * Math.min(
            Math.log10(anabolicHormone),
            1.05 * Math.log10(catabolicHormone)
        );
    }

    /**
     * Ensures that the muscle fiber's size does not exceed its maximum or fall below 1.
     * This method acts as a regulatory mechanism to maintain fiber size within realistic bounds.
     */
    public void regulateMuscleFiber() {
        // minimum boundary
        if (fiberSize < 1) {
            fiberSize = 1;
        }

        // maximum boundary
        if (fiberSize > maxSize) {
            fiberSize = maxSize;
        }
    }

    /**
     * Getter methods for muscle fiber's current fiber size
     * @return double value of current fiber size
     */
    public double getFiberSize() {
        return this.fiberSize;
    }

    /**
     * Prints muscle fiber's max size and current fiber size to the console with its id
     * for debugging purpose
     */
    public void printMuscleFiber() {
        System.out.printf("MuscleFiber %d%n", id);
        System.out.printf("  maxSize   = %d%n", maxSize);
        System.out.printf("  fiberSize = %.2f%n", fiberSize);
    }
}
