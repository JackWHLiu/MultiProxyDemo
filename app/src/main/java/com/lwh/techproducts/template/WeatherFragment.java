package com.lwh.techproducts.template;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lwh.techproducts.template.bean.City;
import com.lwh.techproducts.template.bean.Data;
import com.lwh.techproducts.template.bean.Forecast;
import com.lwh.techproducts.template.bean.JsonRootBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WeatherFragment extends Fragment {

    private City mCity;
    private final String URL_WEATHER = "https://www.apiopen.top/weatherApi?city=";
    private ForecastAdapter mAdapter;
    RecyclerView rv_1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCity = (City) bundle.getSerializable("city");
        mAdapter = new ForecastAdapter(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        rv_1 = view.findViewById(R.id.rv_1);
        rv_1.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv_1.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        rv_1.setAdapter(mAdapter);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL_WEATHER + mCity.getName())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("response", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                assert body != null;
                String json = body.string();
                Log.d("json", json);
                Gson gson = new Gson();
                JsonRootBean jsonRootBean = gson.fromJson(json, JsonRootBean.class);
                int code = jsonRootBean.getCode();
                if (code == 200) {
                    Data data = jsonRootBean.getData();
                    final List<Forecast> forecasts = data.getForecast();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.addItems(forecasts);
                            mAdapter.addItems(forecasts);
                        }
                    });
                }
            }
        });
    }
}
