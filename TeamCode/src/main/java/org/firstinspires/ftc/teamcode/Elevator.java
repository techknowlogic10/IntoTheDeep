package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {

    private DcMotor elevator;
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
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public class ElevatorUp implements Action {
        private int uppos = 0;
        public ElevatorUp(int elevatorUpPos){
            uppos = elevatorUpPos;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            uppos = (int)(uppos * COUNTS_PER_MM);
            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            elevator.setTargetPosition(uppos);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(0.8);
            return false;
        }
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
            elevator.setTargetPosition(downpos);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(-1.0);
            return false;
        }
    }
    public Action elevatorDown(int elevatorDownPos) {
        return new Elevator.ElevatorDown(elevatorDownPos);
    }
}
