package com.lwh.techproducts.template.compat.impl;

import com.lwh.techproducts.template.R;
import com.lwh.techproducts.template.bean.City;
import com.lwh.techproducts.template.compat.interfaces.CityDiff;
import com.lwh.techproducts.template.compat.wrapper.CityDiffWrapper;

public class CityAAA extends CityDiffWrapper {

    public CityAAA(CityDiff base) {
        super(base);
    }

    @Override
    public City[] getCities() {
        return new City[]{
                new City("北京", R.drawable.beijing),
                new City("上海", R.drawable.shanghai),
                new City("广州", R.drawable.guangzhou)
        };
    }

    @Override
    public CityDiff getDecorator() {
        return this;
    }
}
