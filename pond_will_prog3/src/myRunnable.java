import java.util.Random;

public class myRunnable extends Thread {

    protected Random gen;
    protected myCounter count;

    myRunnable(myCounter count)
    {
        this.count = count;
        this.gen = new Random();
    }

   public void run() {

        int sleepTime = gen.nextInt(101) + 100;

        try
       {
           Thread.sleep(sleepTime);
       }

       catch (InterruptedException e)
       {

       }

       int SecondNumber = gen.nextInt(100) + 1;

        String thread = getName();

        if(SecondNumber % 2 == 0)
        {
            System.out.println(thread + " is INCrementing the counter");
            count.increment();
        }
        else
        {
            System.out.println(thread + " is DECrementing the counter");
            count.decrement();
        }
    }

}
