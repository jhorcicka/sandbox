import com.google.common.cache.CacheLoader;

public class AnimalCacheLoader extends CacheLoader<String, Animal> {
    @Override
    public Animal load(String key) {
        return new Animal(key);
    }
}
