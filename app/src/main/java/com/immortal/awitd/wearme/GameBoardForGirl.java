package com.immortal.awitd.wearme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import Adapter.BoyClothesImageAdapter;
import Adapter.GirlClothesImageAdapter;
import ImageTransformer.DefaultTransformer;

/**
 * Created by AWITD on 8/25/2016.
 */
public class GameBoardForGirl extends Activity {

    int countryFlags[] = {R.drawable.myanmar_flag_275_183,
            R.drawable.thailand_flag,
            R.drawable.singapore_flag_275_183,
            R.drawable.indonesia_flag,
            R.drawable.malaysia_flag_275_183,
            R.drawable.brunei_flag_275_183,
            R.drawable.cambodia_flag_275_183,
            R.drawable.laos_flag_275_183,
            R.drawable.philippine_flag_275_183,
            R.drawable.vietnam_flag_275_183};

    int girlWithDressing[] = {R.drawable.myanmar_girl,
            R.drawable.thailand_girl,
            R.drawable.singapore_girl,
            R.drawable.indonesia_girl,
            R.drawable.malaysia_girl,
            R.drawable.brunei_girl,
            R.drawable.cambodiam_girl,
            R.drawable.laos_girl,
            R.drawable.philippines_girl,
            R.drawable.vietnam_girl};


    String countryNames[] = {"Myanmar",
            "Thailand", "Singapore",
            "Indonesia", "Malaysia",
            "Brunei    ", "Cambodia",
            "Laos      ", "Philippines",
            "Vietnam"};
    TextView textView_country_name;
    ImageView imgView_country_flag;
    Button button_correct;
    int n, image_position = 0, win_count = 0, click_count = 0;

    /*
    win_count variable is for the correct count.
    click_count variable is for click count when the check button (button_correct) is clicked.
    initialize values are zero.
     */

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
            setContentView(R.layout.activity_game_board);
        } else {
            setContentView(R.layout.activity_game_board_normal_phone);
        }
        imgView_country_flag = (ImageView) findViewById(R.id.imageView_country_flag_in_gameboard);
        textView_country_name = (TextView) findViewById(R.id.textView_country_name_in_gameboard);

        randomGenerate();

        button_correct = (Button) findViewById(R.id.button_correct);

        button_correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country_name_result = textView_country_name.getText().toString();
                click_count++;

                if (image_position == 0 && country_name_result.equals("Myanmar")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[0], "Myanmar");
                    randomGenerate();
                } else if (image_position == 1 && country_name_result.equals("Thailand")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[1], "Thialand");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Thialand", Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 2 && country_name_result.equals("Singapore")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[2], "Singapore");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Singapore" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 3 && country_name_result.equals("Indonesia")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[3], "Indonesia");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Indonesia" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 4 && country_name_result.equals("Malaysia")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[4], "Malaysia");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Malaysia" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 5 && country_name_result.equals("Brunei    ")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[5], "Brunei");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Brunei    " + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 6 && country_name_result.equals("Cambodia")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[6], "Cambodia");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Cambodia" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 7 && country_name_result.equals("Laos      ")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[7], "Laos");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Laos      " + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 8 && country_name_result.equals("Philippines")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[8], "Philippines");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Philippines" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 9 && country_name_result.equals("Vietnam")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showGirlDialog(girlWithDressing[9], "Vietnam");
                    Toast.makeText(GameBoardForGirl.this, "Correct_this_is Vietnam" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else {
                    doVibrate();
                }
            }
        });

        /*
        This is for choosing clothes by sliding : using view pagger.
         */
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPagerBoyAndroid);
        GirlClothesImageAdapter adapterView = new GirlClothesImageAdapter(this);
        mViewPager.setAdapter(adapterView);
        mViewPager.setPageTransformer(true, new DefaultTransformer());

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                image_position = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void showGirlDialog(int drawable, String countryName) {
        final Dialog boy_dialog = new Dialog(this, R.style.Theme_CustomDialog);
        boy_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        boy_dialog.setContentView(R.layout.correct_result_dialog);

        Button btn_ok = (Button) boy_dialog.findViewById(R.id.button_correct_ok);
        TextView tv_country_name = (TextView) boy_dialog.findViewById(R.id.textView_c_name);
        ImageView correct_img = (ImageView) boy_dialog.findViewById(R.id.imageView_correct_img);
        correct_img.setImageResource(drawable);
        tv_country_name.setText(countryName);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boy_dialog.dismiss();
            }
        });
        boy_dialog.show();

    }

    public void randomGenerate() {
           /*
    This randomGenerate() method is created for country flags and country names
    thar were at the left top angle.
    At the activity start, this method will be started.
    And then, if the Check-Button is clicked, this method will also started.
     */
        Random r = new Random();
        n = r.nextInt(10);
        Animation animation_text, animation_image;
        animation_text = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.right_in);
        animation_image = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.scale_up);
        imgView_country_flag.setImageResource(countryFlags[n]);
        imgView_country_flag.startAnimation(animation_image);
        textView_country_name.setText(countryNames[n]);
        textView_country_name.startAnimation(animation_text);
        //Toast.makeText(GameBoardForBoy.this, "Country Point "+countryFlags[n], Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {

        showEndScoreDialog();

    }


    private void showEndScoreDialog() {
          /*This method is for showing the click button count and win-scores.
            This method will be called when the device back button is clicked.
            */

        final Dialog score_dialog = new Dialog(this, R.style.Theme_CustomDialog);

            /*The following line is for the dialig dismiss when onTouch outside..
            //dialog.setCanceledOnTouchOutside(true);
            */
        score_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        score_dialog.setContentView(R.layout.end_score_dialog);
        //overridePendingTransition(R.anim.show_from_bottom,R.anim.hide_to_buttom);
        Button btnOkId = (Button) score_dialog.findViewById(R.id.button_home_score_dialog);
        Button btnCancelId = (Button) score_dialog.findViewById(R.id.button_continue_score_dialog);

        TextView tvScores = (TextView) score_dialog.findViewById(R.id.textView_scores);
        tvScores.setText(click_count + " Clicks - " + win_count + " Correct! ");

        btnOkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Music.stop(getApplicationContext());
                score_dialog.dismiss();
                finish();
            }
        });

        btnCancelId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score_dialog.dismiss();
            }
        });

        score_dialog.show();
    }
    private void showStar() {
        //The following codes are for custom toast message...
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.star_toast,
                (ViewGroup) findViewById(R.id.star_toast_layout));
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setGravity(Gravity.DISPLAY_CLIP_HORIZONTAL, 20, 0);
        toast.show();
    }

    private void doVibrate(){
        Vibrator v = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }
    private void correctMsg(){
        //The following codes are for custom toast message...
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup) findViewById(R.id.custom_toast_layout));
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 20, 0);
        toast.show();
    }
}
