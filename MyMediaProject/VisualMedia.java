public abstract class VisualMedia extends Media {
    private String director;
    private String country;
    private String rating;
    private String description;

    public VisualMedia(int id, String title, String director, String country, 
                       int releaseYear, String rating, String description) {
        super(id, title, releaseYear);
        this.director = director;
        this.country = country;
        this.rating = rating;
        this.description = description;
    }

    public String getDirector() { return director; }
    public String getCountry() { return country; }
    public String getRating() { return rating; }
    public String getDescription() { return description; }
}