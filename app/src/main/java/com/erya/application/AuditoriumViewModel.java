package com.erya.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import static com.erya.application.AbsCell.ViewType.BUTTONS;
import static com.erya.application.AbsCell.ViewType.CLASS;

public final class AuditoriumViewModel extends ViewModel implements AuditoriumAdapter.OnClickListener{

    private final SingleEventLiveData<String> errorLiveData = new SingleEventLiveData<>();
    private final MutableLiveData<List<AbsCell>> cellsLiveData = new MutableLiveData<>();

    public AuditoriumViewModel(){
        resetCells();
    }

    public void setErrorLiveData(@NonNull final String string){
        errorLiveData.setValue(string);
    }

    public void addCell(@NonNull final AbsCell audCell){
        final List<AbsCell> oldList = cellsLiveData.getValue();
        if (oldList == null){
            resetCells();
            return;
        }
        final List<AbsCell> tmp = new ArrayList<>(oldList);
        tmp.add(audCell);
        cellsLiveData.setValue(tmp);
    }


    public void resetCells() {
        final ButtonsCell buttonsCell = new ButtonsCell(null);
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
    final public void onClickButton(@NonNull final AbsCell absCell ) {
        this.addCell(absCell);
    }

    @Override
    public void onClickItem(@NonNull final AuditoriumCell audCell) {
        this.setErrorLiveData(audCell.name);
    }

    @Override
    public void onClickButClear() {
        this.resetCells();
    }

    @Override
    public void onNameTextChanged(@NonNull final String text){
        final List<AbsCell> oldList = cellsLiveData.getValue();
        if (oldList == null){
            return;
        }
        final ButtonsCell cell = (ButtonsCell) oldList.get(0);

        final List<AbsCell> newList = new ArrayList<>(oldList);
        newList.set(0, cell.setNameFieldText(text));
        cellsLiveData.setValue(newList);
    }

    @Override
    public void onClickError(final String text){
        this.setErrorLiveData(text);
    }
}