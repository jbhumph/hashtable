import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class HashMap {
    private int size;
    private List<List<Entry>> buckets;

    private class Entry {
        String key;
        int value;

        public Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashMap(int size) {
        this.size = size;
        this.buckets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.buckets.add(new ArrayList<>());
        }
    }

    private int hashFunction(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return sum % 10;
    }

    public void put(String key, int value) {
        int index = hashFunction(key);
        List<Entry> bucket = this.buckets.get(index);
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry(key, value));
    }

    public int get(String key) {
        int index = hashFunction(key);
        List<Entry> bucket = this.buckets.get(index);
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return 0;
    }

    public String getID(String key) {
        int index = hashFunction(key);
        List<Entry> bucket = this.buckets.get(index);
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.key;
            }
        }
        return null;
    }

    public void remove(String key) {
        int index = hashFunction(key);
        List<Entry> bucket = this.buckets.get(index);
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                return;
            }
        }
    }

    public void forEach(BiConsumer<String, Integer> action) {
        for (List<Entry> bucket : buckets) {
            for (Entry entry : bucket) {
                action.accept(entry.key, entry.value);
            }
        }
    }

    public void printMap() {
        System.out.println("Hash Map Contents:");
        for (int i = 0; i < buckets.size(); i++) {
            List<Entry> bucket = buckets.get(i);
            System.out.println("Bucket " + i + ": " + bucket);
        }
    }

}
