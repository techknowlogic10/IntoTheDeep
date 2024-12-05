package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Lift {

    private DcMotor lift = null;

    public Lift(DcMotor lift) {
        this.lift = lift;
        //lift = hardwareMap.dcMotor.get("lift");
        //lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //lift.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public class LiftUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                lift.setPower(1);
                initialized = true;
            }

            double pos = lift.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 3000.0) {
                return true;
            } else {
                lift.setPower(0);
                return false;
            }
        }
    }
    public Action liftUp() {
        return new LiftUp();
    }

    public class LiftDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                lift.setPower(-1);
                initialized = true;
            }

            double pos = lift.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos > 100.0) {
                return true;
            } else {
                lift.setPower(0);
                return false;
            }
        }
    }
    public Action liftDown(){
        return new LiftDown();
    }
}