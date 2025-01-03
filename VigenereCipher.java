package vigenerecipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VigenereCipher1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text : ");
        String s = sc.nextLine();

        System.out.print("Enter key : ");
        String key = sc.nextLine();

        System.out.print("0 : encryption  ::  1 : decryption    ::");
        int c = sc.nextInt();
        if(c==0) System.out.println("Encryption is : "+enc(s,key));
        else if(c==1) System.out.println("decryption is : "+dec(s,key));
    }

    public static char[][] Table(String key) {
        List<Character> all = new ArrayList<>();
        List<Character> copy = new ArrayList<>();
        int c = 0;
        for (char i = 32; i <= 126; i++) {
            all.add(i);
            copy.add(i);
            c++;
        }

        for (int i = key.length() - 1; i >= 0; i--) {
            Character temp = key.charAt(i);
            copy.remove(temp);
            copy.add(0, temp);
        }
        System.out.println(copy.toString());

        char[][] table = new char[96][96];

        for (int i = 1; i < 96; i++) {
            table[0][i] = all.get(i - 1);
            table[i][0] = all.get(i - 1);
        }

        table[0][0] = '0';
        for (int i = 1; i < 96; i++) {
            for (int j = 1; j < 96; j++) {
                table[i][j] = copy.get(j - 1);
            }
            copy.addLast(copy.get(0));
            copy.removeFirst();
        }

        return table;
    }
    public static String enc(String s, String key) {
        char[] keys = key.toCharArray();
        char[][] table = Table(key);
        char[] keyStr = new char[s.length()];
        int c = 0;
        
        for (int i = 0; i < s.length(); i++) {

            keyStr[i] = keys[c];
            c++;
            if (c == key.length()) {
                c = 0;
            }
        }
        String l = "";

        for (int i = 0; i < s.length(); i++) {
            int x = keyStr[i] - 31;
            int y = s.charAt(i) - 31;
            l += table[x][y];
        }
        return l;
    }

    public static String dec(String s, String key) {
        char[] keys = key.toCharArray();
        char[][] table = Table(key);
        char[] keyStr = new char[s.length()];

        int c = 0;

        for (int i = 0; i < s.length(); i++) {

            keyStr[i] = keys[c];
            c++;
            if (c == key.length()) {
                c = 0;
            }
        }
        String l = "";
        for (int i = 0; i < s.length(); i++) {
            int x = keyStr[i] - 31;
            int y=1;
            for(;y<96;y++)
            {
                if(table[x][y]==s.charAt(i))
                {
                    break;
                }
            }
            l += table[0][y];
        }
        return l;
    }
}
