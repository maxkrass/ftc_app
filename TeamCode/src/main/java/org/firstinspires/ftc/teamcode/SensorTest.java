package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

/**
 * Test Opmode for a color and light sensor
 * Created by Marcel Alexander Wagner on 9/22/16.
 */

@TeleOp (name = "Sensortest", group = "Concept")
@SuppressWarnings("unused")
public class SensorTest extends OpMode {

    private ColorSensor cm=null;
    private LightSensor lm=null;
    private String errorm="";

    @Override
    public void init() {
        try{
            cm=hardwareMap.colorSensor.get("Colorsensor");
        }catch(Exception e){
            errorm+="No Colorsensor;"+e.getMessage();
        }


        try{
            lm=hardwareMap.lightSensor.get("Lightsensor");
            lm.enableLed(true);
        }catch(Exception e){
            errorm+="No Lightsensor;"+e.getMessage();
        }
        telemetry.addData("",errorm);

    }

    @Override
    public void loop() {
        telemetry.update();
        if(cm!=null){
            telemetry.addData("Red:",cm.red());
            telemetry.addData("Green:",cm.green());
            telemetry.addData("Blue:",cm.blue());

        }
        if(lm!=null){

            telemetry.addData("Raw", lm.getRawLightDetected());
            telemetry.addData("Normal", lm.getLightDetected());
        }

    }
}
