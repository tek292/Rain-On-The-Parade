package com.riis.rainontheparade.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.riis.rainontheparade.R;
import com.riis.rainontheparade.ui.DetailActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>
{
    private ArrayList<String> mList = new ArrayList<>();
    private Context mContext;

    public ForecastAdapter(Context context)
    {
        mContext = context;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_forecast, parent, false);
        return new ForecastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, final int position)
    {
        holder.forecastLabel.setText(mList.get(position));
        holder.forecastRow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mContext.startActivity(new Intent(mContext, DetailActivity.class).putExtra(Intent.EXTRA_TEXT, mList.get(position)));
            }
        });
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
        View forecastRow;

        public ForecastViewHolder(View itemView) {
            super(itemView);

            forecastRow = itemView;
            forecastLabel = (TextView) itemView.findViewById(R.id.list_item_forecast_textview);
        }
    }
}
