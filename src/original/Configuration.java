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
 * @date 1 May 2024
 */

package original;

public class Configuration {

    // constant values for simulation
    protected static final int MAX_TIC = 1000;
    protected static final int GRID_WIDTH = 17;
    protected static final int GRID_HEIGHT = 17;
    protected static final int ANABOLIC_HORMONE_MAX = 200;
    protected static final int ANABOLIC_HORMONE_MIN = 50;
    protected static final int CATABOLIC_HORMONE_MAX = 250;
    protected static final int CATABOLIC_HORMONE_MIN = 52;
    protected static final double HORMONE_DIFFUSE_RATE = 0.75;
    protected static final int MAX_NEIGHBOUR = 8;

    // user defined values for simulation
    private int intensity;
    private double hoursOfSleep;
    private int daysBetweenWorkouts;
    private int slowTwitchFibersPercentage;
    private boolean lift;

    // singleton instance
    private static Configuration instance = null;

    /**
     * Construtor for Configuration setting the user defined values to the instance.
     * @param intensity
     * @param sleepHours
     * @param workoutInterval
     * @param slowTwitchFiberPercentage
     * @param lift
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
     * @param intensity
     * @param sleepHours
     * @param workoutInterval
     * @param slowTwitchFiberPercentage
     * @param lift
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
     * @return singleton Configuration instance
     */
    public static Configuration getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance not created yet.");
        }
        return instance;
    }

    // Getter for intensity
    public int getIntensity() {
        return this.intensity;
    }

    // Getter for hoursOfSleep
    public double getHourseOfSleep() {
        return this.hoursOfSleep;
    }

    // Getter for daysBetweenWOrkouts
    public int getDaysBetweenWorkouts() {
        return this.daysBetweenWorkouts;
    }

    // Getter for slowTwitchFibersPercentage
    public int getSlowTwitchFibersPercentage() {
        return this.slowTwitchFibersPercentage;
    }

    // Getter for lift
    public boolean isLift() {
        return this.lift;
    }

    // Format the user defined parameters for saving the values into CSV
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

    // Print parameters to console
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
