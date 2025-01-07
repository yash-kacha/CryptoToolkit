import java.util.Scanner;

public class Ceaser {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a String : ");
        String ip = sc.nextLine();

        System.out.print("Enter a key (int) : ");
        int n = sc.nextInt();

        System.out.print("Encode == 0  :: Decode ==1 : ");
        int encdc = sc.nextInt();

        System.out.println(enc(ip, n, encdc));
    }

    public static String enc(String ip, int n, int encdc) {

        if (encdc == 1) {
            n *= -1;
        }
        while (n < 0) {
            n += 26;
        }
        String ans = "";
        char temp;
        for (int i = 0; i < ip.length(); i++) {
            temp = ip.charAt(i);

            if (temp >= 'A' && temp <= 'Z') 
            { 
                ans += ((char) (((temp - 'A' + n) % 26 + 26) % 26 + 'A'));
            } 
            else if (temp >= 'a' && temp <= 'z') 
            {
                ans += ((char) (((temp - 'a' + n) % 26 + 26) % 26 + 'a'));
            } 
            else if(temp==' ') ans+=' ';
            else 
            {
                System.out.println("Error in the input String");
                return "";
            }
        }
        return ans;
    }

}
