package pl.sg.pjm.bible.utils;

import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.UnknownBox;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import pl.sg.pjm.bible.model.ChapterVerse;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class Mp4ParserChaptersExtractor implements ChaptersExtractor {

    @Override
    public List<ChapterVerse> getChaptersFromFile(Path file) {
        Map<String, List<Integer>> result = parseFile(file);
        return result.keySet().stream().map(k -> {
            Pattern chapterVersePattern = Pattern.compile("(\\d*:\\d*)");
            Matcher matcher = chapterVersePattern.matcher(k);
            if (matcher.find()) {
                return ChapterVerse.parse(matcher.group(1));
            }
            throw new NullPointerException();
        }).collect(Collectors.toList());

    }

    private Map<String, List<Integer>> parseFile(Path file) {
        try (FileInputStream fileInputStream = new FileInputStream(file.toAbsolutePath().toString()); FileChannel fc = fileInputStream.getChannel()) {
            IsoFile isoFile = new IsoFile(fc);
            MovieBox moov = isoFile.getMovieBox();

            return findType(moov).filter(b -> b instanceof UnknownBox).map(UnknownBox.class::cast).map(this::parseBox).orElseGet(() -> {
                System.out.println(file);
                return new HashMap<>();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Box> findType(Box b) {
        if (b == null) return empty();
        if (b.getType().equals("chpl")) {
            return of(b);
        }
        if (b instanceof Container) {
            for (Box child : ((Container) b).getBoxes()) {
                Optional<Box> chpl = findType(child);
                if (chpl.isPresent()) {
                    return chpl;
                }
            }
        }
        return empty();
    }

    private Map<String, List<Integer>> parseBox(UnknownBox box) {
        Map<String, List<Integer>> chapters = new HashMap<>();
        ByteBuffer bb = box.getData();
//        bb.rewind();
//        writeToAFile(bb);
        bb.rewind();
        bb.get();
        bb.getInt();
        bb.getInt();
        while (bb.hasRemaining()) {
            readSingleChapter(chapters, bb);
        }
        return chapters;
    }

    private static void readSingleChapter(Map<String, List<Integer>> chapters, ByteBuffer bb) {
        int first = bb.getInt();
        int second = bb.getInt();
        int titleLength = bb.get();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < titleLength; i++) {
            char c = (char) bb.get();
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        chapters.put(sb.toString(), List.of(first, second));
    }
}
