package Q6;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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

    private void readData() throws IOException {
        long numberOfTests = in.nextInt();
        //System.out.println(numberOfTests);


        for (int x = 0; x < numberOfTests; x++) {
            ArrayList<Integer> piles = new ArrayList<>();
            int noOfgames = in.nextInt();
            //System.out.println(noOfgames);
            for (int h = 0; h < noOfgames; h++) {
                int noOfPiles = in.nextInt();
                for (int g = 0; g < noOfPiles; g++) {
                    piles.add(in.nextInt());
                }
            }

            Collections.sort(piles);


            int counter = 0;

            for (int g = 0; g < piles.size() - 1; g++) {
                if (piles.get(g) == piles.get(g + 1)) {
                    counter++;
                }
            }

            /*for (int h = 0; h < piles.size(); h++) {
                if(piles.get(h)==0){
                    continue;
                }
                if (piles.get(h)==3){
                    counter++;
                }else{
                    int temp = piles.get(h);
                    while(temp>1){
                        //System.out.println("sdfsd");
                        counter++;
                        temp-=2;
                    }

                }

            }*/



            /*int temp=1;
            while(temp==1){
                if(piles.get(0)==1){
                    piles.remove(0);
                }
                temp=piles.get(0);
            }

            while(piles.size()>0){
                int num = piles.get(0);
                if (num==1){
                    piles.remove(0);
                    continue;
                }
                piles.remove(0);
                piles.add(num-2);
                counter++;

            }*/

            if (counter % 2 == 0) {
                System.out.println("Bob");
            } else {
                System.out.println("Alice");
            }
        }

    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        out.close();
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
