package com.erya.application;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public final class AuditoriumAdapter extends AbsDiffableListAdapter<AbsCell, AbsHolder> {

    @NonNull
    private final AbsCell.ViewType[] viewTypeValues = AbsCell.ViewType.values();

    @NonNull
    private final LayoutInflater inflater;

    @NonNull
    private final OnClickListener onClickListener;

    public AuditoriumAdapter(@NonNull final List<AbsCell> absCells,
                             @NonNull final LayoutInflater inflater,
                             @NonNull final OnClickListener onClickListener) {

        super(absCells);
        this.inflater = inflater;
        this.onClickListener  = onClickListener;
    }

    @NonNull
    @Override
    public AbsHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewTypeOrdinal) {
        final AbsCell.ViewType viewType = viewTypeValues[viewTypeOrdinal];
        switch (viewType) {
            case BUTTONS:
                return new ButtonHolder(inflater, parent, onClickListener);
            case CLASS:
                return new AuditoriumHolder(inflater, parent, onClickListener);

            default:
                throw new IllegalArgumentException("Unknown ViewType: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final AbsHolder holder, final int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemViewType(final int position) {
        return getItem(position).getViewType().ordinal();
    }

    public interface OnClickListener {
        void onClickButton(@NonNull AbsCell absCell);
        void onClickItem(@NonNull AuditoriumCell audCell);
        void onClickButClear();

        void onNameTextChanged(String text);
        void onClickError(String text);
    }

}