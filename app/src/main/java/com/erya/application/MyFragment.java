package com.erya.application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.AUTOFILL_TYPE_DATE;
import static com.erya.application.Cell.ViewType.BUT;
import static com.erya.application.Cell.ViewType.CLASS;

public final class MyFragment extends Fragment {

    @NonNull
    private static MyViewModel myViewModel;

    private static boolean once = true;


    @NonNull
    public static MyViewModel getMyViewModel() {
        return myViewModel;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

    }

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

        final MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        if (once) {
            final Cell cell1 = new Cell(BUT) {
                @Override
                public boolean isContentsSame(Cell other) {
                    return false;
                }

                @Override
                public boolean isSame(Cell other) {
                    return false;
                }
            };
            myViewModel.create(cell1);
            once = false;
        }
        myViewModel.getCells().observe(getViewLifecycleOwner(), new Observer<List<Cell>>() {
            @Override
            public void onChanged(@Nullable final List<Cell> cells) {
                if (cells == null) {

                   return;
                }
                final RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter == null) {

                    final MyTripsAdapter2 myAdapter = new MyTripsAdapter2(cells, LayoutInflater.from(context.getApplicationContext()));
                    recyclerView.setAdapter(myAdapter);

                } else {

                    final MyTripsAdapter2 myAdapter = (MyTripsAdapter2) adapter;
                    myAdapter.update(cells);
                }
            }
        });




        myViewModel.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String error) {

                assert error != null;
                Snackbar.make(view, error, Snackbar.LENGTH_LONG).show();
            }
        });

    }

}