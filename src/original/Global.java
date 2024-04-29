/**
 * Global
 */

package original;

public class Global {

    private static int tic;
    private static double muscleMass;
    private static double averageAnabolic;
    private static double averageCatabolic;
    private static Configuration config;

    public static void setUp(Muscle muscle) {

    }

    public static void go(Muscle muscle) {

    }

    public static void loadConfig(String[] args) {
        Integer intensity = null;
        Double hoursOfSleep = null;
        Integer daysBwWorkouts = null;
        Integer slowTwitchFibersPercentage = null;
        Boolean lift = null;

        for (String arg : args) {
            if (arg.startsWith("--intensity=")) {
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

        config = Configuration.getInstance(
            intensity,
            hoursOfSleep,
            daysBwWorkouts,
            slowTwitchFibersPercentage,
            lift
        );
    }

    public static void main(String[] args) {
        loadConfig(args);
        config.printConfiguration();
        Muscle muscle = new Muscle();
        setUp(muscle);
    }
}