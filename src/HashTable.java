import java.util.Collection;
/**
 * Hash table with separate chaining. 
 * Each key and value gets placed together as a single entry in the table.
 * CS 310-002.
 * @author Bao Vo
 * @param <K> generic type key
 * @param <V> generic type value
 */ 
public class HashTable<K,V> {
    /**
     * Minimum number of slots in the hash table.
     */
    private static final int MIN_SLOTS = 2;
    /**
     * Internal storage (ALP) of the class.
     */
    protected ArrayOfListsOfPairs<K,V> storage;
    /**
     * NumEntries of the storage (will be modifies after adding or removing).
     */
    private int numEntries = 0;
    /**
     * Capacity of the storage.
     */
    private int capacity = 0;
    /**
     * Constructor of the class with the number of slots in the table.
     * @param numSlots the given capacity
     */
    public HashTable(int numSlots) {
        // If the given numSlots is less than the minimum slots, use the minimum slots.
        if (numSlots < 2) {
            storage = new ArrayOfListsOfPairs<K,V>(MIN_SLOTS);
            this.capacity = MIN_SLOTS;
        }
        else {
            storage = new ArrayOfListsOfPairs<K,V>(numSlots);
            this.capacity = numSlots;
        }
    }
    /**
     * The number of key-value entries in the table.
     * @return the number of key-value entries in the table
     */
    public int size() {
        return numEntries;
    }
    /**
     * Get the number of slots in the table.
     * @return the number of slots in the table
     */
    public int getNumSlots() {
        return this.capacity;
    }
    
    /**
     * Get the load on the table.
     * @return the load on the table
     */
    public double getLoad() {
        return (double) numEntries/ (double) capacity;
    }
    
    /**
     * Add a key-value pair to the table.
     * If the load goes above 3 after adding an entry, this method should also rehash to three times the number of slots.
     * @param key the key
     * @param value the assiciated value
     * @return true if the addition is successful, false otherwise
     */
    public boolean add(K key, V value) {
        // Check if the given key or value is null, if yes return false.
        if (key == null || value == null) {
            return false;
        }
        // Check if the storage contains the given key or not, if not return false.
        boolean contain = contains(key);
        if (contain == true) {
            return false;
        }
        // Add a new pair to the storage by calling the method addPair in ALP class.
        storage.addPair(key, value);
        // Increment the numEntries by 1 for each addition.
        numEntries++;
        // Check if the load goes above 3.0 or not, if yes rehash the storage by the current capacity * 3.
        if (getLoad() > 3.0) {
            rehash(capacity * 3);
        }
        return true;
    }
    
    /**
     * Rehashes the table to the given new size.
     * @param newSize the given new size
     */
    public void rehash(int newSize) {
        // Check if the given newSize is less than the minimum slots or not, if yes use the minimum slots as the new size.
        if (newSize < MIN_SLOTS) {
            storage = storage.resize(MIN_SLOTS);
            this.capacity = MIN_SLOTS;
            this.numEntries = numEntries;
        }
        // Create a new storage with the new size and set it to the current storage.
        storage = storage.resize(newSize);
        // Set the capacity to new size and keep the same numEntries.
        this.capacity = newSize;
        this.numEntries = numEntries;
    }
    /**
     * Replace the value of the given key.
     * @param key the key
     * @param value the value
     * @return true if successful, false otherwise
     */
    public boolean replace(K key, V value) {
        // Check if the storage contains the given key or not, if not return false.
        if (contains(key) == false) {
            return false;
        }
        // If the storage contains the given key, remove the value by calling replaceValue() method in ALP class.
        storage.replaceValue(key, value);    
        return true;
    }
    
    /**
     * Remove the key-value pair.
     * @param key the key
     * @return true if the removal is successful, false otherwise
     */
    public boolean remove(K key) {
        // Check if the storage contains the key or not, if not return false.
        if (contains(key) == false) {
            return false;
        }
        // Get the index of the given key in the hash table.
        int index = storage.getIndex(key);
        // Remove the pair by calling the removePair() method in ALP class.
        storage.removePair(index, key);
        // Decrement numEntries by 1 for each removal.
        numEntries--;
        return true;
    }
    /**
     * Check the key requested is in the table.
     * @param key the requested key
     * @return true if the key requested is in the table, false otherwise
     */
    public boolean contains(K key) {
        return storage.containsKey(key);
    }
    /**
     * Get the associated value of the given key.
     * @param key the key
     * @return null if the given key is not in the table, otherwise return the associated value
     */
    public V get(K key) {
        if (contains(key) == false) {
            return null;
        }
        V value = storage.getValue(key);
        return value;
    }
    /**
     * Get a string representation of the given value.
     * @return a string representation
     */
    public String toString() {
        return super.toString();
    }
    
    /**
     * Main method of the class which is used for testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    /**
     * Get the internal storage of the table.
     * @return the storage
     */
    public ArrayOfListsOfPairs<K,V> getInternalTable() {
        return storage;
    }
}