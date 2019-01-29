package com.dpdgroup.versions.android.androidversions.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpdgroup.versions.android.androidversions.R;
import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AndroidVersionAdapter extends RecyclerView.Adapter<AndroidVersionAdapter.VersionsAdepterViewHolder> {

    ArrayList<AndroidVersion> androidVersions;
    Context context;
    RecyclerView recyclerView;
    boolean isClicked = true;

    public AndroidVersionAdapter(ArrayList<AndroidVersion> androidVersions, Context context) {
        this.androidVersions = androidVersions;
        this.context = context;
    }

    @NonNull
    @Override
    public VersionsAdepterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_android_version, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClicked) {
                    view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
                    isClicked = false;
                } else {
                    view.setBackgroundColor(Color.TRANSPARENT);
                    isClicked = true;
                }
            }
        });
        return new VersionsAdepterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionsAdepterViewHolder holder, int position) {
        holder.codeName.setText(androidVersions.get(position).getCodeName());
        holder.apiLevel.setText("Api level: " + androidVersions.get(position).getApiLevel());
        holder.relaseDate.setText("Relase date: " + androidVersions.get(position).convertToNiceDateFormat(androidVersions.get(position).getReleaseDate()));
        getImages(holder, androidVersions.get(position));
    }

    @Override
    public int getItemCount() {
        return androidVersions.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
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

    private void getImages(VersionsAdepterViewHolder holder, AndroidVersion version) {
        holder.androidImage.setImageBitmap(null);
        Picasso.get().load(version.getImageUrl()).placeholder(R.drawable.android_bike).resize(150, 150).centerCrop().into(holder.androidImage);
    }
}
