package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;


@Config
@Autonomous
public class TestRedLeft extends LinearOpMode {

    //Servo elbow = null;
   // Servo intake = null;
    //DcMotor elevator = null;

    //public static int LOW_CHAMBER_LEVEL = 1;


    public class ElevatorTest {

        private DcMotor elevator;

        public ElevatorTest(HardwareMap hardwareMap) {

            telemetry.addLine(" Construcor ElevatorTest");
            telemetry.update();
            elevator = hardwareMap.get(DcMotor.class, "elevator");
            elevator.setDirection(DcMotorSimple.Direction.FORWARD);
            //elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);  // Reset Encoders to zero
            elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            telemetry.addLine(" end Construcor ElevatorTest");
            telemetry.update();
        }

        public class ElevatorUp implements Action {
           // private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                /*if (!initialized) {
                    elevator.setPower(0.8);
                    initialized = true;
                }

                double pos = elevator.getCurrentPosition();
                packet.put("elevaorPos: ", pos);
                if (pos < 100.0) {
                    return true;
                } else {
                    elevator.setPower(0);
                    return false;
                }*/

                /*elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                */

                telemetry.addLine("elevaor taregt 1400");
                elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.setTargetPosition(400);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(0.8);
                return false;

            }
        }
        public Action elevatorUp() {
            return new ElevatorUp();
        }

        public class ElevatorDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
               /* if (!initialized) {
                    elevator.setPower(-0.8);
                    initialized = true;
                }

                double pos = elevator.getCurrentPosition();
                packet.put("elevatorPos :", pos);
                if (pos > 100.0) {
                    return true;
                } else {
                    elevator.setPower(0);
                    return false;
                }*/

                /*elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/

                telemetry.addLine("elevaor taregt 0");
                elevator.setTargetPosition(1400);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(0.8);
                return true;
            }
        }
        public Action elevatorDown(){
            return new ElevatorDown();
        }
    }

    public class Elbow {
        private Servo elbow;

        public Elbow(HardwareMap hardwareMap) {

            elbow = hardwareMap.get(Servo.class, "elbow");
            elbow.setPosition(0.48);
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
                elbow.setPosition(0.1);
                return false;
            }
        }
        public Action openClaw() {
            return new DownElbow();
        }
    }

    public class Intake {
        private Servo intake;

        public Intake(HardwareMap hardwareMap) {
            intake = hardwareMap.get(Servo.class, "intake");
            intake.setPosition(0.8);
            telemetry.addLine(" Construcor Intake");
            telemetry.update();
        }

        public class CloseIntake implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intake.setPosition(0.4);
                return false;
            }
        }
        public Action closeIntake() {
            return new CloseIntake();
        }

        public class OpenIntake implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intake.setPosition(0.8);
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

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
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
                .lineToY(-45)
                /*.lineToY(-54.5)
                .turn(Math.toRadians(90))
                .lineToX(-20)*/

                ;


        telemetry.addLine("1. tab1");
        telemetry.update();


      /*  Action trajectoryActionCloseOut = tab1.fresh()
                 .build();*/

        Action trajectoryActionChosen = tab1.build();


       /* Action backAction = drive.actionBuilder(new Pose2d(-30, -55, Math.toRadians(90)))
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(30, 12),0).build();
               // .lineToY(-51).build();
       */



        telemetry.addLine("2. trajectoryActionChosen");
        telemetry.update();

        Actions.runBlocking(
                new SequentialAction(
                        //elevator.elevatorUp()
                        //,
                       trajectoryActionChosen



                       /* intake.openIntake(),
                        backAction*/



                )
        );


       telemetry.addLine("end autonomous ");
       telemetry.update();

    }

}
