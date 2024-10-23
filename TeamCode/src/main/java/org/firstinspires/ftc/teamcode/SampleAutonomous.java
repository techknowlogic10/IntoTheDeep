/* Created by Phil Malone. 2023.
    This class illustrates my simplified Odometry Strategy.
    It implements basic straight line motions but with heading and drift controls to limit drift.
    See the readme for a link to a video tutorial explaining the operation and limitations of the code.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
 * This OpMode illustrates an autonomous opmode using simple Odometry
 * All robot functions are performed by an external "Robot" class that manages all hardware interactions.
 * Pure Drive or Strafe motions are maintained using two Odometry Wheels.
 * The IMU gyro is used to stabilize the heading during all motions
 */

@Autonomous(name="Sample Autonomous", group = "Auto")
public class SampleAutonomous extends LinearOpMode
{
    // get an instance of the "Robot" class.
    private SimplifiedOdometryRobot robot = new SimplifiedOdometryRobot(this);
    Servo elbow = null;
    Servo intake = null;
    DcMotor elevator = null;

    @Override public void runOpMode()
    {
        // Initialize the robot hardware & Turn on telemetry
        robot.initialize(true);

        elbow = hardwareMap.get(Servo.class, "elbow");
        elbow.scaleRange(0,1);
        elbow.setPosition(0.48);

        intake = hardwareMap.get(Servo.class, "intake");
        intake.scaleRange(0,1);
        intake.setPosition(0.8);

        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setDirection(DcMotorSimple.Direction.FORWARD);
        //elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);  // Reset Encoders to zero
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Wait for driver to press start
        telemetry.addData(">", "Touch Play to run Auto");
        telemetry.update();

        waitForStart();
        robot.resetHeading();  // Reset heading to set a baseline for Auto

        // Run Auto if stop was not pressed.
        if (opModeIsActive())
        {
            // Note, this example takes more than 30 seconds to execute, so turn OFF the auto timer.


            robot.drive(  10, 0.60, 0.25);

            /*

            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            telemetry.addLine("elevaor taregt 1400");
            elevator.setTargetPosition(1400);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(0.8);*/


            // Drive a large rectangle, turning at each corner
            /*robot.drive(  84, 0.60, 0.25);
            robot.turnTo(90, 0.45, 0.5);
            robot.drive(  72, 0.60, 0.25);
            robot.turnTo(180, 0.45, 0.5);
            robot.drive(  84, 0.60, 0.25);
            robot.turnTo(270, 0.45, 0.5);
            robot.drive(  72, 0.60, 0.25);
            robot.turnTo(0, 0.45, 0.5);

            sleep(500);

            // Drive the path again without turning.
            robot.drive(  84, 0.60, 0.15);
            robot.strafe( 72, 0.60, 0.15);
            robot.drive( -84, 0.60, 0.15);
            robot.strafe(-72, 0.60, 0.15);*/
        }
    }
}
