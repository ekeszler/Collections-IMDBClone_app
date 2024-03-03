import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String surname;
    private String role;
    private List<Movie> favoriteMovies;

    public User(String name, String surname, String role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.favoriteMovies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(List<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", favoriteMovies=" + favoriteMovies +
                '}';
    }

    public void addMovieReview(Movie movie, Review review){
        //trebuie sa adaug in lista de review-uri a filmului, o recenzie
        movie.getReviews().add(review);
        movie.setReviewAverage();
    }

    public void addMovieToFavorites(Movie movie){
        favoriteMovies.add(movie);

    }
}
