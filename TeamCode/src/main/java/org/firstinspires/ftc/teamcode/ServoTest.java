package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class ServoTest extends OpMode {

    private Servo elbow = null;
    private Servo intake = null;

    public static double intake_start = 0.9;
    public static double intake_end = 0.53;

    public static double intake_sr_start = 0;
    public static double intake_sr_end = 1;
    public static double intake_initial_pos = 0.53;

    public static double elbow_start = 0.15;
    public static double elbow_end = 0.48;

    public static double elbow_sr_start = 0;
    public static double elbow_sr_end = 1;
    public static double elbow_initial_pos = 0.15;

    @Override
    public void init() {


        elbow = hardwareMap.get(Servo.class, "elbow");

        // elbow.setPosition(1);
        elbow.scaleRange(elbow_sr_start,elbow_sr_end);
        elbow.setPosition(elbow_initial_pos);

        //intake = hardwareMap.servo.get("intake");
        intake = hardwareMap.get(Servo.class, "intake");
        intake.scaleRange(intake_sr_start,intake_sr_end);
        intake.setPosition(intake_initial_pos);
    }

    @Override
    public void loop() {

        if(gamepad2.dpad_up) {
            telemetry.addLine("gamepad2.dpad_up: "+elbow_start);
            elbow.setPosition(elbow_start);
        } else if(gamepad2.dpad_down){
            telemetry.addLine("gamepad2.dpad_down: "+elbow_end);
            elbow.setPosition(elbow_end);
        }

        if(gamepad2.left_bumper){
            telemetry.addLine("gamepad2.left_bumper: "+ intake_start);
            // intake.setPosition(0);
            intake.setPosition(intake_start);
        }
        else if(gamepad2.right_bumper){
            telemetry.addLine("gamepad2.right_bumper: "+intake_end);
            //intake.setPosition(1);
            intake.setPosition(intake_end);
        }

        telemetry.update();

    }
}
