import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashMapTest {
    private HashMap map;
    @BeforeEach
    public void init() {
        map = new HashMap();
    }

    private void putItems() throws TableIsFullException {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", "value6");
    }

    private void putMoreItems() throws TableIsFullException {
        map.put("yek7", "value7");
        map.put("yke4", "value8");
        map.put("kye5", "value9");
        map.put("azz1", "value10");
        map.put("ke11", "value11");
        map.put("k12", "value12");
    }

    @Test
    public void testHash() {
        assertEquals(91043, map.hashFunction("key"));
        assertEquals(1538548999, map.hashFunction("string"));
    }

    @Test
    public void testPut() throws TableIsFullException {
        putItems();
        assertEquals(6, map.size());
        assertEquals(
            "[null, {1055510:value1}, {1075193:value2}, {1094876:value3}, {1114559:value4}, " + 
            "{1134242:value5}, {1153925:value6}, null, null, null, null, null, null]", 
            map.toString());
        
        map.put("yek7", "value7");
        map.put("yke4", "value8");
        map.put("kye5", "value9");
        assertEquals("[null, {1055510:value1}, {1075193:value2}, {1094876:value3}, {1114559:value4}, {1134242:value5}, " + 
        "{1153925:value6}, {1163416:value7}, {1100155:value8}, {1120202:value9}, null, null, null]", 
            map.toString());

        map.put("azz1", "value10");
        map.put("ke11", "value11");
        map.put("k12", "value12");
        assertEquals("[{1056796:value10}, {1055510:value1}, {1075193:value2}, {1094876:value3}, {1114559:value4}, " + 
        "{1134242:value5}, {1153925:value6}, {1163416:value7}, {1100155:value8}, {1120202:value9}, {1003022:value11}, {37880:value12}, null]",
            map.toString());
        
        map.put("oneMore", "value");
        assertThrows(TableIsFullException.class, () -> map.put("k12", "value12"));
    }

    @Test
    public void testGet() throws TableIsFullException, ItemNotFoundException {
        putItems();
        putMoreItems();
        assertEquals("value6", map.get("key6"));
        assertEquals("value10", map.get("azz1"));
        assertThrows(ItemNotFoundException.class, () -> map.get("key9"));
    }

    @Test
    public void testUpdate() throws TableIsFullException, ItemNotFoundException {
        putItems();
        putMoreItems();

        map.update("key3", "THREE");
        assertEquals("THREE", map.get("key3"));
        assertEquals(12, map.size());
        assertThrows(ItemNotFoundException.class, () -> map.update("nonKey", "aaaaaa"));
        
    }

    @Test
    public void testDelete() throws TableIsFullException, ItemNotFoundException {
        putItems();

        assertEquals("value2", map.delete("key2"));
        assertEquals(5, map.size());
        assertEquals("[null, {1055510:value1}, {0:value2}, {1094876:value3}, {1114559:value4}, " + 
            "{1134242:value5}, {1153925:value6}, null, null, null, null, null, null]", map.toString());
        putMoreItems();
        assertThrows(ItemNotFoundException.class, () -> map.get("value2"));
        assertThrows(ItemNotFoundException.class, () -> map.delete("nonKey"));
        assertEquals("value10", map.delete("azz1"));
    }
}
