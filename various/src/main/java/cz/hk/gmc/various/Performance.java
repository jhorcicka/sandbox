package cz.hk.gmc.various;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Performance {
    private static Map<String, Long> _times = new HashMap<>();
    
    public static void main(String[] args) {
        start("main");
        final int size = 5;
        final Random generator = new Random();
        
        for (int i = 0; i < size; i++) {
            start("i-" + i);
            try {
                Thread.sleep((generator.nextInt(10) + 1) * 100);
            } catch (Exception e) {
            }
            stop("i-" + i);
        }
        
        stop("main");
        print();
    }
    
    private static void print() {
        for (String id : _times.keySet()) {
            System.out.println(id + ": " + _times.get(id) + "ms");
        }
    }

    private static void start(String id) {
        _times.put(id, System.currentTimeMillis());
    }

    private static void stop(String id) {
        _times.put(id, System.currentTimeMillis() - _times.get(id));
    }
}
