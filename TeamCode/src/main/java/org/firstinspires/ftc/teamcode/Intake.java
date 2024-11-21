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
    //intake.setPosition(0.85);
    intake.scaleRange(0,0.06);
    intake.setPosition(1);
}

public class CloseIntake implements Action {
    double position;
    public CloseIntake(double position){
        this.position = position;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        intake.setPosition(0.85); //tiny
        // intake.setPosition(0.52);
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
        intake.setPosition(0.52);
        return false;
    }
}
public Action openIntake(double position) {
    return new Intake.OpenIntake(position);
}

}
