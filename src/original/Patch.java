/**
 * 
 */

package original;

import java.util.Random;

public class Patch {

    private static final int MAX_NEIGHBOUR = 8;
    private static final Configuration config = Configuration.getInstance();

    private Random random = new Random();
    
    private Muscle muscle;
    private MuscleFiber muscleFiber;

    private int coordX;
    private int coordY;
    private double anabolicHormone;
    private double catabolicHormone;

    public Patch(Muscle muscle, int coordX, int coordY) {
        this.muscle = muscle;
        this.coordX = coordX;
        this.coordY = coordY;
        this.muscleFiber = new MuscleFiber();
        this.anabolicHormone = Configuration.ANABOLIC_HORMONE_MIN;
        this.catabolicHormone = Configuration.CATABOLIC_HORMONE_MIN;
    }

    public void performDailyActivity() {
        anabolicHormone += Math.log10(muscleFiber.getFiberSize()) * 2.5;
        catabolicHormone += Math.log10(muscleFiber.getFiberSize()) * 2.0;
    }

    public void liftWeight() {
        double intensity = config.getIntensity() / 100;
        double threshold = intensity * intensity;

        if (random.nextDouble() < threshold) {
            anabolicHormone += Math.log10(muscleFiber.getFiberSize()) * 55;
            catabolicHormone += Math.log10(muscleFiber.getFiberSize()) * 44;
        }
    }

    public void sleep() {
        anabolicHormone -= 0.48 * Math.log10(anabolicHormone) * config.getHourseOfSleep();
        catabolicHormone -= 0.5 * Math.log10(catabolicHormone) * config.getHourseOfSleep();
    }

    public void developMuscle() {
        muscleFiber.grow(anabolicHormone, catabolicHormone);
        muscleFiber.regulateMuscleFiber();
    }

    public void regulateHormones() {
        diffuse();
        anabolicHormone = Math.min(anabolicHormone, Configuration.ANABOLIC_HORMONE_MAX);
        anabolicHormone = Math.max(anabolicHormone, Configuration.ANABOLIC_HORMONE_MIN);
        catabolicHormone = Math.min(catabolicHormone, Configuration.CATABOLIC_HORMONE_MAX);
        catabolicHormone = Math.max(catabolicHormone, Configuration.CATABOLIC_HORMONE_MIN);
    }

    /**
     * Implementation of Netlogo's diffuse function based on description sepecified on
     * https://ccl.northwestern.edu/netlogo/docs/dictionary.html#diffuse.
     */
    public void diffuse() {
        Patch[] neighbours = muscle.getNeighbours(coordX, coordY);

        double totalAnabolicToDiffuse = anabolicHormone * Configuration.HORMONE_DIFFUSE_RATE;
        double totalCatabolicToDiffuse = catabolicHormone * Configuration.HORMONE_DIFFUSE_RATE;

        double anabolicShare = totalAnabolicToDiffuse / MAX_NEIGHBOUR;
        double catabolicShare = totalCatabolicToDiffuse / MAX_NEIGHBOUR;

        for (Patch neighbour : neighbours) {
            neighbour.anabolicHormone += anabolicShare;
            neighbour.catabolicHormone += catabolicShare;
        }

        anabolicHormone -= anabolicShare * neighbours.length;
        catabolicHormone -= catabolicShare * neighbours.length;
    }

    public MuscleFiber getMuscleFiber() {
        return this.muscleFiber;
    }

    public double getAnabolicHormone() {
        return this.anabolicHormone;
    }

    public double getCatabolicHormone() {
        return this.catabolicHormone;
    }

    public void printPatch() {
        System.out.printf("Patch at %d %d: %n", coordX, coordY);
        System.out.printf("  anabolicHormone: %.2f%n", anabolicHormone);
        System.out.printf("  catabolicHormone: %.2f%n", catabolicHormone);
    }
}
