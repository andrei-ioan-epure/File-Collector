package fileCollector.interfaces;

import fileCollector.models.CacheItem;

import java.util.List;

public interface ICacheService {

    void store(long l, List<String> allTabs);
    CacheItem getFromCache();
}
