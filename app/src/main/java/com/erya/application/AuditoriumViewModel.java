package com.erya.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import static com.erya.application.AbsCell.ViewType.BUTTONS;

public final class AuditoriumViewModel extends ViewModel implements AuditoriumAdapter.OnClickListener{

    private final SingleEventLiveData<String> errorLiveData = new SingleEventLiveData<>();
    private MutableLiveData<List<AbsCell>> cellsLiveData = new MutableLiveData<>();

    public void addCell(@Nullable AbsCell absCell){
        final ButtonsCell buttonsCell = new ButtonsCell();
        final List<AbsCell> oldList = cellsLiveData.getValue();
        if (oldList == null) {
            final  List<AbsCell> tmp = new ArrayList<>();
            tmp.add(buttonsCell);
            cellsLiveData.setValue(tmp);
            return;
        }
        if (absCell ==null) { return; }
        oldList.add(absCell);
        cellsLiveData.setValue(oldList);
    }

    public void setErrorLiveData(String string){
        errorLiveData.setValue(string);
    }

    @NonNull
    public void clearCells() {
        final ButtonsCell buttonsCell = new ButtonsCell();
        final List<AbsCell> tmp = new ArrayList<>();

        tmp.add(buttonsCell);
        cellsLiveData.setValue(tmp);
    }

    @NonNull
    public LiveData<List<AbsCell>> getCellsLiveData() {
        return cellsLiveData;
    }

    @NonNull
    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }


    @Override
    final public void onClickButton(@NonNull AbsCell cell) {
        this.addCell(cell);
    }

    @Override
    final public void onClickItem(@NonNull AuditoriumCell audCell) {
        this.setErrorLiveData(audCell.name);
    }

    @Override
    final public void onClickButClear() {
        this.clearCells();
    }

    @Override
    final public void onNameTextChanged(String text){
        final List<AbsCell> oldList = cellsLiveData.getValue();
        final ButtonsCell cell = (ButtonsCell) oldList.get(0);
        cell.setNameFieldText(text);
    }

    @Override
    final public void onClickError(String text){
        this.setErrorLiveData(text);
    }
}