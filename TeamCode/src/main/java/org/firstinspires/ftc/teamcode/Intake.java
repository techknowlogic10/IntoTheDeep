package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

private Servo intake = null;
public static double intake_close = 0;
public static double intake_sample_open = 0.5;


    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(Servo.class, "intake");
        intake.setDirection(Servo.Direction.REVERSE);
        intake.scaleRange(0,0.06);
        intake.setPosition(intake_close);
    }

    public class CloseIntake implements Action {
        double position;
        public CloseIntake(double position){
            this.position = position;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.setPosition(intake_close);
            return false;
        }
    }
    public Action closeIntake(double position) {
        return new Intake.CloseIntake(position);
    }

    public class OpenIntake implements Action {
        double position;
        public OpenIntake(double position){
            this.position = position;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.setPosition(intake_sample_open);
            return false;
        }
    }
    public Action openIntake(double position) {
        return new Intake.OpenIntake(position);
    }

}
