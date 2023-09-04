package fileCollector.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileCollector.interfaces.ICacheService;
import fileCollector.models.CacheItem;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CacheService implements ICacheService {
    private final String path="src\\cache.txt";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void store(long l, List<String> allTabs) {
        try {
            CacheItem newItem = new CacheItem();
            newItem.setTimestamp(l);
            newItem.setResult(allTabs);
            mapper.writeValue(Paths.get(path).toFile(), newItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CacheItem getFromCache() {

        try {
            File file = new File(path);
            if (file.length()!=0) {
                CacheItem item = new ObjectMapper().readValue(new File(path), CacheItem.class);
                if (System.currentTimeMillis() - item.getTimestamp() < 21600000) {

                    return item;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

