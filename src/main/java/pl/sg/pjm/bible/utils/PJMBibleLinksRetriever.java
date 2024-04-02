package pl.sg.pjm.bible.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.sg.utils.MatcherIterator;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class PJMBibleLinksRetriever {
    private final static String URL = "https://b.jw-cdn.org/apis/pub-media/P_GETPUBMEDIALINKS?pub=nwt&docid=0&fileformat=&langwritten=PDF&langspoken=PDF&booknum=%d";
    private final static Pattern MAX_RESOLUTION_URL_PATTER = Pattern.compile("\"url\":\"https://[a-zA-Z0123456789.\\-]*.net[^\"]*?720P\\.(mp4|m4v)\"");

    public List<String> getChaptersLinkForBook(int bookNumber) {
        return getBookData(bookNumber)
                .map(MAX_RESOLUTION_URL_PATTER::matcher)
                .map(MatcherIterator::wholeMatches)
                .map(MatcherIterator::toList)
                .stream()
                .flatMap(Collection::stream)
                .map(s -> s.replace("\"", "").replace("url:", ""))
                .collect(Collectors.toList());
    }

    private Optional<String> getBookData(int bookNumber) {
        String urlToBook = String.format(URL, bookNumber);
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlToBook)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            return of(new String(response.body().bytes()));
        } catch (IOException e) {
            System.out.printf("Error during retrieval of %s%n", urlToBook);
            System.out.println("e = " + e);
            return empty();
        }
    }
}
