package cm.cm.mytest04;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new WallpaperPagerNewAdapter(getSupportFragmentManager(), getWallpaperList()));

    }

    private List<Wallpaper> getWallpaperList() {
        List<Wallpaper> wallpapers = new ArrayList<>();
        wallpapers.add(new Wallpaper(1, R.drawable.wallpaper_1, R.drawable.portrait_1, "这是1"));
        wallpapers.add(new Wallpaper(2, R.drawable.wallpaper_2, R.drawable.portrait_2, "这是2"));
        wallpapers.add(new Wallpaper(3, R.drawable.wallpaper_3, R.drawable.portrait_3, "这是3"));
        wallpapers.add(new Wallpaper(4, R.drawable.wallpaper_4, R.drawable.portrait_4, "这是4"));
        return wallpapers;
    }

    private static int dp2px(Context ctx, int dip) {
        return (int) (ctx.getResources().getDisplayMetrics().density * dip + 0.5f);
    }

}
