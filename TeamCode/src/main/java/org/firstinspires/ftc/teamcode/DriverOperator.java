package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@TeleOp

public class DriverOperator extends OpMode {

    // Declaring the DC Motors
   private DcMotor frontLeft = null;
   private DcMotor frontRight = null;
   private DcMotor backLeft = null;
   private DcMotor backRight= null;
  // private DcMotor elevator = null;
   private int drivePower = 1;


    @Override
    public void init(){


        // Initializing the DC Motors
        frontLeft = hardwareMap.dcMotor.get("frontleft"); // Port #0
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight = hardwareMap.dcMotor.get("frontright"); // Port #1
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeft = hardwareMap.dcMotor.get("backleft"); //Port#2
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        backRight = hardwareMap.dcMotor.get("backright"); //Port #3
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

       /* elevator = hardwareMap.dcMotor.get("elevator"); //Port #?
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/
        //elevator.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    @Override
    public void loop(){

        // Taking the values from the Gamepad/Joysticks
        double y = -gamepad1.left_stick_y * 4.1;
        double x = gamepad1.left_stick_x * 5.1;
        double rx = gamepad1.right_stick_x;
        //double elevatorPower = gamepad2.right_stick_y;

        telemetry.addLine("y: "+ y +" , x: "+ x + " , rx: +"+ rx);

        // Normalizing the Power
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx),drivePower);
        double frontLeftPower = (y + x + rx) / denominator;
        //double frontRightPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        telemetry.addLine("denominator: "+ denominator +" , frontLeftPower: "+ frontLeftPower + " , backLeftPower: +"+ backLeftPower +", frontRightPower: "+frontRightPower +" , backRightPower: "+backRightPower);
        //telemetry.addLine("elevatorPower"+ elevatorPower);

        //Setting the Power for the DC Motors
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
       // elevator.setPower(elevatorPower);

        telemetry.update();


    }
}
