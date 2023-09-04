package fileCollector.orchestrators;

import fileCollector.interfaces.*;
import fileCollector.models.CacheItem;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Orchestrator implements IOrchestrator {

    @Autowired
    private ICommentGetter iCommentGetter;
    @Autowired
    private ICommentsFilter iCommentsFilter;
    @Autowired
    private IHTMLGetter ihtmlGetter;
    @Autowired
    private ICacheService cacheService;


    @Override
    public String getTabs() {

        List<String> allTabs = new ArrayList<>();

        Map<String, String> urls = new HashMap<>() {{
            put("Alexandr Misko", "https://vk.com/topic-180018751_40961976");
            put("Marcin Patrzalek", "https://vk.com/topic-180018751_39696191");
            put("Sungha Jung", "https://vk.com/topic-180018751_39955914");
            //etc
        }};
        CacheItem cacheItem = cacheService.getFromCache();
        if (cacheItem == null) {
            for (Map.Entry<String, String> it : urls.entrySet()) {
                System.out.println(it.getValue());
                String html = ihtmlGetter.getHTML(it.getValue());

                List<Element> links = iCommentGetter.getComments(html);

                List<String> tabs = iCommentsFilter.filterComments(links);
                tabs.add(0, "<h2>" + it.getKey() + "</h2>");

                allTabs = Stream.of(allTabs, tabs).flatMap(Collection::stream).collect(Collectors.toList());
                cacheService.store(System.currentTimeMillis(), allTabs);

            }
            System.out.println("From program");

            return ("<h1>Tabs</h1>" + String.join("<br>", allTabs) + "<br><br>");
        } else {
            System.out.println("From cache");
            return ("<h1>Tabs</h1>" + String.join("<br>", cacheItem.getResult()) + "<br><br>");
        }
    }
}


