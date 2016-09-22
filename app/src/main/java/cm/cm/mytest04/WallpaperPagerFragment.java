package cm.cm.mytest04;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * @author shidefeng
 * @since 2016/6/30
 */
public class WallpaperPagerFragment extends Fragment {

    private Wallpaper mWallpaper;

    public WallpaperPagerFragment(){}

    public WallpaperPagerFragment(Wallpaper wallpaper) {
        this();
        mWallpaper = wallpaper;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_item, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ParallaxScrollView scrollView = (ParallaxScrollView) view.findViewById(R.id.scrollView);
        final ImageView background = (ImageView) view.findViewById(R.id.imageView);
        final ImageView portrait = (ImageView) view.findViewById(R.id.user_portrait);
        final TextView name = (TextView) view.findViewById(R.id.user_name);
        final View content = view.findViewById(R.id.content);

//        background.setImageResource(mWallpaper.sourceId);
        setImage(background);
        portrait.setImageResource(mWallpaper.portrait);
        name.setText(mWallpaper.name);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                final int sh = scrollView.getMeasuredHeight();
                final int th = content.getMeasuredHeight();
                Log.i("forTest", "sh:" + sh + "   th:" + th + "   background_view:");
                final int rh = sh <= 0 ? getScreenVisibleHeight(view.getContext()) : sh;
                scrollView.setBackgroundHeight(rh);
                scrollView.scrollBy(0, th);
            }
        });
    }

    private void setImage(ImageView view) {
        view.getLayoutParams().height = getScreenVisibleHeight(view.getContext());
        view.requestLayout();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i("forTest", "onActivityCreated:" + this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private int getScreenVisibleHeight(Context ctx) {
        return ctx.getResources().getDisplayMetrics().heightPixels - getStatusBarHeight(ctx);
    }

    private static int getStatusBarHeight(Context ctx) {
        int top = 0;
        try {
            final Class<?>  clazz    = Class.forName("com.android.internal.R$dimen");
            final Object    obj      = clazz.newInstance();
            final Field     field    = clazz.getField("status_bar_height");
            final int       height   = Integer.parseInt(field.get(obj).toString());

            top = ctx.getResources().getDimensionPixelSize(height);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return top;
    }
}
