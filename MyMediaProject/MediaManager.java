import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MediaManager {
    // Single global ArrayList allowed as a class member 
    private ArrayList<Media> mediaList = new ArrayList<>();

    // Load data from file name parameter [cite: 26]
    public void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                // Inside MediaManager.java -> loadData method
// 1. Go back to the standard split recommended by the assignment
String[] tokens = line.split(",");
String type = tokens[1].trim().toLowerCase();

int id = Integer.parseInt(tokens[0].trim());
String title;
int releaseYear;

switch (type) {
    case "movie":
        title = tokens[2].trim();
        String mDirector = tokens[3].trim();
        String mCountry = tokens[4].trim();
        releaseYear = Integer.parseInt(tokens[5].trim());
        String mRating = tokens[6].trim();
        
        // Extract numbers safely; default to 0 if the column is blank
        String durationStr = tokens[7].trim().replaceAll("[^0-9]", "");
        int mDuration = durationStr.isEmpty() ? 0 : Integer.parseInt(durationStr);
        
        String mDesc = tokens[8].trim();
        mediaList.add(new Movie(id, title, mDirector, mCountry, releaseYear, mRating, mDuration, mDesc));
        break;

    case "tv show":
        title = tokens[2].trim();
        String tvDirector = tokens[3].trim();
        String tvCountry = tokens[4].trim();
        releaseYear = Integer.parseInt(tokens[5].trim());
        String tvRating = tokens[6].trim();
        
        // Extract numbers safely; default to 0 if the column is blank
        String seasonStr = tokens[7].trim().replaceAll("[^0-9]", "");
        int tvSeasons = seasonStr.isEmpty() ? 0 : Integer.parseInt(seasonStr);
        
        String tvDesc = tokens[8].trim();
        mediaList.add(new TVShow(id, title, tvDirector, tvCountry, releaseYear, tvRating, tvSeasons, tvDesc));
        break;

    case "video game":
        title = tokens[2].trim();
        String vgPlatform = tokens[3].trim();
        releaseYear = Integer.parseInt(tokens[4].trim());
        String vgGenre = tokens[5].trim();
        String vgPublisher = tokens[6].trim();
        
        String vgSalesStr = tokens[7].trim();
        double vgSales = vgSalesStr.isEmpty() ? 0.0 : Double.parseDouble(vgSalesStr);
        
        mediaList.add(new VideoGame(id, title, vgPlatform, releaseYear, vgGenre, vgPublisher, vgSales));
        break;

    case "music album":
        releaseYear = Integer.parseInt(tokens[2].trim());
        String muArtist = tokens[3].trim();
        title = tokens[4].trim();
        
        String muSalesStr = tokens[5].trim();
        double muSales = muSalesStr.isEmpty() ? 0.0 : Double.parseDouble(muSalesStr);
        
        int muTracks = Integer.parseInt(tokens[6].trim());
        
        String muDurationStr = tokens[7].trim().replaceAll("[^0-9.]", "");
        double muDuration = muDurationStr.isEmpty() ? 0.0 : Double.parseDouble(muDurationStr);
        
        String muGenre = tokens[8].trim();
        mediaList.add(new MusicAlbum(id, releaseYear, muArtist, title, muSales, muTracks, muDuration, muGenre));
        break;
}
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // --- ANALYSIS METHODS [cite: 30, 31, 32] ---

    public int countTotalProducts() { return mediaList.size(); }

    public int countMovies() {
        int count = 0;
        for (Media m : mediaList) if (m instanceof Movie) count++;
        return count;
    }

    public int countTVShows() {
        int count = 0;
        for (Media m : mediaList) if (m instanceof TVShow) count++;
        return count;
    }

    public int countVideoGames() {
        int count = 0;
        for (Media m : mediaList) if (m instanceof VideoGame) count++;
        return count;
    }

    public int countMusicAlbums() {
        int count = 0;
        for (Media m : mediaList) if (m instanceof MusicAlbum) count++;
        return count;
    }

    public Media findOldestProduct() {
        if (mediaList.isEmpty()) return null;
        Media oldest = mediaList.get(0);
        for (Media m : mediaList) {
            if (m.getReleaseYear() < oldest.getReleaseYear()) {
                oldest = m;
            }
        }
        return oldest;
    }

    public MusicAlbum findMostPopularMusicAlbum() {
        MusicAlbum topAlbum = null;
        for (Media m : mediaList) {
            if (m instanceof MusicAlbum) {
                MusicAlbum album = (MusicAlbum) m;
                if (topAlbum == null || album.getGlobalSales() > topAlbum.getGlobalSales()) {
                    topAlbum = album;
                }
            }
        }
        return topAlbum;
    }

    public VideoGame findMostPopularVideoGame() {
        VideoGame topGame = null;
        for (Media m : mediaList) {
            if (m instanceof VideoGame) {
                VideoGame game = (VideoGame) m;
                if (topGame == null || game.getCopiesSold() > topGame.getCopiesSold()) {
                    topGame = game;
                }
            }
        }
        return topGame;
    }

    public String findMostCommonAgeRating() {
        Map<String, Integer> ratingCounts = new HashMap<>();
        for (Media m : mediaList) {
            if (m instanceof VisualMedia) {
                String rating = ((VisualMedia) m).getRating();
                ratingCounts.put(rating, ratingCounts.getOrDefault(rating, 0) + 1);
            }
        }
        String mostCommon = "N/A";
        int maxCount = -1;
        for (Map.Entry<String, Integer> entry : ratingCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommon = entry.getKey();
            }
        }
        return mostCommon;
    }

    public Movie findShortestMovie() {
        Movie shortest = null;
        for (Media m : mediaList) {
            if (m instanceof Movie) {
                Movie movie = (Movie) m;
                if (shortest == null || movie.getDuration() < shortest.getDuration()) {
                    shortest = movie;
                }
            }
        }
        return shortest;
    }

    public MusicAlbum findShortestMusicAlbum() {
        MusicAlbum shortest = null;
        for (Media m : mediaList) {
            if (m instanceof MusicAlbum) {
                MusicAlbum album = (MusicAlbum) m;
                if (shortest == null || album.getDuration() < shortest.getDuration()) {
                    shortest = album;
                }
            }
        }
        return shortest;
    }
}