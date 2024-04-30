/**
 * Global class responsible for running the simulation.
 * 
 * The class will parse command line arguments and set up the simulation accordingly then
 * run the simulation.
 * 
 * @author Dong Hyeog Jang (582238)
 * @author Junheng Chen (1049540)
 * @author Ning Wang (1468286)
 * 
 * @date 1 May 2024
 */

package original;

import java.io.FileWriter;
import java.io.IOException;

public class Global {

    private static int tic;
    private static int totalGrid;
    private static double muscleMass;
    private static double averageAnabolic;
    private static double averageCatabolic;
    private static Configuration config;

    /**
     * Set up the simulation to be run.
     * @param muscle the muscle which will be inspected.
     */
    public static void setUp(Muscle muscle) {
        tic = 0;
        totalGrid = Configuration.GRID_WIDTH * Configuration.GRID_HEIGHT;
    
        double sumMuscleMass = 0;
        double sumAnabolic = 0;
        double sumCatabolic = 0;

        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                Patch patch = muscle.getPatch(i, j);

                patch.regulateHormones();

                sumAnabolic += patch.getAnabolicHormone();
                sumCatabolic += patch.getCatabolicHormone();
                sumMuscleMass += patch.getMuscleFiber().getFiberSize();
            }
        }
        
        muscleMass = sumMuscleMass / 100;
        averageAnabolic = sumAnabolic / totalGrid;
        averageCatabolic = sumCatabolic / totalGrid;
    }

    /**
     * Run the simulation.
     * @param muscle the muscle which will be inspected.
     */
    public static void go(Muscle muscle) {
        double sumMuscleMass = 0;
        double sumAnabolic = 0;
        double sumCatabolic = 0;

        for (int i = 0; i < Configuration.GRID_WIDTH; i++) {
            for (int j = 0; j < Configuration.GRID_HEIGHT; j++) {
                Patch patch = muscle.getPatch(i, j);

                patch.performDailyActivity();

                if (config.isLift() && tic % config.getDaysBetweenWorkouts() == 0) {
                    patch.liftWeight();
                }

                patch.sleep();
                patch.regulateHormones();
                patch.developMuscle();

                sumAnabolic += patch.getAnabolicHormone();
                sumCatabolic += patch.getCatabolicHormone();
                sumMuscleMass += patch.getMuscleFiber().getFiberSize();
            }
        }

        muscleMass = sumMuscleMass / 100;
        averageAnabolic = sumAnabolic / totalGrid;
        averageCatabolic = sumCatabolic / totalGrid;
    }

    /**
     * Create singleton insatnce of Configuration
     * @param intensity
     * @param hoursOfSleep
     * @param daysBwWorkouts
     * @param slowTwitchFibersPercentage
     * @param lift
     */
    public static void loadConfig(
        int intensity,
        double hoursOfSleep,
        int daysBwWorkouts,
        int slowTwitchFibersPercentage,
        boolean lift
    ) {
        config = Configuration.getInstance(
            intensity,
            hoursOfSleep,
            daysBwWorkouts,
            slowTwitchFibersPercentage,
            lift
        );
    }

    /**
     * Format the current tic, muscleMass, average anabolic and catabolic into String to
     * save it into CSV.
     * @return formatted string with tic, muscleMass, averageAnabolic, averageCatabolic
     */
    public static String formatCurrentStatus() {
        return String.format(
            "%d, %f, %f, %f\n",
            tic, muscleMass,
            averageAnabolic,
            averageCatabolic
        );
    }

    public static void main(String[] args) {
        // command line arguments that will be parsed.
        String path = null;
        Integer intensity = null;
        Double hoursOfSleep = null;
        Integer daysBwWorkouts = null;
        Integer slowTwitchFibersPercentage = null;
        Boolean lift = null;

        // parse command line arguments.
        for (String arg : args) {
            if (arg.startsWith("--output=")) {
                path = arg.split("=")[1];
            } else if (arg.startsWith("--intensity=")) {
                intensity = Integer.parseInt(arg.split("=")[1]);
            } else if (arg.startsWith("--hoursOfSleep=")) {
                hoursOfSleep = Double.parseDouble(arg.split("=")[1]);
            } else if (arg.startsWith("--daysBwWorkouts=")) {
                daysBwWorkouts = Integer.parseInt(arg.split("=")[1]);
            } else if (arg.startsWith("--slowTwitchFibersPercentage=")) {
                slowTwitchFibersPercentage = Integer.parseInt(arg.split("=")[1]);
            } else if (arg.startsWith("--lift=")) {
                lift = Boolean.parseBoolean(arg.split("=")[1]);
            }
        }

        // create Configuration with parameters obtained from CLI.
        loadConfig(intensity, hoursOfSleep, daysBwWorkouts, slowTwitchFibersPercentage, lift);

        // set up the simulation
        Muscle muscle = new Muscle();
        setUp(muscle);

        try {
            // prepare writer to save results for each stage.
            FileWriter writer = new FileWriter(path);
            writer.append(config.formatConfiguration());
            writer.append("Tic, MuscleMass, Anabolic, Catabolic\n");
            writer.append(formatCurrentStatus());

            // Run the simulation
            while (tic < Configuration.MAX_TIC) {
                go(muscle);
                tic++;
                
                // save the current status to output file
                writer.append(formatCurrentStatus());
            }

            writer.close();
        
        } catch(IOException e) {
            System.out.println("IO Exception occured during creating/saving/closing FileWriter");
            e.getStackTrace();
        }
    }
}