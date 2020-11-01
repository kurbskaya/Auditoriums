package com.erya.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;


final class ButHolder extends AbsHolder {
    public Button button1;
    public Button button2;
    private EditText idText;
    private final AuditoriumAdapter.OnClickListener onClickListener;

  ButHolder(@NonNull final LayoutInflater inflater, @NonNull final ViewGroup parent, final AuditoriumAdapter.OnClickListener onClickListener) {

        super(inflater.inflate(R.layout.list2, parent, false));
        idText =  itemView.findViewById(R.id.idtext);
        button1 = itemView.findViewById(R.id.button1);
        this.onClickListener = onClickListener;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = idText.getText().toString();

                if (name.equals("")) {
                    onClickListener.onClickItem("Error: empty enter");
                    return;
                }

                AuditoriumCell audCell = new AuditoriumCell(name);
                onClickListener.onClickButton(audCell);
                idText.setText(null);
            }
        });

        button2 = itemView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClickButClear();
                idText.setText(null);
            }
        });
    }

    public void bind(@NonNull final AbsCell absCell) {

    }

}

