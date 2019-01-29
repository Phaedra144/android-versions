package com.dpdgroup.versions.android.androidversions.adapter;

import android.view.ViewGroup;

import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AndroidVersionAdapter extends RecyclerView.Adapter {

    ArrayList<AndroidVersion> androidVersions;

    public AndroidVersionAdapter(ArrayList<AndroidVersion> androidVersions) {
        this.androidVersions = androidVersions;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
