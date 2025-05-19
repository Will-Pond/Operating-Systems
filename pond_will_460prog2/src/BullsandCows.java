import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BullsandCows {
    public static void main(String[] args) {

        DataInputStream inclient;
        DataOutputStream outclient;
        Socket socketclient;

        Scanner sc = new Scanner(System.in);

        try {

            socketclient = new Socket("localhost", 12345);
            inclient = new DataInputStream(socketclient.getInputStream());
            outclient = new DataOutputStream(socketclient.getOutputStream());
            PrintWriter out = new PrintWriter(outclient, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(inclient));

            int counter = 0;
            String guessString = "";
            String response = in.readLine();

            while (counter < 20 && !guessString.equals("QUIT"))
            {
                if(response.equals("GO"))
                {
                    System.out.println("\nWelcome to Bulls and Cows. You will try to guess a 4 digit code using\n" +
                            "only the digits 0-9). You will lose the game if you are unable to guess\n" +
                            "the code correctly in 20 guesses. Good Luck!\n");
                }

                do
                {
                    System.out.print("Please enter your guess for the secret code or " + "\"QUIT\" : ");
                    guessString = sc.next();

                    if (guessString.equals("QUIT"))
                    {
                        System.out.println("\nGoodbye but please play again!");
                        out.println(guessString);
                        sc.close();
                        return;
                    }
                }
                while (!verifyInput(guessString));

                out.println(guessString);
                counter++;
                response = in.readLine();

                System.out.println(guessString+"   "+response);

                if(response.equals("BBBB"))
                {
                    System.out.println("\nCongratulations!!! You guessed the code correctly in "+counter+" guesses");
                    sc.close();
                    return;
                }

            }

            if(counter >= 20)
            {
                System.out.println("\nSorry – the game is over. You did not guess the code correctly in 20 moves.");
            }

            sc.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static boolean verifyInput(String gs)
    {
        for(int i = 0; i < gs.length(); i++)
        {
            if(!Character.isDigit(gs.charAt(i)))
            {
               System.out.println("Improperly formatted guess.");
               return false;
            }
        }

        if (gs.length() == 4 || gs.equals("QUIT"))
        {
            return true;
        }
        System.out.println("Improperly formatted guess.");
        return false;
    }
}