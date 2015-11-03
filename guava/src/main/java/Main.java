import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @since 28. 08. 2015
 */
public class Main {
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, Animal> animalCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(new AnimalCacheLoader());

//        Animal animal = animalCache.get("monkey");
//        System.out.println(animal);

        System.out.println(animalCache.get("monkey"));
        System.out.println(animalCache.get("tiger"));
        System.out.println(animalCache.get("monkey"));
        System.out.println(animalCache.get("tiger"));
        System.out.println(animalCache.get("monkey"));
    }
}
