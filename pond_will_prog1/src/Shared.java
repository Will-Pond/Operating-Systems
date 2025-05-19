public class Shared {
    private int readCount;
    private int writeCount;

    public Shared(int first, int second){
        readCount = readCount;
        writeCount = writeCount;
    }

    int getWriteCount(){
        return writeCount;
    }

    int getReadCount(){
        return readCount;
    }

    void incReadCount(){
        readCount = readCount + 1;
    }

    void incWriteCount(){
        writeCount = writeCount + 1;
    }

    void decReadCount(){
        readCount = readCount - 1;
    }

    void decWriteCount(){
        writeCount = writeCount - 1;
    }

}
