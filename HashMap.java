import java.util.ArrayList;
import java.util.List;

public class HashMap {
    private int size;
    private List<List<Entry>> buckets;

    private class Entry {
        String key;
        String value;

        public Entry(String key, String value) {
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

    public void put(String key, String value) {
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

    public String get(String key) {
        int index = hashFunction(key);
        List<Entry> bucket = this.buckets.get(index);
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
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

    public void printMap() {
        System.out.println("Hash Map Contents:");
        for (int i = 0; i < buckets.size(); i++) {
            List<Entry> bucket = buckets.get(i);
            System.out.println("Bucket " + i + ": " + bucket);
        }
    }

}
