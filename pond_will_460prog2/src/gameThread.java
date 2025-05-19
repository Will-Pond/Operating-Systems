import java.io.*;
import java.net.Socket;
import java.util.Random;

public class gameThread extends Thread
{
    private Socket toclient;

    private String secret_code;

    public gameThread(Socket toclientsocket)
    {
        toclient = toclientsocket;
    }

    public void run()
    {
        try {

            DataInputStream instream;
            DataOutputStream outstream;

            instream = new DataInputStream(toclient.getInputStream());
            outstream = new DataOutputStream(toclient.getOutputStream());
            PrintWriter out = new PrintWriter(outstream, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(instream));


            Random random = new Random();
            StringBuilder code = new StringBuilder();

            for (int i = 0; i < 4; i++)
            {
                code.append(random.nextInt(10));
            }

            secret_code = code.toString();
            System.out.println("CODE : "+secret_code);

            String result = "    ";
            int counter = 0;


            while ((!result.equals("BBBB")) && counter <= 20)
            {
                if (counter == 0)
                {
                    out.println("GO");
                    counter++;
                }

                String guess = String.valueOf(in.readLine());

                if (guess.equals("QUIT"))
                {
                    break;
                }
                else
                {
                    result = ProcessGuess(guess);
                    out.println(result);
                    counter++;
                }

            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public String ProcessGuess(String guess)
    {
        StringBuilder number1 = new StringBuilder(secret_code);
        StringBuilder number2 = new StringBuilder(guess);

        String bbb = "";
        String ccc = "";
        String sss = "";
        String code = "";


        for (int i = 0; i < number2.length(); i=i) {

            if ((number1.charAt(i) == number2.charAt(i)))
            {
                bbb = bbb + "B";
                number1.deleteCharAt(i);
                number2.deleteCharAt(i);

            }
            else
            {
                i++;
            }

        }

        while(number1.length() != 0)
        {
            boolean flag = false;

            for(int b = 0; b < number2.length() && number1.length() != 0; b++)
            {
                char t = number1.charAt(0);
                char x = number2.charAt(b);

                if (t == x)
                {
                    ccc = ccc + "C";
                    number1.deleteCharAt(0);
                    number2.deleteCharAt(b);
                    flag = true;

                }
            }

            if(!flag)
            {
                sss = sss + " ";
                number1.deleteCharAt(0);
            }
        }

        code = ccc + bbb + sss;

        return code;
    }
}



