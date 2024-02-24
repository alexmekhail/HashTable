import java.util.Arrays;

class DataItem {
    long key;
    String item;

    public DataItem(long key, String item) {
        this.key = key;
        this.item = item;
    }

    @Override
    public String toString()
    {
        return String.format("{%s:%s}", key, item);
    }
}

public class HashMap {
    private int size = 0;
    private static final int INITIAL_SIZE = 13;
    private static final int DELETED_KEY = 0;
    private DataItem[] items;

    public HashMap()
    {
        items = new DataItem[INITIAL_SIZE];
    }

    public int size()
    {
        return size;
    }

    /*
        the hashing method that converts a string to a long value
        this method performs a simple conversion by multiplying each
        character's value to 27 to the power of the position of the
        character in the index.
        Example : "str" -> 's'*27^0 + 't'*27^1 + 'r'*27^2
    */
    public long hashFunction(String key) {
        long hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            hashValue += (c * Math.pow(27, i));
        }
        return hashValue;
    }

    /**
     * This optional method can be used to perform a linear probe on the table and return the index
     * of the next available slot. If the item at that index is null or the index has reached the end of the table
     * you can throw an "ItemNotFoundException"
     *
     * @param key the key of the item
     * @return the index of the next available slot
     * @throws ItemNotFoundException if the item is not found
     */
    private int linearProbe(String key) throws ItemNotFoundException {
        long hashValue = hashFunction(key);
        int index = (int) (hashValue % items.length);

        while (items[index] != null && items[index].key != DELETED_KEY && !(items[index].key == hashValue)) {
            index = (index + 1) % items.length;
        }

        if (items[index] == null || items[index].key == DELETED_KEY || !(items[index].key == hashValue)) {
            throw new ItemNotFoundException();
        }

        return index;
    }

    /**
     * This method adds the item to the array by converting the key to
     * the hashed long. In case of collision, the method will perform a
     * linear probe to find an empty spot for insertion
     */
    public void put(String key, String value) throws TableIsFullException {
        // Check if resizing is needed
        if (size == items.length) {
            throw new TableIsFullException();
        }

        long hashValue = hashFunction(key);
        int index = (int) (hashValue % items.length);

        while (items[index] != null && items[index].key != DELETED_KEY) {
            index = (index + 1) % items.length;
        }

        items[index] = new DataItem(hashValue, value);
        size++;
    }

    /**
     * This method will perform a linear probe and return the value stored in the DataItem at index. If the key does not
     * map to any item in the table or the item has been deleted this should throw an "ItemNotFoundException"
     *
     * @param key is used to retrieve the object from the table
     * @return the value stored in the DataItem at the specified key
     * @throws ItemNotFoundException if the item is not found
     */
    public String get(String key) throws ItemNotFoundException {
        int index = linearProbe(key);
        return items[index].item;
    }

    /**
     * Updates the value at key
     *
     * @param key      key to the item in the table
     * @param newValue the value to replace the old one
     * @throws ItemNotFoundException if the item is not found
     */
    public void update(String key, String newValue) throws ItemNotFoundException {
        int index = linearProbe(key);
        items[index].item = newValue;
    }

    /**
     * Deletes the item at the specified key.
     *
     * @param key the key of the item to delete
     * @return the value of the deleted item
     * @throws ItemNotFoundException if the item is not found
     */
    public String delete(String key) throws ItemNotFoundException {
        int index = linearProbe(key);
        String deletedValue = items[index].item;
        items[index].key = DELETED_KEY;
        size--;
        return deletedValue;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}

class TableIsFullException extends Exception {}

class ItemNotFoundException extends Exception {}
