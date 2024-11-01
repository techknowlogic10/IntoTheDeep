package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

private Servo intake = null;

public Intake(HardwareMap hardwareMap) {
    intake = hardwareMap.get(Servo.class, "intake");
    intake.setDirection(Servo.Direction.REVERSE);
    intake.setPosition(0.85);
}

public class CloseIntake implements Action {
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        intake.setPosition(0.85); //tiny
        // intake.setPosition(0.52);
        return false;
    }
}
public Action closeIntake() {
    return new Intake.CloseIntake();
}

public class OpenIntake implements Action {
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        intake.setPosition(0.52);
        return false;
    }
}
public Action openIntake() {
    return new Intake.OpenIntake();
}

}
