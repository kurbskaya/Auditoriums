package com.erya.application;

import androidx.annotation.NonNull;

public class ButtonsCell extends AbsCell {
    ButtonsCell(@NonNull ViewType viewType) {
        super(viewType.BUTTONS);
    }

    @Override
    public boolean isSame(AbsCell other) {
        return false;
    }

    @Override
    public boolean isContentsSame(AbsCell other) {
        return false;
    }
}
