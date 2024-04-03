package pl.sg.pjm.bible;

import pl.sg.pjm.bible.model.Book;
import pl.sg.pjm.bible.model.ChapterVerse;
import pl.sg.pjm.bible.utils.FFMPEGChaptersExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class M4Chapters {
    public static final Path VIDEOS_LOCATION = Paths.get("D:", "Development", "bibleFiles");
    //public static final Path VIDEOS_LOCATION = Paths.get("F:", "Videos", "JWLibrary", "nwt_PDF");
    //public static final Path VIDEOS_LOCATION = Paths.get("C:", "Users", "qvc843", "Downloads", "Biblia");

    public static void main(String[] args) {
        List<Book> newTestament = Stream.of(Book.values()).filter(Book::isNewTestament).collect(Collectors.toList());
        System.out.println("New testament");
        calculateStatistics(newTestament);
        List<Book> oldTestament = Stream.of(Book.values()).filter(not(Book::isNewTestament)).collect(Collectors.toList());
        System.out.println("Old testament");
        calculateStatistics(oldTestament);
        List<Book> all = Stream.of(Book.values()).collect(Collectors.toList());
        System.out.println("All");
        calculateStatistics(all);
    }

    public static Map<Book, List<ChapterVerse>> calculateStatistics(Path videosLocation) {
        List<Book> allBooks = Stream.of(Book.values()).collect(Collectors.toList());
        try (Stream<Path> walk = Files.walk(videosLocation).filter(isFileInBooks(allBooks))) {
            return videoStats(walk);
        } catch (IOException e) {
            return null;
        }
    }

    private static void calculateStatistics(List<Book> booksToGenerateStats) {
        try (Stream<Path> walk = Files.walk(VIDEOS_LOCATION).filter(isFileInBooks(booksToGenerateStats))) {
            Map<Book, List<ChapterVerse>> allBooks = videoStats(walk);
            printSummary(allBooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> isFileInBooks(List<Book> expectedBooks) {
        return file -> expectedBooks.stream().anyMatch(fileForBook(file));
    }

    private static Predicate<Book> fileForBook(Path file) {
        return book -> file.getFileName().toString().startsWith(String.format("nwt_%02d_", book.getOrder()));
    }

    private static Map<Book, List<ChapterVerse>> videoStats(Stream<Path> walk) {
        return walk.filter(Files::isRegularFile).collect(
                Collectors.toMap(
                        Book::fromFileName,
                        new FFMPEGChaptersExtractor()::getChaptersFromFile,
                        (strings, strings2) -> {
                            strings.addAll(strings2);
                            return strings;
                        }));
    }


    private static void printSummary(Map<Book, List<ChapterVerse>> allBooks) {
        int allVerses = allBooks.keySet().stream().flatMap(b -> b.getVerses().stream()).mapToInt(i -> i).sum()
                - allBooks.keySet().stream().flatMap(b -> b.getSkippedVersesInNWT().stream()).mapToInt(i -> i).sum();
        int translatedVerses = allBooks.values().stream().mapToInt(List::size).sum();
        System.out.println(String.format("Translated %d out of %d verses.", translatedVerses, allVerses));
        System.out.println(String.format("That is %.2f%% of total.", translatedVerses * 100.0 / allVerses));
    }
}
