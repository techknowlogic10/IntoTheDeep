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
public class OBSParkBlueRight extends LinearOpMode {

    public static double lineToY = 34;
    /* public static int elevatorUpPos = 330;
     public static int elevatorDownPos = 260; */
    public static int elevatorUpPos = 375;//365
    public static int elevatorDownPos = 230; //250
    public static double strafeToX = -46;
    public static double forward1LineToY = 7;

    public static double strafe2ToX = -60;
    public static double strafe3ToX = -70;
    public static double strafe4ToX = -78;

    public static double strafeToY = 33.5;
    public static double backLineToY = 50;

    public static double backLineTo2Y = 43;
    public static int turn1Angle = 180;

    public static int elevatorDown1Pos = 85;
    public static double lineTo5Y = 49.5;
    public static double lineTo6Y = 49;
    public static int turn2Angle = -180;
    public static double lineTo4Y = 35.5;

    /*public static double spinetToX= 35;
    public static double spinetToY = -50;
    public static double spinetToTangent = -25;*/

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);
        Elbow elbow = new Elbow(hardwareMap);
        Intake intake = new Intake(hardwareMap);
       // DistanceSensor distance = new DistanceSensor(hardwareMap);


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

        Pose2d initialPose = new Pose2d(-10, 55, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Actions.runBlocking(
                new SequentialAction(
                        elbow.straightEobow(),
                        new SleepAction(0.5)
                )
        );

        // TrajectoryActionBuilder step1 = drive.actionBuilder(initialPose)
        Action step1Action = drive.actionBuilder(drive.pose)
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
        Action step2Action = drive.actionBuilder(drive.pose)
                //.strafeTo(new Vector2d(strafeToX, lineToY))
                .strafeTo(new Vector2d(strafeToX, lineToY))
                .setTangent(Math.toRadians(-90))
                .lineToY(forward1LineToY)
                .strafeTo(new Vector2d(strafe2ToX, forward1LineToY))
                .setTangent(Math.toRadians(-90))
                .lineToY(backLineToY) //57
                .lineToY(forward1LineToY) //57
                .strafeTo(new Vector2d(strafe3ToX, forward1LineToY))
                .setTangent(Math.toRadians(-90))
                .lineToY(backLineToY) //second sample push
                /*.lineToY(forward1LineToY)
                .strafeTo(new Vector2d(strafe4ToX, forward1LineToY))
                .setTangent(Math.toRadians(-90))
                .lineToY(backLineToY)
                 */

                .lineToY(backLineTo2Y)
                .turn(Math.toRadians(turn1Angle))

                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step2Action,
                        elbow.straightEobow(),
                        intake.openIntake(),
                        elevator.elevatorDown(elevatorDown1Pos),
                        new SleepAction(1)
                )
        );

        Action step3Action = drive.actionBuilder(drive.pose)
                .lineToY(lineTo5Y)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step3Action,
                        intake.closeIntake(),
                        new SleepAction(0.5),
                        elevator.elevatorDown(elevatorUpPos)

                ));

        Action step4Action = drive.actionBuilder(drive.pose)
                .lineToY(lineTo6Y)
                .turn(Math.toRadians(turn2Angle))
                .strafeTo(new Vector2d(-10, 50))
                .setTangent(Math.toRadians(-90))
                .lineToY(lineTo4Y)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        step4Action,
                        elevator.elevatorDown(elevatorDownPos),
                        elbow.downElbow(),
                        new SleepAction(0.5),
                        intake.openIntake()
        ));



        telemetry.addLine("end autonomous ");
        telemetry.update();

    }

}

