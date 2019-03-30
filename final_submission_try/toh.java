package final_submission_try;

import java.io.*;
import java.util.Scanner;

public class toh {
    public static void printToh(int n, int[] tower){
        int i=0;
        int d1=0;
        int d2=0;
        int d3=0;
        for(i=0; i<6*n; i++){
            if((i+1)%n==0 && (i+1)%(2*n)!=0){
                System.out.print(" ");
                if((i+1)>2*n){
                    System.out.print(" ");
                }
//                if((i+1)>4*n){
//                    System.out.print(" ");
//                }
                System.out.print("!");
            }
            else{
                System.out.print(" ");
            }
        }
        System.out.println();
        for(i=0; i<n; i++){
            d1= tower[i];
            d2 = tower[n+i];
            d3 = tower[2*n+i];

            for(int j=n; j>0;j--){
                if(d1==j){
                    System.out.print("#");
                    d1--;
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.print("!");
            d1 = tower[i];
            for(int j=0; j<n;j++){
                if(d1>=j+1){
                    System.out.print("#");
//                    d1--;
                }
                else{
                    System.out.print(" ");
                }
            }

            System.out.print(" ");
            for(int j=n; j>0;j--){
                if(d2==j){
                    System.out.print("#");
                    d2--;
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.print("!");
            d2 = tower[n+i];
            for(int j=0; j<n;j++){
                if(d2>=j+1){
                    System.out.print("#");
//                    d2--;
                }
                else{
                    System.out.print(" ");
                }
            }

            System.out.print(" ");
            for(int j=n; j>0;j--){
                if(d3==j){
                    System.out.print("#");
                    d3--;
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.print("!");
            d3 = tower[2*n+i];
            for(int j=0; j<n;j++){
                if(d3>=j+1){
                    System.out.print("#");
//                    d3--;
                }
                else{
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
        for(int k=0; k< 10; k++){
            System.out.print("-");

        }
        System.out.println();

    };

    public static void print_simple(int n, int[] tower){
        for(int i=0; i<n; i++){
            System.out.print(tower[i]);
            System.out.print(" ");
            System.out.print(tower[n+i]);
            System.out.print(" ");
            System.out.println(tower[2*n+i]);
        }
        for(int i=0; i< 10; i++){
            System.out.print("-");

        }
        System.out.println();

    }

    public static void toh_move(int n, int a, int b, int[] tower){
        System.out.println("Move from: "+ a + " to "+b);


        int value=0;
        int b_change=0;
        for(int j=(a-1)*n; j<(a)*n; j++){
            if(tower[j]!=0){
                value = tower[j];
                tower[j]=0;
                break;
            }
        }
        for(int j=(b-1)*n; j<(b)*n; j++){
            if(tower[j]!=0){
                tower[j-1] = value;
                b_change=1;

                
                break;
            }
        }
        if(b_change==0){
            tower[n*(b)-1] = value;
        }
    }
    public static void main(String[] args) throws IOException
   {
        File file = new File("/Users/tanmaygoyal/Downloads/toh_with_recursion.txt");

//        java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] strs = new String[2];
        String st;
        st = br.readLine();
        int n = Integer.parseInt(st);
        st = br.readLine();
        int s = Integer.parseInt(st);

        int[] tower = new int[n*3];
        for(int i=0; i<3*n; i++){
            tower[i] = 0;
        }
        for(int i=n*(s-1); i<s*n; i++){
            tower[i] = i%n+1;
        }

//        System.out.println(n);
//        System.out.println(s);
        printToh(n, tower);

        while ((st = br.readLine()) != null) {
            strs = st.trim().split("\\s+");

            int x = Integer.parseInt(strs[0]);
            int y = Integer.parseInt(strs[1]);
            System.out.println("Press \"ENTER\" to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            toh_move(n,x, y,tower);
            printToh(n, tower);
//            System.out.println(a);
//            System.out.println(b);

        }


//        printToh(n, 2, 2, tower);

    }
}

