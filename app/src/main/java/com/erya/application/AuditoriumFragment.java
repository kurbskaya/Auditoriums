package com.erya.application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erya.application.AuditoriumAdapter.OnClickListener;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class AuditoriumFragment extends Fragment {
    public AuditoriumFragment() {
        super(R.layout.fragment_auditorium);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final Context context = requireContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final LifecycleOwner lifecycleOwner = getViewLifecycleOwner();

        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        final AuditoriumViewModel auditoriumViewModel = new ViewModelProvider(this).get(AuditoriumViewModel.class);

        auditoriumViewModel.addCell(null);
        auditoriumViewModel.getCellsLiveData().observe(lifecycleOwner, absCells -> {
            if (absCells == null) {
                return;
            }

            final RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter == null) {

                final AuditoriumAdapter myAdapter = new AuditoriumAdapter(absCells, inflater, auditoriumViewModel);

                recyclerView.setAdapter(myAdapter);

            } else {
                final AuditoriumAdapter myAdapter = (AuditoriumAdapter) adapter;
                myAdapter.submitList(absCells);
            }
        });

        auditoriumViewModel.getErrorLiveData().observe(lifecycleOwner, error -> {
            if (error == null) {
                return;
            }
            Snackbar.make(view, error, Snackbar.LENGTH_LONG).show();
        });

    }

}