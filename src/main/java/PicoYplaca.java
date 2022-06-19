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

    /**
     * constructor of the class
     *
     * @param plate date time
     * @return void
     * @author Cristian Brazales
     */

    public PicoYplaca(int[][] restrictionsByPlate, String[][] hourPeriod) {
        this.restrictionsByPlate = restrictionsByPlate;
        this.hourPeriod = hourPeriod;
    }

    /**
     * returns the days a vehicle should not drive
     *
     * @param lastDigit (int)
     * @return Array of days the car cannot drive
     * @author Cristian Brazales
     */

    public int[] getRestrictionByLastDigit(int lastDigit) {
        return restrictionsByPlate[lastDigit];
    }

    /**
     * returns if the specified hour is within the periods of pico y placa
     *
     * @param string hour
     * @return boolean
     * @author Cristian Brazales
     */

    public boolean isRestrictedAtGivenHour(String hour) {
        return true;
    }
}
