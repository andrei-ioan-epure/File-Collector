package fileCollector.services;

import fileCollector.interfaces.ICommentGetter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentGetter implements ICommentGetter {

    @Override
    public List<Element> getComments(String html)  {

        Document doc=Jsoup.parse(html);
        return new ArrayList<>(doc.select("a"));


    }
}
