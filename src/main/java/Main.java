public class Main {
    public static void main(String[] args) throws TableIsFullException, ItemNotFoundException {
        HashMap map = new HashMap();
        System.out.println(map.hashFunction("key"));

        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", "value6");

        System.out.println(map.get("key3"));
        System.out.println(map.size());

        System.out.println(map);

        //System.out.println(map.delete("key2"));
        System.out.println(map);

        map.put("key7", "value7");
        System.out.println("---------------");
        System.out.println(map);
        map.put("key8", "value8");

        System.out.println(map);

        System.out.println(map.get("key5"));
    }
}