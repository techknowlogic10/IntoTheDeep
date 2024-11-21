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

    public static double intake_close = 0; //0.85;
    public static double intake_specimen_open = 0.4; // 0.4; tiny
    public static double intake_sample_open = 0.5;
    public static double intake_wide_open = 1;

   // public static double intake_sr_start = 0;
  //  public static double intake_sr_end = 1;
    //public static double intake_initial_pos = 0.4;

    /*
    public static double elbow_start = 0.48; // 0.1; tiny claw//down //0.475
    public static double elbow_end = 0.34; //up
    public static double elbow_initial_pos = 0.42;
    public static double elbow_middle = 0.42; */

  /*  public static double elbow_start = 0.29; // 0.1; tiny claw//down //0.475
    public static double elbow_end = 0.7; //up //auto 0.88
    public static double elbow_initial_pos = 0.49;
    public static double elbow_middle = 0.49;

   */

    public static double elbow_down = 0.16; // 0.1; tiny claw//down //0.475
    public static double elbow_up = 0.7; //up //auto 0.88
    public static double elbow_middle = 0.4;
    public static double elbow_lower = 0;
    public static double elbow_top = 1;


    // public static double elbow_sr_start = 0;
   // public static double elbow_sr_end = 1;
    private DcMotor elevator = null;



    @Override
    public void init() {


        elbow = hardwareMap.get(Servo.class, "elbow");
       // elbow.setDirection(Servo.Direction.REVERSE);

        // elbow.setPosition(1);
       //elbow.scaleRange(0.2,0.6);
        //elbow.scaleRange(0.005,0.072); //.072
        elbow.scaleRange(0,0.1);
        elbow.setPosition(elbow_middle);

        //intake = hardwareMap.servo.get("intake");
        intake = hardwareMap.get(Servo.class, "intake");
        intake.setDirection(Servo.Direction.REVERSE);
        //intake.scaleRange(0,1);
        //intake.scaleRange(0,0.04);
        intake.scaleRange(0,0.06);
        intake.setPosition(intake_close);

        elevator = hardwareMap.dcMotor.get("elevator"); //Port #?
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        if(gamepad2.dpad_up) {
            telemetry.addLine("gamepad2.dpad_up: "+elbow_up);
            elbow.setPosition(elbow_up);
        } else if(gamepad2.dpad_down) {
            telemetry.addLine("gamepad2.dpad_down: "+elbow_down);
            elbow.setPosition(elbow_down);
        } else if(gamepad2.dpad_left){
            telemetry.addLine("gamepad2.dpad_left :" + elbow_middle);
            elbow.setPosition(elbow_middle); //elbow 90-parallal
        } else if(gamepad2.b){
            telemetry.addLine("gamepad2.dpad_left :" + elbow_middle);
            elbow.setPosition(elbow_lower); //elbow 90-parallal
        } else if(gamepad2.x){
             telemetry.addLine("gamepad2.x top:" + elbow_top);
              elbow.setPosition(elbow_top); //elbow top
         }

        if(gamepad2.left_bumper){
            telemetry.addLine("gamepad2.left_bumper: "+intake_close);
            //intake.setPosition(1);
            intake.setPosition(intake_close); //close
        } else if(gamepad2.right_bumper){
            telemetry.addLine("gamepad2.right_bumper: "+ intake_specimen_open);
            // intake.setPosition(0);
            intake.setPosition(intake_specimen_open); // sample open
        }else  if(gamepad2.y){
            telemetry.addLine("gamepad2.y: "+intake_sample_open);
            intake.setPosition(intake_sample_open); //intake sample open
        } else if(gamepad2.dpad_right){
            telemetry.addLine("gamepad2.dpad_right: "+intake_wide_open);
            intake.setPosition(intake_wide_open); //intake wide open  //0.8-1021
        }


        double elevatorPower = gamepad2.right_stick_y;
        telemetry.addLine("elevatorPower: "+ elevatorPower);
        elevator.setPower(elevatorPower);





        telemetry.update();

    }
}
