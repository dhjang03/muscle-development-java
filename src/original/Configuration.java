/**
 * Configuration for the model that is used for simulation.
 * 
 * The configuration class is a singleton class to ensure every other class use
 * same parameter values. The class contains constant value for the simulation as well as
 * user defined values from command line interface.
 * 
 * @author Dong Hyeog Jang (582238)
 * @author Junheng Chen (1049540)
 * @author Ning Wang (1468286)
 * 
 * @date 4 May 2024
 */

package original;

public class Configuration {

    protected static final int MAX_TIC = 3000;                 // upper bound for tic
    protected static final int GRID_WIDTH = 17;                // width of grid
    protected static final int GRID_HEIGHT = 17;               // height of grid
    protected static final int ANABOLIC_HORMONE_MAX = 200;     // upper bound for anabolic hormone
    protected static final int ANABOLIC_HORMONE_MIN = 50;      // lower bound for anabolic hormone
    protected static final int CATABOLIC_HORMONE_MAX = 250;    // upper bound for catabolic hormone
    protected static final int CATABOLIC_HORMONE_MIN = 52;     // lower bound for catabolic hormone
    protected static final double HORMONE_DIFFUSE_RATE = 0.75; // diffuse rate of hormones
    protected static final int MAX_NEIGHBOUR = 8;              // maximum number of patch neighbours

    private int intensity;                  // intensity of workout
    private double hoursOfSleep;            // hours of sleep between day
    private int daysBetweenWorkouts;        // number of days between workout
    private int slowTwitchFibersPercentage; // percentage of slow twitch fibers in muscle
    private boolean lift;                   // indication whether subject do workout or not

    private static Configuration instance = null; // singleton instance

    /**
     * Construtor for Configuration setting the user defined values to the instance.
     * 
     * @param intensity intensity of workout
     * @param sleepHours hours of sleep between day
     * @param workoutInterval number of days between workout
     * @param slowTwitchFiberPercentage percentage of slow twitch fibers in muscle
     * @param lift indicate whether a subject do workout or not
     */
    private Configuration(
        int intensity,
        double sleepHours,
        int workoutInterval,
        int slowTwitchFiberPercentage,
        boolean lift
    ) {
        this.intensity = intensity;
        this.hoursOfSleep = sleepHours;
        this.daysBetweenWorkouts = workoutInterval;
        this.slowTwitchFibersPercentage = slowTwitchFiberPercentage;
        this.lift = lift;
    }

    /**
     * Create and return Configuration instance
     * 
     * @param intensity intensity of workout
     * @param sleepHours hours of sleep between day
     * @param workoutInterval number of days between workout
     * @param slowTwitchFiberPercentage percentage of slow twitch fibers in muscle
     * @param lift indicate whether a subject do workout or not
     * 
     * @return singleton Configuration instance
     */
    public static Configuration getInstance(
        int intensity,
        double sleepHours,
        int workoutInterval,
        int slowTwitchFiberPercentage,
        boolean lift
    ) {
        if (instance == null) {
            instance = new Configuration(
                intensity,
                sleepHours,
                workoutInterval,
                slowTwitchFiberPercentage,
                lift
            );
        }
        return instance;
    }

    /**
     * Get singleton instance of the Configuration.
     * 
     * @return singleton Configuration instance
     */
    public static Configuration getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance not created yet.");
        }
        return instance;
    }

    /**
     * Getter method for intensity
     * 
     * @return int value of intensity
     */
    public int getIntensity() {
        return this.intensity;
    }

    /**
     * Getter method for hours of sleep
     * 
     * @return double value of hours of sleep
     */
    public double getHourseOfSleep() {
        return this.hoursOfSleep;
    }

    /**
     * Getter method days between workouts
     * 
     * @return int value of days between workout
     */
    public int getDaysBetweenWorkouts() {
        return this.daysBetweenWorkouts;
    }

    /**
     * Getter method for slow twitch fiber percentage
     * 
     * @return int value of slow twitch fibers percentage
     */
    public int getSlowTwitchFibersPercentage() {
        return this.slowTwitchFibersPercentage;
    }

    /**
     * Getter method for lift
     * 
     * @return boolean value of lift
     */
    public boolean isLift() {
        return this.lift;
    }

    /**
     * Format user defined configuration value in a form that will be saved in CSV
     * 
     * @return formatted string of user defined configuration value
     */
    public String formatConfiguration() {
        return String.format(
            "MODEL SETTING\n" +
            "SlowTwitchFibersPercentage, DaysBetweenWorkout, HourseOfSleep, Intensity, Lift\n" +
            "%d, %d, %f, %d, %s\n\n",
            slowTwitchFibersPercentage,
            daysBetweenWorkouts,
            hoursOfSleep,
            intensity,
            lift
        );
    }

    /**
     * Helper function for debugging which prints Configuration variables to console
     */
    public void printConfiguration() {
        System.out.println("Static Variables:");
        System.out.println("  MAX_TIC: " + MAX_TIC);
        System.out.println("  GRID_WIDTH: " + GRID_WIDTH);
        System.out.println("  GRID_HEIGHT: " + GRID_HEIGHT);
        System.out.println("  ANABOLIC_HORMONE_MAX: " + ANABOLIC_HORMONE_MAX);
        System.out.println("  ANABOLIC_HORMONE_MIN: " + ANABOLIC_HORMONE_MIN);
        System.out.println("  CATABOLIC_HORMONE_MAX: " + CATABOLIC_HORMONE_MAX);
        System.out.println("  CATABOLIC_HORMONE_MIN: " + CATABOLIC_HORMONE_MIN);
        System.out.println("  HORMONE_DIFFUSE_RATE: " + HORMONE_DIFFUSE_RATE);
        System.out.println("\nInstance Variables:");
        System.out.println("  Intensity: " + intensity);
        System.out.println("  Hours of Sleep: " + hoursOfSleep);
        System.out.println("  Days Between Workouts: " + daysBetweenWorkouts);
        System.out.println("  Slow Twitch Fibers Percentage: " + slowTwitchFibersPercentage);
        System.out.println("  Lift: " + lift);
    }
}
