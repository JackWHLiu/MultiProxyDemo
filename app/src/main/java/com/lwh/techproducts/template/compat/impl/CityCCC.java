package com.lwh.techproducts.template.compat.impl;

import com.lwh.techproducts.template.R;
import com.lwh.techproducts.template.bean.City;
import com.lwh.techproducts.template.compat.interfaces.CityDiff;
import com.lwh.techproducts.template.compat.wrapper.CityDiffWrapper;

public class CityCCC extends CityDiffWrapper {

    public CityCCC(CityDiff base) {
        super(base);
    }

    @Override
    public City[] getCities() {
        return new City[]{
                new City("苏州", R.drawable.suzhou),
                new City("厦门", R.drawable.xiamen),
                new City("宁波", R.drawable.ningbo)
        };
    }

    @Override
    public CityDiff getDecorator() {
        return this;
    }
}
