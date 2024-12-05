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
@Autonomous
public class RedRightThree extends LinearOpMode {

    public static int elevatorTopPos = 208;// 202;
    public static int elevatorDownPos = 0;
    public static int elevatorSpecimenPickPos = 100;
    public static int elevatorSpecimenHookPos = 102;//202;
    public static int sliderForwardPos= 90;
    public static int sliderBackwardPos= 0;

    //first specimen
    public static double forwardSTrafeX = 5;
    public static double forwardToChamber = -34;// -35;//-33.5;

    //second specimen
    public static double secondSpecimenStrafeToX = 48;
    public static int secondSpecimenforward1LineToY = -8;
    public static double secondSpecimenStrafe2ToX = 59;
    public static double secondSpecimenBackLineToY = -55;
    public static double secondSpecimenFwdToChamber = -34;

    //third specimen
    public static double thirdSpecimenStrafeToX = 70;

    //fourth specimen
    public static double fourthSpecimenStrafeToX = 78;

    //second specimen hooking
    public static double moveBackToTurnLineToY = -50;
    public static int turnAngle = 180;
    public static int turn1RghtAngle = -90;
    public static int turn2RghtAngle = -90;
    public static double grabSpecimenLineToY = -60;
    public static double secondSpecimenHookLineToX = 10;

    //third specimen hook
    public static double thirdSpecimenHookStrafeToX = 55;
    public static double thirdSpecimenHookLineToX = 12;

    public static double intake_close = 0; //0.85;
    public static double intake_specimen_open = 0.4; ;

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);
        Elbow elbow = new Elbow(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Slider slider = new Slider(hardwareMap);

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

        Pose2d initialPose = new Pose2d(4, -64, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action parallelAction = drive.actionBuilder(initialPose)
                .lineToY(forwardToChamber)
                .waitSeconds(0.25)
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        elevator.elevatorUp(elevatorTopPos),
                        parallelAction,
                        //new SleepAction(1),
                        slider.sliderForward(sliderForwardPos),
                        // new SleepAction(1.25)
                        new SleepAction(0.75)
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .stopAndAdd(slider.sliderBackward(sliderBackwardPos))
                                .waitSeconds(1)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .stopAndAdd(elbow.elbowTop())

                                //drag the second specimen
                                .strafeTo(new Vector2d(secondSpecimenStrafeToX, forwardToChamber))
                                .setTangent(Math.toRadians(90))
                                .lineToY(secondSpecimenforward1LineToY)
                                .strafeTo(new Vector2d(secondSpecimenStrafe2ToX, secondSpecimenforward1LineToY))
                                .setTangent(Math.toRadians(90))
                                .lineToY(secondSpecimenBackLineToY)

                                //drag third sapecimen
                                /* .strafeTo(new Vector2d(thirdSpecimenStrafeToX, secondSpecimenforward1LineToY))
                                 .setTangent(Math.toRadians(90))
                                 .lineToY(secondSpecimenBackLineToY) */

                                //grab the second specimen and hook
                                .lineToY(moveBackToTurnLineToY)
                                .turn(Math.toRadians(turnAngle))
                                .stopAndAdd(elbow.straightEobow())
                                .stopAndAdd(elevator.elevatorUp(elevatorSpecimenPickPos))
                                .waitSeconds(0.25)
                                .lineToY(grabSpecimenLineToY)
                                // .waitSeconds(0.25)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.75)
                                .stopAndAdd(elevator.elevatorUp(elevatorSpecimenHookPos))
                                .stopAndAdd(elbow.elbowTop())
                                .lineToY(moveBackToTurnLineToY)
                                .turn(Math.toRadians(turn1RghtAngle))
                                //.waitSeconds(0.25)
                                .lineToX(secondSpecimenHookLineToX)
                                .turn(Math.toRadians(turn2RghtAngle))

                                .build()
                ));

        parallelAction = drive.actionBuilder(drive.pose)
                //.waitSeconds(1)
                .lineToY(secondSpecimenFwdToChamber)
                .waitSeconds(0.25)
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        parallelAction,
                        //new SleepAction(1),
                        slider.sliderForward(sliderForwardPos),
                        //.waitSeconds(1.5)
                        new SleepAction(0.75)
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .stopAndAdd(slider.sliderBackward(sliderBackwardPos))
                                .waitSeconds(1)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                //.waitSeconds(1)
                                .stopAndAdd(elbow.elbowTop())

                                //drag third sapecimen
                                //.strafeTo(new Vector2d(thirdSpecimenStrafeToX, secondSpecimenforward1LineToY))
                               /* .lineToY(-39)
                                .strafeTo(new Vector2d(60, -39))
                                .setTangent(Math.toRadians(90))
                                .lineToY(secondSpecimenforward1LineToY)
                                .strafeTo(new Vector2d(thirdSpecimenStrafeToX, secondSpecimenforward1LineToY))
                                .setTangent(Math.toRadians(90))
                                .lineToY(secondSpecimenBackLineToY)

                                */



                                //hook the third specimen
                                .lineToY(moveBackToTurnLineToY)
                                .strafeTo(new Vector2d(thirdSpecimenHookStrafeToX, moveBackToTurnLineToY))
                                .setTangent(Math.toRadians(90))
                                .stopAndAdd(elevator.elevatorUp(elevatorSpecimenPickPos))
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(turnAngle))
                                .stopAndAdd(elbow.straightEobow())
                                .lineToY(grabSpecimenLineToY)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.75)
                                .stopAndAdd(elevator.elevatorUp(elevatorSpecimenHookPos))
                                .stopAndAdd(elbow.elbowTop())
                                .lineToY(moveBackToTurnLineToY)
                                .turn(Math.toRadians(turn1RghtAngle))
                                //.waitSeconds(0.25)
                                .lineToX(thirdSpecimenHookLineToX)
                                .turn(Math.toRadians(turn2RghtAngle))


                                .build()
                ));



        parallelAction = drive.actionBuilder(initialPose)
                .lineToY(forwardToChamber)
                .waitSeconds(0.25)
                .build();

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        parallelAction,
                       // new SleepAction(1),
                        slider.sliderForward(sliderForwardPos),
                        new SleepAction(1)
                )
        ));

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(drive.pose)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .stopAndAdd(slider.sliderBackward(sliderBackwardPos))
                                .waitSeconds(1)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .stopAndAdd(elbow.elbowTop())

                                //drag fourth sapecimen
                                /* .strafeTo(new Vector2d(fourthSpecimenStrafeToX, secondSpecimenforward1LineToY))
                                 .setTangent(Math.toRadians(90))
                                 .lineToY(secondSpecimenBackLineToY)
                                 */

                                .build()
                ));





        telemetry.addLine("end autonomous ");
        telemetry.update();

    }

}

