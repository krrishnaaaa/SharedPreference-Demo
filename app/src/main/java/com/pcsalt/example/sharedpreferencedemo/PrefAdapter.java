package com.pcsalt.example.sharedpreferencedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by krrishnaaaa on Oct 02, 2015
 */
public class PrefAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<PrefData> mPrefDataList;

    public PrefAdapter(Context context, List<PrefData> prefDataList) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mPrefDataList = prefDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_pref, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            PrefData current = mPrefDataList.get(position);
            viewHolder.textViewKey.setText(current.key);
            viewHolder.textViewValue.setText(current.value);
        }
    }

    @Override
    public int getItemCount() {
        if (mPrefDataList != null) {
            return mPrefDataList.size();
        } else {
            return 0;
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewKey, textViewValue;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewKey = (TextView) itemView.findViewById(R.id.text_view_key);
            textViewValue = (TextView) itemView.findViewById(R.id.text_view_value);
        }
    }
}