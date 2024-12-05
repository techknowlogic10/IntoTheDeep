package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Config
@Autonomous
public class YellowSplineTest extends LinearOpMode {

    public static int elevatorTopPos = 650;
    public static int elevatorDownPos = 0;

    //first sample
    public static double firstSampleForwardToBasket = -56.5;

    //second sample
    public static double secondSampleStrafeX = -38;
    public static double secondSampleStrafeY = -28;
    public static int secondSampleRightTurnAngle = -50;
    public static int secondSampleLeftTurnAngle = 106;
    public static double secondSampleLineToYForward = -23.5;
    public static double secondSampleForwardToBasket = -49;


    //third sample
    public static double thirdSampleBackwardY = -30;
    public static int thirdSampleRightTurnAngle = -103;
    public static double thirdSampleForwardY = -23.5;
    public static int thirdSampleLeftTurnAngle = 118;
    public static double thirdSampleForwardToBasket = -48;

    //fourth sample
    public static double fourthSampleBackwardY = -35;
    public static int fourthSampleRightTurnAngle = -80;
    public static double fourthSampleStrafeX = -60;
    public static double fourthSampleStrafeY = -10;
    public static double fourthSampleBackToX = -55;
    public static int fourthSampleLeftTurnAngle = 45;
    public static double fourthSampleForwardToBasket = -48;

    public static double intake_close = 0; //0.85;
    public static double intake_sample_open = 0.5;
    public static double intake_specimen_open = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);
        Elbow elbow = new Elbow(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Slider slider = new Slider( hardwareMap);

        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addLine("!isStopRequested() && !opModeIsActive()");
            telemetry.update();
        }

        telemetry.addData(">>", "Press start to continue");
        telemetry.update();
        waitForStart();

        if (isStopRequested()) {
            telemetry.addLine("isStopRequested");
            telemetry.update();
            return;
        }

        Pose2d initialPose = new Pose2d(-38, -64, Math.toRadians(180));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action parallelAction = drive.actionBuilder(initialPose)
                .waitSeconds(2)
                .lineToX(firstSampleForwardToBasket)
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(0.25),
                        parallelAction

                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .stopAndAdd(elbow.upElbow())
                                //.waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .waitSeconds(0.25)
                                .splineTo(new Vector2d(secondSampleStrafeX, secondSampleLineToYForward), secondSampleRightTurnAngle)

                                //second sample
                               /* .strafeTo(new Vector2d(secondSampleStrafeX, secondSampleStrafeY))
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(secondSampleRightTurnAngle))
                                .lineToY(secondSampleLineToYForward)
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.elbowTop())
                                .turn(Math.toRadians(secondSampleLeftTurnAngle))*/
                                //.waitSeconds(0.25)
                                .build()
                ));

/*
        parallelAction = drive.actionBuilder(drive.pose)
                .waitSeconds(2)
                .lineToY(secondSampleForwardToBasket)
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(0.25),
                        parallelAction
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)


                                //.waitSeconds(0.25)
                                .stopAndAdd(elbow.upElbow())
                                //.waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .waitSeconds(0.25)

                                //third sample
                                .lineToY(thirdSampleBackwardY)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(thirdSampleRightTurnAngle))
                                .lineToY(thirdSampleForwardY)
                                .waitSeconds(0.25)
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.elbowTop())
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(thirdSampleLeftTurnAngle))
                                .build()
                ));

        parallelAction = drive.actionBuilder(drive.pose)
                .waitSeconds(2)
                .lineToY(thirdSampleForwardToBasket)
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(0.25),
                        parallelAction
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                //.waitSeconds(0.25)
                                .stopAndAdd(elbow.upElbow())
                                //.waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .waitSeconds(0.25)

                                //fourth sample
                                .lineToY(fourthSampleBackwardY)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(fourthSampleRightTurnAngle))
                                .strafeTo(new Vector2d(fourthSampleStrafeX, fourthSampleStrafeY))
                                .waitSeconds(0.25)
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.5)
                                .lineToX(fourthSampleBackToX)
                                .stopAndAdd(elbow.elbowTop())
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(fourthSampleLeftTurnAngle))
                                .build()
                ));

        parallelAction = drive.actionBuilder(drive.pose)
                .waitSeconds(2)
                .lineToY(fourthSampleForwardToBasket)
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(0.25),
                        parallelAction
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                //.waitSeconds(0.25)
                                .stopAndAdd(elbow.upElbow())
                                //.waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .waitSeconds(0.25)
                                .build()
                )); */



        telemetry.addLine("end autonomous ");
        telemetry.update();

    }

}

