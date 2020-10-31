package com.erya.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


final class ButMyTripsHolder extends AbsMyTripsHolder {
    public Button button1;
    public Button button2;
    private EditText idText;

  ButMyTripsHolder(@NonNull final LayoutInflater inflater, @NonNull final ViewGroup parent) {

        super(inflater.inflate(R.layout.list2, parent, false));
        idText =  itemView.findViewById(R.id.idtext);
        button1 = itemView.findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = idText.getText().toString();

                if (name.equals("")) {
                    MyFragment.getMyViewModel().SetErrorLiveData("empty enter");
                    return;
                }

                Cell_class cell_class = new Cell_class(name);
                MyFragment.getMyViewModel().create(cell_class);

                idText.setText(null);
            }
        });

        button2 = itemView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyFragment.getMyViewModel().onCleared();
                idText.setText(null);
            }
        });
    }

    public void bind(@NonNull final Cell cell) {

    }

}

