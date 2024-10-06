package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
@Autonomous
public class TestRedLeft extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData(">>", "Press start to continue");
        telemetry.update();

        waitForStart();

       /* while (opModeIsActive()) {

        }*/

        telemetry.addLine("started... ");

        //Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        Pose2d initialPose = new Pose2d(-10, -55, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
       // TankDrive drive = new TankDrive(hardwareMap, initialPose);

        telemetry.addLine("after MecanumDrive ");

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
               /* .lineToY(-30)
                .waitSeconds(3)
                .turn(Math.toRadians(90))
                .lineToX(-50)
                .turn(Math.toRadians(90))
                .lineToY(-55);*/
                .lineToY(-45)
                .waitSeconds(2)
                .turn(Math.toRadians(90))
                .lineToX(-40)
                .waitSeconds(2)
                .turn(Math.toRadians(90))
                .lineToY(-50);

       /* Action trajectoryActionCloseOut = tab1.fresh()
                 .build(); */

        Action trajectoryActionChosen = tab1.build();
        telemetry.addLine("after build");

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen

                )
        );


       telemetry.addLine("end autonomous ");
       telemetry.update();

    }

}
