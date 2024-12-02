package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous
public class OBSParkBlueRight extends LinearOpMode {

    public static int elevatorTopPos = 202;
    public static int elevatorDownPos = 0;
    public static int elevatorSpecimenPickPos = 100;
    public static int elevatorSpecimenHookPos = 102;//202;
    public static int sliderForwardPos= 200;// 330;
    public static int sliderBackwardPos= 0;

    //first specimen
    public static double firstSampleForwardToChamber = 35;//-33.5;

    //second specimen
    public static double secondSpecimenStrafeToX = -43;
    public static int secondSpecimenforward1LineToY = 8;
    public static double secondSpecimenStrafe2ToX = -60;
    public static double secondSpecimenBackLineToY = 55;

    //third specimen
    public static double thirdSpecimenStrafeToX = -70;

    //fourth specimen
    public static double fourthSpecimenStrafeToX = -78;

    //second specimen hooking
    public static double secondSpecimenGrabBackLineToY = 50;
    public static int turnAngle = 180;
    public static int turn1RghtAngle = -90;
    public static int turn2RghtAngle = -90;
    public static double secondSpecimenGrabLineToY = 60;
    public static double secondSpecimenHookLineToX = -10;

    //third specimen hook
    public static double thirdSpecimenHookStrafeToX = -50;

    public static double intake_close = 0; //0.85;
    public static double intake_specimen_open = 0.4; ;

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);
        Elbow elbow = new Elbow(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Slider slider = new Slider(hardwareMap);
        //DistanceSensor distance = new DistanceSensor(hardwareMap);

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

        //Pose2d initialPose = new Pose2d(10, -55, Math.toRadians(90));
        Pose2d initialPose = new Pose2d(-7, 64, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(initialPose)
                                // first loaded sample
                                // .stopAndAdd(elbow.elbowTop())
                                .stopAndAdd(elevator.elevatorUp(elevatorTopPos))
                                .lineToY(firstSampleForwardToChamber)
                                //.waitSeconds(0.25)

                                .stopAndAdd(slider.sliderForward(sliderForwardPos))
                                .waitSeconds(1.5)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .stopAndAdd(slider.sliderBackward(sliderBackwardPos))
                                .waitSeconds(1)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                //.waitSeconds(1)
                                .stopAndAdd(elbow.elbowTop())

                                //dragg the second specimen
                                .strafeTo(new Vector2d(secondSpecimenStrafeToX, firstSampleForwardToChamber))
                                .setTangent(Math.toRadians(90))
                                .lineToY(secondSpecimenforward1LineToY)
                                .strafeTo(new Vector2d(secondSpecimenStrafe2ToX, secondSpecimenforward1LineToY))
                                .setTangent(Math.toRadians(90))
                                .lineToY(secondSpecimenBackLineToY) //first sample push

                                //drag third sapecimen
                                /* .strafeTo(new Vector2d(thirdSpecimenStrafeToX, secondSpecimenforward1LineToY))
                                 .setTangent(Math.toRadians(90))
                                 .lineToY(secondSpecimenBackLineToY) */

                                //grab a specimen and hook
                                .lineToY(secondSpecimenGrabBackLineToY)
                                .turn(Math.toRadians(turnAngle))
                                .stopAndAdd(elbow.straightEobow())
                                .stopAndAdd(elevator.elevatorUp(elevatorSpecimenPickPos))
                                .waitSeconds(0.25)
                                .lineToY(secondSpecimenGrabLineToY)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.25)
                                .stopAndAdd(elevator.elevatorUp(elevatorSpecimenHookPos))
                                .stopAndAdd(elbow.elbowTop())
                                .lineToY(secondSpecimenGrabBackLineToY)
                                .turn(Math.toRadians(turn1RghtAngle))
                                .waitSeconds(0.25)
                                .lineToX(secondSpecimenHookLineToX)
                                .turn(Math.toRadians(turn2RghtAngle))
                                .lineToY(firstSampleForwardToChamber)
                                .stopAndAdd(slider.sliderForward(sliderForwardPos))
                                .waitSeconds(1.5)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .stopAndAdd(slider.sliderBackward(sliderBackwardPos))
                                .waitSeconds(1)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                //.waitSeconds(1)
                                .stopAndAdd(elbow.elbowTop())


                                //hook third specimen
                                .strafeTo(new Vector2d(thirdSpecimenHookStrafeToX, firstSampleForwardToChamber))
                                .setTangent(Math.toRadians(90))
                                .lineToY(secondSpecimenGrabLineToY)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                .stopAndAdd(elbow.straightEobow())
                                .turn(Math.toRadians(turnAngle))
                                .stopAndAdd(elevator.elevatorUp(elevatorSpecimenPickPos))
                                .waitSeconds(0.25)
                                .stopAndAdd(intake.closeIntake(intake_close))
                                .waitSeconds(0.25)
                                .stopAndAdd(elevator.elevatorUp(elevatorSpecimenHookPos))
                                .stopAndAdd(elbow.elbowTop())
                                .lineToY(secondSpecimenGrabBackLineToY)
                                .turn(Math.toRadians(turn1RghtAngle))
                                .waitSeconds(0.25)
                                .lineToX(secondSpecimenHookLineToX)
                                .turn(Math.toRadians(turn2RghtAngle))
                                .lineToY(firstSampleForwardToChamber)
                                .stopAndAdd(slider.sliderForward(sliderForwardPos))
                                .waitSeconds(1)
                                .stopAndAdd(intake.openIntake(intake_specimen_open))
                                .stopAndAdd(slider.sliderBackward(sliderBackwardPos))
                                .waitSeconds(1)
                                .stopAndAdd(elevator.elevatorDown(elevatorDownPos))
                                //.waitSeconds(1)
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

