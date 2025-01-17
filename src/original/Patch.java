/**
 * Patch class which represents environment around each muscle fiber.
 * 
 * Contains anabolic and catablic hormone level which change over time due to various
 * activities like daily activity, lifting weights, sleep, muscle development and hormone
 * regulation.
 * 
 * @author Dong Hyeog Jang (582238)
 * @author Junheng Chen (1049540)
 * @author Ning Wang (1468286)
 * 
 * @date 4 May 2024
 */

package original;

import java.util.Random;

public class Patch {

    private static final Configuration config = Configuration.getInstance();

    private Random random = new Random();
    
    private Muscle muscle;              // muscle which the patch belongs to
    private MuscleFiber muscleFiber;    // muscle fiber which resides in the patch

    // Coordinate for the patch.
    private int coordX;
    private int coordY;

    // Current level of anabolic and catabolic hormone for the patch.
    private double anabolicHormone;
    private double catabolicHormone;

    /**
     * Constructs a Patch object with specified muscle, x coordinate, and y coordinate.
     * Initializes muscle fiber and sets initial levels of anabolic and catabolic hormones
     * to their minimum values.
     * 
     * @param muscle the muscle which the patch belongs to
     * @param coordX x coordinate of the patch
     * @param coordY y coordinate of the patch
     */
    public Patch(Muscle muscle, int coordX, int coordY) {
        this.muscle = muscle;
        this.coordX = coordX;
        this.coordY = coordY;
        this.muscleFiber = new MuscleFiber();
        this.anabolicHormone = Configuration.ANABOLIC_HORMONE_MIN;
        this.catabolicHormone = Configuration.CATABOLIC_HORMONE_MIN;
    }

    /**
     * Copy constructor for creating a new Patch object from an existing one.
     * 
     * @param other The Patch object to copy from.
     */
    public Patch(Patch other) {
        this.muscle = other.muscle;
        this.coordX = other.coordX;
        this.coordY = other.coordY;
        this.muscleFiber = other.muscleFiber;
        this.anabolicHormone = other.anabolicHormone;
        this.catabolicHormone = other.catabolicHormone;
    }

    /**
     * Simulates the effect of daily activities on the hormone levels.
     * Increases both anabolic and catabolic hormones proportionally to the muscle fiber size.
     */
    public void performDailyActivity() {
        anabolicHormone += Math.log10(muscleFiber.getFiberSize()) * 2.5;
        catabolicHormone += Math.log10(muscleFiber.getFiberSize()) * 2.0;
    }

    /**
     * Simulates the effect of weight lifting on the hormone levels.
     * Hormone levels are increased based on the intensity and muscle fiber size.
     */
    public void liftWeight() {
        double intensity = (double) config.getIntensity() / 100;
        double threshold = intensity * intensity;

        if (random.nextDouble() < threshold) {
            anabolicHormone += Math.log10(muscleFiber.getFiberSize()) * 55;
            catabolicHormone += Math.log10(muscleFiber.getFiberSize()) * 44;
        }
    }

    /**
     * Simulates hormone regulation during sleep.
     * Decreases both anabolic and catabolic hormones based on the number of hours of sleep.
     */
    public void sleep() {
        anabolicHormone -= 0.48 * Math.log10(anabolicHormone) * config.getHourseOfSleep();
        catabolicHormone -= 0.5 * Math.log10(catabolicHormone) * config.getHourseOfSleep();
    }

    /**
     * Facilitates growth and regulation of the muscle fiber within the patch.
     * Uses the current levels of hormones to influence muscle fiber growth and regulation.
     */
    public void developMuscle() {
        muscleFiber.grow(anabolicHormone, catabolicHormone);
        muscleFiber.regulateMuscleFiber();
    }

    /**
     * Regulates the hormone levels to ensure they remain within physiological limits.
     * Hormones are adjusted to stay within predefined maximum and minimum thresholds.
     */
    public void regulateHormones() {
        anabolicHormone = Math.min(anabolicHormone, Configuration.ANABOLIC_HORMONE_MAX);
        anabolicHormone = Math.max(anabolicHormone, Configuration.ANABOLIC_HORMONE_MIN);
        catabolicHormone = Math.min(catabolicHormone, Configuration.CATABOLIC_HORMONE_MAX);
        catabolicHormone = Math.max(catabolicHormone, Configuration.CATABOLIC_HORMONE_MIN);
    }

    /**
     * Getter methods for muscle fiber
     * 
     * @return muscle fiber which resides in this patch
     */
    public MuscleFiber getMuscleFiber() {
        return this.muscleFiber;
    }

    /**
     * Getter methods for anabolic hormone
     * 
     * @return double value of current anabolic hormone level
     */
    public double getAnabolicHormone() {
        return this.anabolicHormone;
    }

    /**
     * Getter methods for catabolic hormone
     * 
     * @return double value of current catabolic hormone level
     */
    public double getCatabolicHormone() {
        return this.catabolicHormone;
    }

    /**
     * Increase level of anabolic hormome base on the amount given
     * @param amount amount diffused from neighbour
     */
    public void increaseAnabolicHormone(double amount) {
        this.anabolicHormone += amount;
    }

    /**
     * Increase level of catabolic hormone base on the amount given
     * 
     * @param amount amount diffused from neighbour
     */
    public void increaseCatabolicHormone(double amount) {
        this.catabolicHormone += amount;
    }

    /**
     * Decrease level of anabolic hormone base on the amount given
     * 
     * @param amount amount to decrease the level
     */
    public void decreaseAnabolicHormone(double amount) {
        this.anabolicHormone -= amount;
    }

    /**
     * Decrease level of catabolic hormone base on the amount given
     * 
     * @param amount amount to decrease the level
     */
    public void decreaseCatabolicHormone(double amount) {
        this.catabolicHormone -= amount;
    }

    /**
     * Prints coordinate of the patch and corresponding anabolic and catabolic hormone
     * level to console for debugging purpose
     */
    public void printPatch() {
        System.out.printf("Patch at %d %d: %n", coordX, coordY);
        System.out.printf("  anabolicHormone: %.2f%n", anabolicHormone);
        System.out.printf("  catabolicHormone: %.2f%n", catabolicHormone);
    }
}
