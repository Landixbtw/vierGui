package com.viergui.vierguiwinnt;

import java.util.Random;

/**
 * This class controls the computer. Here, the computer could be improved.
 * @author Ole Wortmann, 6007350
 * @author Yannik Schuldes, 6008313
 */

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
