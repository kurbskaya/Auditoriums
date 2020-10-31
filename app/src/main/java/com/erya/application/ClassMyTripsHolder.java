package com.erya.application;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

final public class ClassMyTripsHolder extends AbsMyTripsHolder {
    private final TextView textView;

    ClassMyTripsHolder(@NonNull final LayoutInflater inflater, @NonNull final ViewGroup parent) {
        super(inflater.inflate(R.layout.list1, parent, false));
        textView = itemView.findViewById(R.id.label);
    }

    public void bind(@NonNull final Cell cell) {

        textView.setText(cell.toString());

    }

}

