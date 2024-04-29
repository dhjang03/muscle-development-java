/**
 * 
 */

package original;

public class Patch {

    private static Configuration config = Configuration.getInstance();

    private int coordX;
    private int coordY;

    protected MuscleFiber muscleFiber;
    protected double anabolicHormone;
    protected double catabolicHormone;


    public Patch(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.muscleFiber = new MuscleFiber(coordX, coordY);
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
}
