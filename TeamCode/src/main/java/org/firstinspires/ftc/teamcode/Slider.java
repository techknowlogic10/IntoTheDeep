package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slider {

    private DcMotor slider;
    private DcMotorEx sliderEx;
    static final double     COUNTS_PER_MOTOR_REV    = 28.0;
    static final double     DRIVE_GEAR_REDUCTION    = 30.21;
    static final double     PULLEY_DIAMETER_MM = 40;  // ToDo: Measure and Change. 46 mm
    static final double     WHEEL_CIRCUMFERENCE_MM  = PULLEY_DIAMETER_MM * Math.PI;
    static final double     COUNTS_PER_WHEEL_REV    = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
    static final double     COUNTS_PER_MM           = COUNTS_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE_MM;
    //double ticksPerRev = 0.0;

    public Slider(HardwareMap hardwareMap) {
        slider = hardwareMap.get(DcMotor.class, "slider");
        slider.setDirection(DcMotorSimple.Direction.REVERSE);
        //ticksPerRev = elevator.getMotorType().getTicksPerRev();
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderEx = hardwareMap.get(DcMotorEx.class, "slider");


    }

    public class SliderForward implements Action {
        private int forwardPos = 0;
        public SliderForward(int pos){
            forwardPos = pos;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            forwardPos = (int)(forwardPos * COUNTS_PER_MM);

            //TPS: 2467.1499999999996
            //forwardPos: 1211
            slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            double TPS; // Ticks per second
            TPS = ((double) 175 /60) * COUNTS_PER_WHEEL_REV * 4;
            System.out.println("Slider TPS: "+TPS);
            System.out.println("Slider forwardPos: "+forwardPos);
            sliderEx.setVelocity(TPS);
            sliderEx.setTargetPosition(forwardPos);
            sliderEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sliderEx.setVelocity(TPS);


            return false;
        }

    }
    public Action sliderForward(int pos) {
        return new Slider.SliderForward(pos);
    }

    public class SliderBackward implements Action {
        private int backwardPos = 0;
        public SliderBackward(int pos) {
            backwardPos = pos;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            backwardPos = (int) (backwardPos * COUNTS_PER_MM);
            double TPS; // Ticks per second
            TPS = ((double) 175 /60) * COUNTS_PER_WHEEL_REV * 4;
            sliderEx.setVelocity(TPS);
            sliderEx.setTargetPosition(backwardPos);
            sliderEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sliderEx.setVelocity(TPS);

            return false;
        }
    }
    public Action sliderBackward(int pos) {
        return new Slider.SliderBackward(pos);
    }



}
