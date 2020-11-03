package com.erya.application;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AbsHolder extends RecyclerView.ViewHolder {

    public AbsHolder(@NonNull final View itemView) {
        super(itemView);
    }

    public abstract void bind(@NonNull final AbsCell absCell);
}
