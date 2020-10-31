package com.erya.application;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public final class MyTripsAdapter2 extends AbsDiffableListAdapter<Cell, AbsMyTripsHolder> {

    @NonNull
    private final Cell.ViewType[] viewTypeValues = Cell.ViewType.values();

    @NonNull
    private final LayoutInflater inflater;

    @NonNull
    public List<Cell> cells;

    public MyTripsAdapter2(@NonNull final List<Cell> cells,
                          @NonNull final LayoutInflater inflater) {
        super(cells);
        this.cells = cells;
        this.inflater = inflater;
    }

    public void update (List<Cell> cells){
        this.cells = cells;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public AbsMyTripsHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewTypeOrdinal) {
        final Cell.ViewType viewType = viewTypeValues[viewTypeOrdinal];
        switch (viewType) {
            case BUT:
                return new ButMyTripsHolder(inflater, parent);
            case CLASS:
                return new ClassMyTripsHolder(inflater, parent);

            default:
                throw new IllegalArgumentException("Unknown ViewType: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final AbsMyTripsHolder holder, final int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemViewType(final int position) {
        return getItem(position).getViewType().ordinal();
    }

}