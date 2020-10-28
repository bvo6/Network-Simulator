/**
 * This class represents a key-value pair.
 * @param <K> generic type key
 * @param <V> generic type value
 */ 
public class KeyValuePair<K,V> {
    /**
     * The key.
     */
    private K key;
    /**
     * The value of the key.
     */
    private V value;
    /**
     * Constructor of KeyValuePair class which is used to initialize all its fields.
     * @param key the key
     * @param value the value
     */
    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    /**
     * Get the key.
     * @return the key
     */
    public K getKey() {
        return key;
    }
    /**
     * Get current value.
     * @return the value
     */
    public V getValue() {
        return value;
    }
    /**
     * Check if the given object is an instance of the KeyValuePair class.
     * @param o object
     * @return true of the given object's key matches the current key, false otherwise
     */
    public boolean equals(Object o) {
        if(o instanceof KeyValuePair) {
            return key.equals(((KeyValuePair)o).key);
        }
        return false;
    }
    /**
     * Get the hashCode of the current key.
     * @return the hash code of the key
     */
    public int hashCode() {
        return key.hashCode();
    }
    /**
     * Return a string representation of the key and value.
     * @return a string representation of the key and value
     */
    public String toString() {
        return "("+key+","+value+")";
    }
}
