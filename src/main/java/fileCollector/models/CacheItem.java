package fileCollector.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CacheItem {
    private long timestamp ;
    private List<String> result;
}
