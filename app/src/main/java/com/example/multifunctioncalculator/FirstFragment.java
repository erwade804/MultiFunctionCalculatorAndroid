package com.example.multifunctioncalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.example.multifunctioncalculator.model;

import com.example.multifunctioncalculator.databinding.FragmentFirstBinding;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

public class FirstFragment extends Fragment implements Observer {

    private FragmentFirstBinding binding;
    private model module;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        module = new model();
        module.addObserver(this);

        class CalculatorClickHandler  implements View.OnClickListener{
            @Override
            public void onClick(View v){
                String tag = ((Button) v).getTag().toString();

                Log.i("Test", tag);
                boolean flags = false;
                for (int i = 0; i < 10; i++) {
                    if (tag.equals(Integer.toString(i))) {
                        module.append(i);
                        flags = true;
                        break;
                    }
                }
                if (!flags){
                    module.setAction(tag);
                }
            }
        }
        CalculatorClickHandler click = new CalculatorClickHandler();
        ConstraintLayout layout = binding.layout;
        for(int i = 0; i < layout.getChildCount(); i++){
            View child = layout.getChildAt(i);
            if(child instanceof Button){
                Log.i("Test", "new child");
                child.setOnClickListener(click);
            }
        }
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void update(Observable observable, Object o) {
        binding.output.setText(o.toString());
    }
}