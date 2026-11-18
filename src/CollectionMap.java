import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface CollectionMap<K,V> extends Map<K, Collection<V>> {
    static <K,V> CollectionMap<K,V> combine(CollectionMap<? extends K, ? extends V>... which) {
        CollectionMap<K,V> m = (CollectionMap<K, V>) new HashMap<K, Collection<V>>();
        for (var a : which)
            a.forEach((k, v) -> m.merge(k, new ArrayList<>(v), MapFun::addTo));
        return m;
    }
}
