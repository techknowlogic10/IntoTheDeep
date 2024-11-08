package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;

@Config
@TeleOp
public class TurnTest extends OpMode {


    Pose2d initialPose = new Pose2d(-10, -55, Math.toRadians(90));
    MecanumDrive drive = null;

    @Override
    public void init() {
        drive = new MecanumDrive(hardwareMap, initialPose);
    }

    @Override
    public void loop() {


        if(gamepad2.dpad_up) {
            Action turnAction = drive.actionBuilder(drive.pose)
                    //.turn(Math.toRadians(-90)).build();
                    .turn(-90).build();

            Actions.runBlocking(
                    new SequentialAction(
                            turnAction
                    )
            );
        } else if(gamepad2.dpad_down){

            Action turnAction = drive.actionBuilder(drive.pose)
                   // .turn(Math.toRadians(90)).build();
                           .turn(90).build();

            Actions.runBlocking(
                    new SequentialAction(
                            turnAction
                    )
            );

        }

        telemetry.update();

    }
}
