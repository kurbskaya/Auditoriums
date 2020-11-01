package com.erya.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

final public class AuditoriumHolder extends AbsHolder {

    private final TextView textView;
    private final AuditoriumAdapter.OnClickListener onClickListener;

    AuditoriumHolder(@NonNull final LayoutInflater inflater, @NonNull final ViewGroup parent, final AuditoriumAdapter.OnClickListener onClickListener) {
        super(inflater.inflate(R.layout.list1, parent, false));
        this.onClickListener = onClickListener;
        textView = itemView.findViewById(R.id.label);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textView.getText().toString();
                onClickListener.onClickItem(name);
            }
        });
    }

    public void bind(@NonNull final AbsCell absCell) {
        AuditoriumCell audCell = (AuditoriumCell) absCell;
        textView.setText(audCell.getName());

    }
}

