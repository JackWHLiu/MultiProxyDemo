package com.lwh.techproducts.template;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerItemAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<FragmentPagerItem> mItems;
    private SparseArray<Fragment> mFragments = new SparseArray<>();
    private OnInstantiateFragmentListener mOnInstantiateFragmentListener;

    private FragmentPagerItemAdapter(Context context, FragmentManager fragmentManager,
                                     List<FragmentPagerItem> items) {
        super(fragmentManager);
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragments.put(position, fragment);
        if (mOnInstantiateFragmentListener != null) {
            mOnInstantiateFragmentListener.onInstantiate(position, fragment, mItems.get(position).getArgs());
        }
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mItems.get(position).newInstance(mContext);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        mFragments.remove(position);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public String getPageTitle(int position) {
        return mItems.get(position).getPageTitle();
    }

    public void setOnInstantiateFragmentListener(OnInstantiateFragmentListener listener) {
        this.mOnInstantiateFragmentListener = listener;
    }

    public interface OnInstantiateFragmentListener {
        void onInstantiate(int position, Fragment fragment, Bundle args);
    }

    public static class Builder {

        private Context context;
        private FragmentManager fragmentManager;
        private List<FragmentPagerItem> items = new ArrayList<>();

        public Builder(Context context, FragmentManager fragmentManager) {
            this.context = context;
            this.fragmentManager = fragmentManager;
        }

        public Builder add(FragmentPagerItem item) {
            items.add(item);
            return this;
        }

        public Builder add(int resId, Fragment fragment) {
            return add(context.getString(resId), fragment);
        }

        public Builder add(int resId, Class<? extends Fragment> clazz) {
            return add(context.getString(resId), clazz);
        }

        public Builder add(int resId, Class<? extends Fragment> clazz, Bundle args) {
            return add(context.getString(resId), clazz, args);
        }

        public Builder add(String title, Fragment fragment) {
            return add(FragmentPagerItem.create(title, fragment));
        }

        public Builder add(String title, Class<? extends Fragment> clazz) {
            return add(FragmentPagerItem.create(title, clazz));
        }

        public Builder add(String title, Class<? extends Fragment> clazz, Bundle args) {
            return add(FragmentPagerItem.create(title, clazz, args));
        }

        public FragmentPagerItemAdapter build() {
            return new FragmentPagerItemAdapter(context, fragmentManager, items);
        }
    }
}
