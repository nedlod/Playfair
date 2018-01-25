import javax.crypto.Cipher;
import java.util.Scanner;

public class Playfair {

    static char cipher[][] = new char[5][5];
    static String message, cipherS;
    static StringBuilder plainTxt = new StringBuilder();
    static int index = 0, alphIndex = 0, length;
    static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        System.out.println("****Playfair Cipher****");
        System.out.println();
        System.out.println("Please enter key; ");
        Scanner kb = new Scanner(System.in);
        String key = kb.nextLine().toUpperCase();
        key = removeDuplicates(key).replaceAll("\\s+", "");

        System.out.println("Please write the message: ");
        message = kb.nextLine().toUpperCase();
        message = message.replaceAll("\\s+", "");
        message = message.replaceAll("[^a-zA-Z]", "");
        key = key.replaceAll("J", " ");
        key = key.replaceAll("\\s", "");
        key = key.replaceAll("[^a-zA-Z]", "");

        for (int i = 0; i < 25; i++) {
            StringBuilder k = new StringBuilder(key);
            if (key.indexOf(alphabet[i]) == -1) {
                k.append(alphabet[i]);
            }
            key = k.toString();
        }
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
        printCipher();

        if (message.length() % 2 != 0) {
            StringBuilder ex = new StringBuilder(message);
            ex.append('X');
            message = ex.toString();
        }
        pairByPair(message);


        System.out.print("The plain text is: " + plainTxt);

        kb.close();
    }


    public static String removeDuplicates(String s) {
        StringBuilder noDupes = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String yes = s.substring(i, i + 1);
            if (noDupes.indexOf(yes) == -1) {
                noDupes.append(yes);
            }
        }
        return noDupes.toString();
    }

    public static void printCipher() {
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(cipher[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void pairByPair(String message) {
        char A, B;
        if (message.length() % 2 == 0) {
            length = message.length() - 1;
        } else {
            length = message.length();
        }
        for (int i = 0; i < length; i += 2) {
            A = message.charAt(i);
            if (i + 1 > length) {
                B = 'X';
            } else {
                B = message.charAt(i + 1);
            }
            if (A == B) {
                B = 'X';
                i -= 1;
            }
            if (A == 'J') {
                A = 'I';
            }
            if (B == 'J') {
                B = 'I';
            }
            plainTxt.append(findCharPosition(A, B, cipher));
        }

    }

    public static String findCharPosition(char A, char B, char[][] matrix) {
        int msgIndex = 0, rowA = 0, rowB = 0, colA = 0, colB = 0;
        while (msgIndex < length) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (matrix[i][j] == A) {
                        rowA = i;
                        colA = j;
                    }
                    if (matrix[i][j] == B) {
                        rowB = i;
                        colB = j;
                    }


                }

            }
            msgIndex++;
        }
        cipherS = encrypt(rowA, rowB, colA, colB, matrix);
        return cipherS;

    }

    public static String encrypt(int rowA, int rowB, int colA, int colB, char[][] matrix) {
        char s1, s2;
        StringBuilder pair = new StringBuilder();
        if (rowA == rowB) {
            s1 = matrix[rowA][(colA + 1) % 5];
            s2 = matrix[rowB][(colB + 1) % 5];
            pair.append(s1).append(s2);
            return pair.toString();
        } else if (colA == colB) {
            s1 = matrix[(rowA + 1) % 5][colA];
            s2 = matrix[(rowB + 1) % 5][colB];
            pair.append(s1).append(s2);
            return pair.toString();
        } else if (rowA != rowB && colA != colB) {
            s1 = matrix[rowA][colB];
            s2 = matrix[rowB][colA];
            pair.append(s1).append(s2);
        }
        return pair.toString();

    }
}




