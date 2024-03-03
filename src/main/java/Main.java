import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User("Popescu", "Ion", "user");
        User user2 = new User("Gheorghe", "Marin", "admin");

        Movie movie1 = new Movie("movie 1", 2021,Genre.COMEDY, Type.MOVIE);
        Movie movie2 = new Movie("movie 2", 1996, Genre.ACTION, Type.MOVIE);
        Movie movie3 = new Movie("series1",2016,Genre.DRAMA,Type.TV_SHOW);
        Movie movie4 = new Movie("series2",2011, Genre.COMEDY,Type.TV_SHOW);

        Actor actor1 = new Actor("actor1");
        Actor actor2= new Actor("actor2");
        Actor actor3 = new Actor("actor3");
        Actor actor4 = new Actor("actor4");
        Actor actor5 = new Actor("actor5");
        Actor actor6 = new Actor("actor6");
        Actor actor7 = new Actor("actor7");
        Actor actor8 = new Actor("actor8");


        movie1.setActors(List.of(actor1,actor2));
        movie2.setActors(List.of(actor3,actor4));
        movie3.setActors(List.of(actor5,actor6,actor2));
        movie4.setActors(List.of(actor7,actor8));


         List<Movie> movies = List.of(movie1, movie2, movie3,movie4);

//         ReleaseYearComparator releaseYearComparator = new ReleaseYearComparator();
//         Comparator<Movie> comparator = (m1,m2) -> Integer.compare(m1.getReleaseYear(), m2.getReleaseYear());
//
//
//
//         movies.sort(new ReleaseYearComparator());
//         movies.sort((m1,m2) -> Integer.compare(m1.getReleaseYear(), m2.getReleaseYear()));


        IMDBService service = new IMDBService(movies);

        user.addMovieToFavorites(movie1);
        user2.addMovieToFavorites(movie2);

        user.addMovieReview(movie1,Review.FIVE_STARS);
        user.addMovieReview(movie1,Review.THREE_STARS);
        user.addMovieReview(movie1,Review.TWO_STARS);
        user.addMovieReview(movie2,Review.FIVE_STARS);
        user.addMovieReview(movie1,Review.FIVE_STARS);

        System.out.println(user.getFavoriteMovies());
        System.out.println("Movies by genre: ");
        System.out.println(service.getMoviesByGenre());

        System.out.println("----------------");
        System.out.println("movies of actor2");
        try {
            System.out.println(service.getMoviesByActor("acto234234"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("----------------");
        System.out.println("movies ordered by year");
        System.out.println(service.sortMoviesByReleaseYear());

        System.out.println("----------------");
        System.out.println("movies ordered by reviewavreage");
        System.out.println(service.sortMoviesByReviewAverage());


        System.out.println("----------------");
        System.out.println("actors that play in film de comedie");
        System.out.println(service.getActorsByMovieTypeAndGenre(Type.MOVIE, Genre.COMEDY));

        System.out.println("----------------");
        System.out.println("best rated movie");
        try {
            System.out.println(service.getBestRatedMovie(Type.MOVIE, Genre.COMEDY));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }




//        User user4 = new User("ionut","sdf","dsfdsf");
//        user4.addMovieToFavorites(movie1);
//
//
//        UserService userService = new UserService(user);
//        userService.addMovieToFavorites(movie1);






    }
}
