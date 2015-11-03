import java.util.*;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkHistoryChart;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import com.carrotsearch.junitbenchmarks.annotation.LabelType;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;

@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "benchmark-lists")
@BenchmarkHistoryChart(labelWith = LabelType.CUSTOM_KEY, maxRuns = 20)
public class MainTest {
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();
    /*
    private static final int TIMEOUT_BASE = 100;
    private static final int TIMEOUT_ADD_MAX = 100;

    private Random random = new Random();

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @BenchmarkOptions(benchmarkRounds = 20, warmupRounds = 10)
    @Test
    public void testGetName() throws Exception {
        int timeout = TIMEOUT_BASE + random.nextInt(TIMEOUT_ADD_MAX);
        System.out.println("Sleeping for " + timeout);
        Thread.sleep(timeout);
    }
    */

    private static Object singleton = new Object();
    private static int COUNT = 50000;
    private static int [] rnd;

    /** Prepare random numbers for tests. */
    @BeforeClass
    public static void prepare()
    {
        rnd = new int [COUNT];

        final Random random = new Random();
        for (int i = 0; i < COUNT; i++)
        {
            rnd[i] = Math.abs(random.nextInt());
        }
    }

    @BenchmarkOptions(callgc = true, benchmarkRounds = 20, warmupRounds = 5)
    @Test
    public void arrayList() throws Exception
    {
        runTest(new ArrayList<Object>());
    }

    @BenchmarkOptions(callgc = true, benchmarkRounds = 20, warmupRounds = 5)
    @Test
    public void linkedList() throws Exception
    {
        runTest(new LinkedList<Object>());
    }

    @BenchmarkOptions(callgc = true, benchmarkRounds = 20, warmupRounds = 5)
    @Test
    public void vector() throws Exception
    {
        runTest(new Vector<Object>());
    }

    private void runTest(List<Object> list)
    {
        assert list.isEmpty();

        // First, add a number of objects to the list.
        for (int i = 0; i < COUNT; i++)
            list.add(singleton);

        // Randomly delete objects from the list.
        for (int i = 0; i < rnd.length; i++)
            list.remove(rnd[i] % list.size());
    }
}
