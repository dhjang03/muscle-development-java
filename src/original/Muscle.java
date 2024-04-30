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
 * @date 1 May 2024
 */

package original;

import java.util.ArrayList;

public class Muscle {

    private Patch[][] patches; // 2D array of patches for the muscle.

    /**
     * Constructor for Muscle to initilise the pathces with grid width and height from
     * the configuration.
     */
    public Muscle() {
        this.patches = new Patch[Configuration.GRID_WIDTH][Configuration.GRID_HEIGHT];
        initialise();
    }

    /**
     * Initialise patches for each coordinate.
     */
    private void initialise() {
        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                this.patches[i][j] = new Patch(this, i, j);
            }
        }
    }

    /**
     * Get patch from patches with given coordinate.
     * @param i x coordinate of the patch
     * @param j y coordinate of the patch
     * @return patch at given coordinate
     */
    public Patch getPatch(int i, int j) {
        if (i >= 0 && i < Configuration.GRID_WIDTH && j >= 0 && j < Configuration.GRID_HEIGHT) {
            return this.patches[i][j];
        }
        return null;
    }

    /**
     * Find neighbouring patches for given coordinate.
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
}
