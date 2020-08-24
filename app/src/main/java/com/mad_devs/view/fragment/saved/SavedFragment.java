package com.mad_devs.view.fragment.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mad_devs.internet.R;

public class SavedFragment extends Fragment {

    private SavedViewModel mViewModel;

    public static SavedFragment newInstance() {
        return new SavedFragment ();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container ,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.saved_fragment , container , false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
        mViewModel = ViewModelProviders.of ( this ).get ( SavedViewModel.class );
        // TODO: Use the ViewModel
    }

}