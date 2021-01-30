package model.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {

    // key is productId, value is count
    @JsonProperty("content")
    private Map<Integer, Integer> content;

    public Cart() {
        content = new HashMap<Integer, Integer>();
    }

    public void putOne(int productId) {
        if (content.containsKey(productId)) {
            int count = content.get(productId);
            count++;
            content.put(productId, count);
            return;
        }
        content.put(productId, 1);
    }

    public void removeOne(int productId) {
        if (!content.containsKey(productId)) {
            return;
        }

        int count = content.get(productId);
        if (count == 1) {
            content.remove(productId);
            return;
        }

        count--;
        content.put(productId, count);
    }

    public int getCountById(int id) {
        return content.get(id);
    }

    public void clear() {
        content.clear();
    }

    @JsonIgnore
    public Set<Integer> getProductIdSet() {
        return content.keySet();
    }

    public void removeAll(int productId) {
        content.remove(productId);
    }

}
