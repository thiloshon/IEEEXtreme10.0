package Q3;

import java.io.*;
import java.util.*;

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
        long numberOfTests = in.nextInt();
        long[] accessess;


        for (int x = 0; x < numberOfTests; x++) {

            int ans = 0;

            long numberOfPages = in.nextInt();
            long sizeOfMemory = in.nextInt();
            int num = in.nextInt();
            accessess = new long[num];


            for (int y = 0; y < num; y++) {
                accessess[y] = (long) Math.floor(in.nextInt() / sizeOfMemory);
            }


            ArrayList<Long> arr = new ArrayList<>();
            int temp = 0;

            for (int u = 0; u < num; u++) {
                if (temp < numberOfPages) {
                    int index = 0;
                    boolean found = false;
                    for (Long key : arr) {
                        if (key == accessess[u]) {
                            found = true;
                            break;
                        }
                        index++;
                    }

                    if (!found) {
                        arr.add(accessess[u]);
                        temp++;
                    }
                } else {
                    boolean found = false;
                    int index = 0;

                    for (Long key : arr) {
                        if (key == accessess[u]) {
                            found = true;
                            break;
                        }
                        index++;
                    }

                    if (!found) {
                        arr.remove(0);
                        arr.add(accessess[u]);
                        //System.out.println(accessess[u]+" causes");
                        temp++;
                        ans++;
                    }
                }


            }
            int ans1 = ans;

            temp = 0;
            arr.clear();
            ans = 0;

            for (int u = 0; u < num; u++) {
                if (temp < numberOfPages) {
                    int index = 0;
                    boolean found = false;
                    for (Long key : arr) {
                        if (key == accessess[u]) {
                            found = true;
                            break;
                        }
                        index++;
                    }

                    if (!found) {

                        //System.out.println("found " + accessess[u]);

                        arr.add(0, accessess[u]);
                        temp++;
                    } else {
                        arr.remove(index);
                        arr.add(0, accessess[u]);
                    }
                } else {
                    boolean found = false;
                    int index = 0;

                    for (Long key : arr) {
                        if (key == accessess[u]) {
                            found = true;
                            break;
                        }
                        index++;
                    }

                    if (!found) {

                        arr.remove(arr.size() - 1);
                        arr.add(0, accessess[u]);
                        //System.out.println(accessess[u]+" causes");
                        temp++;
                        ans++;
                    } else {
                        arr.remove(index);
                        arr.add(0, accessess[u]);
                    }
                }


            }

            if (ans1 > ans) {
                System.out.println("yes " + ans1 + " " + ans);
            } else {
                System.out.println("no " + ans1 + " " + ans);
            }


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
