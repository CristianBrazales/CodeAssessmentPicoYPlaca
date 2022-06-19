import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Vehicle {
    private String plateNumber;
    private PicoYplaca restrictions;
    private String DATEFORMAT = "yyyy/MM/dd";

    /**
     * Class constructor
     *
     * @param plate, picoYplaca
     * @return void
     * @author Cristian Brazales
     */
    public Vehicle(String plate, PicoYplaca restrictions) {
        if (plate == null || validatePlateNumber(plate) == false)
            throw new IllegalArgumentException();
        else {
            this.plateNumber = plate;
        }
        this.restrictions = restrictions;

    }

    /**
     * Verifies if the car associated the pico y placa can drive at given time
     *
     * @param date and time
     * @return void
     * @author Cristian Brazales
     */
    public boolean canDriveAt(String date, String time) throws ParseException {
        // input validation
        if (date == null || time == null || (time.length() < 4 || time.length() > 5) || !isDateValid(date))
            throw new IllegalArgumentException();
        int hours = Integer.parseInt(time.substring(0, time.indexOf(':')));
        int minutes = Integer.parseInt(time.substring(time.indexOf(':') + 1));
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59)
            throw new IllegalArgumentException();

        Date dateIns = new SimpleDateFormat(DATEFORMAT).parse(date);
        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.setTime(dateIns);
        int dayNumber = calendarInstance.get(Calendar.DAY_OF_WEEK) - 1;
        int lastDigit = getLastDigit();
        int[] daysRestricted = this.restrictions.getRestrictionByLastDigit(lastDigit);
        // check if the day is within the days restricted
        if (Arrays.stream(daysRestricted).anyMatch(x -> x == dayNumber)) {
            // check if the hour is within the periods restricted
            if (this.restrictions.isRestrictedAtGivenHour(time))
                return false;
        }
        return true;
    }

    /**
     * Returns the last digit of the vehicles plate
     *
     * @return int
     * @author Cristian Brazales
     */
    private int getLastDigit() {
        return Integer.parseInt(this.plateNumber.substring(this.plateNumber.length() - 1));
    }

    /**
     * Validates if the plate assigned is correct
     * Contains the First three letters
     * Remaining characters are Numbers
     *
     * @param plate,
     * @return boolean
     * @author Cristian Brazales
     */
    private boolean validatePlateNumber(String plate) {
        // return true on size and validation
        if (plate.length() >= 6) {
            String lettersPart = plate.substring(0, 3);
            String numbersPart = plate.substring(3);
            if (numbersPart.matches("[0-9]+") && lettersPart.matches("[a-zA-Z]+")) {
                return true;
            }
        }
        return false;

    }

    /**
     * Validate if any given dateString is valid
     *
     * @param dateStr
     * @return boolean
     * @author Cristian Brazales
     */
    private boolean isDateValid(String dateStr) {
        DateFormat date = new SimpleDateFormat(this.DATEFORMAT);
        date.setLenient(false);
        try {
            date.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
