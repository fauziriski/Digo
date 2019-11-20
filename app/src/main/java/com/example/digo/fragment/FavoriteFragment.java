package com.example.digo.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.digo.LoginActivity;
import com.example.digo.R;
import com.example.digo.ViewTempatActivity;

public class FavoriteFragment extends Fragment{

    private FavoriteViewModel favoriteViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.favorite_fragment,container,false);

        ImageView angsoduo = (ImageView) root.findViewById(R.id.angsoduo);
        angsoduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewTempatActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
