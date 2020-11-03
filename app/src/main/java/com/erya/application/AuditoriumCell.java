package com.erya.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

final public class AuditoriumCell extends AbsCell {
    @NonNull
    public final String name;

    AuditoriumCell(@NonNull final String name) {
        super(ViewType.CLASS);
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean isSame(@NonNull final AbsCell other) {
        return this.equals(other);
    }

    @Override
    public boolean isContentsSame(@NonNull final AbsCell other) {
        return this.equals(other);
    }

    @Override
    @SuppressWarnings({"SimplifiableIfStatement", "RedundantIfStatement"})
    public boolean equals(final Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditoriumCell cell1 = (AuditoriumCell) o;
        return (name == cell1.getName());
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    @NonNull
    public String toString() {
        return name.toString();
    }
}
