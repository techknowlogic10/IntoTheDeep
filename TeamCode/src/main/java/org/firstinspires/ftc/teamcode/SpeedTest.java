package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Disabled
@Autonomous
public class SpeedTest extends LinearOpMode {
    public static double lineToY = 0;


    @Override
    public void runOpMode() throws InterruptedException {



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
        Pose2d initialPose = new Pose2d(7, -64, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        long timestamp = System.currentTimeMillis();
        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(initialPose)
                                .lineToY(lineToY)
                                .build()
                ));


        telemetry.addLine("end speedtest : "+ (System.currentTimeMillis() - timestamp));
        telemetry.update();

    }

}

