package com.example.multifunctioncalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.multifunctioncalculator.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private double number = 0;
    private double storage = Double.NaN;
    private boolean dec;
    private int power;
    private String action = "";


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        number = 0;
        storage = Double.NaN;
        dec = false;


        binding.btn1.setOnClickListener(view117 -> add(1));
        binding.btn2.setOnClickListener(view115 -> add(2));
        binding.btn3.setOnClickListener(view111 -> add(3));
        binding.btn4.setOnClickListener(view19 -> add(4));
        binding.btn5.setOnClickListener(view18 -> add(5));
        binding.btn6.setOnClickListener(view17 -> add(6));
        binding.btn7.setOnClickListener(view16 -> add(7));
        binding.btn8.setOnClickListener(view15 -> add(8));
        binding.btn9.setOnClickListener(view14 -> add(9));
        binding.btn0.setOnClickListener(view13 -> add(0));
        binding.btnmlt.setOnClickListener(view12 -> setAction("x"));
        binding.btndiv.setOnClickListener(view110 -> setAction("/"));
        binding.btnclr.setOnClickListener(view1 -> clear());
        binding.btnneg.setOnClickListener(view113 -> setAction("+-"));
        binding.btnadd.setOnClickListener(view112 -> {
            setAction("+");
            Log.i("mood my guy", "added");
        });
        binding.btnmin.setOnClickListener(view114 -> setAction("-"));
        binding.btnmod.setOnClickListener(view116 -> setAction("mod"));
        binding.btndec.setOnClickListener(view118 -> setAction("."));
        binding.btneql.setOnClickListener(view119 -> takeAction());
        binding.btnsqr.setOnClickListener(view120 -> setAction("sqr"));

    }

    private void add(int num){
        if (dec){
            this.number += num*Math.pow(10, -power);
        }else {
            this.number *= 10;
            this.number += num;
        }
        updateScreen();
    }

    private void setAction(String act){
        String a = "Setting action as " + act;
        Log.i("mood my guy", a);
        if (!action.equals("")){
            takeAction();
        }

        if (act.equals("+-")){
            makeNeg();
        }else if (act.equals("sqr")){
            setSqrt();
        }
        else{
            Log.i("mood my guy", "Got to setting the variables different");
            action = act;
            storage = number;
            number = 0;
        }

        updateScreen();
    }

    private void clear(){
        storage = Double.NaN;
        number = 0;
        action = "";
        dec = false;
        updateScreen();
    }

    private void takeAction(){
        switch (action) {
            case "+":
                number = number + storage;
                break;
            case "-":
                number = storage - number;
                break;
            case "/":
                number = storage / number;
                break;
            case "mod":
                number = storage % number;
                break;
            case "x":
                number = storage * number;
                break;
            case ".":
                dec = true;
                power += 1;
                break;
        }
        storage = Double.NaN;
        action = "";
        updateScreen();
    }

    private void makeNeg(){
        number *= -1;
    }

    private void updateScreen(){
        binding.output.setText(String.valueOf(number));

    }

    private void setSqrt(){
        number = Math.sqrt(number);
        Log.i("mood my guy", "sqrt = "+Math.sqrt(number));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}