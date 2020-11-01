package com.erya.application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import static com.erya.application.AbsCell.ViewType.BUTTONS;

public final class AuditoriumViewModel extends ViewModel{

    private final SingleEventLiveData<String> errorLiveData = new SingleEventLiveData<>();
    private MutableLiveData<List<AbsCell>> cells = new MutableLiveData<>();


    public void reset(AbsCell absCell){
        ButtonsCell buttonsCell = new ButtonsCell(BUTTONS);
        List<AbsCell> tmp = cells.getValue();
        if (tmp == null) {
            tmp = new ArrayList<>();
            tmp.add(buttonsCell);
            cells.setValue(tmp);
            return;
        }
        if (absCell ==null) {return;}
        tmp.add(absCell);
        cells.setValue(tmp);
    }

    public void SetErrorLiveData(String string){
        errorLiveData.setValue(string);
    }

    @NonNull
    public void clearCells() {
        super.onCleared();
        List<AbsCell> tmp = cells.getValue();
        int i2 = 0;
        for (int i1 = 0; i1 < tmp.size(); i1++) {
            if (tmp.get(i1).getViewType() == BUTTONS) {
                tmp.set(i2, tmp.get(i1));
                i2++;
            }
        }
        if (tmp.size() > i2) {
            tmp.subList(i2, tmp.size()).clear();
        }
        cells.setValue(tmp);
    }

    @NonNull
    public LiveData<List<AbsCell>> getCells() {
        return cells;
    }

    @NonNull
    public LiveData<String> getError() {
        return errorLiveData;
    }


}