public class myCounter {

    protected int count;

    public myCounter()
    {
        this.count = count;
    }

    public synchronized void increment(){
        count++;
    }
    public synchronized void decrement(){
        count--;
    }
    public synchronized int getCount(){
        return count;
    }

}
