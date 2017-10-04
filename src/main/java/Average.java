import java.io.*;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Created by KataMar on 03/10/17.
 */
public class Average {


    //does not work atm
    public static double average() throws IOException {

        double sum = 0 ;
        int count = 0;
        Scanner scan = null;
        try{
            scan = new Scanner(new File("data_for_average_of_suffered.txt"));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        while(scan.hasNextInt()){
            sum = sum + scan.nextInt();
            count++;
        }

        //count is the number of people in the population
        //so to get the percentage of people who had to suffer the infection we calculate
        //double percent = (sum/count)/sizeOfPopulation*100;
        return (sum/count);

    }

}
