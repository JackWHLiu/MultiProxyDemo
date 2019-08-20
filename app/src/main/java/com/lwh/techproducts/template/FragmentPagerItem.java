package com.lwh.techproducts.template;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class FragmentPagerItem {

    private String title;
    private Fragment fragment;
    private Class<? extends Fragment> clazz;
    private Bundle args;

    protected FragmentPagerItem(String title, Fragment fragment) {
        this(title, fragment.getClass(), fragment.getArguments());
        this.fragment = fragment;
    }

    protected FragmentPagerItem(String title, Class<? extends Fragment> clazz, Bundle args) {
        this.title = title;
        this.clazz = clazz;
        this.args = args;
    }

    public static FragmentPagerItem create(String title, Fragment fragment) {
        return new FragmentPagerItem(title, fragment);
    }

    public static FragmentPagerItem create(String title, Class<? extends Fragment> clazz) {
        return create(title, clazz, null);
    }

    public static FragmentPagerItem create(String title, Class<? extends Fragment> clazz, Bundle args) {
        return new FragmentPagerItem(title, clazz, args);
    }

    public String getPageTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public Fragment newInstance(Context context) {
        return Fragment.instantiate(context, clazz.getName(), args);
    }

    public Bundle getArgs() {
        return args;
    }
}
