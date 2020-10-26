package com.hse.androidfirsthomework;

import android.content.res.Configuration;
import android.os.Bundle;

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




    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView recyclerView = layout.findViewById(R.id.recycler);
        int nOfColumns = 3;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            nOfColumns = 4;
        }
        ColoredNumberRepository repository = ColoredNumberRepository.getInstance();
        ColoredNumberAdapter adapter = new ColoredNumberAdapter(repository.list());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), nOfColumns));
        FloatingActionButton floatingActionButton = layout.findViewById(R.id.button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.addItem(repository.list().size()+1);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setListener(new ColoredNumberAdapter.Listener() {
            @Override
            public void onClick(int position) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                NumberFragment numberFragment = new NumberFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
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