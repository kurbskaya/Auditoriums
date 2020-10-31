package com.erya.application;

import androidx.annotation.NonNull;


public abstract class Cell implements Diffable<Cell> {
    public enum ViewType {
        BUT,
        CLASS
    }

    @NonNull
    private final ViewType viewType;

    Cell(@NonNull final ViewType viewType) {
        this.viewType = viewType;
    }

    @NonNull
    public ViewType getViewType() {
        return viewType;
    }


    @Override
    @SuppressWarnings({"SimplifiableIfStatement", "RedundantIfStatement"})
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell1 = (Cell) o;
        return (viewType == cell1.getViewType());
    }

    @Override
    public int hashCode() {
        return viewType.hashCode();
    }

    @Override
    @NonNull
    public String toString() {
        return viewType.toString();
    }

}
