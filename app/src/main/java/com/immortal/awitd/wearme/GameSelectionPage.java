package com.immortal.awitd.wearme;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AWITD on 8/9/2016.
 */
public class GameSelectionPage extends Activity {

    ImageButton imgbtn_boy, imgbtn_girl;
    Button btn_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // to close action bar and block screen lock
        requestWindowFeature(1);

        overridePendingTransition(R.anim.right_in, R.anim.left_out);

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.activity_game_selection_page_tablet);
        } else {
            setContentView(R.layout.activity_game_selection_page);
        }

        TextView tvAseanDress = (TextView)findViewById(R.id.textView_asean_st);
        Typeface fontsel = Typeface.createFromAsset(getAssets(),"fonts/remachine.ttf");
        tvAseanDress.setTypeface(fontsel);
        tvAseanDress.setShadowLayer(1.5f, -1, 1, Color.WHITE);

        imgbtn_boy = (ImageButton) findViewById(R.id.imageButton_boy);
        imgbtn_girl = (ImageButton) findViewById(R.id.imageButton_girl);
        btn_back = (Button) findViewById(R.id.button_back_from_select_page);

        imgbtn_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectionPage.this, GameBoardForBoy.class));
            }
        });

        imgbtn_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectionPage.this, GameBoardForGirl.class));
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //The following codes are for custom toast message...
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup) findViewById(R.id.custom_toast_layout));
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 20, 0);

        Toast toast1 = new Toast(this);
        toast1.setView(view);
        toast1.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 30);
    }

    @Override
    public void onBackPressed() {

    }
}
