# CodeAssessmentPicoYPlaca

Code assessment about pico y placa.
Rules:

Depending on the license plate number , a date , and a time. The program should return whether or not that car can be on the road. 
Input: 
Plate number : 
The unique letter-number combination consists of three letters followed by three or four digits ranging from 000 to 9999
Format accepted: 'AAA000'

Date: Date the car is on the road

Format accepted: 'YYYY/MM/DD'

Time: Hour the car is on the road
Format accepted: (24Hour) 'HH:MM'

To create flexibility and maiteniance, two classes were created (PicoYplaca and Vehicle)

#PicoYplaca

PicoYplaca sets the restrictions based on an input array that maps the last digit (index of the array) of a vehicle and the list of days this can not drive for example
example:

[[5,1], [2], [1],..]

[5,1] is the list at index 0, meaning that vehicles with the last digit 0 can not drive on days 5(Friday) and 1(Monday)

[2] is the list at index 1, meaning that vehicles with the last digit 2 can not drive on days 2 (Tuesday)

...

PicoYplaca sets the restrictions hours based on a list of periods where the pico y placa is active

example:
[[06:00 - 09:00],[17:00-19:00]]

By having the set of restrictions as input we can easily mantain the code and make it independent of certain values

For testing this are the restrictions:
PICO Y PLACA( RESTRICTIONS )
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
 *  Sábado, domingo , libre circulación vehicular las 24 horas del día.
 *
 *  Horario: 7:00 a 9:00 y de 16:00 a 19:30 
 *
 Code example:
 
    private int[][] arr = {{5}, {1}, {1}, {2}, {2}, {3}, {3}, {4}, {4}, {5}};
    private String[][] hourPeriods = {{"7:00", "9:00"}, {"16:00", "19:30"}};
    // Set up the pico y placa
    private PicoYplaca restrictions = new PicoYplaca(arr, hourPeriods);
    // create a car
    Vehicle carEndingOne = new Vehicle("COT871", restrictions);
    carEndingOne.canDriveAt("2022/06/20", "8:00") -> returns false since Monday is restricted for vehicles ending in one

## Vehicle ## 
This class validates the plates and other inputs and checks if the vehicle is allowed to drive
## Technology stack ##
Programming language: Java sdk17
Build tool: Maven
Continuos Integration (Builds and Regresion Testing): Travis CI
## Travis CI ##
this tool perform regression tests after every push and make sure that the application is running and passing all tests.
For detailed information on the builds and tests
https://app.travis-ci.com/github/CristianBrazales/CodeAssessmentPicoYPlaca

## Travis CI run ##
![alt text](https://github.com/CristianBrazales/CodeAssessmentPicoYPlaca/blob/main/src/main/resources/screenshot/ci.png)
## Testing coverage ##
![alt text](https://github.com/CristianBrazales/CodeAssessmentPicoYPlaca/blob/main/src/main/resources/screenshot/testCoverage.png)
## Tests passed ##
![alt text](https://github.com/CristianBrazales/CodeAssessmentPicoYPlaca/blob/main/src/main/resources/screenshot/testsPassed.png)
