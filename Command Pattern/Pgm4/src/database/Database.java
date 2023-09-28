package database;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, String> data;
    private String id;

    public Database(String id) {
        this.id = id;
        data = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public boolean add(String key, String value) {
        if (data.containsKey(key)) {
            System.out.println("Key already exists");
            return false;
        }
        data.put(key, value);
        return true;
    }

    public boolean remove(String key) {
        if (!data.containsKey(key)) {
            System.out.println("Key does not exist");
            return false;
        }
        data.remove(key);
        return true;
    }

    public boolean update(String key, String value) {
        if (!data.containsKey(key)) {
            System.out.println("Key does not exist");
            return false;
        }
        data.replace(key, value);
        return true;
    }

    public boolean contains(String key) {
        return data.containsKey(key);
    }

    public String get(String key) {

        if (!data.containsKey(key)) {
            System.out.println("Key does not exist");
            return null;
        }
        return data.get(key);
    }

    public void clear() {
        data.clear();
    }

    public void printContents() {

        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + "| " + entry.getValue());
        }
    }
}
