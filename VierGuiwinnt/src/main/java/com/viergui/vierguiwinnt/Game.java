package com.viergui.vierguiwinnt;

import java.util.Scanner;

public class Game {

    private void game1(){
        Scanner scan = new Scanner(System.in);
        Board board = new Board();

        boolean player = true;

        while(!board.isFull()){
            board.printBoard();

            System.out.print("Spieler " + (player? "A" : "B") + " Spalte >> ");
            int input = scan.nextInt() -1;
            //Prüfen, ob Eingabe im Rahmen
            if(input < 0 || input > 6){
                System.out.println("Ungültige Eingabe!");
                continue;
            }
            //Stein setzen und prüfen ob die Spalte voll ist
            if (!board.setValue(input, (player? 'X' : 'O'))) {
                System.out.println("Spalte voll.");
                continue;
            }
            //Prüfen, ob gewonnen wurde
            if(board.checkField(input, board.getHighestYCoord(input))){
                board.printBoard();
                System.out.println("Spieler "+ (player? "A" : "B") + " hat gewonnen!");
                return;
            }

            //anderer Spieler
            player = !player;
        }
        System.out.println("Brett zu voll, unentschieden.");
    }

    private void game2(){
        Scanner scan = new Scanner(System.in);
        Board board = new Board();
        Computer com = new Computer(7);

        System.out.println("Dein Stein ist X");

        while(!board.isFull()){
            board.printBoard();

            //Spieler
            System.out.print("Spalte >> ");
            int input = scan.nextInt() -1;
            if(input < 0 || input > 6){
                System.out.println("Ungültige Eingabe!");
                continue;
            }
            if (!board.setValue(input, 'X')) {
                System.out.println("Spalte voll.");
                continue;
            }
            if(board.checkField(input, board.getHighestYCoord(input))){
                board.printBoard();
                System.out.println("Du hast gewonnen!");
                return;
            }

            //Computer
            int comInput = com.set();
            //Solange ziehen bis eine nicht-volle Spalte gewählt wird
            while (!board.setValue(comInput, 'O')) {
                comInput = com.set();
            }
            if(board.checkField(comInput, board.getHighestYCoord(comInput))){
                board.printBoard();
                System.out.println("Der Computer hat gewonnen!");
                return;
            }


        }
        System.out.println("Brett zu voll, unentschieden.");
    }

    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        Game game = new Game();

        System.out.println("Wähle deinen Gegner: 1 = Spieler, 2 = Computer");
        System.out.print(">> ");
        if(scan.nextInt() == 1) game.game1();
        else game.game2();
    }
}
