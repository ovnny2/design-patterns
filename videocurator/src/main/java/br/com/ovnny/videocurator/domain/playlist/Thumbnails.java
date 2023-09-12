package br.com.ovnny.videocurator.domain.playlist;

public class Thumbnails {
    public Thumbnail default1;
    public Thumbnail medium;
    public Thumbnail high;
    public Thumbnail standard;
    public Thumbnail maxres;

    public Thumbnail getDefault1() {
        return default1;
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

    private void setDefault1(Thumbnail default1) {
        this.default1 = default1;
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