/**
 * Global class responsible for running the simulation.
 * 
 * The class will parse command line arguments and set up the simulation accordingly then
 * run the simulation. At each stage of the simulation it will save the result to the CSV.
 * 
 * @author Dong Hyeog Jang (582238)
 * @author Junheng Chen (1049540)
 * @author Ning Wang (1468286)
 * 
 * @date 4 May 2024
 */

package exteded;

import java.io.FileWriter;
import java.io.IOException;

public class Global {

    private static int tic;                 // indicate iteration step of simulation
    private static double muscleMass;       // sum of muscle fiber size divided by 100
    private static double averageAnabolic;  // average anabolic hormone level in muscle
    private static double averageCatabolic; // average catabolic hormone level in muscle
    private static Configuration config;    // configuration for simulation

    /**
     * Set up the simulation to be run.
     * 
     * @return muscle the muscle which will be inspected.
     */
    public static Muscle setUp() {
        tic = 0;
    
        Muscle muscle = new Muscle();
        muscle.triggerRegulateHormones();
        
        muscleMass = muscle.getMuscleMass();
        averageAnabolic = muscle.getAverageAnabolicHormone();
        averageCatabolic = muscle.getAverageCatabolicHormone();

        return muscle;
    }

    /**
     * Run the simulation.
     * 
     * @param muscle the muscle which will be inspected.
     */
    public static void go(Muscle muscle) {
        muscle.triggerDailyActivity();

        if (config.isLift() && tic % config.getDaysBetweenWorkouts() == 0) {
            muscle.triggerLiftWeight();
        }

        muscle.triggerEat();
        muscle.triggerSleep();
        muscle.triggerRegulateHormones();
        muscle.triggerDevelopMuscle();

        muscleMass = muscle.getMuscleMass();
        averageAnabolic = muscle.getAverageAnabolicHormone();
        averageCatabolic = muscle.getAverageCatabolicHormone();
    }

    /**
     * Create singleton insatnce of Configuration
     * 
     * @param intensity intensity of workout
     * @param sleepHours hours of sleep between day
     * @param workoutInterval number of days between workout
     * @param slowTwitchFiberPercentage percentage of slow twitch fibers in muscle
     * @param lift indicate whether a subject do workout or not
     * @param nutritionQuality quality of nutrition
     */
    public static void loadConfig(
        int intensity,
        double hoursOfSleep,
        int daysBwWorkouts,
        int slowTwitchFibersPercentage,
        boolean lift,
        double nutritionQuality
    ) {
        config = Configuration.getInstance(
            intensity,
            hoursOfSleep,
            daysBwWorkouts,
            slowTwitchFibersPercentage,
            lift,
            nutritionQuality
        );
    }

    /**
     * Format the current tic, muscleMass, average anabolic and catabolic into String to
     * save it into CSV.
     * 
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

    /**
     * This program simulates muscle growth based on various parameters provided by the user.
     * It uses command line arguments to configure a muscle simulation environment, performs 
     * the simulation, and outputs the results to a specified file.
     *
     * Command line arguments:
     *  --output=[filePath]                        Path to the output file
     *  --intensity=[intensityLevel]               Workout intensity as an integer
     *  --hoursOfSleep=[hours]                     Average hours of sleep per night as a double
     *  --daysBwWorkouts=[daysBetweenWorkouts]     Days between workouts as an integer
     *  --slowTwitchFibersPercentage=[percentage]  Slow twitch fibers % in muscle as an integer
     *  --lift=[true|false]                        Indicates whether a subject do lift or not
     *  --nutritionQuality=[nutritionQuality]      Quality of nutrition as double
     *
     * @param args the command line arguments used to configure the simulation parameters.
     */
    public static void main(String[] args) {
        // command line arguments that will be parsed.
        String path = null;
        Integer intensity = null;
        Double hoursOfSleep = null;
        Integer daysBwWorkouts = null;
        Integer slowTwitchFibersPercentage = null;
        Boolean lift = null;
        Double nutritionQuality = null;

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
            } else if (arg.startsWith("--nutritionQuality=")) {
                nutritionQuality = Double.parseDouble(arg.split("=")[1]);
            }
        }

        // create Configuration with parameters obtained from CLI.
        loadConfig(
            intensity,
            hoursOfSleep,
            daysBwWorkouts, 
            slowTwitchFibersPercentage,
            lift,
            nutritionQuality
        );

        // set up the simulation
        Muscle muscle = setUp();

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