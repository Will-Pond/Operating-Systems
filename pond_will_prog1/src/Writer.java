import java.util.Random;
import java.util.concurrent.Semaphore;

public class Writer extends Thread {

    static Shared share;
    static Semaphore rmutex;
    static Semaphore wmutex;
    static Semaphore M1;
    static Semaphore M2;
    static Semaphore M3;


    public Writer(Shared obj1, Semaphore r, Semaphore w, Semaphore M_one, Semaphore M_two, Semaphore M_three){

        share = obj1;
        rmutex = r;
        wmutex = w;
        M1 = M_one;
        M2 = M_two;
        M3 = M_three;

     }

    public void run() {

        Random random = new Random();
        int random_number = random.nextInt(31);

        //entry Section

        try{

            wmutex.acquire();
            System.out.println("WRITER "+this.getName()+" is ENTERING CODE");
            share.incWriteCount();
            if(share.getWriteCount() == 1){
                M1.acquire();
            }

            wmutex.release();
            M2.acquire();

        }catch(Exception e){
            System.out.println("An error Occurred in the Write entry");
        }

        //Critical Section
        try{

            System.out.println("WRITER "+this.getName()+" is now in Critical Section performing WRITE");
            System.out.println("WRITER "+this.getName()+" is writing for "+random_number+" milliseconds");
            sleep(random_number);
            M2.release();

        }catch(Exception e) {
            System.out.println("An error Occurred in the Write critical");
           }

        //Exit Section
        try{
            System.out.println("WRITER "+this.getName()+" is now exiting");
            wmutex.acquire();
            share.decWriteCount();
            if(share.getWriteCount() == 0){
                M1.release();
            }
            wmutex.release();

        }catch(Exception e) {
           System.out.println("An error Occurred in the Write exit");
        }

    }

}
