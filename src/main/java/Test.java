import java.util.ArrayList;

/**
 * Created by KataMar on 03/10/17.
 */
public class Test {

    public static void main(String [] args) throws java.io.IOException {

        Average a = new Average();
        System.out.println(a.average());

        Estimating_S s = new Estimating_S();
        System.out.println("S: "+ s.estimate_S());

        CI ci = new CI();
        double [] interval = ci.conf_interval();
        System.out.println("Confidence interval for the average number of pple who have suffered the infection: ");
        for(int i = 0; i<2; i++) {
            System.out.println(interval[i]);
        }


        ArrayList<Double> probabilities = new ArrayList<Double>();
        double p = 0.00;
        while(p<1){
            probabilities.add(p);
            System.out.println("p : "+ p);
            p = p + 0.05;

        }
        probabilities.add(p);
        System.out.println("p : "+ p);


    }
}
