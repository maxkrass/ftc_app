package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

/**
 * NEEDS REWORKING FOR NEW ROBOT!
 * @TODO: FIX THE METHODS FOR THIS YEARS DESIGN!!!
 *
 * This class is used for moving, unloading, harvesting etc
 * Every Op-Mode creates an instance of this class that will get the input from the Op-Mode and
 * than will run the methods using the given data
 *
 * Created by Marcel on 01.10.2016.
 */
@SuppressWarnings("unused")
public class BasicOPMode extends OpMode {

    private DcMotor left1;
    private DcMotor left2;
    private DcMotor right1;
    private DcMotor right2;
    private DcMotor debris;
    private Servo ziplineL;
    private Servo ziplineR;
    private Servo unloaddebris;
    private static double nullung;

    /**
     * Initiliazes the basic motors
     * <p>
     * MUST BE CALLED BEFORE USING THE METHODS
     */
    @Override
    public void init() {
        left1 = hardwareMap.dcMotor.get("links1");
        left2 = hardwareMap.dcMotor.get("links2");

        right1 = hardwareMap.dcMotor.get("rechts1");
        right2 = hardwareMap.dcMotor.get("rechts2");

        right1.setDirection(DcMotor.Direction.REVERSE);
        right2.setDirection(DcMotor.Direction.REVERSE);

        nullung = FtcRobotControllerActivity.getmValuesOrientation()[0];


        // debris = hardwareMap.dcMotor.get("motor_5");
        // debris.setDirection(DcMotor.Direction.REVERSE);
        //unloaddebris = hardwareMap.servo.get("servo_2");

        ziplineL = hardwareMap.servo.get("ziplinel");
        ziplineR = hardwareMap.servo.get("zipliner");
    }

    @Override
    public void loop() {
        //does nothing
    }

    @Override
    public void stop() {
        left1.setPower(0);
        left2.setPower(0);
        right1.setPower(0);
        right2.setPower(0);
        ziplineL.setPosition(ziplineL.getPosition());
        ziplineR.setPosition(ziplineR.getPosition());
    }


    /**
     * Method for returning delta turnangle since init()
     * @return the delta x of the current position from the start rotation
     */
    public double deltadegrees() {
        return (FtcRobotControllerActivity.getmValuesOrientation()[0] - nullung);
    }


    /**
     * Method for driving forward in a straight line
     * speed needs to be between 1 and -1
     * the algorithms checks the slope and prevents the robot from driving to fast;
     * the values will be mapped to 0.5;
     */
    public void moveforward(double speed) {
        /*if (FtcRobotControllerActivity.getmValuesOrientation()[1] * 180 / Math.PI > 50 && speed > 0.5) {
            speed = 0.5;
        }
        */
        left1.setPower(speed);
        left2.setPower(speed);
        right1.setPower(speed);
        right2.setPower(speed);

    }

    //Method for driving certain number of encoderpositions
    @Deprecated
    public void moveforwardI(int length) {
        left1.setTargetPosition(length);
        left2.setTargetPosition(length);
        right1.setTargetPosition(length);
        right2.setTargetPosition(length);
    }

    //Method for driving certain number of encoderpositions;both sides can be accessed individually
    @Deprecated
    public void moveforwardI(int lengthl, int lengthr) {
        left1.setTargetPosition(lengthl);
        left2.setTargetPosition(lengthl);
        right1.setTargetPosition(lengthr);
        right2.setTargetPosition(lengthr);
    }

