package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@Config
@Autonomous
public class TestRedLeft extends LinearOpMode {

    //Servo elbow = null;
   // Servo intake = null;
    //DcMotor elevator = null;

    //public static int LOW_CHAMBER_LEVEL = 1;

    public static double lineToY = -31.5;
    public static int elevatorUpPos = 2000;
    public static int elevatorDownPos = 1800; // -1994;
    public static double strafeToX= -50;


    public class DistnaceTest {

        private DistanceSensor sensorDistance;
        Rev2mDistanceSensor sensorTimeOfFlight;

        public DistnaceTest(HardwareMap hardwareMap) {

            sensorDistance = hardwareMap.get(DistanceSensor.class, "distancesensor");
             sensorTimeOfFlight = (Rev2mDistanceSensor) sensorDistance;
            telemetry.addLine("distancesensor");
            telemetry.update();
        }

        protected double getDistance() {

            // generic DistanceSensor methods.
            telemetry.addData("deviceName", sensorDistance.getDeviceName() );
            telemetry.addData("range", String.format("%.01f mm", sensorDistance.getDistance(DistanceUnit.MM)));
            telemetry.addData("range", String.format("%.01f cm", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData("range", String.format("%.01f m", sensorDistance.getDistance(DistanceUnit.METER)));
            telemetry.addData("range", String.format("%.01f in", sensorDistance.getDistance(DistanceUnit.INCH)));

            // Rev2mDistanceSensor specific methods.
            telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
            telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));

            telemetry.update();

           return sensorDistance.getDistance(DistanceUnit.INCH);


        }




    }


    public class ElevatorTest {

        private DcMotor elevator;

        public ElevatorTest(HardwareMap hardwareMap) {

            telemetry.addLine(" Construcor ElevatorTest");
            telemetry.update();
            elevator = hardwareMap.get(DcMotor.class, "elevator");
            elevator.setDirection(DcMotorSimple.Direction.REVERSE);


            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            telemetry.addLine(" end Construcor ElevatorTest");
            telemetry.update();
        }

        public class ElevatorUp implements Action {
           // private boolean initialized = false;
            private int uppos = 0;
            public ElevatorUp(int elevatorUpPos){
                uppos = elevatorUpPos;
            }


            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
              /* if (!initialized) {
                    elevator.setPower(0.8);
                    initialized = true;
                }

                double pos = elevator.getCurrentPosition();
               // packet.put("elevaorPos: ", pos);
                telemetry.addLine("elevaorPos: "+ pos);
                telemetry.update();

                if (pos < uppos) {
                    return true;
                } else {
                    elevator.setPower(0);
                    return false;
                }*/

                elevator.setTargetPosition(uppos);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(0.8);
                return false;


            }
        }
        public Action elevatorUp(int elevatorUpPos) {
            return new ElevatorUp(elevatorUpPos);
        }

        public class ElevatorDown implements Action {
            //private boolean initialized = false;
            private int downpos = 0;

            public ElevatorDown(int elevatorDownPos){
                downpos = elevatorDownPos;
            }

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                /*if (!initialized) {
                    elevator.setPower(-1.0);
                    initialized = true;
                }

                double pos = elevator.getCurrentPosition();
                packet.put("elevatorPos :", pos);
                if (pos > downpos) {
                    return true;
                } else {
                    elevator.setPower(0);
                    return false;
                }*/

