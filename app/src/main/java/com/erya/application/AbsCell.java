package com.erya.application;

import androidx.annotation.NonNull;

public abstract class AbsCell implements Diffable<AbsCell> {
    public enum ViewType {
        BUTTONS,
        CLASS
    }

    @NonNull
    private final ViewType viewType;

    AbsCell(@NonNull final ViewType viewType) {
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
        AbsCell absCell1 = (AbsCell) o;
        return (viewType == absCell1.getViewType());
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
