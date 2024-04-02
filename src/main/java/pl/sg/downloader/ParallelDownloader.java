package pl.sg.downloader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ParallelDownloader implements LinkDownloader {

    private final Path destinationDir;
    private final List<String> urls;
    private final Function<String, String> fileNameSupplier;
    private final ExecutorService executor;

    private final AtomicInteger downloadCount = new AtomicInteger(0);
    private final Map<String, Boolean> downloaded = new ConcurrentHashMap<>();

    ParallelDownloader(Path destinationDir, List<String> urls, Function<String, String> fileNameSupplier, int noOfThreads) {
        Objects.requireNonNull(urls);
        Objects.requireNonNull(destinationDir);
        Objects.requireNonNull(fileNameSupplier);
        this.destinationDir = destinationDir;
        this.urls = new ArrayList<>(urls);
        this.fileNameSupplier = fileNameSupplier;
        this.executor = Executors.newFixedThreadPool(noOfThreads);
    }

    public DownloadResult download() {
        try {
            createDestinationDirIfNotExists(this.destinationDir);
            urls
                    .stream()
                    .peek(url -> downloaded.put(url, false))
                    .parallel()
                    .forEach(this::download);
            return DownloadResult.success(this.urls, 0);
        } catch (IOException e) {
            return DownloadResult.fail(this.urls);
        } finally {
            executor.shutdown();
        }
    }

    private void download(String url) {
        String[] units = {"B", "kB", "MB", "GB", "TB"};
        DownloadResult downloadResult = LinkDownloaderBuilder.of(url)
                .as(fileNameSupplier.apply(url))
                .to(this.destinationDir)
                .download();
        if (downloadResult.isSuccess()) {
            float size = downloadResult.getDownloadSize();
            int unit = 0;
            while (size > 1024) {
                size /= 1024;
                unit++;
            }
            downloaded.put(url, true);
            System.out.println(fileName(url)
                    + " download finished, "
                    + "total size "
                    + String.format("%06.2f %s", size, units[unit])
                    + ". Finished " + downloadCount.incrementAndGet() + " of " + urls.size());
            if (downloadCount.get() > 837) {

                report();
            }
        } else {
            System.out.println("Download failed " + fileName(url) + ". Retrying...");
            download(downloadResult.getUrl());
            report();
        }
    }

    private void report() {
        downloaded.entrySet().stream().filter(e -> !e.getValue()).forEach(e -> System.out.println(e.getKey()));
    }

    private static String fileName(String url) {
        String[] split = url.split("/");
        return split[split.length - 1];
    }
}
