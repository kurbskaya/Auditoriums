package com.erya.application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public final class AuditoriumFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, @Nullable final Bundle savedInstanceState){
       View v = inflater.inflate(R.layout.frag, null);
       return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final Context context = requireContext();

        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        final AuditoriumViewModel auditoriumViewModel = new ViewModelProvider(this).get(AuditoriumViewModel.class);
        System.out.println("11111");


        auditoriumViewModel.reset(null);

        auditoriumViewModel.getCells().observe(getViewLifecycleOwner(), new Observer<List<AbsCell>>() {

            @Override
            public void onChanged(@Nullable final List<AbsCell> absCells) {
                if (absCells == null) {
                   return;
                }

                final RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter == null) {

                    final AuditoriumAdapter myAdapter = new AuditoriumAdapter(absCells, LayoutInflater.from(context.getApplicationContext()), new AuditoriumAdapter.OnClickListener() {
                        @Override
                        public void onClickButton(AbsCell cell) {
                            auditoriumViewModel.reset(cell);
                        }

                        @Override
                        public void onClickItem(String string) {
                            auditoriumViewModel.SetErrorLiveData(string);
                        }

                        @Override
                        public void onClickButClear() {
                            auditoriumViewModel.clearCells();
                        }
                    });
                    recyclerView.setAdapter(myAdapter);

                } else {

                    final AuditoriumAdapter myAdapter = (AuditoriumAdapter) adapter;
                    myAdapter.submitList(absCells);
                    myAdapter.notifyDataSetChanged();
                    System.out.println(absCells.toString());
                }
            }
        });

        auditoriumViewModel.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String error) {
                if (error == null)
                    return;
                Snackbar.make(view, error, Snackbar.LENGTH_LONG).show();
            }
        });

    }

}