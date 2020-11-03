package com.erya.application;

import androidx.annotation.NonNull;

public class ButtonsCell extends AbsCell {
    private String fieldText;

    ButtonsCell() {
        super(ViewType.BUTTONS);
    }

    public void setNameFieldText(String text){
        this.fieldText = text;
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
