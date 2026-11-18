

static Map<Object, Collection<Object>> combine(Map<Object, Collection<Object>> a, Map<Object, Collection<Object>> b) {
    Map<Object, Collection<Object>> newMap = new HashMap<>(a);
    b.forEach((k, v) -> {
        if (newMap.containsKey(k)) newMap.get(k).addAll(v);
        else newMap.put(k, v);
    });
    return newMap;
}

void main() {
    Map<Object, Collection<Object>> d1 = new HashMap<>();
    Map<Object, Collection<Object>> d2 = new HashMap<>();
    d1.put(1, Arrays.asList(1, 2, 4));
    d1.put(2, Arrays.asList(5, 6, 7));
    d1.put(3, Arrays.asList(8, 9, 0));
    d2.put(1, Arrays.asList(-1, -2, -3, -6, -7));
    IO.println(combine(d1, d2));
}
