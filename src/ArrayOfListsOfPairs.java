/**
 * ArrayofListsOfPairs is an array where each entry is the head of its own linked list.
 * Each node in each linked list contains a key-value pair.
 * CS 310-002.
 * @author Bao Vo
 * @param <K> generic type key
 * @param <V> generic type value
 */ 
public class ArrayOfListsOfPairs<K,V> {
    /**
     * Internal storage for the class which will hold each key-value pair.
     */
    private Node<K,V>[] storage;
    /**
     * Check if the storage contains the given key.
     * @param key the key
     * @return true if storage contains the given key, false otherwise
     */
    public boolean containsKey (K key) {
        int index = getIndex(key);
        Node<K,V> tempKey = storage[index];
        while (tempKey != null) {
            if (tempKey.pair.getKey().equals(key)) {
                return true;
            }
            tempKey = tempKey.next;
        }
        return false;
    }
    /**
     * Add a key-value pair to the internal storage.
     * @param key the key
     * @param value the value that is associated with the given key
     */
    public void addPair(K key, V value) {
        int index = getIndex(key);
        Node<K, V> node = new Node<>(new KeyValuePair<>(key,value));
        node.next = storage[index];
        storage[index] = node;
    }
    
    /**
     * Add an edge (value) assiciated with the key (to) to the linked list of the array at index (id).
     * @param id the index of the array
     * @param value the value (edge) 
     * @param to the key that will be adding the edge to
     */
    public void addNewEdge(int id, V value, K to) {
        // Create a new node with the given key-value.
        Node<K, V> node = new Node<>(new KeyValuePair<>(to,value));
        // Add the new node to the head of the array at the given index (id).
        node.next = storage[id];
        storage[id] = node;
    }
    /**
     * Compute the index of the given key using seperate chaining.
     * Compute the hash code of the given key then mod the hash code with the capacity to get its index.
     * @param key the given key
     * @param capacity the capacity of the storage
     * @return the index of the array
     */
    private int getIndex(K key, int capacity) {
        int hashCode = key.hashCode();
        int index = hashCode % capacity;
        return index;
    }
    
