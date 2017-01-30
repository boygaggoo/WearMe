package Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.immortal.awitd.wearme.R;

/**
 * Created by AWITD on 8/25/2016.
 */
public class GirlClothesImageAdapter extends PagerAdapter {
    Context mGirlContext;

    public GirlClothesImageAdapter(Context context) {
        this.mGirlContext = context;
    }

    @Override
    public int getCount() {
        return girlSliderImagesId.length;
    }

    private int[] girlSliderImagesId = new int[]{
            R.drawable.myanmar_girl_dressing,
            R.drawable.thailand_girl_dressing,
            R.drawable.singapore_girl_dressing,
            R.drawable.indonesian_girl_dressing,
            R.drawable.malaysian_girl_dressing,
            R.drawable.brunei_girl_dressing,
            R.drawable.cambodian_girl_dressing,
            R.drawable.loas_girl_dressing,
            R.drawable.phillipines_girl_dressing,
            R.drawable.vietnam_girl_dressing
    };

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mGirlContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mImageView.setImageResource(girlSliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}
