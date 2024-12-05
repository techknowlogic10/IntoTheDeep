package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {

    private DcMotor elevator = null;
    private DcMotorEx elevatorEx = null;
    static final double     COUNTS_PER_MOTOR_REV    = 28.0;
    static final double     DRIVE_GEAR_REDUCTION    = 30.21;
    static final double     PULLEY_DIAMETER_MM = 46;  // ToDo: Measure and Change. 46 mm
    static final double     WHEEL_CIRCUMFERENCE_MM  = PULLEY_DIAMETER_MM * Math.PI;
    static final double     COUNTS_PER_WHEEL_REV    = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
    static final double     COUNTS_PER_MM           = COUNTS_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE_MM;

    public Elevator(HardwareMap hardwareMap) {
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setDirection(DcMotorSimple.Direction.REVERSE);
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorEx = hardwareMap.get(DcMotorEx.class, "elevator");
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

            double TPS; // Ticks per second
            TPS = ((double) 175 /60) * COUNTS_PER_WHEEL_REV * 3;
            //TPS: 2467.1499999999996
            //uppos: 1182
            /*System.out.println("Elevator TPS: "+TPS);
            System.out.println("Elevator uppos: "+uppos);*/

            elevatorEx.setVelocity(TPS);
            elevatorEx.setTargetPosition(uppos);
            elevatorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorEx.setVelocity(TPS);
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
         /* elevator.setTargetPosition(downpos);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(-1.0); */

            double TPS; // Ticks per second
            TPS = ((double) 175 /60) * COUNTS_PER_WHEEL_REV * 3;
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
}
