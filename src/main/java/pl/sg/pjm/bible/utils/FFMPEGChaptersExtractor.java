package pl.sg.pjm.bible.utils;

import pl.sg.pjm.bible.model.Book;
import pl.sg.pjm.bible.model.ChapterVerse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FFMPEGChaptersExtractor implements ChaptersExtractor {
    public static final Path FFMPEG_EXE_PATH = Paths.get("C:", "Users", "slawe", "Downloads", "ffmpeg", "bin", "ffmpeg.exe");
    private static final List<Book> COMPLETED_BOOKS = List.of(Book.GENESIS, Book.EXODUS, Book.LEVITICUS, Book.MATTHEW, Book.MARK, Book.LUKE, Book.JOHN, Book.ACTS, Book.ROMANS, Book.FIRST_CORINTHIANS, Book.SECOND_CORINTHIANS, Book.GALATIANS, Book.EPHESIANS, Book.PHILIPPIANS, Book.COLOSSIANS, Book.FIRST_THESSALONIANS, Book.SECOND_THESSALONIANS, Book.FIRST_TIMOTHY, Book.SECOND_TIMOTHY, Book.TITUS, Book.PHILEMON, Book.HEBREWS, Book.JAMES, Book.FIRST_PETER, Book.SECOND_PETER, Book.FIRST_JOHN, Book.SECOND_JOHN, Book.THIRD_JOHN, Book.JUDE, Book.REVELATION);

    @Override
    public List<ChapterVerse> getChaptersFromFile(Path file) {
        List<ChapterVerse> result = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(FFMPEG_EXE_PATH.toAbsolutePath().toString(), "-i", file.toAbsolutePath().toString());
            processBuilder.redirectErrorStream(true);
            Process proc = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("Chapter ")) {
                    reader.readLine();
                    line = reader.readLine();
                    String trim = line.replace("        title           :", "").trim();
                    Pattern chapterVersePattern = Pattern.compile("((\\d+:)?\\d+)$");
                    Matcher matcher = chapterVersePattern.matcher(trim);
                    if (line.contains("Przypis"))
                        continue;
                    if (matcher.find()) {
                        result.add(ChapterVerse.parse(matcher.group(1)));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Book bookOfFile = Book.fromFileName(file);
        Integer numberOfVerses = bookOfFile.getVerses().get(result.get(0).getChapter() - 1);
        Integer numberOfSkippedVerses = bookOfFile.getSkippedVersesInNWT().get(result.get(0).getChapter() - 1);
        if (COMPLETED_BOOKS.contains(bookOfFile) && (numberOfVerses - numberOfSkippedVerses) != result.size()) {
            throw new RuntimeException("This exception means new bible was translated and contains verses skipped in the NWT translation. " +
                    "It's required to update Book.skippedVersesInNWT. The problematic file was: " + file);
        }
        return result;
    }
}
