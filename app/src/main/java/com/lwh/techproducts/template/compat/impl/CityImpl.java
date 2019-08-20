package com.lwh.techproducts.template.compat.impl;

import com.lwh.jackknife.multiproxy.annotation.Wrapper;
import com.lwh.techproducts.template.BuildConfig;
import com.lwh.techproducts.template.R;
import com.lwh.techproducts.template.bean.City;
import com.lwh.techproducts.template.compat.annotation.DDD;
import com.lwh.techproducts.template.compat.annotation.EEE;
import com.lwh.techproducts.template.compat.interfaces.CityDiff;
import com.lwh.techproducts.template.compat.wrapper.CityDiffWrapper;

@DDD
@EEE
@Wrapper(proxyName = BuildConfig.PROXY_CODE)
public class CityImpl extends CityDiffWrapper implements CityDiff {

    public CityImpl(CityDiff base) {
        super(base);
    }

    @Override
    public City[] getCities() {
        return new City[]{
                new City("香港", R.drawable.xianggang),
                new City("澳门", R.drawable.aomen),
                new City("台湾", R.drawable.taiwan)
        };
    }

    @Override
    public CityDiff getDecorator() {
        return this;
    }
}
