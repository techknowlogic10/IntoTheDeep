package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

       /* RoadRunnerBotEntity myRedLeftBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build(); */



       // myRedLeftBot.runAction(myRedLeftBot.getDrive().actionBuilder(new Pose2d(-10, -55, Math.toRadians(90)))
               /*.lineToY(-30)
               .turn(Math.toRadians(90))
               .lineToX(-50)
               .turn(Math.toRadians(90))
               .lineToY(-55)
                .splineTo(new Vector2d(-53,-24),0)
                .turn(Math.toRadians(-90))
                .lineToY(-55)
                .splineTo(new Vector2d(-58,-24),0)
                .turn(Math.toRadians(-90))
                .lineToY(-55)
                .splineTo(new Vector2d(-63,-24),0)
                .turn(Math.toRadians(-90))
                .lineToY(-55)
                .turn(Math.toRadians(180))
                .splineTo(new Vector2d(-22, -12),0)*/
              /*  .lineToY(-31.5)
                .strafeTo(new Vector2d(-55,-31.5))
                .setTangent(Math.toRadians(90) - 1e-6)
                .lineToY(-10)
                .turn(Math.toRadians(-90))
                .lineToX(-35)
                .build());*/
      /*  myRedLeftBot.runAction(myRedLeftBot.getDrive().actionBuilder(new Pose2d(-55, -55, Math.toRadians(0)))
                .lineToX(-60)
               // .turn(Math.toRadians(-90))
               // .lineToX(-356
                .build()); */


        RoadRunnerBotEntity myRedRightBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();



        myRedRightBot.runAction(myRedRightBot.getDrive().actionBuilder(new Pose2d(4, -64, Math.toRadians(90)))
                /*.lineToY(-30)
                .turn(Math.toRadians(-90))
                .lineToX(50)
                .turn(Math.toRadians(-90))
                .lineToY(-55) */
               /* .lineToY(-31.5)
                .strafeTo(new Vector2d(55,-31.5))
                .setTangent(Math.toRadians(90) - 1e-6)
                .lineToY(-50)
                .build());*/
                .lineToY(-37)
               /* .strafeTo(new Vector2d(45,-37))
                .setTangent(Math.toRadians(90) - 1e-6)
                .lineToY(-8)
                .strafeTo(new Vector2d(59, -8))
                .setTangent(Math.toRadians(90) - 1e-6)
                .lineToY(-50)
                .turn(Math.toRadians(180))
                .lineToY(-59)
                .strafeToLinearHeading(new Vector2d(15, -31.5), Math.toRadians(180))*/

                .build());

        // Declare out second bot
       /* RoadRunnerBotEntity myBlueLeftBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();



        myBlueLeftBot.runAction(myBlueLeftBot.getDrive().actionBuilder(new Pose2d(10, 55, Math.toRadians(-90)))
                .lineToY(30)
                .turn(Math.toRadians(90))
                .lineToX(50)
                .turn(Math.toRadians(90))
                .lineToY(55)
                .build());

        // Declare out second bot
        RoadRunnerBotEntity myBlueRightBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();



       /* myBlueRightBot.runAction(myBlueRightBot.getDrive().actionBuilder(new Pose2d(10, 55, Math.toRadians(-90)))
                .lineToY(30)
                .turn(Math.toRadians(90))
                .lineToX(50)
                .turn(Math.toRadians(90))
                .lineToY(55)
                .build());*/


      /*  meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myRedLeftBot)
              //  .addEntity(myRedRightBot)
               // .addEntity(myBlueLeftBot)
              //  .addEntity(myBlueRightBot)
                .start();*/
    }
}