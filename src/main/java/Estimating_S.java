import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by KataMar on 03/10/17.
 */
public class Estimating_S {

    public static double estimate_S() throws java.io.IOException{

        Average a = new Average();
        double x = a.average();
        int xi=0;
        double variance = 0;
        int count = 0;

        double sum = 0;

        Scanner scan = null;
        try{
            scan = new Scanner(new File("data_for_average_of_suffered.txt"));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        while(scan.hasNextInt()){
            xi = scan.nextInt();
            sum = sum + (xi-x)*(xi-x);
            count++;
        }
        variance = sum/(count-1);
       // System.out.println("Count: "+ count);
        return Math.sqrt(variance);

    }
}
