/**
 * 
 */

package original;

import java.util.Random;

public class MuscleFiber {

    private static final Configuration config = Configuration.getInstance();
    private static int nextId = 1;

    private Random random = new Random();

    private int id;
    private int maxSize;
    private double fiberSize;

    public MuscleFiber() {
        this.id = nextId++;
        this.maxSize = sproutMuscleFiber();
        this.fiberSize = initFiberSize();
    }

    private int sproutMuscleFiber() {
        int size = 4;

        for (int i = 0; i < 20; i++) {
            if (random.nextDouble() * 100 > config.getSlowTwitchFibersPercentage()) {
                size += 1;
            }
        }

        return size;
    }

    private double initFiberSize() {
        return (0.2 + random.nextDouble() * 0.4) * maxSize;
    }

    public void grow(double anabolicHormone, double catabolicHormone) {
        fiberSize -= 0.2 * Math.log10(catabolicHormone);
        fiberSize += 0.2 * Math.min(
            Math.log10(anabolicHormone),
            1.05 * Math.log10(catabolicHormone)
        );
    }

    public void regulateMuscleFiber() {
        if (fiberSize < 1) {
            fiberSize = 1;
        }

        if (fiberSize > maxSize) {
            fiberSize = maxSize;
        }
    }

    public double getFiberSize() {
        return this.fiberSize;
    }

    public void printMuscleFiber() {
        System.out.printf("MuscleFiber %d%n", id);
        System.out.printf("  maxSize   = %d%n", maxSize);
        System.out.printf("  fiberSize = %.2f%n", fiberSize);
    }
}
