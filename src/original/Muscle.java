/**
 * 
 */

package original;

public class Muscle {

    private Patch[][] patches;

    public Muscle() {
        this.patches = new Patch[Configuration.GRID_WIDTH][Configuration.GRID_HEIGHT];
        initialise();
    }

    private void initialise() {

    }

    public Patch getPatch(int i, int j) {
        return this.patches[i][j];
    }
}
