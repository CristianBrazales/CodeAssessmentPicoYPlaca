import java.text.ParseException;

public class PicoYplaca {
    /**
     * Map last digit plate to day of restriction , DAY_OF_THE_WEEK(Monday is 1,
     * Tuesday is 2, etc)
     * <p>
     * Example of restrictions array: [5,1,2, ...]
     * <p>
     * [0]=[5] Last digit 0 can't drive on Friday
     * <p>
     * [1]=[1] Last digit 1 can't drive on Monday
     * <p>
     * [2]=[2] Last digit 2 can't drive on Monday ...
     */
    private int[][] restrictionsByPlate;
    private String[][] hourPeriod;
    private int[][] timeIntervals;

    /**
     * constructor of the class
     *
     * @param restrictionsByPlate ,hourPeriod
     * @return void
     * @author Cristian Brazales
     */
    public PicoYplaca(int[][] restrictionsByPlate, String[][] hourPeriod) throws ParseException {
        this.restrictionsByPlate = restrictionsByPlate;
        this.hourPeriod = hourPeriod;
        this.timeIntervals = new int[hourPeriod.length][2];
        for (int i = 0; i < hourPeriod.length; i++) {

            String[] interval = hourPeriod[i];
            int lowerInterval = Integer.parseInt(interval[0].replace(":", ""));
            int upperInterval = Integer.parseInt(interval[1].replace(":", ""));
            this.timeIntervals[i] = new int[]{lowerInterval, upperInterval};
        }
    }

    /**
     * returns the days a vehicle should not drive
     *
     * @param lastDigit (int)
     * @return Array of days the car cannot drive
     * @author Cristian Brazales
     */
    public int[] getRestrictionByLastDigit(int lastDigit) {
        return this.restrictionsByPlate[lastDigit];
    }

    /**
     * returns if the specified hour is within the periods of pico y placa
     * (ie TRUE if the hour given is within a pico y placa and FALSE otherwise)
     *
     * @param hour (String)
     * @return boolean
     * @throws Exception on non-correct inputs
     * @author Cristian Brazales
     */
    public boolean isRestrictedAtGivenHour(String hour) throws ParseException {
        int timeValue = Integer.parseInt(hour.replace(":", ""));
        // Check for every interval specified on pico Y placa
        for (int i = 0; i < this.timeIntervals.length; i++) {
            if (timeValue >= timeIntervals[i][0] && timeValue <= timeIntervals[i][1])
                return true;
        }
        return false;
    }
}
