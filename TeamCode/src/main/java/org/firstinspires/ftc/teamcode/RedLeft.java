package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;



@Config
@Autonomous
public class RedLeft extends LinearOpMode{

    Servo elbow = null;
    Servo intake = null;
    DcMotor elevator = null;

    @Override
    public void runOpMode() throws InterruptedException {

         elbow = hardwareMap.get(Servo.class, "elbow");
        elbow.scaleRange(0,1);
        elbow.setPosition(0.53);

        intake = hardwareMap.get(Servo.class, "intake");
        intake.scaleRange(0,1);
        intake.setPosition(0.9);

        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevator.setDirection(DcMotorSimple.Direction.REVERSE);


        Pose2d initialPose = new Pose2d(-23,-54,Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap,initialPose);

        waitForStart();


        TrajectoryActionBuilder path1 = drive.actionBuilder(initialPose)
                .lineToY(-24)
                .turn(Math.toRadians(90))
                        .lineToX(-48);


        Actions.runBlocking(new SequentialAction(path1.build()));


    }
}
