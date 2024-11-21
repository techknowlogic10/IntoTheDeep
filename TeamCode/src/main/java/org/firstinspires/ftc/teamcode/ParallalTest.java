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
public class ParallalTest extends LinearOpMode {


    public static int elevatorTopPos = 650;
    public static int elevatorDownPos = 0;

    //first sample
    public static double firstSampleForwardToBasket = -70;



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


        Action parallelAction = drive.actionBuilder(initialPose)
                .lineToX(firstSampleForwardToBasket)
                .build();

        Actions.runBlocking(new SequentialAction(
                parallelAction,
                new ParallelAction(

                        elevator.elevatorUp(elevatorTopPos)
                        // .waitSeconds(3)
                )
        ));





        telemetry.addLine("end autonomous ");
        telemetry.update();

    }

}

