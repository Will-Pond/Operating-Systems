import java.util.concurrent.Semaphore;
import java.util.Random;
public class Prog1 {

    public static void main(String[] args){

        try{
            Thread t;
            Random random = new Random();
            Shared share = new Shared(0,0);

             Semaphore rmutex = new Semaphore(1,true);
             Semaphore wmutex = new Semaphore(1,true);
             Semaphore M1 = new Semaphore(1,true);
             Semaphore M2 = new Semaphore(1,true);
             Semaphore M3 = new Semaphore(1,true);

            for (int i = 1; i <= 100; i++){

                int randomNumber = random.nextInt(3);

                if(randomNumber == 0){
                      Writer wr = new Writer(share, rmutex, wmutex, M1, M2, M3);
                    System.out.println("Creating new WRITER "+wr.getName());
                    wr.start();


                }
                if(randomNumber != 0){

                    t = new Thread(new Reader(share, rmutex, wmutex, M1, M2, M3));
                    System.out.println("Creating new READER "+t.getName());
                    t.start();

                }

                Thread.sleep(random.nextInt(31));
            }

            Thread.sleep(2000);
            System.out.println("####### MAIN THREAD ENDING NOW!");

        }catch(Exception e){
            System.err.println("An error occurred: " + e.getMessage());
        }

    }
}
