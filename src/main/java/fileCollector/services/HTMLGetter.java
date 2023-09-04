package fileCollector.services;

import fileCollector.interfaces.IHTMLGetter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HTMLGetter implements IHTMLGetter {
    @Override
    public String getHTML(String url) {

        int offset=20;
        List<String> html= new ArrayList<>();
        try {
            Document doc =  Jsoup.connect(url).get();

            do {
                html.add(doc.html());
                doc = Jsoup.connect(url + "?offset=" + offset).get();
                offset += 20;
            } while (doc.html().contains("<div class=\"bp_post clear_fix \""));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        return String.join("\n",html);

    }
}
