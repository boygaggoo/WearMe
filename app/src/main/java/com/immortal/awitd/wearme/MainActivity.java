package com.immortal.awitd.wearme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.tutoshowcase.TutoShowcase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btn_play;
    private MediaPlayer mp_sound;
    private SoundPool soundPool;
    public static Boolean boolSoundOn = true;
    public static int check = 1;
    private int sound;
    public static String URL = "http://www.appszoom.com/android_applications/awitdj";

    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // to close action bar and block screen lock
        requestWindowFeature(1);

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.activity_main_tablet);
        } else {
            setContentView(R.layout.activity_main);
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        final FloatingActionMenu menu1 = (FloatingActionMenu) findViewById(R.id.menu1);
        menu1.hideMenuButton(false);
        menus.add(menu1);
        int delay = 500;

        for (final FloatingActionMenu menu : menus) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    menu.showMenuButton(true);
                }
            }, delay);
            break;
        }

        menu1.setClosedOnTouchOutside(true);
        menu1.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu1.isOpened()) {

                }
                menu1.toggle(true);
            }
        });

        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab2.setOnClickListener(clickListerner);
        fab3.setOnClickListener(clickListerner);

        android.support.design.widget.FloatingActionButton fab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp_sound.start();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
            }
        });

        TextView tvAsean = (TextView)findViewById(R.id.textView_asean);
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/remachine.ttf");
        tvAsean.setTypeface(font);
        tvAsean.setShadowLayer(1.5f, -1, 1, Color.LTGRAY);

        mp_sound = MediaPlayer.create(this, R.raw.pickup_oin);

        btn_play = (Button) findViewById(R.id.button_play);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(getBaseContext(), R.raw.theme_sound, 1);

        btn_play.setOnClickListener(this);

    }

    private View.OnClickListener clickListerner = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab2:
                    fab2.setVisibility(View.VISIBLE);
                    playMusicSound();
                    break;
                case R.id.fab3:
                    fab3.setVisibility(View.VISIBLE);
                    startActivity(new Intent(MainActivity.this, Info.class));
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_play:
                mp_sound.start();
                startActivity(new Intent(MainActivity.this, GameSelectionPage.class));
                break;
            default:
                break;
        }
    }

    private void playMusicSound() {
        if (boolSoundOn) {
            boolSoundOn = false;
            Music.pause(getApplicationContext());
            check = 0;
            fab2.setImageResource(R.drawable.music_icon_close_50_50);
        } else if (boolSoundOn == false) {
            boolSoundOn = true;
            check = 1;
            Music.play(getBaseContext(), R.raw.theme_sound);
           fab2.setBackgroundResource(R.drawable.music_icon_50_50);
        }
    }


    protected void showCustomDialog() {

        final Dialog dialog = new Dialog(this, R.style.Theme_CustomDialog);
        //dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.exit_dialog);
        //overridePendingTransition(R.anim.show_from_bottom,R.anim.hide_to_buttom);
        Button btnOkId = (Button) dialog.findViewById(R.id.btnOKId);
        Button btnCancelId = (Button) dialog.findViewById(R.id.btnCancelId);

        btnOkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Music.stop(getApplicationContext());
                dialog.dismiss();
                System.exit(1);
                finish();
            }
        });


        btnCancelId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // ...

        dialog.show();

    }

    @Override
    public void onBackPressed() {

//        Toast.makeText(getApplicationContext(), "Back press again to exit!", Toast.LENGTH_LONG).show();
//        count++;
//
//        if (count == 2) {
//            super.onBackPressed();
//        }
        showCustomDialog();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if (check == 0) {
            Music.stop(getApplicationContext());
        } else {
            Music.play(getApplicationContext(), R.raw.theme_sound);
        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Music.stop(getApplicationContext());
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (check == 0) {
            Music.stop(getApplicationContext());
        } else {
            Music.play(getApplicationContext(), R.raw.theme_sound);
        }
    }
}
