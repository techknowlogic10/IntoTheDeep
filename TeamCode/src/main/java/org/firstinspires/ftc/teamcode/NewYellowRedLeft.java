package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous
public class NewYellowRedLeft extends LinearOpMode {

    /*
    public static double lineToY = -31.45;
    public static int elevatorUpPos = 365;
    public static int elevatorDownPos = 0;
    public static double strafeToX= -65;
    public static double spinetToX= -35;
    public static double spinetToY = 50;
    public static double spinetToTangent = 25;
    public static double lineToY1 = -10;
    public static double lineToX1 = -35;


    public static double lineTo1X = -64.5;
    public static double lineTo2X = -55;
    public static int elevatorTopPos = 650;

    //step2
    public static double step3StrafeX = - 55;
    public static double step3StrafeY = - 21;
    public static int step3Angle = -45;

    //step4
    public static int step4Angle = 90;
    public static double step5LineToY = -38;

    public static double step6LineToY = -30;
    public static int step6Angle = -108;

    public static double step8LineToY = -24;

    public static int step9Angle = 130;

    public static double step10LineToY = -37;*/

    //New Values

    //public static int elevatorUpPos = 365;
    public static int elevatorTopPos = 650;
    public static int elevatorDownPos = 0;

    //first sample
    public static double firstSampleForwardToBasket = -64.5;

    //second sample
    public static double secondSampleStrafeX = - 55;
    public static double secondSampleStrafeY = - 21;
    public static int secondSampleRightTurnAngle = -45;
    public static int secondSampleLeftTurnAngle = 90;
    public static double secondSampleForwardToBasket = -38;

    //third sample
    public static double thirdSampleBackwardY = -30;
    public static int thirdSampleRightTurnAngle = -108;
    public static double thirdSampleForwardY = -24;
    public static int thirdSampleLeftTurnAngle = 130;
    public static double thirdSampleForwardToBasket = -37;

