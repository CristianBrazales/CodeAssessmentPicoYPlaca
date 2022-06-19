import junit.framework.TestCase;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertThrows;

/*
 * Base rules:
 * Restriction
 *  lunes, 1 y 2
 *
 *  martes, 3 y 4;
 *
 *  miércoles, 5 y 6;
 *
 *  jueves, 7 y 8;
 *
 *  viernes, 9 y 0.
 *
 *  Sábado, domingo y feriados, libre circulación vehicular las 24 horas del día.*/

public class PicoYplacaTest extends TestCase {
    private int[][] arr = {{5}, {1}, {1}, {2}, {2}, {3}, {3}, {4}, {4}, {5}};
    private String[][] hourPeriods = {{"7:00", "9:00"}, {"16:00", "19:30"}};
    private PicoYplaca restrictions = new PicoYplaca(arr, hourPeriods);
    Vehicle carEndingOne = new Vehicle("COT871", restrictions);
    Vehicle carEndingTwo = new Vehicle("PXA872", restrictions);
    Vehicle carEndingThree = new Vehicle("IMB873", restrictions);
    Vehicle carEndingFour = new Vehicle("THC874", restrictions);
    Vehicle carEndingFive = new Vehicle("COT875", restrictions);
    Vehicle carEndingSix = new Vehicle("PIC876", restrictions);
    Vehicle carEndingSeven = new Vehicle("AZU877", restrictions);
    Vehicle carEndingEight = new Vehicle("COP878", restrictions);
    Vehicle carEndingNine = new Vehicle("TUC879", restrictions);
    Vehicle carEndingCero = new Vehicle("PCT870", restrictions);

    public PicoYplacaTest() throws ParseException {
    }

    @Test
    public void testMonday() throws ParseException {
        // Monday Restricted ending 1 and 2
        // Testing restriction day and time interval 1
        assertFalse("should fail can not drive at time ", carEndingOne.canDriveAt("2022/06/20", "8:00"));
        // Testing restriction day and time interval 2
        assertFalse("should fail can not drive at time ", carEndingTwo.canDriveAt("2022/06/20", "17:00"));
        // Testing restriction day but no at time interval
        assertTrue("should pass, allowed outside window time ", carEndingTwo.canDriveAt("2022/06/20", "6:00"));
        // Testing no restriction
        assertTrue("should pass", carEndingThree.canDriveAt("2022/06/20", "6:00"));
    }

    @Test
    public void testTuesday() throws ParseException {
        // Tuesday Restricted ending 3 and 4
        // Testing restriction day and time interval 1
        assertFalse("should fail can not drive at time ", carEndingThree.canDriveAt("2022/06/21", "9:00"));
        // Testing restriction day and time interval 2
        assertFalse("should fail can not drive at time ", carEndingFour.canDriveAt("2022/06/21", "19:00"));
        // Testing restriction day but no at time interval
        assertTrue("should pass, allowed outside window time ", carEndingThree.canDriveAt("2022/06/21", "6:00"));
        // Testing no restriction
        assertTrue("should pass", carEndingOne.canDriveAt("2022/06/21", "6:00"));
    }

    @Test
    public void testWednesday() throws ParseException {
        // Wednesday Restricted ending 5 and 6
        // Testing restriction day and time interval 1
        assertFalse("should fail can not drive at time ", carEndingFive.canDriveAt("2022/06/22", "7:30"));
        // Testing restriction day and time interval 2
        assertFalse("should fail can not drive at time ", carEndingSix.canDriveAt("2022/06/22", "18:00"));
        // Testing restriction day but no at time interval
        assertTrue("should pass, allowed outside window time ", carEndingSix.canDriveAt("2022/06/22", "6:00"));
        // Testing no restriction
        assertTrue("should pass", carEndingTwo.canDriveAt("2022/06/22", "6:00"));
    }

    @Test
    public void testThursday() throws ParseException {
        // Thursday Restricted ending 7 and 8
        // Testing restriction day and time interval 1
        assertFalse("should fail can not drive at time ", carEndingSeven.canDriveAt("2022/06/09", "7:00"));
        // Testing restriction day and time interval 2
        assertFalse("should fail can not drive at time ", carEndingEight.canDriveAt("2022/06/16", "16:00"));
        // Testing restriction day but no at time interval
        assertTrue("should pass, allowed outside window time ", carEndingSeven.canDriveAt("2022/06/23", "5:00"));
        // Testing no restriction
        assertTrue("should pass", carEndingNine.canDriveAt("2022/06/23", "6:00"));
    }

    public void testFriday() throws ParseException {
        // Thursday Restricted ending 9 and 0
        // Testing restriction day and time interval 1
        assertFalse("should fail can not drive at time ", carEndingNine.canDriveAt("2022/06/10", "7:15"));
        // Testing restriction day and time interval 2
        assertFalse("should fail can not drive at time ", carEndingNine.canDriveAt("2022/06/17", "17:00"));
        // Testing restriction day but no at time interval
        assertTrue("should pass, allowed outside window time ", carEndingCero.canDriveAt("2022/06/24", "6:59"));
        // Testing no restriction
        assertTrue("should pass", carEndingNine.canDriveAt("2022/06/10", "6:00"));
    }

    public void testWeekend() throws ParseException {
        // No Restriction, any valid input should pass
        assertTrue("should pass ", carEndingOne.canDriveAt("2022/06/11", "7:15"));
        assertTrue("should pass", carEndingTwo.canDriveAt("2022/06/18", "17:00"));
        assertTrue("should pass", carEndingThree.canDriveAt("2022/06/26", "6:59"));
        assertTrue("should pass", carEndingFour.canDriveAt("2022/06/12", "6:00"));
        assertTrue("should pass ", carEndingFive.canDriveAt("2022/06/11", "7:15"));
        assertTrue("should pass", carEndingSix.canDriveAt("2022/06/18", "17:00"));
        assertTrue("should pass", carEndingSeven.canDriveAt("2022/06/26", "6:59"));
        assertTrue("should pass", carEndingEight.canDriveAt("2022/06/12", "6:00"));
        assertTrue("should pass", carEndingNine.canDriveAt("2022/06/26", "6:59"));
        assertTrue("should pass", carEndingCero.canDriveAt("2022/06/12", "6:00"));
    }

    public void testInvalidInput() throws ParseException {
        // Invalid inputs
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            carEndingOne.canDriveAt("2022/06/11", null);
        });

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            carEndingOne.canDriveAt(null, "7:15");
        });
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            carEndingOne.canDriveAt("2022/06/51", "37:15");
        });
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            carEndingOne.canDriveAt("2022/06/100", "7:15");
        });
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> {
            carEndingOne.canDriveAt("2022/06/11", "57:15");
        });
    }


}