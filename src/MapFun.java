import java.util.*;

/*
 * Copyright (c) 2026 Langdon Staab <langdon@langdonstaab.ca>
 *
 * Permission to use, copy, modify, and distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
public class MapFun {
    static <V> Collection<V> addTo(Collection<V> a, Collection<V> b) {
        ArrayList<V> l = new ArrayList<>(a);
        l.addAll(b);
        return l;
    }

    static Map<Object, Collection<Object>> combine(Map<Object, Collection<Object>> a, Map<Object, Collection<Object>> b) {
        Map<Object, Collection<Object>> m = new HashMap<>();
        a.forEach((k, v) -> m.merge(k, new ArrayList<>(v), MapFun::addTo));
        b.forEach((k, v) -> m.merge(k, new ArrayList<>(v), MapFun::addTo));
        return m;
    }
    static Map<Object, Collection<Object>> combine(Map<Object, Collection<Object>>... what) {
        Map<Object, Collection<Object>> m = new HashMap<>();
        for (var map : what)
            map.forEach((k, v) -> m.merge(k, new ArrayList<>(v), MapFun::addTo));
        return m;
    }

    void main() {
        List.of();
        Map<Object, Collection<Object>> d1 = new HashMap<>();
        Map<Object, Collection<Object>> d2 = new HashMap<>();
        d1.put(1, Arrays.asList(1, 2, 4));
        d1.put(2, Arrays.asList(5, 6, 7));
        d1.put(3, Arrays.asList(8, 9, 0, "4", "Hello World!"));
        d2.put(1, Arrays.asList(-1, -2, -3, -6, -7));
        IO.println(combine(d1, d2));
    }
}