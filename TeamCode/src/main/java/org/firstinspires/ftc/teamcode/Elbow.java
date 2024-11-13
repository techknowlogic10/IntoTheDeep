package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Elbow {

    private Servo elbow = null;

    public Elbow(HardwareMap hardwareMap) {

        elbow = hardwareMap.get(Servo.class, "elbow");
        elbow.setDirection(Servo.Direction.REVERSE);
        elbow.scaleRange(0.2,0.6);
        elbow.setPosition(0.26); //tiny

    }

    public class ElbowUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(0.34);
            return false;
        }
    }
    public Action upElbow() {
        return new Elbow.ElbowUp();
    }

    public class DownElbow implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(0.475);
            return false;
        }
    }
    public Action downElbow() {
        return new Elbow.DownElbow();
    }

    public class ElbowStraight implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(0.42);
            return false;
        }
    }
    public Action straightEobow() {
        return new Elbow.ElbowStraight();
    }

}
