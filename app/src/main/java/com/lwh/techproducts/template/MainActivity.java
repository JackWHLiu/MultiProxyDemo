package com.lwh.techproducts.template;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;

import com.lwh.techproducts.template.bean.City;
import com.lwh.techproducts.template.compat.module.CityModule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tl_weather;
    ViewPager vp_weather;
    ImageView iv_city;
    private CityModule mCityModule;
    private WeatherFragment mPageOne;
    private WeatherFragment mPageTwo;
    private WeatherFragment mPageThree;
    private List<WeatherFragment> mPages;
    private FragmentPagerItemAdapter mAdapter;
    private FragmentManager mFragmentManager;
    private float mLastX;
    private int mTouchSlop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mPages = new ArrayList<>();
        mPageOne = new WeatherFragment();
        mPageTwo = new WeatherFragment();
        mPageThree = new WeatherFragment();
        mPages.add(mPageOne);
        mPages.add(mPageTwo);
        mPages.add(mPageThree);
        tl_weather = findViewById(R.id.tl_weather);
        vp_weather = findViewById(R.id.vp_weather);
        iv_city = findViewById(R.id.iv_city);
        mFragmentManager = getSupportFragmentManager();
        tl_weather.setupWithViewPager(vp_weather);
        mCityModule = new CityModule();
        final City[] cities = mCityModule.getDecorator().getCities();
        if (cities.length == 3) {
            for (int i = 0; i < mPages.size(); i++) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("city", cities[i]);
                mPages.get(i).setArguments(bundle);
            }
            mAdapter = new FragmentPagerItemAdapter.Builder(this, mFragmentManager)
                    .add(cities[0].getName(), mPageOne)
                    .add(cities[1].getName(), mPageTwo)
                    .add(cities[2].getName(), mPageThree)
                    .build();
            vp_weather.setAdapter(mAdapter);
            vp_weather.setOffscreenPageLimit(2);//缓存周边2页的数据，2*2+1=5
            iv_city.setBackgroundResource(cities[0].getSceneResId());
            tl_weather.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();
                    vp_weather.setCurrentItem(position);
                    iv_city.setBackgroundResource(cities[position].getSceneResId());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            vp_weather.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            //解决水平和垂直滑动冲突，轻微的左右滑动忽略
                            float x = event.getX();
                            if (Math.abs(x - mLastX) < mTouchSlop * 2) {
                                return true;
                            }
                            mLastX = x;
                            break;
                    }
                    return false;
                }
            });
//            for (int i = 0; i < tl_weather.getTabCount(); i++) {
//                TabLayout.Tab tab = tl_weather.getTabAt(i);
//                if (tab == null) return;
//                //这里使用到反射，拿到Tab对象后获取Class
//                Class c = tab.getClass();
//                try {
//                    //Filed “字段、属性”的意思,c.getDeclaredField 获取私有属性。
//                    //"mView"是Tab的私有属性名称(可查看TabLayout源码),类型是 TabView,TabLayout私有内部类。
//                    Field field = c.getDeclaredField("mView");
//                    //值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
//                    //如果不这样会报如下错误
//                    // java.lang.IllegalAccessException:
//                    //Class com.test.accessible.Main
//                    //can not access
//                    //a member of class com.test.accessible.AccessibleTest
//                    //with modifiers "private"
//                    field.setAccessible(true);
//                    final View view = (View) field.get(tab);
//                    if (view == null) return;
//                    view.setTag(i);
//                    view.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            int position = (int) view.getTag();
//                            vp_weather.setCurrentItem(position);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
