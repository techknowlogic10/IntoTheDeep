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

    public static double intake_start = 0.52; // 0.4; tiny
    public static double intake_end = 0.85;

   // public static double intake_sr_start = 0;
  //  public static double intake_sr_end = 1;
    public static double intake_initial_pos = 0.4;

    public static double elbow_start = 0.265; // 0.1; tiny claw
    public static double elbow_end = 0.48;

   // public static double elbow_sr_start = 0;
   // public static double elbow_sr_end = 1;
    public static double elbow_initial_pos = 0.48;

    public static double elbow_middle = 0.38;



    @Override
    public void init() {


        elbow = hardwareMap.get(Servo.class, "elbow");
       // elbow.setDirection(Servo.Direction.REVERSE);

        // elbow.setPosition(1);
        elbow.scaleRange(0,1);
        elbow.setPosition(elbow_initial_pos);

        //intake = hardwareMap.servo.get("intake");
        intake = hardwareMap.get(Servo.class, "intake");
        intake.setDirection(Servo.Direction.REVERSE);
        intake.scaleRange(0,1);
        intake.setPosition(intake_initial_pos);
    }

    @Override
    public void loop() {

        if(gamepad2.dpad_up) {
            telemetry.addLine("gamepad2.dpad_down: "+elbow_end);
            elbow.setPosition(elbow_end);
        } else if(gamepad2.dpad_down){

            telemetry.addLine("gamepad2.dpad_up: "+elbow_start);
            elbow.setPosition(elbow_start);
        } else if(gamepad2.dpad_left){

            telemetry.addLine("gamepad2.dpad_left 0.3");
            elbow.setPosition(elbow_middle); //elbow 90-parallal
        }

        if(gamepad2.left_bumper){
            telemetry.addLine("gamepad2.right_bumper: "+intake_end);
            //intake.setPosition(1);
            intake.setPosition(intake_end);
        }
        else if(gamepad2.right_bumper){
            telemetry.addLine("gamepad2.left_bumper: "+ intake_start);
            // intake.setPosition(0);
            intake.setPosition(intake_start);
        }
        else if(gamepad2.dpad_right){
            telemetry.addLine("gamepad2.dpad_right");
            // intake.setPosition(0);
            intake.setPosition(0.2); //intake wide open
        }




        telemetry.update();

    }
}
