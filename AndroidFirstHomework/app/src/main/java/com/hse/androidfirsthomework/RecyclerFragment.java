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

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class RecyclerFragment extends Fragment {

    private final ColoredNumberRepository repository = ColoredNumberRepository.getInstance();
    private int counter = repository.list().size();


    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter");
            if (counter > 100 && counter != repository.list().size()) {
                for (int i = 100; i < counter; i++) {
                    repository.addItem(i + 1);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("counter", counter);
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
                repository.addItem(temp + 1);
                counter = repository.list().size();
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