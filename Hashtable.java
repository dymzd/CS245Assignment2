import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Hashtable {
    private static int INITIAL_SIZE = 314527;
    private HashNode[] entries = new HashNode[INITIAL_SIZE];
    private int cap = entries.length;
    private int size; 
    public static class HashNode {
        String key;
        String value;
        HashNode next;
        //basic linked list
        public HashNode(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    public String remove (String key){
        int hash = hashCode(key);
        hash = Math.abs(hash);

        if(entries[hash] != null) {
            HashNode temp = entries[hash];
            HashNode nextNode = entries[hash];
            while( !temp.key.equals(key) && temp.next != null ) {
                temp = temp.next;
                if(temp.next != null){
                    nextNode = temp.next;
                }
                if(nextNode.key == key){
                    break;
                }
            }
            if(nextNode.next != null){
                temp.next = nextNode.next;
            }
            String returning = temp.value;
            temp.value = null;
            return returning;
        }
        throw new NoSuchElementException();
    }
    public void put(String key, String value) {
        size++;
        int hash = hashCode(key);
        hash = Math.abs(hash);
    
        final HashNode HashNode = new HashNode(key, value);
        if(entries[hash] == null) {
            entries[hash] = HashNode;
        }
        else {
            HashNode temp = entries[hash];
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = HashNode;
        }
    }

    public boolean containsKey(String key){
        if(get(key) != null){
            return true;
        } else {
            return false;
        }
    }

    public String get(String key){
        int hash = hashCode(key);
        hash = Math.abs(hash);
        if(entries[hash] != null) {
            HashNode temp = entries[hash];

            while( !temp.key.equals(key) && temp.next != null ) {
                temp = temp.next;
            }
            return temp.value;
        }
        return null;    
    }
    public int hashCode(String key){
        return key.hashCode() % INITIAL_SIZE;
    }
}
