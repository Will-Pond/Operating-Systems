import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Prog3
{
    public static void main(String[] args)  {

        try {

            //Part A

            System.out.println("PART A.");
            System.out.println("*********\n\n");

            List<Future<Integer>> allFutures = new ArrayList<>();

            ExecutorService ES = Executors.newFixedThreadPool(7);

            myCounter mcount = new myCounter();


            for (int m = 0; m < 50; m++) {
                myCallable temp = new myCallable(m, m);
                allFutures.add(m, ES.submit(temp));
            }

            for (int n = 0; n < allFutures.size(); n++) {
                int result = allFutures.get(n).get();
                System.out.println("Thread " + n + " returned " + result);
            }

            ES.shutdown();

            //Part B

            System.out.println("\n\nPART B.");
            System.out.println("*******\n\n");

            Future<?>[] runFutures = new Future<?>[50];

            ExecutorService ES2 = Executors.newCachedThreadPool();

            for (int b = 51; b < 101; b++) {
                myRunnable ran = new myRunnable(mcount);
                runFutures[b - 51] = ES2.submit(ran);

            }

            for (int c = 0; c < runFutures.length; c++) {
                runFutures[c].get();

            }

            ES2.shutdown();

            int thecount = mcount.getCount();

            System.out.println("\nFinal counter value is " + thecount);

            System.out.println("\nEnd of Execution");


        }
        catch (Exception e)
        {

        }
    }
}

