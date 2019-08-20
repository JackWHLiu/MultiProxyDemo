package com.lwh.techproducts.template.compat.interfaces;

import com.lwh.jackknife.multiproxy.annotation.DifferenceInterface;
import com.lwh.jackknife.multiproxy.interfaces.IDifference;
import com.lwh.techproducts.template.bean.City;

@DifferenceInterface(
        packageName = "com.lwh.techproducts.template.compat.impl",
        moduleName = "City"
)
public interface CityDiff extends IDifference<CityDiff> {

    /**
     * 获取城市的差异。
     *
     * @return 用于tab显示的三个城市的名称
     */
    City[] getCities();
}
