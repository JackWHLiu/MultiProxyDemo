package com.lwh.techproducts.template.compat.module;

import com.lwh.jackknife.multiproxy.MultiProxy;
import com.lwh.jackknife.multiproxy.annotation.Difference;
import com.lwh.techproducts.template.BuildConfig;
import com.lwh.techproducts.template.bean.City;
import com.lwh.techproducts.template.compat.annotation.AAA;
import com.lwh.techproducts.template.compat.annotation.BBB;
import com.lwh.techproducts.template.compat.annotation.CCC;
import com.lwh.techproducts.template.compat.annotation.DDD;
import com.lwh.techproducts.template.compat.annotation.EEE;
import com.lwh.techproducts.template.compat.interfaces.CityDiff;

/**
 * 在以下代理产品中启用装饰器。
 */
@AAA
@BBB
@CCC
@DDD
@EEE
@Difference(proxyName = BuildConfig.PROXY_CODE)
public class CityModule implements CityDiff {

    @Override
    public City[] getCities() {
        return new City[0];
    }

    @Override
    public CityDiff getDecorator() {
        return MultiProxy.getDecorator(this, CityDiff.class);
    }
}
