package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Config
//@Disabled
@Autonomous (name = "SliderEncoderTest")
public class SliderEncoderTest extends LinearOpMode {
    private DcMotor slider;
    private int sliderPos;

    private Servo elbow;
    private Servo intake;

    public static int targetDistance = 50;// 330;

    private DcMotorEx sliderEx; //ToDo: to test Velocity

    // Converting Encoder Ticks to a Distance
    //
    // 1000 ticks is less than a foot (Approximately)
    //
    //For the conversion process the following information is needed:
    //    1) Ticks per revolution of the encoder
    //        For HD Hex Motors the encoder counts 28 ticks per revolution of the motor shaft.
    //    2) Total gear reduction on the motor
    //        Including gearboxes and motion transmission components like gears, sprockets and chain, or belts and pulleys
    //    3) Circumference of the driven wheels

    static final double     COUNTS_PER_MOTOR_REV    = 28.0;
    static final double     DRIVE_GEAR_REDUCTION    = 30.21;
    static final double     PULLEY_DIAMETER_MM = 40;  // ToDo: Measure and Change. 46 mm
    static final double     WHEEL_CIRCUMFERENCE_MM  = PULLEY_DIAMETER_MM * Math.PI;

    static final double     COUNTS_PER_WHEEL_REV    = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
    static final double     COUNTS_PER_MM           = COUNTS_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE_MM;



    @Override
    public void runOpMode() throws InterruptedException {

        elbow = hardwareMap.get(Servo.class, "elbow");
        // elbow.setDirection(Servo.Direction.REVERSE);
       /* elbow.scaleRange(0.2,0.6);
        elbow.setPosition(0.42); //elbow up tiny */
        elbow.scaleRange(0,0.072);
        elbow.setPosition(1); //0.88

        intake = hardwareMap.get(Servo.class, "intake");
        intake.setDirection(Servo.Direction.REVERSE);
        intake.scaleRange(0,0.06);
        intake.setPosition(0);

        telemetry.addData("BalaElevatorTest: COUNTS_PER_WHEEL_REV: ", COUNTS_PER_WHEEL_REV);
        telemetry.update();
        System.out.println("BalaElevatorTest: COUNTS_PER_WHEEL_REV: "+ COUNTS_PER_WHEEL_REV);


        telemetry.addData("BalaElevatorTest: COUNTS_PER_MM: ", COUNTS_PER_MM);
        telemetry.update();
        System.out.println("BalaElevatorTest: COUNTS_PER_MM: "+ COUNTS_PER_MM);

        slider = hardwareMap.get(DcMotor.class, "slider");
        slider.setDirection(DcMotorSimple.Direction.REVERSE);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double ticksPerRev= slider.getMotorType().getTicksPerRev();
        telemetry.addData("BalaSliderTest: Ticks per revolution: ", ticksPerRev);
        telemetry.update();

        System.out.println("BalaElevatorTest: Ticks per revolution: "+ ticksPerRev);
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderEx = hardwareMap.get(DcMotorEx.class, "slider");


        sliderPos = 0;

        waitForStart();

        // Distance to move in Inches

        int distance2MoveInMM = (int)(targetDistance * COUNTS_PER_MM);
        telemetry.addData("BalaElevatorTest: distance2MoveInMM: ", distance2MoveInMM);
        telemetry.update();
        System.out.println("BalaElevatorTest: distance2MoveInMM: "+ distance2MoveInMM);

        // move2Target(distance2MoveInMM, 1);
        // move2Target(-distance2Move, 0.25);
        telemetry.addData("BalaElevatorTest: slider getCurrentPosition: ", slider.getCurrentPosition());
        telemetry.update();

        System.out.println("BalaElevatorTest: slider getCurrentPosition: "+ slider.getCurrentPosition());

        // When working with encoder setting a velocity is recommended over setting a power level, as it offers a higher level of control.

        //To set a velocity, its important to understand the maximum velocity in RPM your motor is capable of.
        // For the Class Bot V2 the motors are capable of a maximum RPM of 300.
        // With a drivetrain, you are likely to get better control by setting velocity lower than the maximum.
        // In this case, lets set the velocity to 175 RPM

        //ToDo: to test Velocity

        double TPS; // Ticks per second
        TPS = ((double) 175 /60) * COUNTS_PER_WHEEL_REV;
        sliderEx.setVelocity(TPS);
        sliderEx.setTargetPosition(distance2MoveInMM);

        sliderEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        sliderEx.setVelocity(TPS);

        while (opModeIsActive() && (sliderEx.isBusy())) {
            telemetry.addData("BalaElevatorTest: getCurrentPosition:", sliderEx.getCurrentPosition());
            telemetry.update();
        }

    }


    private void move2Target(int eleTarget, double speed){
        sliderPos += eleTarget;
        telemetry.addData(" move2Target elePos: ", sliderPos);
        telemetry.update();

        System.out.println(" move2Target elePos: "+ sliderPos);


        slider.setTargetPosition(sliderPos);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slider.setPower(speed);
        while (opModeIsActive() && slider.isBusy()) {
            idle();
        }
    }
}
