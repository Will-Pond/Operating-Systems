import java.util.Random;
import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
    static Shared share;
    static Semaphore rmutex;
    static Semaphore wmutex;
    static Semaphore M1;
    static Semaphore M2;
    static Semaphore M3;

   public Reader(Shared obj1, Semaphore r, Semaphore w, Semaphore M_one, Semaphore M_two, Semaphore M_three){

        share = obj1;
        rmutex = r;
        wmutex = w;
        M1 = M_one;
        M2 = M_two;
        M3 = M_three;

   }

    public void run(){

        Random random = new Random();
        int random_number = random.nextInt(21);

        //Entry Section

        try{

            M3.acquire();
            M1.acquire();
            rmutex.acquire();
            System.out.println("READER "+Thread.currentThread().getName()+" is ENTERING CODE");
            share.incReadCount();
            if(share.getReadCount() == 1){
                M2.acquire();
            }
            rmutex.release();
            M1.release();
            M3.release();

        } catch (Exception e) {
            System.out.println("An error occurred in the READ entry");
        }

            //Critical Section

        try{

            System.out.println("READER "+Thread.currentThread().getName()+" is now in Critical Section performing READ");
            System.out.println("READER "+Thread.currentThread().getName()+" is reading for "+random_number+" milliseconds");
            Thread.sleep(random_number);

        } catch (Exception e) {
           System.out.println("An error occurred in the READ critical");
        }

            // Exit Section

        try{

            System.out.println("READER "+Thread.currentThread().getName()+" is now exiting");
            rmutex.acquire();
            share.decReadCount();
            if(share.getReadCount() == 0){
                M2.release();
            }
            rmutex.release();

        } catch (Exception e) {
            System.out.println("An error occurred in the READ exit");
        }

    }

}
