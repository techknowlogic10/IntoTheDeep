package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@TeleOp

public class DriverOperator extends OpMode {

    // Declaring the DC Motors
   private DcMotor frontLeft = null;
   private DcMotor frontRight = null;
   private DcMotor backLeft = null;
   private DcMotor backRight = null;
   private DcMotor elevator = null;
   private DcMotor slider = null;
    private DcMotor lift = null;

    Elevator elevatorObj = null;

  /*  private DcMotorEx encoderFrontLeft = null;
    private DcMotorEx encoderFrontRight = null;
    private DcMotorEx encoderBackLeft = null;
    private DcMotorEx encoderBackRight = null;*/
   private DcMotorEx encoderElevator = null;

   private Servo elbow = null;
   private Servo intake = null;

   private double drivePower = 1.5;


    @Override
    public void init(){


        // Initializing the DC Motors
        frontLeft = hardwareMap.dcMotor.get("frontleft"); // Port #0
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        frontRight = hardwareMap.dcMotor.get("frontright"); // Port #1
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeft = hardwareMap.dcMotor.get("backleft"); //Port#2
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        backRight = hardwareMap.dcMotor.get("backright"); //Port #3
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        elevator = hardwareMap.dcMotor.get("elevator"); //Port #?
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //elevator.setDirection(DcMotorSimple.Direction.REVERSE);

        slider = hardwareMap.dcMotor.get("slider");
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //slider.setDirection(DcMotorSimple.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);



        elbow = hardwareMap.get(Servo.class, "elbow");
        elbow.setDirection(Servo.Direction.REVERSE);
        elbow.scaleRange(0.2,0.6);
        elbow.setPosition(0.34); //elbow up tiny
      //  elbow.setPosition(0.48); //elbow up

        intake = hardwareMap.get(Servo.class, "intake");
        intake.setDirection(Servo.Direction.REVERSE);
        intake.scaleRange(0,1);
        intake.setPosition(0.4); //intake open tiny
       // intake.setPosition(0.52); //intake open


       /* encoderFrontLeft = hardwareMap.get(DcMotorEx.class, "frontleft");
        encoderFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        encoderFrontRight = hardwareMap.get(DcMotorEx.class, "frontright");
        encoderFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        encoderBackLeft = hardwareMap.get(DcMotorEx.class, "backleft");
        encoderBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        encoderBackRight = hardwareMap.get(DcMotorEx.class, "backright");
        encoderBackRight.setDirection(DcMotorSimple.Direction.REVERSE);*/

        encoderElevator = hardwareMap.get(DcMotorEx.class, "elevator");
        encoderElevator.setDirection(DcMotorSimple.Direction.FORWARD);


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        elevatorObj= new Elevator(hardwareMap);

    }
    @Override
    public void loop(){

        // Taking the values from the Gamepad/Joysticks
        double y = -gamepad1.left_stick_y;// * 4.1;
        double x = gamepad1.left_stick_x * 1.1; // * 5.1;
        double rx = gamepad1.right_stick_x;



        telemetry.addLine("y: "+ y +" , x: "+ x + " , rx: +"+ rx);

        // Normalizing the Power
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx),drivePower);
        double frontLeftPower = (y + x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        telemetry.addLine("denominator: "+ denominator +" , frontLeftPower: "+ frontLeftPower + " , backLeftPower: +"+ backLeftPower +", frontRightPower: "+frontRightPower +" , backRightPower: "+backRightPower);




        //Setting the Power for the DC Motors
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);


       /* telemetry.addLine("encoderFrontLeft: "+ encoderFrontLeft.getCurrentPosition());
        telemetry.addLine("encoderFrontRight: "+ encoderFrontRight.getCurrentPosition());
        telemetry.addLine("encoderBackLeft: "+ encoderBackLeft.getCurrentPosition());
        telemetry.addLine("encoderBackRight: "+ encoderBackRight.getCurrentPosition());*/

        telemetry.addLine("elevator encoder: "+ elevator.getCurrentPosition());
        telemetry.addLine("encoderElevator encoder: "+ encoderElevator.getCurrentPosition());

        if(gamepad2.dpad_up) {
            telemetry.addLine("gamepad2.dpad_up 0.32");
            elbow.setPosition(0.34); // elbow up/ elbow_end
        } else if(gamepad2.dpad_down){
            telemetry.addLine("gamepad2.dpad_down 0");
            //elbow.setPosition(0.1); //elbow up //tiney elbow
            elbow.setPosition(0.475); //elbow down/ elbow_start
        }else if(gamepad2.dpad_left){
            telemetry.addLine("gamepad2.dpad_left 0.3");
            elbow.setPosition(0.42); //elbow 90-parallal/ elbow_middle
        }

        if(gamepad2.left_bumper){
            telemetry.addLine("gamepad2.right_bumper 0.8");
            intake.setPosition(0.85); //intake open  //0.8-1021
        }
        else if(gamepad2.right_bumper){
            telemetry.addLine("gamepad2.left_bumper 0.37");
           // intake.setPosition(0.4); //intake close // tiny
            intake.setPosition(0.52); //intake close
        }
        else if(gamepad2.dpad_right){
            telemetry.addLine("gamepad2.dpad_right");
            intake.setPosition(0.2); //intake wide open
        }

        double sliderPower = gamepad2.left_stick_y * 1.5;
        telemetry.addLine("sliderPower: "+ sliderPower);
        slider.setPower((sliderPower));

        double elevatorPower = gamepad2.right_stick_y;
        telemetry.addLine("elevatorPower: "+ elevatorPower);
        elevator.setPower(elevatorPower);

        double liftPower = gamepad1.right_stick_y * 2;
        telemetry.addLine("liftPower: "+ liftPower);
        lift.setPower(liftPower);

        if (gamepad2.a){
            telemetry.addLine("gamepad2.a: ");
            elevatorObj.elevatorUp(650);
        }
        if (gamepad2.b){
            telemetry.addLine("gamepad2.b: ");
            elevatorObj.elevatorDown(0);
        }

        if (gamepad2.y){
            telemetry.addLine("gamepad2.y: ");
            elevatorObj.elevatorUp(185);
        }
        if (gamepad2.x){
            telemetry.addLine("gamepad2.x: ");
            elevatorObj.elevatorUp(375);
        }

        if (gamepad1.left_trigger > 0 && gamepad1.right_trigger > 0){
            telemetry.addLine("gamepad1.left_trigger  && gamepad2.right_trigger >0");
            while(lift.isBusy()){
                lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
        } else if (gamepad1.dpad_right){
            telemetry.addLine("open the lock");

        }

        telemetry.update();


    }
}
