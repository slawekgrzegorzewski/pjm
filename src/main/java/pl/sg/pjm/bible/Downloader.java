package pl.sg.pjm.bible;

import pl.sg.downloader.DownloadResult;
import pl.sg.downloader.LinkDownloaderBuilder;
import pl.sg.pjm.bible.utils.PJMBibleLinksRetriever;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Downloader {
    private final static Path BIBLE_FILES = Paths.get("D:", "Development", "bibleFiles");

    public static void main(String[] args) {
        DownloadResult result = download(BIBLE_FILES);
        if (result.isFail()) {
            System.out.println("Failed to download following files");
            System.out.println(String.join("\n", result.getUrls()));
        }
    }

    public static DownloadResult download(Path targetDirectory) {
        List<String> toDownload = IntStream.range(0, 67)
                .parallel()
                .mapToObj(new PJMBibleLinksRetriever()::getChaptersLinkForBook)
                .flatMap(List::stream)
                .map(Object::toString)
                .collect(Collectors.toList());
        return LinkDownloaderBuilder.of(toDownload)
                .numberOfThreads(10)
                .as(url -> {
                    String[] split = url.split("/");
                    return split[split.length - 1];
                })
                .to(targetDirectory)
                .download();
    }
}
