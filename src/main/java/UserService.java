public class UserService {

    User user;

    //Creeaza o clasa UserService
    //
    //Adaugarea unui review la un film
    //Adaugarea unui film la o lista de filme favorite


    public UserService(User user) {
        this.user = user;
    }

    public void addMovieReview(Movie movie, Review review){
        //trebuie sa adaug in lista de review-uri a filmului, o recenzie
        movie.getReviews().add(review);
    }

    public void addMovieToFavorites(Movie movie){
        user.getFavoriteMovies().add(movie);

    }
}
