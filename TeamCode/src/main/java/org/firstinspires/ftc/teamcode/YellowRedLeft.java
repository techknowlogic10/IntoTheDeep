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
public class YellowRedLeft extends LinearOpMode {

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

    public static double step10LineToY = -37;

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
        Action step1Action = drive.actionBuilder(initialPose)
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
                        intake.openIntake(intake_sample_open)

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
                        intake.closeIntake(intake_close),
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
                        intake.openIntake(intake_sample_open)
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
                        intake.closeIntake(intake_close),
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
                        intake.openIntake(intake_sample_open),
                        new SleepAction(0.25),
                        elbow.elbowTop()
                )
        );


        // TrajectoryActionBuilder step1 = drive.actionBuilder(initialPose)


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

      /*  telemetry.addLine("2. trajectoryActionChosen");
        telemetry.update();

        Actions.runBlocking(
                new SequentialAction(
                        elevator.elevatorUp(elevatorUpPos),
                        new SleepAction(0.5),
                        step1Action
                )
        ); */

        /*double chamberDistance =  distance.getDistance();
        telemetry.addLine("chamberDistance: "+chamberDistance);
        telemetry.update();

        System.out.println("chamberDistance: "+chamberDistance);


        Action lenthAdjustmentAction = null;

        if(chamberDistance < 8.0) {
            lineToY =lineToY - 1;

            lenthAdjustmentAction = drive.actionBuilder(drive.pose)
                    .lineToY(lineToY)
                    .build();

                    Actions.runBlocking(
                            new SequentialAction(
                                    lenthAdjustmentAction
                            )
                    );

        } else if(chamberDistance > 9.0 ) {
            lineToY =lineToY - 1;
            lenthAdjustmentAction = drive.actionBuilder(drive.pose)
                    .lineToY(lineToY)
                    .build();

                    Actions.runBlocking(
                            new SequentialAction(
                                    lenthAdjustmentAction
                            )
                    );

        }*/

       /* telemetry.addLine("chamberDistance: "+chamberDistance);
        telemetry.update();*/

      /*  Actions.runBlocking(
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

             /*   )
        );*/
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
    /*  Action step2Action = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(strafeToX, lineToY))
                .setTangent(Math.toRadians(90))
               // .waitSeconds(1)
               /* .turn(Math.toRadians(-120))
                .waitSeconds(1)
                .turn(Math.toRadians(120))
                .waitSeconds(1)*/
        // .splineTo(new Vector2d(spinetToX, spinetToY),spinetToTangent
          /*      .lineToY(lineToY1)
                .turn(Math.toRadians(-90))
                .waitSeconds(1)
                .lineToX(lineToX1)*/
          /*      .build();

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

