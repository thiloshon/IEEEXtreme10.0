import java.io.*;
import java.util.*;

public class Solution {

    private FastScanner in;
    private PrintWriter out;
    long noOfDogs;
    long noOfWalkers;
    ArrayList<Dogs> dogArr = new ArrayList<>();
    ArrayList<Long> answer = new ArrayList<>();
    ArrayList<differences> differen = new ArrayList<>();
    ArrayList<differences> differenStorage = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution3. */

        new Solution().solve();
    }

    class Dogs {
        long dogID;
        long size;

        public Dogs(long dogID, long size) {
            this.dogID = dogID;
            this.size = size;
        }

        public long getSize() {
            return size;
        }
    }

    class differences {
        long index1;
        long index2;
        long difference;

        public differences(long difference, long index1, long index2) {
            this.difference = difference;
            this.index1 = index1;
            this.index2 = index2;
        }

        public long getDifference() {
            return difference;
        }

        public long getIndex1() {
            return index1;
        }

        public long getIndex2() {
            return index2;
        }
    }

    private void readData() throws IOException {
        long numberOfTests = in.nextInt();


        for (int x = 0; x < numberOfTests; x++) {
            noOfDogs = in.nextInt();
            noOfWalkers = in.nextInt();
            for (int i = 0; i < noOfDogs; ++i) {
                dogArr.add(new Dogs(i, in.nextInt()));
            }

            //System.out.println(solution(new int[]{1,1,3,5}, 2));

            //System.out.println("run");

            long ans = solving();
            answer.add(ans);
            dogArr.clear();

        }

    }

    public static int solution(int[] a, int m) {
        int[][] cost = new int[a.length][a.length];
        int[][] dp = new int[a.length][m + 1];

        //base cases (compute the maximum in range [i] to [j] and store in cost array)
        for (int i = 0; i < a.length; i++) {
            cost[i][i] = a[i];
            for (int j = i + 1; j < a.length; j++) {
                cost[i][j] = Math.max(cost[i][j - 1], a[j]);
            }
        }

        for (int j = 0; j < dp.length; j++) {
            dp[j][1] = cost[j][a.length - 1];
        }

        //now use the dynamic programming recurrence
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = 2; j <= m; j++) {
                //start out assuming dp[i][j] is as large as possible
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = a.length - j - 1; k >= i; k--) {
                    dp[i][j] = Math.min(dp[i][j], cost[i][k] + dp[k + 1][j - 1]);
                }
            }
        }
        return dp[0][m];
    }

    int solving() {


        Collections.sort(dogArr, new Comparator<Dogs>() {
            @Override
            public int compare(Dogs o1, Dogs o2) {
                if (o1.getSize() > o2.getSize()) {
                    return 1;
                }
                return -1;
            }
        });

        long[][] anddfd = new long[dogArr.size()][dogArr.size()];

        long[] comp = new long[dogArr.size()];
        for (int c = 0; c < comp.length - 1; c++) {
            //System.out.println("getting " + dogArr.get(c + 1).getSize() + dogArr.get(c).getSize());
            comp[c + 1] = dogArr.get(c + 1).getSize() - dogArr.get(c).getSize();
        }

        //System.out.println("-----------");
        //for (long g : comp) System.out.println(g);
        //System.out.println("-----------");
        for (int x = 0; x < dogArr.size(); x++) {
            long sdf = 0;
            for (int y = 0; y < dogArr.size(); y++) {
                if (y <= x) continue;
                anddfd[x][y] = comp[y] + sdf;
                differenStorage.add(new differences(comp[y] + sdf, x, y));
                sdf += comp[y];

            }
        }

        Collections.sort(differenStorage, new Comparator<differences>() {
            @Override
            public int compare(differences o1, differences o2) {
                if (o1.getDifference() > o2.getDifference()) {
                    return 1;
                }
                return -1;
            }
        });

        long teams = noOfDogs - noOfWalkers;
        long teamsleft = noOfWalkers;
        long dogsleft = noOfDogs;
        long ans = 0;
        while (dogsleft > teamsleft && dogsleft > 0 && teamsleft > 0 && differenStorage.size() > 0) {
            //System.out.println("sixe is "+ differenStorage.size());
            long from = differenStorage.get(0).getIndex1();
            long to = differenStorage.get(0).getIndex2();

            //System.out.println("Ans is;;;;" + differenStorage.get(0).difference);

            ans += differenStorage.get(0).difference;

            long temp = to - from + 1;

            differenStorage.remove(0);

            for (int x = 0; x < differenStorage.size(); x++) {
                if (differenStorage.get(x).getIndex1() >= from && differenStorage.get(x).getIndex1() <= to || differenStorage.get(x).getIndex2() >= from && differenStorage.get(x).getIndex2() <= to) {
                    differenStorage.remove(x);
                }
            }

            //System.out.println("subs values "+ dogsleft+" "+ temp+ " "+teamsleft);

            dogsleft -= temp;
            teamsleft--;
        }

        System.out.println(ans);

        return 0;
    }

    private void writeResponse() {
        //out.println(swaps.size());
        for (Long inte : answer) {
            //out.println(inte);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        //generateSwapsEfficient();
        writeResponse();
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