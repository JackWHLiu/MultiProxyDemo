package com.lwh.techproducts.template.compat.impl;

import com.lwh.techproducts.template.R;
import com.lwh.techproducts.template.bean.City;
import com.lwh.techproducts.template.compat.interfaces.CityDiff;
import com.lwh.techproducts.template.compat.wrapper.CityDiffWrapper;

public class CityBBB extends CityDiffWrapper {

    public CityBBB(CityDiff base) {
        super(base);
    }

    @Override
    public City[] getCities() {
        return new City[]{new City("深圳", R.drawable.shenzhen),
                new City("杭州", R.drawable.hangzhou),
                new City("珠海", R.drawable.zhuhai)};
    }

    @Override
    public CityDiff getDecorator() {
        return this;
    }
}
