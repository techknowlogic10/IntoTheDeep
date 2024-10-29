package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Elbow {

    private Servo elbow;

    public Elbow(HardwareMap hardwareMap) {

        elbow = hardwareMap.get(Servo.class, "elbow");
        //elbow.setPosition(0.48); //tiny
        elbow.setPosition(0.38); //tiny
        // elbow.setPosition(0.48);
       /* telemetry.addLine(" Construcor Elbow");
        telemetry.update();*/
    }

    public class ElbowUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(0.48);
            return false;
        }
    }
    public Action upEobow() {
        return new Elbow.ElbowUp();
    }

    public class DownElbow implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            elbow.setPosition(0.265);
            return false;
        }
    }
    public Action downElbow() {
        return new Elbow.DownElbow();
    }

}
