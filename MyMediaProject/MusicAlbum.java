public class MusicAlbum extends Media {
    private String artist;
    private double globalSales;
    private int tracks;
    private double duration; // In minutes, using double [cite: 17]
    private String genre;

    public MusicAlbum(int id, int releaseYear, String artist, String title, 
                      double globalSales, int tracks, double duration, String genre) {
        // Notice the order matching the CSV layout attribute requirements [cite: 42]
        super(id, title, releaseYear);
        this.artist = artist;
        this.globalSales = globalSales;
        this.tracks = tracks;
        this.duration = duration;
        this.genre = genre;
    }

    public String getArtist() { return artist; }
    public double getGlobalSales() { return globalSales; }
    public int getTracks() { return tracks; }
    public double getDuration() { return duration; }
    public String getGenre() { return genre; }
}