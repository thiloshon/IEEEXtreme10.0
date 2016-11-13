package Q4;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Thiloshon on 22-Oct-16.
 */
public class Solution {

    private FastScanner in;
    private PrintWriter out;

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
        long carbonLeft = in.nextInt();
        long hydrogenLeft = in.nextInt();
        long oxygenLeft = in.nextInt();

        long glucose = 0;
        long water = 0;
        long carbondiOxide = 0;

        while (carbonLeft > 0 || hydrogenLeft > 0 || oxygenLeft > 0) {
            //System.out.println(carbonLeft+" "+ hydrogenLeft+" "+oxygenLeft);
            if (carbonLeft >= 6 && hydrogenLeft >= 12 && oxygenLeft >= 6 && (((carbonLeft - 6) % 6 == 0 && (hydrogenLeft - 12) % 12 == 0 && (oxygenLeft - 6) % 6 == 0) || ((carbonLeft - 6) > 0 && (oxygenLeft - 6) % 2 == 0) || ((hydrogenLeft - 12) % 2 == 0 && oxygenLeft - 6 > 0))) {
                glucose++;
                carbonLeft -= 6;
                hydrogenLeft -= 12;
                oxygenLeft -= 6;
            } else if (carbonLeft >= 1 && oxygenLeft >= 2 && (((carbonLeft - 1) > 0 && (oxygenLeft - 2) % 2 == 0) || (oxygenLeft - 2 > 0))) {
                carbondiOxide++;
                carbonLeft -= 1;
                oxygenLeft -= 2;
            } else if (hydrogenLeft >= 2 && oxygenLeft >= 1 && ((hydrogenLeft - 2) % 2 == 0 && oxygenLeft - 1 > 0)) {
                water++;
                hydrogenLeft -= 2;
                oxygenLeft -= 1;
            } else {
                break;
            }
        }

        if (carbonLeft > 0 || hydrogenLeft > 0 || oxygenLeft > 0) {
            System.out.println("Error");
        } else {
            System.out.println(water + " " + carbondiOxide + " " + glucose);
        }

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