    /**
     * Compute the index of the given key from the internal storage's capacity.
     * @param key the given key
     * @return the index of the array using seperate chaining
     */
    public int getIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % storage.length;
        return index;
    }
    /**
     * Resize the internal storage (rehash) 
     * If the load goes above 3 after adding an entry
     * This method should rehash to three times the number of slots (given size).
     * @param size the new size of the internal storage
     * @return a new ALP with the new size
     */
    public ArrayOfListsOfPairs<K, V> resize(int size) {
        // Create a new ALP with the new size.
        ArrayOfListsOfPairs<K, V> arr = new ArrayOfListsOfPairs<>(size);
        // Add all key-value pairs from the original ALP to the new ALP.
        for (int i = 0; i < storage.length; i++) {
            Node<K, V> temp = storage[i];
            while (temp != null) {
                int index = getIndex(temp.pair.getKey(), arr.storage.length);
                arr.addPair(temp.pair.getKey(), temp.pair.getValue());
                temp = temp.next;
            }
        }
        return arr;
    }
    /**
     * Replace the value of the given key with its new given value.
     * @param key the key of the value
     * @param value the new value
     */
    public void replaceValue(K key, V value) {
        // Get the index of the given key.
        int index = getIndex(key);
        // Get the head of the linked list that contains the given key at the computed index.
        Node<K, V> head = storage[index];
        /*
         * Traverse the linked list until found the given key.
         * Replace its value with the new given value.
         */
        while (head != null) {
            if (head.pair.getKey().equals(key)){
                KeyValuePair<K, V> newPair = new KeyValuePair<>(key, value);
                head.pair = newPair;
                break;
            }
            head = head.next;
        }
    }
    /**
     * Get the associated value of the given key.
     * @param key the key
     * @return the associated value of the given key
     */
    public V getValue(K key) { 
        // Get the index of the given key (seperate chanining).
        int index = getIndex(key, storage.length);
        Node<K, V> head = storage[index];
        V value = null;
        /*
         * Traverse the linked list to find the given key.
         * If found, assign the created value to the value of the key found, break the loop.
         */
        while (head != null) {
            if (head.pair.getKey().equals(key)) {
                value = head.pair.getValue();
                break;
            }
            head = head.next;
        }
        return value;
    }   
    
    /**
     * Remove a key-value pair at the provided index (id).
     * @param index the index of the array
     * @param key the key that needs to be removed
     */
    public void removePair(int index, K key) {
        // If the key is the head of the linked list, and there is no more node after the head, set the head to null.
        if (storage[index].pair.getKey().equals(key) && storage[index].next == null) {
            storage[index] = null;
            return;
        }
        // If the key is the head of the linked list and there is more node after the head.
        // Set the head to the node after the head to disconect the head.
        if (storage[index].pair.getKey().equals(key) && storage[index].next!= null) {
            storage[index] = storage[index].next;
            return;
        } 
        /*
         * Create the previous and current node which are pointing to the head of the list.
         */
        Node<K,V> current = storage[index];
        Node<K, V> previous = current;
        /*
         * Traverse through the list.
         * Check if the current is the key, if yes, set the previous.next which is pointing at the current (key) to the current.next. 
         * which is the node after the key to unlink the node that contains the key.
         */
        while (current != null) {
            if (current.pair.getKey().equals(key)) {
                previous.next = current.next;
            }
            // Set the previous to the current node then update the current to the node after.
            previous = current;
            current = current.next;
        }
    }
    
    /**
     * Get a string representation of the value.
     * @return a string representation
     */
    public String toString() {
        //you may edit this to make string representations of your
        //lists for testing
        return super.toString();
    }
    /**
     * Main method of the class.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
    /**
     * This class representation one node in a linked list.
     * CS310-002
     * @author Bao Vo
     * @param <K> generic type key
     * @param <V> generic type value
     */
    public static class Node<K,V> {
        /**
         * It contains one key-value pair.
         */ 
        public KeyValuePair<K,V> pair;
        
        /**
         * Pointer to the next node.
         */
        public Node<K,V> next;
        
        /**
         * Constructor of the node.
         * @param pair the given pair
         */
        public Node(KeyValuePair<K,V> pair) {
            this.pair = pair;
        }
        
        /**
         * Convenience constructor.
         * @param pair the given pair
         * @param next the pointer to the next node
         */
        public Node(KeyValuePair<K,V> pair, Node<K,V> next) {
            this.pair = pair;
            this.next = next;
        }
    }
    
    /**
     * Creates an array with the specified number of lists-of-pairs.
     * @param numLists the given size of the ALP
     */
    @SuppressWarnings("unchecked")
    public ArrayOfListsOfPairs(int numLists) {
        storage = (Node<K,V>[]) new Node[numLists];
    }
    
    /**
     * Returns the number of lists in this collection.
     * @return the number of lists in this collection
     */ 
    public int getNumLists() {
        return storage.length;
    }
    
    /**
     * Returns all key-value pairs in the specified sublist of this collection.
     * @param listId the given index (id)
     * @return all key-value pairs in the specified sublist of this collection
     */ 
    public java.util.ArrayList<KeyValuePair<K,V>> getAllPairs(int listId) {
        java.util.ArrayList<KeyValuePair<K,V>> lst = new java.util.ArrayList<>();
        
        Node<K,V> current = storage[listId];
        while(current != null) {
            lst.add(current.pair);
            current = current.next;
        }
        
        return lst;
    }
    
    /**Returns all key-value pairs in this collection.
      * @return all key-value pairs in this collection
      */
    public java.util.ArrayList<KeyValuePair<K,V>> getAllPairs() {
        java.util.ArrayList<KeyValuePair<K,V>> lst = new java.util.ArrayList<>();
        
        for(int i = 0; i < storage.length; i++) {
            lst.addAll(getAllPairs(i));
        }
        
        return lst;
    }
}