import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class VehicleTest extends TestCase {
    private int[][] arr = {{5}, {1}, {1}, {2}, {2}, {3}, {3}, {4}, {4}, {5}};
    private String[][] hourPeriods = {{"7:00", "9:00"}, {"16:00", "19:30"}};
    private PicoYplaca restrictions = new PicoYplaca(arr, hourPeriods);

    @Test
    public void testForNonValidInputsForCarCreation() {
        // Non Numeric
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Vehicle car1 = new Vehicle("AVVAVVV", restrictions);
        });
        // invalid length
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            Vehicle car1 = new Vehicle("AAAAAAAAAAAA", restrictions);
        });
        // empty parameters
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            Vehicle car1 = new Vehicle("", restrictions);
        });
        // null parameter
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            Vehicle car1 = new Vehicle(null, restrictions);
        });
    }


}