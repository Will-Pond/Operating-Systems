import java.util.Random;
import java.util.concurrent.Callable;

public class myCallable implements Callable<Integer> {

    protected int ID;
    protected int value;
    protected Random gen;

    public myCallable(int id, int value)
    {
        this.ID = id;
        this.value = value;
        this.gen = new Random();
    }

    public Integer call()
    {
       int sleepTime = gen.nextInt(121) + 30;

        try
        {
            Thread.sleep(sleepTime);
        }
        catch (InterruptedException e)
        {

        }

        int other = gen.nextInt(10) + 1;

       System.out.println("Thread " + this.ID + " is returning the sum of "+ this.value + " + " + other);

       return this.value + other;

    }
}
