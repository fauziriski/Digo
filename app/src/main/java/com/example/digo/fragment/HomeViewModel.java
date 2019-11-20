package com.example.digo.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public HomeViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is Home");

    }
    public LiveData<String> getText(){
        return mText;
    }
}
