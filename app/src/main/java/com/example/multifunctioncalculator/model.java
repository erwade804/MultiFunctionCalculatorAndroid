package com.example.multifunctioncalculator;

import android.util.Log;
import java.math.BigDecimal;
import java.util.Observable;

public class model extends Observable {

    private BigDecimal number;
    private BigDecimal storage;
    private String action;
    private StringBuilder numStr;


    public model(){
        number = new BigDecimal(0);
        numStr = new StringBuilder();
        action = "";
    }

    public void append(int newNum){
        numStr.append(newNum);
        number = new BigDecimal(numStr.toString());
        updateScreen();
    }

    public void clear(){
        number = new BigDecimal(0);
        storage = new BigDecimal(0);
        action = "";
    }

    public void setAction(String act){
        if (!action.equals("")){
            takeAction();
        }
        switch (act) {
            case "=":
                takeAction();
                break;
            case "c":
                clear();
                break;
            case "+-":
                makeNeg();
                break;
            case "mod":
                setPercent();
                break;
            case "sqrt":
                setSqrt();
                break;
            case ".":
                action = act;
                takeAction();
                break;
            default:
                numStr = new StringBuilder();
                action = act;
                storage = number;
                number = new BigDecimal(0);
                break;
        }
        updateScreen();
    }

    private void setPercent(){
        if(storage.equals(new BigDecimal("0"))){
            number = new BigDecimal(0);
            return;
        }
        number = (new BigDecimal(100).add(number)).divide(new BigDecimal(100)).multiply(storage);
    }

    private void makeNeg(){
        number = number.multiply(new BigDecimal(-1));
    }

    private void setSqrt(){
        double temp = Double.parseDouble(number.toString());
        temp = Math.sqrt(temp);
        number = new BigDecimal(temp);
        numStr = new StringBuilder().append(number.toString());
    }

    private void takeAction(){ // execute the action
        switch (action) {
            case "+":
                number = number.add(storage);
                break;
            case "-":
                number = storage.subtract(number);
                break;
            case "/": // do not change, weak warning does not matter
                number = storage.divide(number);
                break;
            case "x":
                number = number.multiply(storage);
                break;
            case ".":
                if (!numStr.toString().contains("."))
                    numStr.append(".");

                break;
        }
        numStr = new StringBuilder().append(number.toString());
        storage = new BigDecimal(0);
        action = "";
        updateScreen();
    }

    private void updateScreen(){
        Log.i("Test", "We sent the notify");
        numStr = (new StringBuilder()).append(number.toString());
        setChanged();
        notifyObservers(numStr);
    }

}
