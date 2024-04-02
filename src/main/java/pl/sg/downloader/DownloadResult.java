package pl.sg.downloader;

import java.util.List;

public class DownloadResult {

    public enum Result {SUCCESS, FAIL}

    private final List<String> urls;
    private final Result result;
    private final int downloadSize;

    public static DownloadResult success(String url, int downloadSize) {
        return DownloadResult.success(List.of(url), downloadSize);
    }

    public static DownloadResult success(List<String> urls, int downloadSize) {
        return new DownloadResult(urls, Result.SUCCESS, downloadSize);
    }

    public static DownloadResult fail(String url) {
        return DownloadResult.fail(List.of(url));
    }

    public static DownloadResult fail(List<String> urls) {
        return new DownloadResult(urls, Result.FAIL, 0);
    }

    private DownloadResult(List<String> urls, Result result, int downloadSize) {
        this.urls = urls;
        this.result = result;
        this.downloadSize = downloadSize;
    }

    public String getUrl() {
        return urls.get(0);
    }

    public List<String> getUrls() {
        return urls;
    }

    public Result getResult() {
        return result;
    }

    public boolean isSuccess() {
        return result == Result.SUCCESS;
    }

    public boolean isFail() {
        return result == Result.FAIL;
    }

    public int getDownloadSize() {
        return downloadSize;
    }
}
