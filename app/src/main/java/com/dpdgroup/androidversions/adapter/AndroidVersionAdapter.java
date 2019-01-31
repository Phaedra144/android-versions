package com.dpdgroup.androidversions.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpdgroup.androidversions.R;
import com.dpdgroup.androidversions.model.Version;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AndroidVersionAdapter extends RecyclerView.Adapter<AndroidVersionAdapter.VersionsAdepterViewHolder> {

    ArrayList<Version> versions;
    Context context;
    RecyclerView recyclerView;
    boolean isClicked = true;
    ArrayList<Integer> selectedItems = new ArrayList<>();

    public AndroidVersionAdapter(ArrayList<Version> versions, Context context) {
        this.versions = versions;
        this.context = context;
    }

    @NonNull
    @Override
    public VersionsAdepterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_android_version, parent, false);
        return new VersionsAdepterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VersionsAdepterViewHolder holder, final int position) {
        final Version version = versions.get(position);
        holder.codeName.setText(version.getCodeName());
        holder.apiLevel.setText("Api level: " + version.getApiLevel());
        holder.relaseDate.setText("Relase date: " + version.convertToNiceDateFormat(version.getReleaseDate()));
        getImages(holder, version);
        holder.cardView.setBackgroundColor(version.isSelected() ? ContextCompat.getColor(context, R.color.colorAccent) : ContextCompat.getColor(context, R.color.cardview_light_background));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                version.setSelected(!version.isSelected());
                holder.cardView.setBackgroundColor(version.isSelected() ? ContextCompat.getColor(context, R.color.colorAccent) : ContextCompat.getColor(context, R.color.cardview_light_background));
            }
        });

    }

    @Override
    public int getItemCount() {
        return versions.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    class VersionsAdepterViewHolder extends RecyclerView.ViewHolder {
        TextView codeName;
        TextView apiLevel;
        TextView relaseDate;
        ImageView androidImage;
        CardView cardView;

        VersionsAdepterViewHolder(View v) {
            super(v);
            codeName = v.findViewById(R.id.codeNameText);
            apiLevel = v.findViewById(R.id.apiLevelText);
            relaseDate = v.findViewById(R.id.relaseDateText);
            androidImage = v.findViewById(R.id.versionImage);
            cardView = v.findViewById(R.id.card_view);
        }
    }

    private void getImages(VersionsAdepterViewHolder holder, Version version) {
        holder.androidImage.setImageBitmap(null);
        Picasso.get().load(version.getImageUrl()).placeholder(R.drawable.android_bike).resize(150, 150).centerCrop().into(holder.androidImage);
    }
}
