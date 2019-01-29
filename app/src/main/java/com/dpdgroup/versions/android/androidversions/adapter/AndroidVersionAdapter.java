package com.dpdgroup.versions.android.androidversions.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpdgroup.versions.android.androidversions.R;
import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AndroidVersionAdapter extends RecyclerView.Adapter<AndroidVersionAdapter.VersionsAdepterViewHolder> {

    ArrayList<AndroidVersion> androidVersions;

    public AndroidVersionAdapter(ArrayList<AndroidVersion> androidVersions) {
        this.androidVersions = androidVersions;
    }

    @NonNull
    @Override
    public VersionsAdepterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_android_version, parent, false);
        return new VersionsAdepterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionsAdepterViewHolder holder, int position) {
        holder.codeName.setText(androidVersions.get(position).getCodeName());
        holder.apiLevel.setText("Api level: " + androidVersions.get(position).getApiLevel());
        holder.relaseDate.setText("Relase date: " + androidVersions.get(position).convertToNiceDateFormat(String.valueOf(androidVersions.get(position).getRelaseDate())));
//        getImages(viewHolder, androidVersions.get(position)); to be implemented
    }

    @Override
    public int getItemCount() {
        return androidVersions.size();
    }

    class VersionsAdepterViewHolder extends RecyclerView.ViewHolder {
        public TextView codeName;
        public TextView apiLevel;
        public TextView relaseDate;
        public ImageView androidImage;
        public VersionsAdepterViewHolder(View v) {
            super(v);
            codeName = v.findViewById(R.id.codeNameText);
            apiLevel = v.findViewById(R.id.apiLevelText);
            relaseDate = v.findViewById(R.id.relaseDateText);
            androidImage = v.findViewById(R.id.versionImage);
        }
    }
}
