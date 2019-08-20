package com.lwh.techproducts.template;

import android.content.Context;

import com.lwh.techproducts.template.bean.Forecast;

import cn.jackwhliu.rvadapter.lib.BaseRVAdapter;

public class ForecastAdapter extends BaseRVAdapter<Forecast> {

    public ForecastAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(BaseRVAdapter.ViewHolder holder, int position, Forecast forecast) {
        holder.setText(R.id.tv_2, forecast.getDate());
        holder.setText(R.id.tv_3, forecast.getType());
        holder.setText(R.id.tv_4, forecast.getLow());
        holder.setText(R.id.tv_5, forecast.getHigh());
        holder.setText(R.id.tv_6, "风向 " + forecast.getFengxiang());
        holder.setText(R.id.tv_7, "风力 " + forecast.getFengli());
    }

    @Override
    protected int[] getItemViewIds() {
        return new int[]{
                R.id.tv_2,
                R.id.tv_3,
                R.id.tv_4,
                R.id.tv_5,
                R.id.tv_6,
                R.id.tv_7
        };
    }

    @Override
    protected int getItemId() {
        return R.layout.list_item;
    }
}
