package pl.sg.downloader;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class LinkDownloaderBuilder {
    private Path destinationDir;

    private String url;
    private String fileName;

    private int noOfThreads;
    private List<String> urls;
    private Function<String, String> fileNameSupplier;

    public static SaveAsFileBuilder of(String url) {
        LinkDownloaderBuilder b = new LinkDownloaderBuilder();
        b.url = url;
        return new SaveAsFileBuilder(b);
    }


    public static ThreadNumberBuilder of(List<String> urls) {
        LinkDownloaderBuilder b = new LinkDownloaderBuilder();
        b.urls = urls;
        return new ThreadNumberBuilder(b);
    }

    private LinkDownloader build() {
        if (!isSingleDownload() && !isMultipleDownload()) {
            throw new IllegalArgumentException();
        }
        if (url != null) {
            return new SingleLinkDownloader(url, destinationDir, fileName);
        } else {
            return new ParallelDownloader(destinationDir, urls, fileNameSupplier, noOfThreads);
        }
    }

    private boolean isSingleDownload() {
        return url != null && fileName != null;
    }

    private boolean isMultipleDownload() {
        return urls != null && fileNameSupplier != null && !urls.isEmpty();
    }


    public static class ThreadNumberBuilder {
        private final LinkDownloaderBuilder builder;

        public ThreadNumberBuilder(LinkDownloaderBuilder builder) {
            this.builder = builder;
        }

        public SaveAsFileProviderBuilder numberOfThreads(int noOfThreads) {
            builder.noOfThreads = noOfThreads;
            return new SaveAsFileProviderBuilder(builder);
        }
    }

    public static class SaveAsFileBuilder {
        private final LinkDownloaderBuilder builder;

        public SaveAsFileBuilder(LinkDownloaderBuilder builder) {
            this.builder = builder;
        }

        public DestinationDirBuilder as(String fileName) {
            builder.fileName = fileName;
            return new DestinationDirBuilder(builder);
        }
    }

    public static class SaveAsFileProviderBuilder {
        private final LinkDownloaderBuilder builder;

        public SaveAsFileProviderBuilder(LinkDownloaderBuilder builder) {
            this.builder = builder;
        }

        public DestinationDirBuilder as(Function<String, String> fileNameSupplier) {
            builder.fileNameSupplier = fileNameSupplier;
            return new DestinationDirBuilder(builder);
        }
    }


    public static class DestinationDirBuilder {
        private final LinkDownloaderBuilder builder;

        public DestinationDirBuilder(LinkDownloaderBuilder builder) {
            this.builder = builder;
        }

        public LinkDownloader to(Path destinationDir) {
            builder.destinationDir = destinationDir;
            return builder.build();
        }
    }
}
