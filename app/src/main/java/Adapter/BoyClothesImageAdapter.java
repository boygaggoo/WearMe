package Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.immortal.awitd.wearme.R;

/**
 * Created by AWITD on 8/22/2016.
 */
public class BoyClothesImageAdapter extends PagerAdapter {
    Context mboyContext;

    public BoyClothesImageAdapter(Context context) {
        this.mboyContext = context;
    }

    @Override
    public int getCount() {
        return boySliderImagesId.length;
    }

    private int[] boySliderImagesId = new int[]{
            R.drawable.myanmar_boy_dressing,
            R.drawable.thailand_boy_dressing,
            R.drawable.singapore_boy_dressing,
            R.drawable.indonesia_boy_dressing,
            R.drawable.malaysian_boy_dressing,
            R.drawable.brunei_boy_dressing,
            R.drawable.cambodian_boy_dressing,
            R.drawable.laos_boy_dressing,
            R.drawable.phillipine_boy_dressing,
            R.drawable.vietnam_boy_dressing
    };

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mboyContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mImageView.setImageResource(boySliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}
