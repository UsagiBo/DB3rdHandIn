import java.util.Arrays;

/**
 * Created by l on 01-04-2017.
 */
public class Calc {

    public double CalAvg(long[] times)
    {
        double total_time = 0;
        for (long time : times)
        {
            total_time += time;
        }
        return total_time / times.length;

    }
    public double CalMedian(long[] times)
    {
        Arrays.sort(times);
        int middle = times.length/2;
        if (times.length%2 == 1) {
            return times[middle];
        } else {
            return (times[middle-1] + times[middle]) / 2.0;
        }

    }
}
