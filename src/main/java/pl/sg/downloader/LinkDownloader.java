package pl.sg.downloader;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface LinkDownloader {
    DownloadResult download();

    default void createDestinationDirIfNotExists(Path destinationDir) throws IOException {
        if (!Files.isDirectory(destinationDir)) {
            Files.createDirectory(destinationDir);
        }
    }

    default byte[] getContent(String url) throws IOException {
        return new OkHttpClient()
                .newCall(
                        new Request.Builder()
                                .url(url)
                                .get()
                                .build())
                .execute()
                .body()
                .bytes();
    }

    default void saveToFile(Path destinationFile, byte[] content) throws IOException {
        Files.write(destinationFile, content);
    }
}

