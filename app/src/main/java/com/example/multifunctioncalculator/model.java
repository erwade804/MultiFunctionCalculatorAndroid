package com.example.multifunctioncalculator;

import android.util.Log;
import java.math.BigDecimal;
import java.util.Observable;

public class model extends Observable {

    private BigDecimal number;
    private BigDecimal storage;
    private String action;
    private StringBuilder numStr;
    private boolean dec;
    private String instantAction;


    public model(){
        number = new BigDecimal(0);
        numStr = new StringBuilder().append("0");
        storage = new BigDecimal(0);
        action = "";
        instantAction = "";
        dec = false;
    }

    public void append(int newNum){
        numStr.append(newNum);
        number = new BigDecimal(numStr.toString());
        dec = numStr.toString().contains(".");
        updateScreen();
    }

    public void clear(){
        number = new BigDecimal(0);
        storage = new BigDecimal(0);
        action = "";
        dec = false;
    }

    public void setAction(String act){
        boolean actionTaken = true;
        number = new BigDecimal(numStr.toString());
        // instant actions
        switch(act){
            case "mod":
                setPercent();
                break;
            case "+-":
                makeNeg();
                break;
            default:
                actionTaken = false;
                break;

        }

        if (!actionTaken){
            switch (act) {
                case "=":
                    takeAction();
                    break;
                case "c":
                    clear();
                    break;
                case "sqrt":
                    setSqrt();
                    break;
                case ".":
                    instantAction = act;
                    takeAction();
                    break;
                default:
                    dec = false;
                    numStr = new StringBuilder().append(0);
                    action = act;
                    storage = number;
                    number = new BigDecimal(0);
                    break;
            }
        }
        updateScreen();
    }

    private void setPercent(){
        if(storage.equals(new BigDecimal("0"))){
            number = new BigDecimal(0);

        }else{
            number = number.divide(new BigDecimal(100)).multiply(storage);
        }
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
        if (instantAction.equals(".")){
            if (!numStr.toString().contains(".") || !dec) {
                numStr.append(".");
                dec = true;
            }
            instantAction = "";
            return;
        }
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

        }
        numStr = new StringBuilder().append(number.toString());
        if (!action.equals(".")) {
            storage = new BigDecimal(0);
        }
        action = "";
        updateScreen();

        Log.i("Test", numStr.toString());
    }

    private void updateScreen(){
        numStr = (new StringBuilder()).append(number.toString());
        if (dec && !numStr.toString().contains(".")){
            numStr.append(".");

        }
        setChanged();
        notifyObservers(numStr);
    }

}