    /**
     * Method for driving forward but having some motors faster than the others so the robot will
     * drive in a curve
     * the algorithms checks the slope and prevents the robot from driving to fast;
     * the values will be mapped to 0.5/0.8;
     *
     * @param left  the left speed percentage
     * @param right the right speed percentage
     */
    public void moveforward(double left, double right) {
        //the slope angle is larger than 40° and the speed is larger than 0.5 then rescale values
        /*
        if (Math.toDegrees(getAlpha(FtcRobotControllerActivity.getmValuesOrientation()[1], FtcRobotControllerActivity.getmValuesOrientation()[2])) > 40
                && Math.abs(left) > 0.5 || Math.abs(right) > 0.5) {
            double ratio = left / right;
            if (left > right) {
                if (left > 0) {
                    left = 0.5;
                    right = left * ratio;
                } else {
                    left = -0.5;
                    right = left * ratio;
                }
            } else if (right > left) {
                if (right > 0) {
                    right = 0.5;
                    left = right * ratio;
                } else {
                    right = -0.5;
                    left = right * ratio;
                }
            } else {
                left = 0.5;
                right = 0.5;
            }
        }
        //the slope angle is larger than 20° and the speed is larger than 0.8 then rescale values
        else if (Math.toDegrees(getAlpha(FtcRobotControllerActivity.getmValuesOrientation()[1], FtcRobotControllerActivity.getmValuesOrientation()[2])) > 20
                && Math.abs(left) > 0.8 || Math.abs(right) > 0.8) {
            double ratio = left / right;
            if (left > right) {
                if (left > 0) {
                    left = 0.8;
                    right = left * ratio;
                } else {
                    left = -0.8;
                    right = left * ratio;
                }
            } else if (right > left) {
                if (right > 0) {
                    right = 0.8;
                    left = right * ratio;
                } else {
                    right = -0.8;
                    left = right * ratio;
                }
            } else {
                left = 0.8;
                right = 0.8;
            }
        }
        */
        left1.setPower(left);
        left2.setPower(left);
        right1.setPower(right);
        right2.setPower(right);
    }

    //Method for starting the harvest-mechanism
    //debris is the motor powering the mehcanism to pick up debris
    public void pickupdebris(double mode) {
        debris.setPower(mode);
    }

    //Method for unloading debris
    // if side= -1 it unloads to the left
    // if side=  1 it unloads to the right
    @SuppressWarnings("unused")
    public void unloaddebris(int side) {
        switch (side) {
            case 1:
                unloaddebris.setPosition(1);
                break;
            case -1:
                unloaddebris.setPosition(0);
                break;
            default:
                unloaddebris.setPosition(.5);
                break;
        }
    }

    //The ziplinethread is created whenever we activate the zipline.
    //It will "reset" the position of the servos after a certain amount of time. For this problem
    // threads are used because otherwise the functions can and will interfere with the OP-Modes and
    // freeze them while they wait
    @SuppressWarnings("unused")
    class ziplinethread extends Thread {
        int side = 0;

        public ziplinethread(int side) {
            this.side = side;
        }

        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                //if the program will land here something went horribly wrong!
            }
            if (side == 1) {
                ziplineR.setPosition(0);
            } else if (side == -1) {
                ziplineL.setPosition(1);
            }

        }
    }


    //Method for activating the right servo
    //the method will tell the servo to extend and then return to the start position
    // stayed in the extended position for 0.5 seconds
    @SuppressWarnings("unused")
    private boolean leftzip = false;
    private double timesetl = 0;

    public void activateziplineR() {
        if (time - timesetl > 0.5) {
            if (!leftzip) {
                ziplineR.setPosition(0.5);
            } else {
                ziplineR.setPosition(0);
            }
            leftzip = !leftzip;
            timesetl = time;
        }
    }

    //Method for activating the left servo
    //the method will tell the servo to extend and then return to the start position after it
    // stayed in the extended position for 0.5 seconds
    @SuppressWarnings("unused")
    private boolean rightzip = false;
    private double timesetr = 0;

    public void activateziplineL() {
        if (time - timesetr > 0.5) {
            if (!rightzip) {
                ziplineL.setPosition(0.4);
            } else {
                ziplineL.setPosition(0.9);

            }
            rightzip = !rightzip;
            timesetr = time;
        }
    }

    /**
     * Method for activating the climbermechanism
     * needs editing as the mechanism hasnt been planned yet
     */
    @SuppressWarnings("unused")
    public void climber() {
        //code for using climber mechanism
    }


    //AUto telemetry
    public void send_telemery_data() {
        telemetry.addData("Time", time);
        telemetry.addData("Left_M_1", left1.getCurrentPosition());
        telemetry.addData("Left_M_2", left2.getCurrentPosition());
        telemetry.addData("Right_M_1", right1.getCurrentPosition());
        telemetry.addData("Right_M_2", right2.getCurrentPosition());
    }

//                          ALL METHOD/OBJECTS FOR COMPASS/ANGLES

    /**
     * Method for getting the angle of the slope where the robot is
     * Note that its accuracy is about 1-3 degrees
     */
    public double getAlpha(float xangle, float yangle) {
        return Math.abs(Math.atan(Math.tan(xangle) * Math.tan(yangle) * Math.sqrt((1 / Math.tan(Math.pow(xangle, 2))) + (1 / Math.tan(Math.pow(yangle, 2))))));
    }
}

