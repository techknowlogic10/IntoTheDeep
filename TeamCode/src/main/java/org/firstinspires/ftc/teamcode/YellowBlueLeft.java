package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous
public class YellowBlueLeft extends LinearOpMode {

    public static double lineToY = -31.45;
    public static int elevatorUpPos = 365;
    public static int elevatorDownPos = 0;
    public static double strafeToX = -65;
    public static double spinetToX = -35;
    public static double spinetToY = 50;
    public static double spinetToTangent = 25;
    public static double lineToY1 = -10;
    public static double lineToX1 = -35;

    public static double lineTo1X = 64.5;
    public static int elevatorTopPos = 650;

    //step2
    public static double step3StrafeX = 55;
    public static double step3StrafeY = 21;
    public static int step3Angle = -45;

    //step4
    public static int step4Angle = 90;
    public static double step5LineToY = 38;

    public static double step6LineToY = 30;
    public static int step7Angle = -108;

    public static double step8LineToY = 24;

    public static int step9Angle = 110;

    public static double step10LineToY = 38;

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);
        Elbow elbow = new Elbow(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        //DistanceSensor distance = new DistanceSensor(hardwareMap);

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

        // Step 0: Initial Position
        Pose2d initialPose = new Pose2d(55, 57, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        // Step 1: Elbow Up, Elevator Up and Drive to "Point A" and drop in high basket
        Action step1Action = drive.actionBuilder(initialPose)
                .lineToX(lineTo1X)
                .build();

        Actions.runBlocking(new SequentialAction(
                elbow.upElbow(),
                elevator.elevatorUp(elevatorTopPos),
                new SleepAction(3),
                step1Action,
                new SleepAction(0.5),
                intake.openIntake()
        ));

        // Step 2: Move back to Point"B" and ElevatorDown
       Action step2Action = drive.actionBuilder(drive.pose)
                .lineToX(lineTo1X)
                .build();

        Actions.runBlocking(new SequentialAction(
                step2Action,
                elevator.elevatorDown(elevatorDownPos)
        ));

        // Step 3: Strafe To "Point C", turn 45 Elbow down, Close Intake and Elevator Up
        Action step3Action = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(step3StrafeX, step3StrafeY))
                .waitSeconds(0.5)
                .turn(Math.toRadians(step3Angle))
                .build();

        Actions.runBlocking(new SequentialAction(
                step3Action,
                elbow.downElbow(),
                new SleepAction(0.5),
                intake.closeIntake(),
                new SleepAction(0.5),
                elbow.upElbow()
        ));

        // Step 4: Turn 90 and Elevator Up
        Action step4Action = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(step4Angle))
                .build();

        Actions.runBlocking(new SequentialAction(
                step4Action,
                elevator.elevatorUp(elevatorTopPos),
                new SleepAction(3)
        ));

        // Step 5: Move back to basket ( Point A? ) and drop in high basket
        Action step5Action = drive.actionBuilder(drive.pose)
                .lineToYLinearHeading(step5LineToY, 45)
                .waitSeconds(0.5)
                .build();

        Actions.runBlocking(new SequentialAction(
                step5Action,
                intake.openIntake()
        ));

        //Step 6: Move back to "Point D" Elevator down
        Action step6Action = drive.actionBuilder(drive.pose)
                .lineToYLinearHeading(step6LineToY, 45)
                .build();

        Actions.runBlocking(new SequentialAction(
                step6Action,
                elevator.elevatorDown(elevatorDownPos),
                new SleepAction(1)
        ));

        // Step 7: Turn 108 deg.
        Action step7Action = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(step7Angle))
                .build();

        Actions.runBlocking(new SequentialAction(
                step7Action
        ));

        // Step 8: Move to "Point E" and Elbow down, Close Intake and Elbow Up
        Action step8Action = drive.actionBuilder(drive.pose)
                .lineToY(step8LineToY)
                .build();

        Actions.runBlocking(new SequentialAction(
                step8Action,
                elbow.downElbow(),
                new SleepAction(0.5),
                intake.closeIntake(),
                new SleepAction(0.5),
                elbow.upElbow()
               // new SleepAction(0.5)
        ));

        // Step 9: Turn 110 deg and Elevator Up
        Action step9Action = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(step9Angle))
                .build();

        Actions.runBlocking(new SequentialAction(
                step9Action,
                elevator.elevatorUp(elevatorTopPos),
                new SleepAction(3)
        ));

        // Step 10: Go to "Point " and drop in high basket
        Action step10Action = drive.actionBuilder(drive.pose)
                .lineToYLinearHeading(step10LineToY, 45)
                .build();

        Actions.runBlocking(new SequentialAction(
                step10Action,
                intake.openIntake()
        ));

        telemetry.addLine("end autonomous ");
        telemetry.update();
    }
}
