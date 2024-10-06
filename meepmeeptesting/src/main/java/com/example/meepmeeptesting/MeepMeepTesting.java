package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myRedLeftBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();



        myRedLeftBot.runAction(myRedLeftBot.getDrive().actionBuilder(new Pose2d(-10, -55, Math.toRadians(90)))
               .lineToY(-30)
               .turn(Math.toRadians(90))
               .lineToX(-50)
               .turn(Math.toRadians(90))
               .lineToY(-55)
               .build());

        RoadRunnerBotEntity myRedRightBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();



        myRedRightBot.runAction(myRedRightBot.getDrive().actionBuilder(new Pose2d(10, -55, Math.toRadians(90)))
                .lineToY(-30)
                .turn(Math.toRadians(-90))
                .lineToX(50)
                .turn(Math.toRadians(-90))
                .lineToY(-55)
                .build());

        // Declare out second bot
        RoadRunnerBotEntity myBlueLeftBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();



        myBlueLeftBot.runAction(myBlueLeftBot.getDrive().actionBuilder(new Pose2d(-10, 55, Math.toRadians(-90)))
                .lineToY(30)
                .turn(Math.toRadians(-90))
                .lineToX(-50)
                .turn(Math.toRadians(-90))
                .lineToY(55)
                .build());

        // Declare out second bot
        RoadRunnerBotEntity myBlueRightBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();



        myBlueRightBot.runAction(myBlueRightBot.getDrive().actionBuilder(new Pose2d(10, 55, Math.toRadians(-90)))
                .lineToY(30)
                .turn(Math.toRadians(90))
                .lineToX(50)
                .turn(Math.toRadians(90))
                .lineToY(55)
                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myRedLeftBot)
                .addEntity(myRedRightBot)
                .addEntity(myBlueLeftBot)
                .addEntity(myBlueRightBot)
                .start();
    }
}