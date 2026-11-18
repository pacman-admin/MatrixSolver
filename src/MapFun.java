

static Map<Object, Collection<Object>> combine(Map<Object, Collection<Object>> a, Map<Object, Collection<Object>> b) {
    Map<Object, Collection<Object>> m = new HashMap<>();
    a.forEach((k, _) -> m.put(k, new ArrayList<>()));
    b.forEach((k, _) -> m.put(k, new ArrayList<>()));
    a.forEach((k, v) -> m.get(k).addAll(v));
    b.forEach((k, v) -> m.get(k).addAll(v));
    return m;
}

void main() {
    Map<Object, Collection<Object>> d1 = new HashMap<>();
    Map<Object, Collection<Object>> d2 = new HashMap<>();
    IO.println(combine(d1, d2));
}
