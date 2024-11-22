package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {

    private DcMotor elevator;
    private DcMotorEx elevatorEx;
    static final double     COUNTS_PER_MOTOR_REV    = 28.0;
    static final double     DRIVE_GEAR_REDUCTION    = 30.21;
    static final double     PULLEY_DIAMETER_MM = 46;  // ToDo: Measure and Change. 46 mm
    static final double     WHEEL_CIRCUMFERENCE_MM  = PULLEY_DIAMETER_MM * Math.PI;
    static final double     COUNTS_PER_WHEEL_REV    = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
    static final double     COUNTS_PER_MM           = COUNTS_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE_MM;
    //double ticksPerRev = 0.0;

    public Elevator(HardwareMap hardwareMap) {
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setDirection(DcMotorSimple.Direction.REVERSE);
        //ticksPerRev = elevator.getMotorType().getTicksPerRev();
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevatorEx = hardwareMap.get(DcMotorEx.class, "elevator");

        DcMotor slider = hardwareMap.dcMotor.get("slider");
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public class ElevatorUp implements Action {
        private int uppos = 0;
        public ElevatorUp(int elevatorUpPos){
            uppos = elevatorUpPos;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            uppos = (int)(uppos * COUNTS_PER_MM);

            elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           // elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

          /*  elevator.setTargetPosition(uppos);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(1); */

            double TPS; // Ticks per second
            TPS = ((double) 175 /60) * COUNTS_PER_WHEEL_REV;
            elevatorEx.setVelocity(TPS);
            elevatorEx.setTargetPosition(uppos);
            elevatorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorEx.setVelocity(TPS);

            /*while (elevator.isBusy()) {
                sleep(50);
            }*/
            return false;
        }
        /*private final void sleep(long milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }*/
    }
    public Action elevatorUp(int elevatorUpPos) {
        return new Elevator.ElevatorUp(elevatorUpPos);
    }

    public class ElevatorDown implements Action {
        private int downpos = 0;
        public ElevatorDown(int elevatorDownPos) {
            downpos = elevatorDownPos;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
           downpos = (int) (downpos * COUNTS_PER_MM);
         /*   elevator.setTargetPosition(downpos);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(-1.0); */

            double TPS; // Ticks per second
            TPS = ((double) 175 /60) * COUNTS_PER_WHEEL_REV;
            elevatorEx.setVelocity(TPS);
            elevatorEx.setTargetPosition(downpos);
            elevatorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorEx.setVelocity(TPS);

            return false;
        }
    }
    public Action elevatorDown(int elevatorDownPos) {
        return new Elevator.ElevatorDown(elevatorDownPos);
    }

    public class ElevatorHighBasketUp implements Action {
        private boolean initialized = false;

        private int uppos = 0;
        public ElevatorHighBasketUp(int elevatorUpPos){
            uppos = elevatorUpPos;
            uppos = (int)(uppos * COUNTS_PER_MM);

            elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.setPower(1);
                initialized = true;
            }

            double pos = elevator.getCurrentPosition();
            System.out.println("pos: "+pos + "uppos :"+uppos);

           // packet.put("liftPos", pos);
          //  if (pos < 3000.0) {
            if (pos < uppos) {
                return true;
            } else {
                elevator.setPower(0);
                return false;
            }
        }
    }
    public Action ElevatorHighBasketUp(int elevatorUpPos) {
        return new Elevator.ElevatorHighBasketUp(elevatorUpPos);
    }

    public class ElevatorHighBasketDown implements Action {
        private boolean initialized = false;

        private int uppos = 0;
        public ElevatorHighBasketDown(int elevatorUpPos){
            uppos = elevatorUpPos;
            uppos = (int)(uppos * COUNTS_PER_MM);

            /*elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.setPower(-1);
                initialized = true;
            }

            double pos = elevator.getCurrentPosition();
            packet.put("liftPos", pos);
           // if (pos > 100.0) {
            if (pos > uppos) {
                return true;
            } else {
                elevator.setPower(0);
                return false;
            }
        }
    }
    public Action ElevatorHighBasketDown(int elevatorUpPos){
        return new Elevator.ElevatorHighBasketUp(elevatorUpPos);
    }


}
