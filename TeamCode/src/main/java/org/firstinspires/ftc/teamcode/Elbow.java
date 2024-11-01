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
        elbow.setPosition(0.12); //tiny

    }

    public class ElbowUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(0.12);
            return false;
        }
    }
    public Action upEobow() {
        return new Elbow.ElbowUp();
    }

    public class DownElbow implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(0);
            return false;
        }
    }
    public Action downElbow() {
        return new Elbow.DownElbow();
    }

}
