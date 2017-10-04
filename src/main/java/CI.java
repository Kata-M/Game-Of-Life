/**
 * Created by KataMar on 03/10/17.
 */
public class CI {

    public static double[] conf_interval() throws java.io.IOException{

        Average a = new Average();
        Estimating_S s = new Estimating_S();

        double x = a.average();
        double sd = s.estimate_S();
        double lower_bound=0;
        double upper_bound = 0;

        //t for alpha = 0.05 and two sided confidence interval alpha/2
        //we assume that the number of trials is (100), hence f = 99
        //from the t table
        double t0025 = 1.98;

        lower_bound = x - t0025 * (sd / Math.sqrt(100));
        upper_bound = x + t0025 * (sd / Math.sqrt(100));


        double [] interval = {lower_bound , upper_bound};
        return interval;
    }
}
