package com.viergui.vierguiwinnt;

import java.util.Random;

public class Computer {

    private final int maxColumns;

    public Computer(int columns){
        maxColumns = columns;
    }

    //Stein setzen
    public int set(){
        Random random = new Random();
        return random.nextInt(0, maxColumns);
    }
}
