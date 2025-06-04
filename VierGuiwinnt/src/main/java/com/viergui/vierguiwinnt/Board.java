package com.viergui.vierguiwinnt;

/**
 * This is the model class. It contains the game logic.
 * @author Ole Wortmann,
 * @author Yannik Schuldes, 6008313
 */

public class Board {

    private char[][] fields;

    public Board (){
        fields = new char[6][7];
        initBoard();
    }

    public int getColumn() {
        return fields[0].length;
    }

    //Spielfeld auf ' ' setzen
    private void initBoard(){
        for(int i = 0; i < fields.length; i++){
            for(int c = 0; c < fields[i].length; c++){
                fields[i][c] = ' ';
            }
        }

    }

    //Den Stein an die tiefstmögliche Stelle setzen
    //Gibt false zurück wenn Spalte voll
    public boolean setValue(int x, char value){
        for(int i = fields.length - 1; i >= 0; i--) {
            if(this.fields[i][x] == ' '){
                this.fields[i][x] = value;
                return true;
            }
        }
        return false;
    }

    //Zu einer Spalte die Y-Koordinate des höchsten Steins bekommen
    public int getHighestYCoord(int x){
        for(int i = 0; i < fields.length; i++) {
            if(this.fields[i][x] != ' '){
                return i;
            }
        }
        return 0;
    }

    //Prüft ob Spielfeld voll ist
    public boolean isFull(){
        for(int i = 0; i < fields.length; i++){
            for(int c = 0; c  < fields[i].length; c++){
                if(fields[i][c] == ' ') return false;
            }
        }
        return true;
    }

    //horizontal vertikal, diagonal prüfen
    public boolean checkField(int x, int y) {
        int counter = 0;
        char compare = fields[y][x];

        //horizontal, links
        for(int i = x -1; i >= 0; i--){
            if(fields[y][i] == compare){
                counter++;
                if(counter == 3) return true;
            }
            else
                break;
        }
        //horizontal, rechts
        for(int i = x +1; i < fields[y].length; i++){
            if(fields[y][i] == compare){
                counter++;
                if(counter == 3) return true;
            }
            else
                break;
        }
        counter = 0;

        //vertikal, aufwärts
        for(int i = y -1; i >= 0; i--){
            if(fields[i][x] == compare){
                counter++;
                if(counter == 3) return true;
            }
            else
                break;
        }
        //vertikal, abwärts
        for(int i = y +1; i < fields.length; i++){
            if(fields[i][x] == compare){
                counter++;
                if(counter == 3) return true;
            }
            else
                break;
        }
        counter = 0;

        //diagonal, oben-links nach unten-rechts (aufwärts)
        int i = y - 1;
        int c = x - 1;
        while(i >= 0 && c >= 0){
            if(fields[i][c] == compare){
                counter++;
                if(counter == 3) return true;
            }
            else
                break;
            i--;
            c--;
        }
        //diagonal, oben-links nach unten-rechts (abwärts)
        i = y + 1;
        c = x + 1;
        while(i < fields.length && c < fields[i].length){
            if(fields[i][c] == compare){
                counter++;
                if(counter == 3) return true;
            }
            else
                break;
            i++;
            c++;
        }
        counter = 0;

        //diagonal, unten-links nach oben-rechts (aufwärts)
        i = y - 1;
        c = x + 1;
        while(i >= 0 && c < fields[i].length){
            if(fields[i][c] == compare){
                counter++;
                if(counter == 3) return true;
            }
            else
                break;
            i--;
            c++;
        }
        //diagonal, unten-links nach oben-rechts (abwärts)
        i = y + 1;
        c = x - 1;
        while(i < fields.length && c >= 0){
            if(fields[i][c] == compare){
                counter++;
                if(counter == 3) return true;
            }
            else
                break;
            i++;
            c--;
        }
        return false;
    }
}
