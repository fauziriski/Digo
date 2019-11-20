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

import com.example.digo.LoginActivity;
import com.example.digo.R;

public class ProfilFragment extends Fragment{

    private ProfilViewModel profilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        profilViewModel = ViewModelProviders.of(this).get(ProfilViewModel.class);
        View root = inflater.inflate(R.layout.profil_fragment,container,false);

        Button login = (Button)root.findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
//        final TextView textView = root.findViewById(R.id.text_home);
//        profilViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
