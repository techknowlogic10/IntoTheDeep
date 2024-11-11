package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous
public class BlueLeft extends LinearOpMode {

    public static double lineToY = 33.5;
    public static int elevatorUpPos = 375;//365
    public static int elevatorDownPos = 230; //250
    public static double strafeToX= -55;
    public static double spinetToX= 35;
    public static double spinetToY = -50;
    public static double spinetToTangent = -25;
    public static double lineToY1 = 10;
    public static double lineToX1 = 35;

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);
        Elbow elbow = new Elbow(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        DistanceSensor distance = new DistanceSensor(hardwareMap);

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

        Pose2d initialPose = new Pose2d(10, 55, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Actions.runBlocking(
                new SequentialAction(
                        elbow.straightEobow(),
                        new SleepAction(0.5)
                )
        );

        // TrajectoryActionBuilder step1 = drive.actionBuilder(initialPose)
        Action step1Action = drive.actionBuilder(initialPose)
                .lineToY(lineToY)
                .build();

      /*  Action trajectoryActionCloseOut = tab1.fresh()
                 .build();*/

        /* Action waitAction = drive.actionBuilder(new Pose2d(-10, -33, Math.toRadians(90)))
                .waitSeconds(2).build();

        */
        /*Action waitAction = drive.actionBuilder(drive.pose)
                .waitSeconds(2).build();*/

       /* Action backAction = drive.actionBuilder(new Pose2d(-30, -55, Math.toRadians(90)))
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(30, 12),0).build();
               // .lineToY(-51).build();
       */

        telemetry.addLine("2. trajectoryActionChosen");
        telemetry.update();

        Actions.runBlocking(
                new SequentialAction(
                        elevator.elevatorUp(elevatorUpPos),
                        new SleepAction(0.5),
                        step1Action
                )
        );

        //double chamberDistance =  distance.getDistance();

        /*Action lenthAdjustmentAction = null;

        if(chamberDistance < 8 ) {
            lineToY =lineToY - 1;

            lenthAdjustmentAction = drive.actionBuilder(drive.pose)
                    .lineToY(lineToY)
                    .build();

                    Actions.runBlocking(
                            new SequentialAction(
                            )
                    );

        } else  if(chamberDistance > 9 ) {
            lineToY =lineToY - 1;
            lenthAdjustmentAction = drive.actionBuilder(drive.pose)
                    .lineToY(lineToY)
                    .build();

                    Actions.runBlocking(
                            new SequentialAction(
                            )
                    );

        }*/

       /* telemetry.addLine("chamberDistance: "+chamberDistance);
        telemetry.update();*/

       Actions.runBlocking(
                new SequentialAction(
                       elevator.elevatorDown(elevatorDownPos),
                       elbow.downElbow(),
                        new SleepAction(0.5),
                       intake.openIntake()
                        // elbow.upEobow(),
                        //elevator.elevatorDown(0)

                       /* new ParallelAction(
                             elevator.elevatorDown(0)
                        )*/
                       /* elevator.elevatorDown(0),
                        elbow.downElbow(),
                        intake.closeIntake(),
                        elbow.upEobow()*/

              )
        );
        /*

        Action backAction = drive.actionBuilder(drive.pose)
                .lineToY(lineToY - 1)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        backAction
                )
        );
*/
       /* Action step2Action = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(strafeToX, lineToY))
                .setTangent(Math.toRadians(-90))
                .waitSeconds(0.5)
                /* .turn(Math.toRadians(-120))
                 .waitSeconds(1)
                 .turn(Math.toRadians(120))
                 .waitSeconds(1)*/
                //.splineTo(new Vector2d(spinetToX, spinetToY),spinetToTangent)
             /*   .lineToY(lineToY1)
                .turn(Math.toRadians(-90))
                .waitSeconds(1)
                .lineToX(lineToX1)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step2Action
                        //  new SleepAction(1),
                        // elbow.upEobow(),
                        //  elevator.elevatorDown(0),
                        // new SleepAction(1),
                        // intake.closeIntake()

                )
        ); */


        telemetry.addLine("end autonomous ");
        telemetry.update();

    }

}

