public class Vehicle {
    private String plateNumber;
    private PicoYplaca restrictions;

    /**
     * Class constructor
     *
     * @param a plate number
     * @return void
     * @author Cristian Brazales
     */
    public Vehicle(String plate, PicoYplaca restrictions) {
        if (validatePlateNumber(plate))
            this.plateNumber = plate;
        else {
            throw new IllegalArgumentException();
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
    public boolean canDriveAt(String date, String time) {
        // TODO:Impletement function
        return false;
    }

    private String getLastDigit() {
        // TODO:Impletement function

        return plateNumber;
    }

    private boolean validatePlateNumber(String Plate) {
        // TODO:Impletement function

        return false;

    }
}
