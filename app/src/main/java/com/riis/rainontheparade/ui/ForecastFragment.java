package com.riis.rainontheparade.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.riis.rainontheparade.R;
import com.riis.rainontheparade.data.FetchWeatherTask;
import com.riis.rainontheparade.util.ForecastAdapter;

public class ForecastFragment extends Fragment
{
    private ForecastAdapter mForecastAdapter;
    private String mLocation;
    private String mUnits;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mLocation = prefs.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));
        mUnits = prefs.getString(getString(R.string.pref_units_key),
                getString(R.string.pref_units_metric));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listview_forecast);
        mForecastAdapter = new ForecastAdapter(getActivity());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mForecastAdapter);

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        new FetchWeatherTask(getActivity(), mForecastAdapter).execute(mLocation, mUnits);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.forcast_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_refresh) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            mLocation = prefs.getString(getString(R.string.pref_location_key),
                    getString(R.string.pref_location_default));
            new FetchWeatherTask(getActivity(), mForecastAdapter).execute(mLocation, mUnits);
        }

        return super.onOptionsItemSelected(item);
    }
}