                elevator.setTargetPosition(downpos);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(-1.0);
                return false;
            }
        }
        public Action elevatorDown(int elevatorDownPos){
            return new ElevatorDown(elevatorDownPos);
        }
    }

    public class ElbowTest {
        private Servo elbow;

        public ElbowTest(HardwareMap hardwareMap) {

            elbow = hardwareMap.get(Servo.class, "elbow");
            //elbow.setPosition(0.48); //tiny
            elbow.setPosition(0.38); //tiny
           // elbow.setPosition(0.48);
            telemetry.addLine(" Construcor Elbow");
            telemetry.update();
        }

        public class ElbowUp implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                elbow.setPosition(0.48);
                return false;
            }
        }
        public Action upEobow() {
            return new ElbowUp();
        }

        public class DownElbow implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                elbow.setPosition(0.265);
                return false;
            }
        }
        public Action downElbow() {
            return new DownElbow();
        }
    }

    public class IntakeTest {
        private Servo intake;

        public IntakeTest(HardwareMap hardwareMap) {
            intake = hardwareMap.get(Servo.class, "intake");
            intake.setDirection(Servo.Direction.REVERSE);
            intake.setPosition(0.85);
            telemetry.addLine(" Construcor Intake");
            telemetry.update();
        }

        public class CloseIntake implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intake.setPosition(0.85); //tiny
               // intake.setPosition(0.52);
                return false;
            }
        }
        public Action closeIntake() {
            return new CloseIntake();
        }

        public class OpenIntake implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intake.setPosition(0.52);
                return false;
            }
        }
        public Action openIntake() {
            return new OpenIntake();
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addLine("1 runOpMode");
        telemetry.update();

        /*elbow = hardwareMap.get(Servo.class, "elbow");
        elbow.scaleRange(0,1);
        //elbow.setPosition(0.9);
        elbow.setPosition(0.48);

        intake = hardwareMap.get(Servo.class, "intake");
        intake.scaleRange(0,1);
        intake.setPosition(0.8);*/

        ElevatorTest elevator = new ElevatorTest(hardwareMap);
        ElbowTest elbow = new ElbowTest(hardwareMap);
        IntakeTest intake = new IntakeTest(hardwareMap);
        DistnaceTest distance = new DistnaceTest(hardwareMap);

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

       /* while (opModeIsActive()) {

        }*/

        telemetry.addLine("after waitForStart.... ");
        telemetry.update();

        //Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        Pose2d initialPose = new Pose2d(-10, -55, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
       // TankDrive drive = new TankDrive(hardwareMap, initialPose);

        telemetry.addLine("after MecanumDrive ");
        telemetry.update();



       // TrajectoryActionBuilder step1 = drive.actionBuilder(initialPose)
        TrajectoryActionBuilder step1 = drive.actionBuilder(drive.pose)
               /* .lineToY(-30)
                .waitSeconds(3)
                .turn(Math.toRadians(90))
                .lineToX(-50)
                .turn(Math.toRadians(90))
                .lineToY(-55);*/
               /* .lineToY(-45)
                .waitSeconds(2)
                .turn(Math.toRadians(90))
                .lineToX(-40)
                .waitSeconds(2)
                .turn(Math.toRadians(90))
                .lineToY(-50);*/
                .waitSeconds(1)
               // .lineToY(-33)
                .lineToY(lineToY)
                .waitSeconds(1)
                /*.lineToY(-54.5)
                .turn(Math.toRadians(90))
                .lineToX(-20)*/

                ;


        telemetry.addLine("1. step1");
        telemetry.update();


      /*  Action trajectoryActionCloseOut = tab1.fresh()
                 .build();*/

        Action step1Action = step1.build();

       /* Action waitAction = drive.actionBuilder(new Pose2d(-10, -33, Math.toRadians(90)))
                .waitSeconds(2).build();

        */
        Action waitAction = drive.actionBuilder(drive.pose)
                .waitSeconds(2).build();

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
                        step1Action
                )
        );

        double chamberDistance =  distance.getDistance();
        telemetry.addLine("chamberDistance: "+chamberDistance);
        telemetry.update();

        Action step2Action = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(strafeToX, lineToY))
                .build();


        Actions.runBlocking(
                new SequentialAction(
                       // elbow.downElbow(),
                        elevator.elevatorDown(elevatorDownPos),
                        new SleepAction(2),
                        intake.openIntake()
                       // step2Action,
                       /* new ParallelAction(
                                elevator.elevatorDown(0)
                        )*/
                       /* elevator.elevatorDown(0),
                        elbow.downElbow(),
                        intake.closeIntake(),
                        elbow.upEobow()*/

                )
        );



       telemetry.addLine("end autonomous ");
       telemetry.update();

    }

}