    public static double intake_close = 0; //0.85;
    public static double intake_sample_open = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);
        Elbow elbow = new Elbow(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        while (!isStopRequested() && !opModeIsActive()) {
            // int position = visionOutputPosition;
            //telemetry.addData("Position during Init", position);
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

        Pose2d initialPose = new Pose2d(-55, -57, Math.toRadians(180));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        /*Actions.runBlocking(
                drive.actionBuilder(initialPose)
                        .lineToX(lineTo1X)
                        .build();
        );*/

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(initialPose)
                                // first loaded sample
                                .stopAndAdd(elbow.upElbow())
                                .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                                .waitSeconds(3)
                                .lineToX(firstSampleForwardToBasket)
                                .stopAndAdd(intake.openIntake(intake_sample_open))

                                //second sample
                                .strafeTo(new Vector2d(secondSampleStrafeX, secondSampleStrafeY))
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(secondSampleRightTurnAngle))
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.25)
                                .stopAndAdd(elbow.upElbow())
                                .turn(Math.toRadians(secondSampleLeftTurnAngle))
                               /* .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                                .waitSeconds(3)
                                .lineToY(secondSampleForwardToBasket) *
                                .waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake())

                                //third sample
                                .lineToY(thirdSampleBackwardY)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(1)
                                .turn(Math.toRadians(thirdSampleRightTurnAngle))
                                .lineToY(thirdSampleForwardY)
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake())
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.upElbow())
                                .waitSeconds(0.5)
                                .turn(Math.toRadians(thirdSampleLeftTurnAngle))
                                .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                                .lineToY(thirdSampleForwardToBasket)
                                .stopAndAdd(intake.openIntake())
                                .waitSeconds(0.25)
                                .stopAndAdd(elbow.elbowTop())

                                */
                                .build()
                ));


        Action parallelAction = drive.actionBuilder(drive.pose)
                .lineToY(secondSampleForwardToBasket)
                .build();

        Actions.runBlocking(new SequentialAction(
                parallelAction,
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos)
                   // .waitSeconds(3)
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                // first loaded sample
                               /* .stopAndAdd(elbow.upElbow())
                                .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                                .waitSeconds(3)
                                .lineToX(firstSampleForwardToBasket)
                                .stopAndAdd(intake.openIntake())

                                //second sample
                                .strafeTo(new Vector2d(secondSampleStrafeX, secondSampleStrafeY))
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(secondSampleRightTurnAngle))
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake())
                                .waitSeconds(0.25)
                                .stopAndAdd(elbow.upElbow())
                                .turn(Math.toRadians(secondSampleLeftTurnAngle))
                                /* .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                                 .waitSeconds(3)
                                 .lineToY(secondSampleForwardToBasket) */
                                .waitSeconds(0.25)
                                .stopAndAdd(intake.openIntake(intake_sample_open))

                                //third sample
                                .lineToY(thirdSampleBackwardY)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .waitSeconds(1)
                                .turn(Math.toRadians(thirdSampleRightTurnAngle))
                                .lineToY(thirdSampleForwardY)
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.downElbow())
                                .waitSeconds(1)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.5)
                                .stopAndAdd(elbow.upElbow())
                                .waitSeconds(0.5)
                                .turn(Math.toRadians(thirdSampleLeftTurnAngle))
                                .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                                .lineToY(thirdSampleForwardToBasket)
                                .stopAndAdd(intake.openIntake(intake_sample_open))
                                .waitSeconds(0.25)
                                .stopAndAdd(elbow.elbowTop())
                                .build()
                ));


       /* Actions.runBlocking(
        new SequentialAction(
                drive.actionBuilder(initialPose)
                        // first loaded sample
                        .stopAndAdd(elbow.upElbow())
                        .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                        .waitSeconds(3)
                        .lineToX(firstSampleForwardToBasket)
                        .stopAndAdd(intake.openIntake())

                        //second sample
                        .strafeTo(new Vector2d(secondSampleStrafeX, secondSampleStrafeY))
                        .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                        .waitSeconds(0.25)
                        .turn(Math.toRadians(secondSampleRightTurnAngle))
                        .stopAndAdd(elbow.downElbow())
                        .waitSeconds(1)
                        .stopAndAdd(intake.closeIntake())
                        .waitSeconds(0.25)
                        .stopAndAdd(elbow.upElbow())
                        .turn(Math.toRadians(secondSampleLeftTurnAngle))
                        .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                        .waitSeconds(3)
                        .lineToY(secondSampleForwardToBasket)
                        .waitSeconds(0.25)
                        .stopAndAdd(intake.openIntake())

                        //third sample
                        .lineToY(thirdSampleBackwardY)
                        .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                        .waitSeconds(1)
                        .turn(Math.toRadians(thirdSampleRightTurnAngle))
                        .lineToY(thirdSampleForwardY)
                        .waitSeconds(0.5)
                        .stopAndAdd(elbow.downElbow())
                        .waitSeconds(1)
                        .stopAndAdd(intake.closeIntake())
                        .waitSeconds(0.5)
                        .stopAndAdd(elbow.upElbow())
                        .waitSeconds(0.5)
                        .turn(Math.toRadians(thirdSampleLeftTurnAngle))
                        .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                        .lineToY(thirdSampleForwardToBasket)
                        .stopAndAdd(intake.openIntake())
                        .waitSeconds(0.25)
                        .stopAndAdd(elbow.elbowTop())
                        .build()
        ));
        */


        /*Action step1Action = drive.actionBuilder(initialPose)
                .lineToX(lineTo1X)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        elbow.upElbow(),
                        elevator.elevatorUp(elevatorTopPos),
                        // elevator.ElevatorHighBasketUp(elevatorTopPos),
                        new SleepAction(3),
                        step1Action,
                        new SleepAction(0.25),
                        intake.openIntake()

                )
        );



        Action step2Action = drive.actionBuilder(drive.pose)
                .lineToX(lineTo1X)
                //.lineToX(lineTo2X)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step2Action,
                        elevator.elevatorDown(elevatorDownPos),
                        new SleepAction(0.25)
                )
        );

        Action ste3Action = drive.actionBuilder(drive.pose)

                .strafeTo(new Vector2d(step3StrafeX, step3StrafeY))
                .waitSeconds(0.5)
                .turn(Math.toRadians(step3Angle))
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        ste3Action,
                        elbow.downElbow(),
                        new SleepAction(1),
                        intake.closeIntake(),
                        new SleepAction(0.5),
                        elbow.upElbow()


                )
        );

        Action ste4Action = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(step4Angle))
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        ste4Action,
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(3)

                )
        );

        Action step5Action = drive.actionBuilder(drive.pose)
                //.lineToYLinearHeading(step5LineToY,180)
                .lineToY(step5LineToY)
                .waitSeconds(0.25)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step5Action,
                        intake.openIntake()
                )
        );

        Action step6Action = drive.actionBuilder(drive.pose)
                //.lineToYLinearHeading(step6LineToY,180)
                .lineToY(step6LineToY)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step6Action,
                        elevator.elevatorDown(elevatorDownPos),
                        new SleepAction(1)

                )
        );


        Action step7Action = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(step6Angle))
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step7Action
                )
        );

        Action step8Action = drive.actionBuilder(drive.pose)
                .lineToY(step8LineToY)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step8Action,
                        new SleepAction(0.5),
                        elbow.downElbow(),
                        new SleepAction(1),
                        intake.closeIntake(),
                        new SleepAction(0.5),
                        elbow.upElbow(),
                        new SleepAction(0.5)
                )
        );

        Action step9Action = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(step9Angle))
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step9Action,
                        elevator.elevatorUp(elevatorTopPos),
                        new SleepAction(3)
                )
        );

        Action step10Action = drive.actionBuilder(drive.pose)
                //.lineToYLinearHeading(step10LineToY,180)
                .lineToY(step10LineToY)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step10Action,
                        intake.openIntake(),
                        new SleepAction(0.25),
                        elbow.elbowTop()
                )
        );

        */



        telemetry.addLine("end autonomous ");
        telemetry.update();

    }

}

