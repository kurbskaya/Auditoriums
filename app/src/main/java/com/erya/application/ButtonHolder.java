package com.erya.application;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;


final class ButtonHolder extends AbsHolder {
    private final Button addButton;
    private final Button clearButton;
    private final EditText nameEditText;

    private final TextWatcher nameTextWatcher;

    ButtonHolder(@NonNull final LayoutInflater inflater,
                 @NonNull final ViewGroup parent,
                 @NonNull final AuditoriumAdapter.OnClickListener onClickListener) {

        super(inflater.inflate(R.layout.view_buttons_edittext, parent, false));

        nameEditText =  itemView.findViewById(R.id.idtext);
        addButton = itemView.findViewById(R.id.button1);
        clearButton = itemView.findViewById(R.id.button2);

        addButton.setOnClickListener(view -> {
            final String name = nameEditText.getText().toString();

            if (name.isEmpty()) {
                onClickListener.onClickError("Error: empty enter");
                return;
            }
            final AuditoriumCell audCell = new AuditoriumCell(name);
            onClickListener.onClickButton(audCell);
            nameEditText.setText(null);
        });

        clearButton.setOnClickListener(view -> {
            onClickListener.onClickButClear();
            nameEditText.setText(null);
        });

      nameTextWatcher = new TextWatcher() {
          @Override
          public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
              // Nothing to do here
          }

          @Override
          public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
              // Nothing to do here
          }

          @Override
          public void afterTextChanged(final Editable editable) {
              if (editable == null) {
                  return;
              }
              final String text = editable.toString();
              onClickListener.onNameTextChanged(text);
          }
      };
      nameEditText.addTextChangedListener(nameTextWatcher);
    }

    public void bind(@NonNull final AbsCell absCell) {
        final ButtonsCell cell = (ButtonsCell) absCell;
        nameEditText.removeTextChangedListener(nameTextWatcher);
        nameEditText.setText(cell.getNameFieldText());
        nameEditText.addTextChangedListener(nameTextWatcher);
    }

}

