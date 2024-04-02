package pl.sg.pjm.bible.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChapterVerse implements Comparable<ChapterVerse> {
    private final int chapter;
    private final int verse;

    public static ChapterVerse parse(String from) {
        Pattern pattern = Pattern.compile("(\\d+):(\\d+)");
        Matcher matcher = pattern.matcher(from);
        if (matcher.find()) {
            return new ChapterVerse(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))
            );
        } else {

            pattern = Pattern.compile("(\\d)");
            matcher = pattern.matcher(from);
            if (matcher.find()) {
                return new ChapterVerse(
                        1,
                        Integer.parseInt(matcher.group(1))
                );
            }
        }
        throw new RuntimeException();
    }

    ChapterVerse(int chapter, int verse) {
        this.chapter = chapter;
        this.verse = verse;
    }

    public int getChapter() {
        return chapter;
    }

    public int getVerse() {
        return verse;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterVerse that = (ChapterVerse) o;
        return chapter == that.chapter &&
                verse == that.verse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chapter, verse);
    }

    @Override
    public int compareTo(ChapterVerse o) {
        int chapterDiff = this.chapter - o.chapter;
        if (chapterDiff != 0) {
            return chapterDiff;
        }
        return this.verse - o.verse;
    }

    @Override
    public String toString() {
        return chapter + ":" + verse;
    }
}
