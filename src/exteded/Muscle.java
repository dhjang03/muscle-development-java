/**
 * Represents a muscle as a grid of patches in a simulation environment. 
 * Each patch in the muscle can interact with its neighbors, simulating biological 
 * muscle characteristics.
 * 
 * The Muscle class contains methods to initialize the grid with specified dimensions and 
 * retrieve information about specific patches and their neighbors.
 * 
 * @author Dong Hyeog Jang (582238)
 * @author Junheng Chen (1049540)
 * @author Ning Wang (1468286)
 * 
 * @date 4 May 2024
 */

package exteded;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Muscle {
    
    private Patch[][] patches; // 2D array of patches for the muscle.
    private int totalGrid;

    /**
     * Constructor for Muscle to initilise the pathces with grid width and height from
     * the configuration.
     */
    public Muscle() {
        this.patches = new Patch[Configuration.GRID_WIDTH][Configuration.GRID_HEIGHT];
        this.totalGrid = Configuration.GRID_WIDTH * Configuration.GRID_HEIGHT;
        initialise();
    }

    /**
     * Initialise patches for each coordinate.
     */
    private void initialise() {
        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                patches[i][j] = new Patch(this, i, j);
            }
        }
    }

    /**
     * Trigger hormonal effect of daily activity on each patches
     */
    public void triggerDailyActivity() {
        forEachPatch(Patch::performDailyActivity);
    }

    /**
     * Trigger hormonal effect of lifting weight on each patches
     */
    public void triggerLiftWeight() {
        forEachPatch(Patch::liftWeight);
    }

    /**
     * Trigger hormonal effect of quality of nutrition intake
     */
    public void triggerEat() {
        forEachPatch(Patch::eat);
    }

    /**
     * Trigger hormonal effect of sleeping on each patches
     */
    public void triggerSleep() {
        forEachPatch(Patch::sleep);
    }

    /**
     * Trigger muscle development on muscles fibers in each patch
     */
    public void triggerDevelopMuscle() {
        forEachPatch(Patch::developMuscle);
    }

    /**
     * Trigger hormone regulation on patches
     */
    public void triggerRegulateHormones() {
        diffuse();
        forEachPatch(Patch::regulateHormones);
    }

    /**
     * Wrapper function to perform various action on each patch
     * 
     * @param action action that will be performed
     */
    private void forEachPatch(Consumer<Patch> action) {
        for (Patch[] row : patches) {
            for (Patch patch : row) {
                action.accept(patch);
            }
        }
    }

    /**
     * Implementation of Netlogo's diffuse function based on description sepecified on
     * https://ccl.northwestern.edu/netlogo/docs/dictionary.html#diffuse.
     */
    public void diffuse() {
        Patch[][] oldPatches = copyPatchesArray();
        double[] shares = {0.0, 0.0}; // [anabolicShare, catabolicShare]

        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                computeShares(oldPatches[i][j], shares);
                Patch[] neighbours = getNeighbours(i, j);

                for (Patch neighbour : neighbours) {
                    neighbour.increaseAnabolicHormone(shares[0]);
                    neighbour.increaseCatabolicHormone(shares[1]);
                }

                patches[i][j].decreaseAnabolicHormone(shares[0] * neighbours.length);
                patches[i][j].decreaseCatabolicHormone(shares[1] * neighbours.length);
            }
        }
    }

    /**
     * Calculate amount to be shared to the neighbours with given patch
     * 
     * @param patch deep copied patches array
     * @param shares double array to store the share
     */
    private void computeShares(Patch patch, double[] shares) {
        shares[0] = patch.getAnabolicHormone() * Configuration.HORMONE_DIFFUSE_RATE 
            / Configuration.MAX_NEIGHBOUR;
        shares[1] = patch.getCatabolicHormone() * Configuration.HORMONE_DIFFUSE_RATE 
            / Configuration.MAX_NEIGHBOUR;
    }

    /**
     * Creates a deep copy of the patches array.
     */
    private Patch[][] copyPatchesArray() {
        Patch[][] copiedPatches = new Patch[Configuration.GRID_WIDTH][Configuration.GRID_HEIGHT];

        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                copiedPatches[i][j] = new Patch(patches[i][j]);
            }
        }

        return copiedPatches;
    }

    /**
     * Get patch from patches with given coordinate.
     * 
     * @param i x coordinate of the patch
     * @param j y coordinate of the patch
     * @return patch at given coordinate
     */
    public Patch getPatch(int i, int j) {
        if (i >= 0 && i < Configuration.GRID_WIDTH && j >= 0 && j < Configuration.GRID_HEIGHT) {
            return patches[i][j];
        }

        return null;
    }

    /**
     * Find neighbouring patches for given coordinate.
     * 
     * @param i x coordinate of the patch
     * @param j y coordinate of the patch
     * @return array of neighbouring patches
     */
    public Patch[] getNeighbours(int i, int j) {
        ArrayList<Patch> neighbours = new ArrayList<>();

        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int d = 0; d < 8; d++) {
            int ni = i + dRow[d];
            int nj = j + dCol[d];
            Patch neighbour = getPatch(ni, nj);
            if (neighbour != null) {
                neighbours.add(neighbour);
            }
        }

        return neighbours.toArray(new Patch[0]);
    }

    /**
     * Calculate average anabolic hormone level
     * 
     * @return current average anabolic hormone level of patches
     */
    public double getAverageAnabolicHormone() {
        double sumAnabolic = 0;

        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                sumAnabolic += patches[i][j].getAnabolicHormone();
            }
        }

        return sumAnabolic / totalGrid;
    }

    /**
     * Calculate average catabolic hormone level
     * 
     * @return current average catabolic hormone level of patches
     */
    public double getAverageCatabolicHormone() {
        double sumCatabolic = 0;

        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                sumCatabolic += patches[i][j].getCatabolicHormone();
            }
        }

        return sumCatabolic / totalGrid;
    }

    /**
     * Calculate total size of muscle fibers in muscle
     * 
     * @return total size of muscle fibers divided by 100
     */
    public double getMuscleMass() {
        double sumMass = 0;
        
        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                sumMass += patches[i][j].getMuscleFiber().getFiberSize();
            }
        }

        return sumMass / 100;
    }
}
