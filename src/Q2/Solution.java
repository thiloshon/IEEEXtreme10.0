package Q2;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Thiloshon on 22-Oct-16.
 */
public class Solution {
    private FastScanner in;
    private PrintWriter out;
    ArrayList<values> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution3. */

        new Solution().solve();
    }


    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        //generateSwapsEfficient();
        //writeResponse();
        out.close();
    }

    private void readData() throws IOException {
        long numberOfTests = in.nextInt();


        for (int x = 0; x < numberOfTests; x++) {


            int number = in.nextInt();
            for (int y = 0; y < number; y++) {
                arr.add(new values(in.nextInt(), 0));
            }

            solving();

            arr.clear();

        }

    }

    class values {
        int value;
        int nextOccurance = 0;

        public values(int value, int nextOccurance) {
            this.nextOccurance = nextOccurance;
            this.value = value;
        }
    }

    void solving() {

        int brush1counter = 0;
        ArrayList<values> brush1 = new ArrayList<>();
        ArrayList<values> brush2 = new ArrayList<>();
        int brush1counter2 = 0;

        for (int x = 0; x < arr.size(); x++) {
            for (int y = x + 1; y < arr.size(); y++) {
                if (arr.get(y).value == arr.get(x).value) {
                    arr.get(x).nextOccurance = y - x + 1;
                    break;
                }
            }
        }

        for (values bg : arr) {
            //System.out.println(bg.value + " " + bg.nextOccurance);
        }

        for (int d = 0; d < arr.size(); d++) {
            //System.out.println(arr.get(d).value + " Values are " + brush1counter + " " + brush1counter2);

            if (brush1counter == 1) {
                brush1.add(arr.get(d));
                brush1counter = arr.get(d).nextOccurance;
            } else if (brush1counter2 == 1) {
                brush2.add(arr.get(d));
                brush1counter2 = arr.get(d).nextOccurance;
            } else if (brush1counter == 0) {
                brush1.add(arr.get(d));
                brush1counter = arr.get(d).nextOccurance;
            } else if (brush1counter2 == 0) {
                brush2.add(arr.get(d));
                brush1counter2 = arr.get(d).nextOccurance;
            }else if (brush1counter2 >= brush1counter) {
                brush2.add(arr.get(d));
                brush1counter2 = arr.get(d).nextOccurance;
            } else {
                brush1.add(arr.get(d));
                brush1counter = arr.get(d).nextOccurance;
            }

            if (brush1counter > 0) {
                brush1counter--;
            }

            if (brush1counter2 > 0) {
                brush1counter2--;
            }
        }

        /*for (values vr : brush1) {
            System.out.print(vr.value + " ");
        }*/
        //System.out.println("dfdfd");

        /*for (values vr : brush2) {
            System.out.print(vr.value + " ");
        }*/

        int count =0;
        int countvalue = -1;

        for(values vf: brush1){
            if(vf.value!=countvalue){
                count++;
            }
            countvalue=vf.value;
        }

        countvalue=-1;

        for(values vf: brush2){
            if(vf.value!=countvalue){
                count++;
            }
            countvalue=vf.value;
        }

        System.out.println(count);

        brush1.clear();
        brush2.clear();



    }


    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
