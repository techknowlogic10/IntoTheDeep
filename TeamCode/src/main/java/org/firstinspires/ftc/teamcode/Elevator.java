package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {

    private DcMotor elevator;

    public Elevator(HardwareMap hardwareMap) {

       /* telemetry.addLine(" Construcor ElevatorTest");
        telemetry.update();*/
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setDirection(DcMotorSimple.Direction.REVERSE);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


       /* telemetry.addLine(" end Construcor ElevatorTest");
        telemetry.update();*/
    }

    public class ElevatorUp implements Action {
        private boolean initialized = false;
        private double uppos = 0;
        public ElevatorUp(double elevatorUpPos){
            uppos = elevatorUpPos;
        }


        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.setPower(0.8);
                initialized = true;
            }

            double pos = elevator.getCurrentPosition();
            // packet.put("elevaorPos: ", pos);
           /* telemetry.addLine("elevaorPos: "+ pos);
            telemetry.update();*/

            if (pos < uppos) {
                return true;
            } else {
                elevator.setPower(0);
                return false;
            }

               /* elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
               // elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.setTargetPosition(1300);

                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(0.8);
                return false;*/


               /* telemetry.addLine("elevaor taregt 1400");
                elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.setTargetPosition(400);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(0.8);
                return false;*/

               /* telemetry.addLine("elevaor taregt 600");
                telemetry.update();*/

               /* elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/
                /*
                telemetry.addLine("before setTargetPosition");
                telemetry.update();

                elevator.setTargetPosition(1300);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(1.0);

                telemetry.addLine("before sleep");
                telemetry.update();

                 */

               /* while (elevator.isBusy()) {
                    sleep(50);
                }*/
            /*
                telemetry.addLine("before returning false");
                telemetry.update();
                return false;

             */



        }
    }
    public Action elevatorUp(double elevatorUpPos) {
        return new Elevator.ElevatorUp(elevatorUpPos);
    }

    public class ElevatorDown implements Action {
        private boolean initialized = false;
        private double downpos = 0;

        public ElevatorDown(double elevatorDownPos){
            downpos = elevatorDownPos;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
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
            }

                /*elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/

               /* telemetry.addLine("elevaor taregt 600");
                telemetry.update();

                elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                telemetry.addLine("before setTargetPosition");
                telemetry.update();

                elevator.setTargetPosition(600);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(1.0);

                telemetry.addLine("before sleep");
                telemetry.update();

                while (elevator.isBusy()) {
                    sleep(50);
                }

                telemetry.addLine("before returning false");
                telemetry.update();
                return false;*/


                /*elevator.setTargetPosition(0);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setPower(0.8);
                return false;*/
        }
    }
    public Action elevatorDown(double elevatorDownPos){
        return new Elevator.ElevatorDown(elevatorDownPos);
    }

}
