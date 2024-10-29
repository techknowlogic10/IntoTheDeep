package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class ElevatorTest extends OpMode {

    public static int elevator_up = 2000;
    public static int elevator_down = 1800;
    private DcMotor elevator =null;
    private Servo elbow;
    private Servo intake;

    private DcMotorEx encoderElevator = null;

    @Override
    public void init() {

        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevator.setDirection(DcMotorSimple.Direction.REVERSE);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        encoderElevator = hardwareMap.get(DcMotorEx.class, "elevator");
       // encoderElevator.
       // encoderElevator.setDirection(DcMotorSimple.Direction.FORWARD);

        elbow = hardwareMap.get(Servo.class, "elbow");
        elbow.setPosition(0.38);

        intake = hardwareMap.get(Servo.class, "intake");
        intake.setDirection(Servo.Direction.REVERSE);
        intake.setPosition(0.85);
    }

    @Override
    public void loop() {

        if (gamepad2.dpad_up) {

            telemetry.addLine("before pos: "+elevator.getCurrentPosition());
            telemetry.update();
            System.out.println("before pos: "+elevator.getCurrentPosition());



            elevator.setTargetPosition(elevator_up);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(0.8);

            telemetry.addLine("After pos: "+elevator.getCurrentPosition());
            telemetry.update();
            System.out.println("After pos: "+elevator.getCurrentPosition());

            telemetry.addLine("gamepad2.dpad_up: " + elevator_up);
            telemetry.update();

        } else if (gamepad2.dpad_down) {

            telemetry.addLine("before pos: "+elevator.getCurrentPosition());
            telemetry.update();

            System.out.println("before pos: "+elevator.getCurrentPosition());



            elevator.setTargetPosition(elevator_down);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(-1.0);

            telemetry.addLine("after pos: "+elevator.getCurrentPosition());
            telemetry.update();

            System.out.println("After pos: "+elevator.getCurrentPosition());

            telemetry.addLine("gamepad2.dpad_up: " + elevator_down);
            telemetry.update();

        }else if (gamepad2.dpad_left) {

            telemetry.addLine("before pos: "+elevator.getCurrentPosition());
            telemetry.update();

            System.out.println("before pos: "+elevator.getCurrentPosition());



            elevator.setTargetPosition(0);
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevator.setPower(-1.0);

            telemetry.addLine("after pos: "+elevator.getCurrentPosition());
            telemetry.update();

            System.out.println("After pos: "+elevator.getCurrentPosition());

            telemetry.addLine("gamepad2.dpad_left: " + elevator_down);
            telemetry.update();

        }

    }


}
