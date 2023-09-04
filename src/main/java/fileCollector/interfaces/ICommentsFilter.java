package fileCollector.interfaces;

import org.jsoup.nodes.Element;

import java.util.List;

public interface ICommentsFilter {
    List<String> filterComments(List<Element> links);
}
