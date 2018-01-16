import java.util.Scanner;

public class Playfair_Cipher {
    static char cipher[][] = new char[5][5];
    static String message, decipherS, answer;
    static StringBuilder cipherTxt = new StringBuilder();
    static StringBuilder plainTxt = new StringBuilder();
    static int index = 0, alphIndex = 0, length;
    static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        System.out.println("*****Playfair cipher*****");
        System.out.println();
        System.out.println("Please write the key");
        Scanner kb = new Scanner(System.in);
        String key = kb.nextLine().toUpperCase();
        key = removeDuplicates(key).replaceAll("\\s+", "");
        key = key.replaceAll("[^a-zA-Z]", "");

        System.out.print("Please write the message: ");
        message = kb.nextLine().toUpperCase();

        //Премахване на интервалите
        message = message.replaceAll("\\s+", "");

        //Премахване на J
        key = key.replaceAll("J", " ");
        key = key.replaceAll("\\s", "");

        //Премахване на повтарящите се букви от ключа и азбуката
        for (int i = 0; i < 25; i++) {
            StringBuilder k = new StringBuilder(key);
            if (key.indexOf(alphabet[i]) == -1) {//-1 означава че буквата не се повтаря
                k.append(alphabet[i]);
            }
            key = k.toString();
        }
        //Добавяне на ключа и азбуката към матрицата
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (index < key.length()) {
                    cipher[i][j] = key.charAt(index);
                    index++;
                } else {
                    cipher[i][j] = alphabet[alphIndex++];
                }

            }

        }
        //Добавяне на X ако съобщението е нечетно
        if (message.length() % 2 != 0) {
            StringBuilder ex = new StringBuilder(message);
            ex.append('X');
            message = ex.toString();


        }

    }
}







