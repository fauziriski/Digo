package com.example.digo.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digo.Home.Adapter;
import com.example.digo.Home.Information;
import com.example.digo.Home.InformationData;
import com.example.digo.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;

    private RecyclerView rvMenus;
    private ArrayList<Information> datahome = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.home_fragment,container,false);
////        final TextView textView = root.findViewById(R.id.text_home);
////        homeViewModel.getText().observe(this, new Observer<String>() {
////            @Override
////            public void onChanged(String s) {
////                textView.setText(s);
////            }
////            });
//        return root;


        View view = inflater.inflate(R.layout.home_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_data_home);

        Adapter adapter = new Adapter(datahome);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        datahome.addAll(InformationData.getListData());


        return view;

    }
}
