package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Elbow {

    private Servo elbow = null;
    public static double elbow_down = 0.16; // 0.1; tiny claw//down //0.475
    public static double elbow_up = 0.7; //up //auto 0.88
    public static double elbow_middle = 0.4;
    public static double elbow_lower = 0;
    public static double elbow_top = 1;

    public Elbow(HardwareMap hardwareMap) {

        elbow = hardwareMap.get(Servo.class, "elbow");
       // elbow.setDirection(Servo.Direction.REVERSE);
       /* elbow.scaleRange(0.2,0.6);
        elbow.setPosition(0.26); //tiny
        */
       // elbow.scaleRange(0.15,0.28);
        elbow.scaleRange(0,0.072);
        elbow.setPosition(elbow_top); //0.88

    }

    public class ElbowUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            //elbow.setPosition(0.34);
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
            //elbow.setPosition(0.48); //0.475
            elbow.setPosition(elbow_down); //0.475 , 0.31, 0.28
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
            //elbow.setPosition(0.34);
            elbow.setPosition(elbow_top); //0.88
            return false;
        }
    }
    public Action elbowTop() {
        return new Elbow.ElbowTop();
    }

}
