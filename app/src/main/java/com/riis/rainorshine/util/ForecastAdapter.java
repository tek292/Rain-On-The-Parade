package com.riis.rainorshine.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.riis.rainorshine.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>
{
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_forecast, parent, false);
        return new ForecastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position)
    {
        holder.forecastLabel.setText(mList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    public void refresh(String[] weatherData)
    {
        mList.clear();
        mList.addAll(Arrays.asList(weatherData));
        notifyDataSetChanged();
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
