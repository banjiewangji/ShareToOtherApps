package cm.cm.mytest04;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author shidefeng
 * @since 2016/6/30
 */
public class WallpaperPagerNewAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;
    private List<Wallpaper> mWallpapers;

    public WallpaperPagerNewAdapter(FragmentManager fm, List<Wallpaper> list) {
        super(fm);
        if (list != null && !list.isEmpty()) {
            mWallpapers = list;
            mFragments = new Fragment[mWallpapers.size()];
        }
    }

    @Override
    public Fragment getItem(int position) {

        if (mWallpapers != null && position < mWallpapers.size()) {
            final Wallpaper wallpaper = mWallpapers.get(position);

            if (mFragments != null) {
                if (position < mFragments.length) {
                    Fragment fragment = mFragments[position];
                    if (fragment == null) {
                        fragment = newFragment(wallpaper);
                        mFragments[position] = fragment;
                    }
                    return fragment;
                }
            }
        }
        return null;
    }

    private Fragment newFragment(Wallpaper wallpaper) {
        return new WallpaperPagerFragment(wallpaper);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }


    @Override
    public int getCount() {
        return mWallpapers != null ? mWallpapers.size() : 0;
    }
}
