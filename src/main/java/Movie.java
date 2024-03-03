import java.util.ArrayList;
import java.util.List;

public class Movie {
    //Un film este caracterizat de title,
    // releaseYear, genre, cast (lista de actori), type, reviews (lista de review-uri)

    private String title;
    private int releaseYear;
    private Genre genre;
    private Type type;
    private List<Actor> actors;
    private List<Review> reviews;

    private Double reviewAverage;

    public Movie(String title, int releaseYear, Genre genre, Type type) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.type = type;
        this.actors = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.reviewAverage = 0.0;
    }

    public Double getReviewAverage() {
        return reviewAverage;
    }

    public void setReviewAverage() {
        Double sum = 0.0;
        for (Review review: reviews){
            sum+= review.getValue();
        }
        reviewAverage = sum/reviews.size();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                ", type=" + type +
                ", actors=" + actors +
                ", reviews=" + reviews +
                ", reviewAverage=" + reviewAverage +
                '}';
    }
}
