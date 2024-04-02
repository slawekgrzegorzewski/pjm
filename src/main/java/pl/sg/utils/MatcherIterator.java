package pl.sg.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;

public class MatcherIterator implements Iterator<String> {
    private final Matcher matcher;
    private String next;

    public static MatcherIterator wholeMatches(Matcher matcher) {
        return new MatcherIterator(matcher);
    }

    private MatcherIterator(Matcher matcher) {
        this.matcher = matcher;
        getNext();
    }

    private void getNext() {
        if (this.matcher.find()) {
            next = this.matcher.group(0);
        } else {
            next = null;
        }
    }

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        while (hasNext())
            list.add(next());
        return List.copyOf(list);
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String next() {
        if (next == null) throw new NoSuchElementException();
        String value = next;
        getNext();
        return value;
    }
}
