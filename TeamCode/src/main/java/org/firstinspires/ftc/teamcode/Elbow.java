package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Elbow {

    private Servo elbow = null;
    public static double elbow_down = 0.16;
    public static double elbow_up = 0.7;
    public static double elbow_middle = 0.4;
    public static double elbow_lower = 0;
    public static double elbow_top = 1;

    public Elbow(HardwareMap hardwareMap) {

        elbow = hardwareMap.get(Servo.class, "elbow");
        elbow.scaleRange(0,0.1);
        elbow.setPosition(elbow_top);
    }

    public class ElbowUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(elbow_up);
            return false;
        }
    }
    public Action upElbow() {
        return new Elbow.ElbowUp();
    }

    public class DownElbow implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(elbow_down);
            return false;
        }
    }
    public Action downElbow() {
        return new Elbow.DownElbow();
    }

    public class ElbowStraight implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(elbow_middle); //0.49
            return false;
        }
    }
    public Action straightEobow() {
        return new Elbow.ElbowStraight();
    }

    public class ElbowTop implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(elbow_top);
            return false;
        }
    }
    public Action elbowTop() {
        return new Elbow.ElbowTop();
    }

}
