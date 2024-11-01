package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensor {

    private com.qualcomm.robotcore.hardware.DistanceSensor sensorDistance;
    Rev2mDistanceSensor sensorTimeOfFlight;

    public DistanceSensor (HardwareMap hardwareMap) {

        sensorDistance = hardwareMap.get(com.qualcomm.robotcore.hardware.DistanceSensor.class, "distancesensor");
        sensorTimeOfFlight = (Rev2mDistanceSensor) sensorDistance;
        /*telemetry.addLine("distancesensor");
        telemetry.update();*/
    }

    protected double getDistance() {

        // generic DistanceSensor methods.
       /* telemetry.addData("deviceName", sensorDistance.getDeviceName() );
        telemetry.addData("range", String.format("%.01f mm", sensorDistance.getDistance(DistanceUnit.MM)));
        telemetry.addData("range", String.format("%.01f cm", sensorDistance.getDistance(DistanceUnit.CM)));
        telemetry.addData("range", String.format("%.01f m", sensorDistance.getDistance(DistanceUnit.METER)));
        telemetry.addData("range", String.format("%.01f in", sensorDistance.getDistance(DistanceUnit.INCH)));*/

        // Rev2mDistanceSensor specific methods.
       /* telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
        telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));

        telemetry.update();*/

        return sensorDistance.getDistance(DistanceUnit.INCH);


    }
}
