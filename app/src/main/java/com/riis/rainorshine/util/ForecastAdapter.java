package com.riis.rainorshine.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.riis.rainorshine.R;

import java.util.ArrayList;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>
{
    private ArrayList<String> mList = new ArrayList<>();
    private String[] mTempList = {"Today - Sunny - 88 / 63", "Tomorrow - Foggy - 70 / 46",
                    "Weds - Cloudy - 72 / 63", "Thurs - Rainy - 64 / 51",
                    "Fri - Foggy - 70 / 46", "Saturday - Sunny - 76 / 68"};

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_forecast, parent, false);
        return new ForecastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position)
    {
        holder.forecastLabel.setText(mTempList[position]);
    }

    @Override
    public int getItemCount()
    {
        return mTempList.length;
    }

    final class ForecastViewHolder extends RecyclerView.ViewHolder
    {
        TextView forecastLabel;

        public ForecastViewHolder(View itemView) {
            super(itemView);

            forecastLabel = (TextView) itemView.findViewById(R.id.list_item_forecast_textview);
        }
    }
}
