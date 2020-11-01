package com.hse.androidfirsthomework;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class RecyclerFragment extends Fragment {

    private ColoredNumberRepository repository;
    private Integer counter;


    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Log.d("S_NULL", "true");
        } else {
            Log.d("S_NULL", "false");
        }
        repository = new ColoredNumberRepository();
        counter = repository.list().size();
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter");
            if (counter != repository.list().size()) {
                for (int i = 100; i < counter; i++) {
                    int value = i + 1;
                    repository.addItem(value);
                }
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (counter == null) {
            Log.d("S_NULL_ON", "true");
        } else {
            Log.d("S_NULL_ON", "false");
        }
        savedInstanceState.putInt("counter", counter);
        Integer num = savedInstanceState.getInt("counter");
        Log.d("S_NULL_A", num.toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView recyclerView = layout.findViewById(R.id.recycler);
        ColoredNumberAdapter adapter = new ColoredNumberAdapter(repository.list());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.columns)));
        FloatingActionButton floatingActionButton = layout.findViewById(R.id.button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = repository.list().size();
                counter = temp + 1;
                repository.addItem(counter);
                adapter.notifyItemInserted(temp);
            }
        });

        adapter.setListener(new ColoredNumberAdapter.Listener() {
            @Override
            public void onClick(ColoredNumber coloredNumber) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                NumberFragment numberFragment = new NumberFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("number", coloredNumber);
                numberFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, numberFragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return layout;
    }

}