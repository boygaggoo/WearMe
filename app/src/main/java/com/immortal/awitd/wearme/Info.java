package com.immortal.awitd.wearme;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Adapter.ImageAdapter;
import ImageTransformer.DepthPageTransformer;

/**
 * Created by AWITD on 7/14/2016.
 */
public class Info extends Activity {

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

    String countryNames[] = {"Myanmar",
            "Thailand", "Singapore",
            "Indonesia", "Malaysia",
            "Brunei    ", "Cambodia",
            "Laos      ", "Philippines",
            "Vietnam"};

    @Override
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
            setContentView(R.layout.activity_info_tablet);
        } else {
            setContentView(R.layout.activity_info_tablet);
        }

        //This button is used for onFinish(); method now, may change in future...
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TextView tvCountryNames = (TextView) findViewById(R.id.textView_country_name);

                Animation animation_text, animation_image;
                animation_text = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.right_in);
                animation_image = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.scale_up);

                ImageView imgViewCountryFlag = (ImageView) findViewById(R.id.imageView_country_flag);
                imgViewCountryFlag.setImageResource(countryFlags[position]);
                imgViewCountryFlag.startAnimation(animation_image);

                tvCountryNames.startAnimation(animation_text);
                tvCountryNames.setText(countryNames[position] + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Music.play(this, R.raw.theme_sound);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (MainActivity.check == 1) {
            Music.play(getApplicationContext(), R.raw.theme_sound);
        } else {
            Music.stop(getApplicationContext());
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Music.stop(getApplicationContext());
    }

}
