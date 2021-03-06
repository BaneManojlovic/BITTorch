package com.bitnovisad.bittorch;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.testfairy.TestFairy;

public class MainActivity extends AppCompatActivity {

    private ImageView btn;
    private ImageView bulb;
    private boolean isOn = true;
    private CameraManager manager;
    private String cameraId;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TestFairy.begin(this, "b3b1857dfc1986ee54e72d157f1d42bae2b2e981");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of image button
        btn = findViewById(R.id.btnImageView);

        //initialication of lightbulb image
        bulb = findViewById(R.id.imageViewBulb);

        //initialization of camera manager
        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        //initialization of media player for playing sounds
        mp = MediaPlayer.create(this, R.raw.btnclick);

        //initally set torch in ON mode
        try {
            cameraId = manager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mp.start();
                manager.setTorchMode(cameraId, true);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    //method for setting button images on/off and calling turn On/Off methods
    public void flash(View view) {
        if (!isOn) {
            btn.setImageResource(R.drawable.btnon);
            bulb.setImageResource(R.drawable.arton);
            isOn = true;
            turnOnFlashlight();
        } else {
            btn.setImageResource(R.drawable.btnoff);
            bulb.setImageResource(R.drawable.artoff);
            isOn = false;
            turnOffFlashlight();
        }
    }

    //method for turning on
    public void turnOnFlashlight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                mp.start();
                manager.setTorchMode(cameraId, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //method for turning off
    public void turnOffFlashlight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                mp.start();
                manager.setTorchMode(cameraId, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //override method for app exit on back pressed
    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
