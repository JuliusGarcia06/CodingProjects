public class Driver {
    public static void main(String[] args) {
        MediaManager manager = new MediaManager();
        
        // Pass your data text file path here [cite: 52]
        String filename = "project1dataset.csv"; 
        manager.loadData(filename);

        // Printing results with descriptive labels 
        System.out.println("Total number of products: " + manager.countTotalProducts());
        System.out.println("Total number of Movies: " + manager.countMovies());
        System.out.println("Total number of TV Shows: " + manager.countTVShows());
        System.out.println("Total number of Video Games: " + manager.countVideoGames());
        System.out.println("Total number of Music Albums: " + manager.countMusicAlbums());
        
        Media oldest = manager.findOldestProduct();
        System.out.println("Oldest product: " + (oldest != null ? oldest.getTitle() + " (" + oldest.getReleaseYear() + ")" : "N/A"));
        
        MusicAlbum popularAlbum = manager.findMostPopularMusicAlbum();
        System.out.println("Most popular Music Album: " + (popularAlbum != null ? popularAlbum.getTitle() + " by " + popularAlbum.getArtist() : "N/A"));
        
        VideoGame popularGame = manager.findMostPopularVideoGame();
        System.out.println("Most popular Video Game: " + (popularGame != null ? popularGame.getTitle() : "N/A"));
        
        System.out.println("Most common age rating among film products: " + manager.findMostCommonAgeRating());
        
        Movie shortestMovie = manager.findShortestMovie();
        System.out.println("Shortest Movie: " + (shortestMovie != null ? shortestMovie.getTitle() + " (" + shortestMovie.getDuration() + " mins)" : "N/A"));
        
        MusicAlbum shortestAlbum = manager.findShortestMusicAlbum();
        System.out.println("Shortest Music Album: " + (shortestAlbum != null ? shortestAlbum.getTitle() + " (" + shortestAlbum.getDuration() + " mins)" : "N/A"));
    }
}