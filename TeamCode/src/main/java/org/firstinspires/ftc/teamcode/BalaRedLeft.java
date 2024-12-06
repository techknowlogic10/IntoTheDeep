package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "BalaRedLeft")

public class BalaRedLeft extends LinearOpMode {

    public static int elevatorTopPos = 650;
    public static int elevatorDownPos = 0;

    //first sample
    public static double firstSampleForwardToBasket = -54.5;
    public static double basketX = -48;
    public static double basketY = -58;

    //second sample
    public static double secondSampleStrafeX = -45;
    public static double secondSampleStrafeY = -34;

    //third sample
    public static double thirdSampleStrafeX = -58;
    public static double thirdSampleStrafeY = -37;

    //fourth sample
    public static double fourthSampleStrafeX = -58;
    public static double fourthSampleStrafeY = -26;

    public static double intake_close = 0; //0.85;
    public static double intake_specimen_open = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);
        Elbow elbow = new Elbow(hardwareMap);
        Intake intake = new Intake(hardwareMap);

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

        Action moveToBasketAction = drive.actionBuilder(initialPose)
                .waitSeconds(3)
                .lineToX(firstSampleForwardToBasket)
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(2),
                        moveToBasketAction
                )
        ));


        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .stopAndAdd(elbow.upElbow())
                                .waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .waitSeconds(0.35)

                                //second sample
                                .strafeToLinearHeading(new Vector2d(secondSampleStrafeX, secondSampleStrafeY), Math.toRadians(90))
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.elbowTop())
                                .waitSeconds(0.25)
                                .build()
                ));

        moveToBasketAction = drive.actionBuilder(drive.pose)
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(basketX, basketY), Math.toRadians(225))
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(1),
                        moveToBasketAction
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .stopAndAdd(elbow.upElbow())
                                .waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .waitSeconds(0.35)

                                //third sample)
                                .strafeToLinearHeading(new Vector2d(thirdSampleStrafeX, thirdSampleStrafeY), Math.toRadians(90))
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.elbowTop())
                                .waitSeconds(0.25)
                                .build()
                ));

        moveToBasketAction = drive.actionBuilder(drive.pose)
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(basketX, basketY), Math.toRadians(225))
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(1),
                        moveToBasketAction
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .stopAndAdd(elbow.upElbow())
                                .waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .waitSeconds(0.35)
                                //fourth sample
                                .strafeToLinearHeading(new Vector2d(fourthSampleStrafeX, fourthSampleStrafeY), Math.toRadians(90))
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.elbowTop())
                                .waitSeconds(0.25)
                                .build()
                ));

        moveToBasketAction = drive.actionBuilder(drive.pose)
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(basketX, basketY), Math.toRadians(225))
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(0.25),
                        moveToBasketAction
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .waitSeconds(0.25)
                                .stopAndAdd(elbow.upElbow())
                                .waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .waitSeconds(0.25)
                                .build()
                ));

        telemetry.addLine("end autonomous ");
        telemetry.update();
    }
}