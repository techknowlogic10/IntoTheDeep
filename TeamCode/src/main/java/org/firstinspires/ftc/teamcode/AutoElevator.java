package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutoElevator {

    static final double     COUNTS_PER_MOTOR_REV    = 28.0;
    static final double     DRIVE_GEAR_REDUCTION    = 30.21;
    static final double     PULLEY_DIAMETER_MM = 46;  // ToDo: Measure and Change. 46 mm
    static final double     WHEEL_CIRCUMFERENCE_MM  = PULLEY_DIAMETER_MM * Math.PI;
    static final double     COUNTS_PER_WHEEL_REV    = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
    static final double     COUNTS_PER_MM           = COUNTS_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE_MM;

    private DcMotor elevator;
    private DcMotorEx elevatorEx;
    Telemetry telemetry = null;


    public AutoElevator(DcMotor elevator, DcMotorEx elevatorEx, Telemetry telemetry) {
        this.elevator =  elevator;
        this.elevatorEx = elevatorEx;
        this.telemetry = telemetry;
    }

    public void elevatorUp(int elevatorUpPos){

        elevator.setDirection(DcMotorSimple.Direction.REVERSE);

        elevatorUpPos = (int)(elevatorUpPos * COUNTS_PER_MM);

        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addLine("AutoElevator after RUN_USING_ENCODER");

        double TPS; // Ticks per second
        TPS = ((double) 175 /60) * COUNTS_PER_WHEEL_REV;
       // elevatorEx.setVelocity(TPS);
        elevatorEx.setTargetPosition(elevatorUpPos);
        elevatorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // elevator.setPower(1);

        elevatorEx.setVelocity(TPS);

        //while(elevator.isBusy()){
            sleep(2000);
       // }
        telemetry.addLine("AutoElevator end");
    }

    private final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }




}
