package br.com.ovnny.videocurator.utils;

public final class ParamExtractor {

    private static ParamExtractor INSTANCE;

    private ParamExtractor() {
    }

    private ParamExtractor getInstance() {
        if (INSTANCE == null) {
            ParamExtractor INSTANCE = new ParamExtractor();
        }
        return INSTANCE;
    }

    public static String extractVideoId(String url) {
        return url.replaceAll(".*list=", "");
    }
}