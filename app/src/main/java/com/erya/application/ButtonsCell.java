package com.erya.application;

import androidx.annotation.NonNull;

public final class ButtonsCell extends AbsCell {
    private final String fieldText;


    ButtonsCell(final String fieldText) {
        super(ViewType.BUTTONS);
        this.fieldText = fieldText;
    }

    public ButtonsCell setNameFieldText(final String text){
        return new ButtonsCell(text);
    }

    public String getNameFieldText(){
        return this.fieldText;
    }

    @Override
    public boolean isSame(@NonNull final AbsCell other) {
        return this.getViewType().equals(other.getViewType());
    }

    @Override
    public boolean isContentsSame(@NonNull final AbsCell other) {
        return this.equals(other);
    }
}
