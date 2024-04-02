package pl.sg.downloader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class SingleLinkDownloader implements LinkDownloader {

    private final String url;
    private final Path destinationDir;
    private final String fileName;

    SingleLinkDownloader(String url, Path destinationDir, String fileName) {
        Objects.requireNonNull(url);
        Objects.requireNonNull(destinationDir);
        Objects.requireNonNull(fileName);
        this.url = url;
        this.destinationDir = destinationDir;
        this.fileName = fileName;
    }


    public DownloadResult download() {
        try {
            createDestinationDirIfNotExists(this.destinationDir);
            byte[] content = getContent(this.url);
            saveToFile(this.destinationDir.resolve(this.fileName), content);
            return DownloadResult.success(this.url, content.length);
        } catch (IOException e) {
            return DownloadResult.fail(this.url);
        }
    }
}
