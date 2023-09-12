package br.com.ovnny.videocurator.domain.playlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Thumbnails {

    @JsonProperty(value = "default")
    public Thumbnail defaultThumbnail;
    public Thumbnail medium;
    public Thumbnail high;
    public Thumbnail standard;
    public Thumbnail maxres;

    public Thumbnail getDefaultThumbnail() {
        return defaultThumbnail;
    }

    public Thumbnail getMedium() {
        return medium;
    }

    public Thumbnail getHigh() {
        return high;
    }

    public Thumbnail getStandard() {
        return standard;
    }

    public Thumbnail getMaxres() {
        return maxres;
    }

    private void setDefaultThumbnail(Thumbnail defaultThumbnail) {
        this.defaultThumbnail = defaultThumbnail;
    }

    private void setMedium(Thumbnail medium) {
        this.medium = medium;
    }

    private void setHigh(Thumbnail high) {
        this.high = high;
    }

    private void setStandard(Thumbnail standard) {
        this.standard = standard;
    }

    private void setMaxres(Thumbnail maxres) {
        this.maxres = maxres;
    }
}