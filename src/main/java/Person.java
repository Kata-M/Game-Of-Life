/**
 * Created by KataMar on 28/09/17.
 */
public class Person {

    // coordinates stores row and column coordinates of the person on the world matrix
    // r = row, k = kolumn

    int [] coordinates = new int [2];
    Person [] neighbours;

    //status
    // 0 = healthy not immune
    // 1 = healthy immune
    // 2 = ill first day (not infecting others)
    // 3 = ill (infects others)
    // 4 = dead

    int status = 0;
    int days_sick;




    public void setStatus(int state){
        this.status = state;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setDays_sick(int days){
        this.days_sick = days;
    }

    public void printCoordinates(){
        System.out.println( this.coordinates);
    }



}
