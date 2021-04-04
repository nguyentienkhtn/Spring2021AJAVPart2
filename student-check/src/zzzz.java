import java.util.Scanner;

public class zzzz {

    public static void main(String[] args) {
        int vonglap = vonglap();
        int sumz = tong(vonglap);
        System.out.println(sumz);
    }

    static boolean dk(int number) {
        return number > 0;
    }

    static int vonglap() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a number");
            int number = scan.nextInt();
            if (dk(number))
                return number;
        }
    }

    static int sole(int number) {
        for (int i = 1; i <= number; i++) {
            if (i % 2 != 0)
                System.out.print(i);
        }
        return number;
    }

    static int tong(int number) {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            if (i % 2 != 0)
                sum += i;
        }
        return sum;

    }
}