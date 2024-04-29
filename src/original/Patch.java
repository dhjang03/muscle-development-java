/**
 * 
 */

package original;

public class Patch {

    private static Configuration config = Configuration.getInstance();

    private MuscleFiber muscleFiber;
    private double anabolicHormone;
    private double catabolicHormone;


    public Patch() {
        this.muscleFiber = new MuscleFiber();
        this.anabolicHormone = Configuration.ANABOLIC_HORMONE_MIN;
        this.catabolicHormone = Configuration.CATABOLIC_HORMONE_MIN;
    }

    public void performDailyActivity() {

    }

    public void liftWeight() {

    }

    public void sleep() {

    }

    public void regulateHormones() {

    }

    public void developMuscle() {

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
}
