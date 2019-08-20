package com.lwh.techproducts.template.compat.wrapper;

import com.lwh.techproducts.template.bean.City;
import com.lwh.techproducts.template.compat.interfaces.CityDiff;

public class CityDiffWrapper implements CityDiff {

    protected CityDiff mBase;

    public CityDiffWrapper(CityDiff base) {
        this.mBase = base;
    }

    @Override
    public City[] getCities() {
        throw new UnsupportedOperationException("必须在装饰器中实现此方法");
    }

    @Override
    public CityDiff getDecorator() {
        return this;
    }
}
