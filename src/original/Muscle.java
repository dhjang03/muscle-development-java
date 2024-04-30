/**
 * 
 */

package original;

import java.util.ArrayList;

public class Muscle {

    private Patch[][] patches;

    public Muscle() {
        this.patches = new Patch[Configuration.GRID_WIDTH][Configuration.GRID_HEIGHT];
        initialise();
    }

    private void initialise() {
        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                this.patches[i][j] = new Patch(this, i, j);
            }
        }
    }

    public Patch getPatch(int i, int j) {
        if (i >= 0 && i < Configuration.GRID_WIDTH && j >= 0 && j < Configuration.GRID_HEIGHT) {
            return this.patches[i][j];
        }
        return null;
    }

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
