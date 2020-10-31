package com.erya.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import static com.erya.application.Cell.ViewType.BUT;

public final class MyViewModel extends ViewModel {

    private final SingleEventLiveData<String> errorLiveData = new SingleEventLiveData<>();
    private MutableLiveData<List<Cell>> cells = new MutableLiveData<>();

    public static final class Input {
        final String tripId;
        final String desiredFlightId;

        public Input(@NonNull final String tripId, @Nullable final String desiredFlightId) {
            this.tripId = tripId;
            this.desiredFlightId = desiredFlightId;
        }


        @Override
        @SuppressWarnings({"SimplifiableIfStatement", "RedundantIfStatement", "ConstantConditions"})
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final Input input = (Input) o;

            if (!Objects.equals(tripId, input.tripId)) {
                return false;
            }
            if (!Objects.equals(desiredFlightId, input.desiredFlightId)) {
                return false;
            }

            return true;
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        public int hashCode() {
            int result = tripId != null ? tripId.hashCode() : 0;
            result = 31 * result + (desiredFlightId != null ? desiredFlightId.hashCode() : 0);
            return result;
        }
    }

    @Nullable
    private Input input;

    public void create(Cell cell){
        List<Cell> tmp = cells.getValue();
        if (tmp == null) {
            tmp = new ArrayList<>();
        }
        tmp.add(cell);
        cells.setValue(tmp);
    }

    public void SetErrorLiveData(String string){
        errorLiveData.setValue(string);
    }


    public void reset(@NonNull final Input input) {
        if (input.equals(this.input)) {
            return;
        }
        this.input = input;

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        List<Cell> tmp = cells.getValue();
        int i2 = 0;
        for (int i1 = 0; i1 < tmp.size(); i1++) {
            if (tmp.get(i1).getViewType() == BUT) {
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
    public LiveData<List<Cell>> getCells() {

        return cells;
    }



    @NonNull
    public LiveData<String> getError() {

        return errorLiveData;
    }




}