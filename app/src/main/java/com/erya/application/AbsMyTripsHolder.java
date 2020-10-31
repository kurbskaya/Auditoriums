package com.erya.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AbsMyTripsHolder extends RecyclerView.ViewHolder {

    public AbsMyTripsHolder (@NonNull final View itemView) {
        super(itemView);
    }

    public abstract void bind(@NonNull final Cell cell);{
    }
}
