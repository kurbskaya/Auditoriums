package com.erya.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

final public class AuditoriumHolder extends AbsHolder {

    private final TextView textView;

    @Nullable
    private AuditoriumCell cell = null;

    AuditoriumHolder(@NonNull final LayoutInflater inflater,
                     @NonNull final ViewGroup parent,
                     final AuditoriumAdapter.OnClickListener onClickListener) {
        super(inflater.inflate(R.layout.view_auditorium, parent, false));
        textView = itemView.findViewById(R.id.label);

        itemView.setOnClickListener(view -> {
            if (cell == null) {
                return;
            }
            onClickListener.onClickItem(cell);
        });
    }

    public void bind(@NonNull final AbsCell absCell) {
        final AuditoriumCell audCell = (AuditoriumCell) absCell;
        this.cell = audCell;
        textView.setText(audCell.getName());

    }
}

