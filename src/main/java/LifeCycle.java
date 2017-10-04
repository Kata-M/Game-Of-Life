import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by KataMar on 29/09/17.
 */
public class LifeCycle extends Person{

    public static void run (int size, double pill, double pdead, int minDays, int maxDays, int infectedM, int infectedN){
     /*
        Scanner scanner = new Scanner(System.in);

        //ASK USER DIMENSIONS OF THE WORLD
        System.out.println("Give n, where n x n is the dimension of the matrix representing the world?" );
        int size = scanner.nextInt();


        //ASK USER PROBABILITY OF ILL
        System.out.println("Probability of infection others?" );
        double pill = scanner.nextDouble();
        while(pill<0 || pill>1){
            System.out.println("Probability is out of allowed range 0 < P < 1 . \n Give the correct probability. ");
            pill = scanner.nextDouble();
        }


        //ASK USER PROBABILITY OF DEAD
        System.out.println("Probability of dying?" );
        double pdead = scanner.nextDouble();
        while(pdead<0 || pdead>1){
            System.out.println("Probability is out of allowed range 0 < P < 1 . \n Give the correct probability. ");
            pdead = scanner.nextDouble();
        }

        //ASK USER THE INTERVAL OF ILL DAYS
        System.out.println("Give the minimum number of sick days. ");
        int minDays = scanner.nextInt();
        System.out.println("Give the max number of sick days. ");
        int maxDays = scanner.nextInt();
        while(maxDays < minDays){
            System.out.println("The max days you inputed is larger than the inputed minimum days. Give max days again.");
            maxDays = scanner.nextInt();
        }

        //ASK USER COORDINATES OF INFECTED PERSON
        System.out.println("Coordinates of infected cell in the start of the simulation? ");
        System.out.println("Give the coordinate of the row.");
        int infectedM = scanner.nextInt();
        while(infectedM > size-1){
            System.out.println("The coordinate number is out of range of the population. Give another coordinate");
            infectedM = scanner.nextInt();
        }
        System.out.println("Give the coordinate of the column.");
        int infectedN = scanner.nextInt();

        while(infectedN > size-1){
            System.out.println("The coordinate number is out of range of the population. Give another coordinate");
            infectedN = scanner.nextInt();
        }



        //PRINTING USER'S INPUT
        System.out.print("Here is what you inputed (size, pill, pdead): " + size + "    " + pill + "   " + "  "+ pdead+ "\n");
        System.out.println("(minDays, maxDays) " + minDays+ " , " + maxDays);
    */
   // ---------------------------------------------------------------------------------------------------------------------------//

        //MAKING THE WORLD WITH THE PERSONS
        Person [][] world = new Person[size][size];
        //counter for one run of the simulation. As long as someone has sick days left, the simulation is running.
        int numberOfIll = 0;

        System.out.println("Coordinates of everyone. ");
        //GIVING COORDINATES FOR EVERYONE
        for(int m = 0;m<size; m++) {
            for (int n = 0; n < size; n++) {

                world[m][n] = new Person();
                world[m][n].coordinates[0] = m;
                world[m][n].coordinates[1] = n;

                //infect the initial individual corresponding to the user input of the coordinates of the ill person
                if(world[m][n].coordinates[0] == infectedM && world[m][n].coordinates[1] == infectedN ){
                    world[m][n].status = 2; //this person becomes ill first day (not infecting others)
                    //count the number of sick days for this individual
                    Random ran = new Random();
                    world[m][n].days_sick = ran.nextInt(maxDays-minDays+1) + minDays;
                    // increase the number of ill in the world to 1
                    numberOfIll++ ;

                }

                System.out.print("[" + world[m][n].coordinates[0] + ",");
                System.out.print(world[m][n].coordinates[1] + "]");
                if(n == size-1) System.out.println();
            }
        }

        System.out.println("Status of everyone IN THE START. ");
        for(int m = 0 ; m< size; m++){
            for(int n = 0; n < size; n ++){


                System.out.print("[ " + world[m][n].status );
                System.out.print(" ]");
                if(n == size-1) System.out.println();

            }
        }

        System.out.println();
        System.out.println("Sick days of everyone IN THE START. ");
        for(int m = 0 ; m< size; m++){
            for(int n = 0; n < size; n ++){


                System.out.print("[ " + world[m][n].days_sick );
                System.out.print(" ]");
                if(n == size-1) System.out.println();

            }
        }

    //---------------------------------------------------------------------------------------------------------------------------------//

                    //COLLECT NEIGHBOURS FOR EACH PERSON
                    for(int m = 0 ; m < size ; m ++) {
                        for (int n = 0; n < size; n++) {

                            //NEIGHBOURS

                            //TOP LEFT CORNER
                            if (m == 0 && n == 0) {
                                world[m][n].neighbours = new Person[3];
                                world[m][n].neighbours[0] = world[m][n+1];
                                world[m][n].neighbours[1] = world[m+1][n];
                                world[m][n].neighbours[2] = world[m+1][n+1];

                              //  System.out.println("top left corner " + m + " , "+n );
                                continue;
                            }
                            //TOP RIGHT CORNER
                            if (m == 0 && n == size-1) {
                                world[m][n].neighbours = new Person[3];
                                world[m][n].neighbours[0] = world[m][n-1];
                                world[m][n].neighbours[1] = world[m+1][n-1];
                                world[m][n].neighbours[2] = world[m+1][n];
                              //  System.out.println("top right corner"+ m + " , "+n);
                                continue;
                            }
                            //BOTTOM LEFT CORNER
                            if (m == size-1 && n == 0) {
                                world[m][n].neighbours = new Person[3];
                                world[m][n].neighbours[0] = world[m-1][n];
                                world[m][n].neighbours[1] = world[m-1][n+1];
                                world[m][n].neighbours[2] = world[m][n+1];
                              //  System.out.println("bottom left corner"+ m + " , "+n);
                                continue;
                            }
                            //BOTTOM RIGHT CORNER
                            if (m == size-1 && n == size-1) {
                                world[m][n].neighbours = new Person[3];
                                world[m][n].neighbours[0] = world[m-1][n-1];
                                world[m][n].neighbours[1] = world[m-1][n];
                                world[m][n].neighbours[2] = world[m][n-1];
                              //  System.out.println("bottom right corner"+ m + " , "+n);
                                continue;
                            }
                            //LEFT SIDE
                            if ( 0 < m  && m < size && n == 0) {
                                world[m][n].neighbours = new Person[5];
                                world[m][n].neighbours[0] = world[m-1][n];
                                world[m][n].neighbours[1] = world[m-1][n+1];
                                world[m][n].neighbours[2] = world[m][n+1];
                                world[m][n].neighbours[3] = world[m+1][n];
                                world[m][n].neighbours[4] = world[m+1][n+1];
                             //   System.out.println("left side"+ m + " , "+n);
                                continue;
                            }
                            //RIGHT SIDE
                            if (0 < m && m < size && n == size-1) {
                                world[m][n].neighbours = new Person[5];
                                world[m][n].neighbours[0] = world[m-1][n-1];
                                world[m][n].neighbours[1] = world[m-1][n];
                                world[m][n].neighbours[2] = world[m][n-1];
                                world[m][n].neighbours[3] = world[m+1][n-1];
                                world[m][n].neighbours[4] = world[m+1][n];
                             //   System.out.println("right side"+ m + " , "+n);
                                continue;
                            }
                            //TOP
                            if (m == 0 && 0 < n && n < size) {
                                world[m][n].neighbours = new Person[5];
                                world[m][n].neighbours[0] = world[m][n-1];
                                world[m][n].neighbours[1] = world[m][n+1];
                                world[m][n].neighbours[2] = world[m+1][n-1];
                                world[m][n].neighbours[3] = world[m+1][n];
                                world[m][n].neighbours[4] = world[m+1][n+1];
                             //   System.out.println("top"+ m + " , "+n);
                                continue;
                            }
                            //BOTTOM
                            if (m == size-1 && 0 < n && n < size ) {
                                world[m][n].neighbours = new Person[5];
                                world[m][n].neighbours[0] = world[m-1][n-1];
                                world[m][n].neighbours[1] = world[m-1][n];
                                world[m][n].neighbours[2] = world[m-1][n+1];
                                world[m][n].neighbours[3] = world[m][n-1];
                                world[m][n].neighbours[4] = world[m][n+1];
                             //   System.out.println("bottom"+ m + " , "+n);
                                continue;
                            }

                            //IN GENERAL THE NEIGHBOURS ARE
                            world[m][n].neighbours = new Person[8];
                            world[m][n].neighbours[0] = world[m-1][n-1];
                            world[m][n].neighbours[1] = world[m-1][n];
                            world[m][n].neighbours[2] = world[m-1][n+1];

                            world[m][n].neighbours[3] = world[m][n-1];
                            world[m][n].neighbours[4] = world[m][n+1];

                            world[m][n].neighbours[5] = world[m+1][n-1];
                            world[m][n].neighbours[6] = world[m+1][n];
                            world[m][n].neighbours[7] = world[m+1][n+1];
                         //   System.out.println("In general part. "+ m + " , "+n);

                        }
                    }
     //-------------------------------------------------------------------------------------------------------------------------------------------//


        double ill ;
        double dead ;
        int iteration = 1;
        int numberOfSuffered=0;

        int infectedPerDay;
        int deadPerDay;
        int recoveredPerDay;

        //int numberOfIll
        int accInfectPerDay = 0; //accumulated number of infected cells per day
        int accDeadPerDay = 0;  //accumulated number of deaths per day

        ArrayList<Integer> infectedPerDayList = new ArrayList<Integer>();
        ArrayList<Integer> deadPerDayList = new ArrayList<Integer>();
        ArrayList<Integer> recoveredPerDayList = new ArrayList<Integer>();

        ArrayList<Integer> illPerDayList = new ArrayList<Integer>();
        ArrayList<Integer> accInfectPerDayList = new ArrayList<Integer>();
        ArrayList<Integer> accDeadPerDayList = new ArrayList<Integer>();

        //The start values for the simulation before day 1
        infectedPerDayList.add(0);
        deadPerDayList.add(0);
        recoveredPerDayList.add(0);

        illPerDayList.add(1);
        accInfectPerDayList.add(0);
        accDeadPerDayList.add(0);

        //status
        // 0 = healthy not immune
        // 1 = healthy immune
        // 2 = ill first day (not infecting others)
        // 3 = ill (infects others)
        // 4 = dead

        //one iteration of a while loop represents one day
        while(numberOfIll > 0) {

            infectedPerDay= 0;
            deadPerDay = 0;
            recoveredPerDay = 0;


            System.out.println();
            System.out.println("START OF ITERATION # " + iteration);
            System.out.println("numberOfIll: "+ numberOfIll);
            for (int m = 0; m < size; m++) {
                for (int n = 0; n < size; n++) {
                    //IF ILL AND CAN INFECT OTHERS
                    if (world[m][n].status == 3) {

                        //the following is about the neighbours
                        //loop through neighbours
                        for (int i = 0; i < world[m][n].neighbours.length; i++) {
                            //compute the chance that this neighbour should be infected
                            ill = Math.random();
                            //System.out.println("Random ill: " + ill);

                            //infect neighbour
                            if (ill <= pill && world[m][n].neighbours[i].status == 0) {
                                //System.out.println("Infect (x__x)");
                                world[m][n].neighbours[i].status = 2; // becomes first day infected
                                //increase the ill count
                                numberOfIll++ ;
                                infectedPerDay++;
                                accInfectPerDay++;
                                numberOfSuffered++;
                                //System.out.println("numberOfIll: "+ numberOfIll );

                                //compute the number of days this individual will be sick
                                Random ran = new Random();
                                world[m][n].neighbours[i].days_sick = ran.nextInt(maxDays-minDays+1) + minDays;
                            }

                        }
                    }

                    //the following is about an individual and its status
                    if(world[m][n].status ==2 || world[m][n].status ==3) {

                        //check if this cell will die
                        dead = Math.random();
                       // System.out.println("Random dead: " + dead);
                        if (dead <= pdead) {
                           // System.out.println("Dead ( t ) ");
                            world[m][n].status = 4;         //the status of the cell becomes DEAD
                            deadPerDay++;
                            accDeadPerDay++;
                            world[m][n].days_sick = 0;
                            //since this cell is dead, it is not ill anymore. Hence, decrease the ill count
                            numberOfIll--;
                        } else {
                            //if the individual survives death on that day, the number of sick days will decrease --> the individual gets healthier
                            //decrease the number of sick days attached to the person here
                            world[m][n].days_sick-- ;

                            //RECOVERING
                            //if the cell does not have any ill days left, it is healthy immune. Hence, decrease the ill count.
                            if(world[m][n].days_sick == 0){
                                world[m][n].status = 1; //the cell becomes healthy immune
                                recoveredPerDay++;
                                numberOfIll--;
                            }
                        }
                    }

                    //change the ill status after one day
                    if(world[m][n].status == 2 && world[m][n].days_sick != 0){
                        world[m][n].status = 3;
                    }

                }
            }


            //------------------------------------------------------------------------------------------------------------------//
           /* System.out.println("Status of everyone in the end of Iteration # " + iteration );
            for(int m = 0 ; m< size; m++){
                for(int n = 0; n < size; n ++){


                    System.out.print("[ " + world[m][n].status );
                    System.out.print(" ]");
                    if(n == size-1) System.out.println();

                }
            }
            */
            iteration ++ ;
            //add the illPerDay to the array list to the next day
            infectedPerDayList.add(infectedPerDay);
            deadPerDayList.add(deadPerDay);
            recoveredPerDayList.add(recoveredPerDay);
            illPerDayList.add(numberOfIll);
            accInfectPerDayList.add(accInfectPerDay);
            accDeadPerDayList.add(accDeadPerDay);


            //end of while loop = end of a day
        }
    //------------------------------------------------------------------------------------------------------------------//

        System.out.println();
        System.out.println("Status of everyone FINAL. ");
        for(int m = 0 ; m< size; m++){
            for(int n = 0; n < size; n ++){


                System.out.print("[ " + world[m][n].status );
                System.out.print(" ]");
                if(n == size-1) System.out.println();

            }
        }
        System.out.println();
        System.out.println("Sick days of everyone FINAL. ");
        for(int m = 0 ; m< size; m++){
            for(int n = 0; n < size; n ++){


                System.out.print("[ " + world[m][n].days_sick );
                System.out.print(" ]");
                if(n == size-1) System.out.println();

            }
        }
 //----------------------------------------------------------------------------------------------------------------------------------------------------------//
        System.out.println("In each of the following arrays the index 0 contains data from the initial state of the simulation before the day one. ");
        System.out.println("The index number 1 maps to day 1, index 2 to day 2 and so on... ");
        System.out.println();
        //Number of infected people per day
        System.out.println("Number of individuals who get infected per day: ");
        System.out.print("[");
        for(int i = 0; i<infectedPerDayList.size(); i++){
            System.out.print(infectedPerDayList.get(i));
            if(i<infectedPerDayList.size()-1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println();

        //Number of people who died per day
        System.out.println("Number of individuals who died per day: ");
        System.out.print("[");
        for(int i = 0; i<deadPerDayList.size(); i++){
            System.out.print(deadPerDayList.get(i));

            if(i<deadPerDayList.size() -1){ System.out.print(",");}
        }
        System.out.println("]");
        System.out.println();




        //Number of people who have recovered per day
        System.out.println("Number of individuals who have recovered per day: ");
        System.out.print("[");
        for(int i = 0; i<recoveredPerDayList.size(); i++){
            System.out.print(recoveredPerDayList.get(i));

            if(i<recoveredPerDayList.size() -1){ System.out.print(",");}
        }
        System.out.println("]");
        System.out.println();

        //Number of ill per day
        System.out.println("Number of ill per day: ");
        System.out.print("[");
        for(int i = 0; i<illPerDayList.size(); i++){
            System.out.print(illPerDayList.get(i));

            if(i<illPerDayList.size() -1){ System.out.print(",");}
        }
        System.out.println("]");
        System.out.println();


        //Accumulated number of infected people per day
        System.out.println("Accumulated number of infected people per day: ");
        System.out.print("[");
        for(int i = 0; i<accInfectPerDayList.size(); i++){
            System.out.print(accInfectPerDayList.get(i));

            if(i<accInfectPerDayList.size() -1){ System.out.print(",");}
        }
        System.out.println("]");
        System.out.println();

        //Accumulated number of deaths per day
        System.out.println("Accumulated number of deaths per day: ");
        System.out.print("[");
        for(int i = 0; i<accDeadPerDayList.size(); i++){
            System.out.print(accDeadPerDayList.get(i));

            if(i<accDeadPerDayList.size() -1){ System.out.print(",");}
        }
        System.out.println("]");
        System.out.println();



        //---------------------------------------------------------------------------------------------------------------//

        //OUTPUT DATA TO A FILE (the number of pple who had to suffer the infection in one run of the simulation
       // File f_old = new File("data_for_average_of_suffered.txt");
       // f_old.delete();
        File out = new File("data_for_average_of_suffered.txt");
        FileOutputStream outstream = null;
        try{
            outstream = new FileOutputStream(out, true);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        PrintStream output = new PrintStream(outstream);
        //output.println(numberOfSuffered);
        output.println(accInfectPerDayList.get(accDeadPerDayList.size()-1));
        output.close();
        //-----------------------------------------------------------------------------------------------//
        File out2 = new File("infected_per_day.txt");
        FileOutputStream outstream2 = null;
        try{
            outstream2 = new FileOutputStream(out2, true);
        }catch(FileNotFoundException e){
            e.printStackTrace();

        }
        PrintStream output2 = new PrintStream(outstream2);
        output2.println();
        output2.println("NEW SIMULATION");
        output2.println("infected per day ");
        for(int i = 0; i < infectedPerDayList.size(); i++) {
            output2.println(infectedPerDayList.get(i));
        }
        output2.println("day number ");
        for(int i = 0; i < infectedPerDayList.size(); i++) {
            output2.println(i);
        }

        output2.close();


    }
}


