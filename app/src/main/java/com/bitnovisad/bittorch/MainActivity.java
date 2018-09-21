package com.bitnovisad.bittorch;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView btn;
    private ImageView bulb;
    private boolean isOn = false;
    private CameraManager manager;
    private String cameraId;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of button
        btn = (ImageView) findViewById(R.id.btnImageView);

        //initialication of lightbulb
        bulb = (ImageView) findViewById(R.id.imageViewBulb);

        //initialization of camera manager
        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        //initialization of media player for playing sounds
        mp = MediaPlayer.create(this, R.raw.btnclick);

        try {
            cameraId = manager.getCameraIdList()[0];
            turnOnFlashlight();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    //method for setting button images on/off
    public void flash(View view) {
        if (!isOn) {
            btn.setImageResource(R.drawable.btnon);
            bulb.setImageResource(R.drawable.lightbulb_on);
            isOn = true;
            turnOnFlashlight();
        } else {
            btn.setImageResource(R.drawable.btnoff);
            bulb.setImageResource(R.drawable.lightbulb_off);
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
