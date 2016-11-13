package Q5;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Thiloshon on 22-Oct-16.
 */
public class Solution {

    private FastScanner in;
    private PrintWriter out;
    ArrayList<Long> ans=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution3. */

        try {
            new Solution().solve();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void readData() throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);
        //double lat = sc.nextDouble();
        //double longg = sc.nextDouble();
        String daata = sc.nextLine();
        String[] stry = daata.split(",");
        double lat = Double.parseDouble(stry[0]);
        double longg = Double.parseDouble(stry[1]);
        //System.out.println("lat si "+ lat);
        //System.out.println("lat si "+ longg);
        double radius = sc.nextDouble();
        sc.nextLine();
        sc.nextLine();
        //System.out.println(radius);
        ArrayList<String> arr = new ArrayList<>();
        int x = 0;
        sc.useDelimiter(System.getProperty("line.separator"));
        while (sc.hasNext()) {
            arr.add(sc.nextLine());
            //System.out.println(arr.get(x));
            x++;
        }

        ArrayList<advert> arraylist = new ArrayList<>();
        ArrayList<Adverticement> ansArr = new ArrayList<>();

        for (int y = 0; y < arr.size(); y++) {

            String[] arr1 = arr.get(y).split(" ");
            String[] dates = arr1[0].split("/");
            String[] components = arr1[1].split(",");
            String[] times = components[0].split(":");

            arraylist.add(new advert(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]), Integer.parseInt(times[0]), Integer.parseInt(times[1]), Double.parseDouble(components[1]), Double.parseDouble(components[2]), Long.parseLong(components[3])));

            //System.out.println(arr.get(y));

            /*String[] dates = arr.get(y).split(",");
            DateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm", Locale.ENGLISH);
            SimpleDateFormat df = new SimpleDateFormat(dates[0]);
            Date date = format.parse(dates[0]);
            ansArr.add(new Adverticement(Double.parseDouble(dates[1]), Double.parseDouble(dates[2]), Long.parseLong(dates[3]),df, date));*/


        }

        /*for(Adverticement ad : ansArr){
            System.out.println(ad.toString());
        }*/

        Collections.sort(arraylist, new Comparator<advert>() {
            @Override
            public int compare(advert o1, advert o2) {
                if (o1.getNumber() > o2.getNumber()) {
                    return 1;
                }
                return -1;
            }
        });
        advert temp = arraylist.get(0);

        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        System.out.println(df.parse("2011-9-5 9:00"));*/

        for (int k = 1; k < arraylist.size(); k++) {
            if (arraylist.get(k).getNumber() == temp.getNumber()) {
                //System.out.println(arraylist.get(k).getNumber());
                if (temp.year >= arraylist.get(k).year && temp.month >= arraylist.get(k).month && temp.day >= arraylist.get(k).day && temp.hour >= arraylist.get(k).hour && temp.minutes > arraylist.get(k).minutes) {
                    arraylist.remove(temp);
                } else {

                    arraylist.remove(k);
                }
            } else {
                temp = arraylist.get(k);
            }


        }


        //d = 2 × r × arcsin (sqrt (sin2((lat1 - lat2)/2) + cos(lat1) × cos(lat2) × sin2((long1 - long2)/2)))

        for (int l = 0; l < arraylist.size(); l++) {
            double d = distance(lat, longg, arraylist.get(l).latitude,arraylist.get(l).longitude);


            /*double val1 = Math.pow(Math.sin((lat - arraylist.get(l).latitude) / 2), 2);
            double val2 = Math.pow(Math.sin((longg - arraylist.get(l).longitude) / 2), 2);
            //double val3 = val2 * Math.cos(lat) * Math.cos(arraylist.get(l).latitude);
            //double val4 = val1+val3;
            double val5 = Math.pow(val1 + Math.cos(lat) * Math.cos(arraylist.get(l).latitude) * val2, 0.5);
            double val6 = Math.asin(val5);
            double d = 2 * 6378.137 * val6;*/
            //double d = 2 * 6378.137 * (Math.asin(Math.pow(Math.pow(Math.sin((lat - arraylist.get(l).latitude) / 2), 2) + Math.cos(lat) * Math.cos(arraylist.get(l).latitude) * Math.pow(Math.sin((longg - arraylist.get(l).longitude) / 2), 2), 0.5)));

           // System.out.println("d is " + d);
            if (d <= radius){
                //System.out.println(arraylist.get(l).number);
                ans.add(arraylist.get(l).number);
            }
        }
        for(int h =0; h<ans.size();h++){
            System.out.print(ans.get(h));
            if(h<ans.size()-1){
                System.out.print(",");
            }
        }



        /*System.out.println("------------");
        for (advert ad : arraylist) {
            System.out.println(ad.toString());
        }*/




        /*for(advert adv : arraylist){
            System.out.println("The adv has " + adv.toString());
        }*/

    }


    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    class Adverticement {
        SimpleDateFormat time;
        double longitude;
        double latitude;
        long number;
        Date date;

        public Adverticement(double latitude, double longitude, long number, SimpleDateFormat time, Date dat) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.number = number;
            this.time = time;
            this.date = dat;
        }

        public long getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return "Adverticement{" +
                    "latitude=" + latitude +
                    ", time=" + time +
                    ", longitude=" + longitude +
                    ", number=" + number +
                    '}';
        }
    }

    class advert {
        int day;
        int month;
        int year;
        int hour;
        int minutes;
        double longitude;
        double latitude;
        long number;

        public advert(int month, int day, int year, int hour, int minutes, double latitude, double longitude, long number) {
            this.day = day;
            this.hour = hour;
            this.latitude = latitude;
            this.longitude = longitude;
            this.minutes = minutes;
            this.month = month;
            this.number = number;
            this.year = year;
        }

        public long getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return "advert{" +
                    "day=" + day +
                    ", month=" + month +
                    ", year=" + year +
                    ", hour=" + hour +
                    ", minutes=" + minutes +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    ", number=" + number +
                    '}';
        }
    }


    public void solve() throws IOException, ParseException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        //generateSwapsEfficient();

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

    private static final double EARTH_RADIUS = 6378.137; // Approx Earth radius in KM

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }


}
