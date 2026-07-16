public class TVShow extends VisualMedia {
    private int numberOfSeasons;

    public TVShow(int id, String title, String director, String country, 
                  int releaseYear, String rating, int numberOfSeasons, String description) {
        super(id, title, director, country, releaseYear, rating, description);
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfSeasons() { return numberOfSeasons; }
}