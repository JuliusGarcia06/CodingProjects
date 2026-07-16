public class VideoGame extends Media {
    private String platform;
    private String genre;
    private String publisher;
    private double copiesSold; // In millions [cite: 41]

    public VideoGame(int id, String title, String platform, int releaseYear, 
                     String genre, String publisher, double copiesSold) {
        super(id, title, releaseYear);
        this.platform = platform;
        this.genre = genre;
        this.publisher = publisher;
        this.copiesSold = copiesSold;
    }

    public String getPlatform() { return platform; }
    public String getGenre() { return genre; }
    public String getPublisher() { return publisher; }
    public double getCopiesSold() { return copiesSold; }
}