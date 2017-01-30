package com.immortal.awitd.wearme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import Adapter.BoyClothesImageAdapter;
import ImageTransformer.DefaultTransformer;

/**
 * Created by AWITD on 7/14/2016.
 */
public class GameBoardForBoy extends Activity {

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

    int boyWithDressing[] = {R.drawable.myanmar_boy,
            R.drawable.thailand_boy,
            R.drawable.singapore_boy,
            R.drawable.indonesia_boy,
            R.drawable.malaysian_boy,
            R.drawable.brunei_boy,
            R.drawable.cambodian_boy,
            R.drawable.laos_boy,
            R.drawable.philippines_boy,
            R.drawable.vietnam_boy};

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
    Bitmap bmScreen;
    View screen;
    Dialog screenDialog;
    static final int ID_SCREENDIALOG=1;
    ImageView bmimage;
    Button btnScreenDialog_OK;
    TextView TextOut;
    EditText EditTextIn;
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
        //overridePendingTransition(R.anim.right_in, R.anim.left_out);


        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.activity_game_board);
        } else {
            setContentView(R.layout.activity_game_board_normal_phone);
        }
        imgView_country_flag = (ImageView) findViewById(R.id.imageView_country_flag_in_gameboard);
        textView_country_name = (TextView) findViewById(R.id.textView_country_name_in_gameboard);

        randomGenerate();

        screen =(View)findViewById(R.id.score_screen);
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
                    showBoyDialog(boyWithDressing[0], "Myanmar");
                    randomGenerate();
                } else if (image_position == 1 && country_name_result.equals("Thailand")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[1], "Thailand");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Thialand", Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 2 && country_name_result.equals("Singapore")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[2], "Singapore");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Singapore" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 3 && country_name_result.equals("Indonesia")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[3], "Indonesia");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Indonesia" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 4 && country_name_result.equals("Malaysia")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[4], "Malaysia");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Malaysia" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 5 && country_name_result.equals("Brunei    ")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[5], "Brunei Darulssan");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Brunei    " + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 6 && country_name_result.equals("Cambodia")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[6], "Cambodia");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Cambodia" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 7 && country_name_result.equals("Laos      ")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[7], "Laos");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Laos      " + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 8 && country_name_result.equals("Philippines")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[8], "Philippines");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Philippines" + win_count, Toast.LENGTH_SHORT).show();
                    randomGenerate();
                } else if (image_position == 9 && country_name_result.equals("Vietnam")) {
                    win_count++;
                    correctMsg();
                    showStar();
                    showBoyDialog(boyWithDressing[9], "Vietnam");
                    Toast.makeText(GameBoardForBoy.this, "Correct_this_is Vietnam" + win_count, Toast.LENGTH_SHORT).show();
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
        BoyClothesImageAdapter adapterView = new BoyClothesImageAdapter(this);
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

    private void showWrongDialog() {
        final Dialog wrong_dialog = new Dialog(this, R.style.Theme_CustomDialog);

            /*The following line is for the dialig dismiss when onTouch outside..
            //dialog.setCanceledOnTouchOutside(true);
            */
        wrong_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wrong_dialog.setContentView(R.layout.wrong_result_dialog);
        //overridePendingTransition(R.anim.show_from_bottom,R.anim.hide_to_buttom);
        Button btnOkId = (Button) wrong_dialog.findViewById(R.id.button_retry);

        btnOkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Music.stop(getApplicationContext());
                wrong_dialog.dismiss();
            }
        });


        wrong_dialog.show();
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
//        Button btnCapture = (Button)score_dialog.findViewById(R.id.button_take_capture);

        TextView tvScores = (TextView) score_dialog.findViewById(R.id.textView_scores);
        tvScores.setText(click_count + " Click - " + win_count + " Correct! ");

//        btnCapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//////                screen.setDrawingCacheEnabled(false);
//////                screen.setDrawingCacheEnabled(true);
//////                bmScreen = screen.getDrawingCache();
////////                showDialog(ID_SCREENDIALOG);
////                takeScreenShot();
//            }
//        });
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

//    private void takeScreenShot(){
//        Date now = new Date();
//        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss",now);
//        try {
//            String mPath=Environment.getExternalStorageDirectory().toString()+"/"+now+".jpg";
//
//            View v1 = getWindow().getDecorView().getRootView();
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            v1.setDrawingCacheEnabled(true);
//
//            File imageFile = new File(mPath);
//
//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,outputStream);
//            outputStream.flush();
//            outputStream.close();
//
//            openScreenShot(imageFile);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void openScreenShot(File imageFile) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(imageFile);
//        intent.setDataAndType(uri,"image/*");
//        startActivity(intent);
//    }

    private void showBoyDialog(int drawable, String countryName) {
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
