package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Disabled
@TeleOp
public class SliderTest extends OpMode {

    public static int sliderForwardPos= 300;
    public static int sliderBackwardPos= 0;
    Slider slider = null;

    @Override
    public void init() {
        slider = new Slider(hardwareMap);
    }

    @Override
    public void loop() {

        if (gamepad2.dpad_up) {
            telemetry.addLine("gamepad2.dpad_up sliderForwardPos:"+sliderForwardPos);
            telemetry.update();
            slider.sliderForward(sliderForwardPos);

        } else if (gamepad2.dpad_down) {
            telemetry.addLine("gamepad2.dpad_down sliderBackwardPos:"+sliderBackwardPos);
            telemetry.update();
            slider.sliderBackward(sliderBackwardPos);
        }
    }

    private final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
