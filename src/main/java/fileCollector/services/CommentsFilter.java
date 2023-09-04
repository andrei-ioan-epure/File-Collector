package fileCollector.services;

import fileCollector.interfaces.ICommentsFilter;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsFilter implements ICommentsFilter {
    private final List<String> types;

    public CommentsFilter() {
        types = new ArrayList<>();
        types.add(".gp");
        types.add(".pdf");
        types.add(".gp4");
        types.add(".rar");
        types.add(".zip");
        types.add(".gpx");
        types.add(".gp5");
    }

    @Override
    public List<String> filterComments(List<Element> links) {

        List<String> filteredList = new ArrayList<>();
        for (Element e : links) {
            for (String extension : types) {
                if (e.text().contains(extension)) {
                    String element = "<a href=\"" + "https://vk.com" + e.attr("href") + "\" target=\"_blank\">" + e.text() + "</a>";
                    filteredList.add(element);
                }
            }
        }
                return filteredList;
    }
}



