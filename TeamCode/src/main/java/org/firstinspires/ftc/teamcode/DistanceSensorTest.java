package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
//@Disabled
@Autonomous(name = "DistanceSensorTest")

public class DistanceSensorTest extends LinearOpMode {
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private Rev2mDistanceSensor distanceSensor;
    private MecanumDrive drive;
    public static double dist =10.0;
    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontleft");
        frontRight = hardwareMap.get(DcMotor.class, "frontright");
        backLeft = hardwareMap.get(DcMotor.class, "backleft");
        backRight = hardwareMap.get(DcMotor.class, "backright");

        distanceSensor = hardwareMap.get(Rev2mDistanceSensor.class, "distancesensor");
        Pose2d initialPose = new Pose2d(-10, -55, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, initialPose);

        waitForStart();

        while (opModeIsActive()) {
            double distance = distanceSensor.getDistance(DistanceUnit.INCH);

            if (distance < dist) { // Distance threshold in inches
                // Stop the motors
                setDrivePowers(0, 0, 0, 0);
            } else {
                // Move forward with a certain velocity
                Vector2d linearVelocity = new Vector2d(0.5, 0); // Forward velocity
                PoseVelocity2d velocity = new PoseVelocity2d(linearVelocity, 0); // No rotation
                setDrivePowers(velocity);
            }

            telemetry.addData("Distance (Inch)", distance);
            telemetry.update();
        }
    }

    // Helper method to set the power of all drive motors
    private void setDrivePowers(double fl, double fr, double bl, double br) {
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }

    // Overloaded helper method to set the drive powers using PoseVelocity2d
    private void setDrivePowers(PoseVelocity2d velocity) {
        double x = velocity.linearVel.x;
        double y = velocity.linearVel.y;
        double theta = velocity.angVel;
        //double x = velocity.getLinearVelocity().getX();
        //double y = velocity.getLinearVelocity().getY();
        // double theta = velocity.getHeadingVelocity();

        // Assuming a simple mecanum drive kinematic model
        double frontLeftPower = x + y + theta;
        double frontRightPower = x - y - theta;
        double backLeftPower = x - y + theta;
        double backRightPower = x + y - theta;

        setDrivePowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }
}