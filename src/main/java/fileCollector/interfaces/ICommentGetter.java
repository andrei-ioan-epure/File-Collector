package fileCollector.interfaces;

import org.jsoup.nodes.Element;

import java.util.List;

public interface ICommentGetter {
    List<Element> getComments(String html) ;
}
