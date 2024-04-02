package pl.sg.pjm.bible.utils;

import pl.sg.pjm.bible.model.ChapterVerse;

import java.nio.file.Path;
import java.util.List;

public interface ChaptersExtractor {
    List<ChapterVerse> getChaptersFromFile(Path file);
}
