import java.util.*;
import java.util.stream.Collectors;

public class IMDBService {
    //Creeaza o clasa IMDBService, care sa aiba o lista de filme si care sa implementeze urmatoarele operatii:


    //Gasirea tuturor filmelor in care joaca un anumit actor
    //Ordonarea filmelor dupa anul aparitiei
    //Ordonarea filmelor dupa review-uri, descrescator (de la media de review-uri cea mai buna in jos)
    //Gasirea tuturor actorilor care au jucat in filme de un anumit tip si gen
    //Gasirea celui mai apreciat film de un anumit tip si gen
    //Gasirea autorului care a jucat in cele mai multe filme
    //Cele mai populare n filme (bazat pe cat de des apar acele filme apar in listele de filme favorite ale utilizatorilor)

    List<Movie> movieList;

    List<User> users;
    public IMDBService(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "IMDBService{" +
                "movieList=" + movieList +
                '}';
    }

    //Gruparea tuturor filmelor dupa gen
    public Map<Genre,List<String>> getMoviesByGenre (){
       return movieList.stream()
                .collect(Collectors.groupingBy(movie->movie.getGenre(), Collectors.mapping(movie -> movie.getTitle(), Collectors.toList())));
    }

    //Gasirea tuturor filmelor in care joaca un anumit actor
    public List<Movie> getMoviesByActor(String actorName) throws Exception{
        //ma duc in lista de filme si vreau sa ajung la fiecare film care are o lista de actori
        //din lista de actori a fiecarui film, caut numele cerut
        //imi construiesc mapa cu key numele actorului si value o lista de filme
        Actor actor = findActorByName(actorName);
        return movieList.stream()
               .filter(movie -> movie.getActors().contains(actor))
                .collect(Collectors.toList());
    }

    public Actor findActorByName (String actorName) throws Exception {
        Optional<Actor> actorOptional = movieList.stream()
                .flatMap(movie -> movie.getActors().stream())
                .filter(actor -> actor.getName().equals(actorName))
                .findFirst();

        return actorOptional.orElseThrow(() -> new Exception("actor not found"));
    }

    public List<Movie> sortMoviesByReleaseYear (){
        List<Movie> sortedMoviesByReleaseYear = new ArrayList<>(movieList);

        sortedMoviesByReleaseYear.sort((m1,m2) -> Integer.compare(m1.getReleaseYear(), m2.getReleaseYear()));
        return sortedMoviesByReleaseYear;

    }

    public List<Movie> sortMoviesByReviewAverage(){
        //1. calculam media de review-uri pentru fiecare film
        //2.

        List<Movie> sortedMoviesByReviewAverage = new ArrayList<>(movieList);

        sortedMoviesByReviewAverage.sort((m1,m2) -> Double.compare(m2.getReviewAverage(), m1.getReviewAverage()));
        //sortedMoviesByReviewAverage.sort(Comparator.comparing().reversed());
        return sortedMoviesByReviewAverage;
    }

    public List<Actor> getActorsByMovieTypeAndGenre(Type type, Genre genre){
        return movieList.stream()
                .filter(movie -> movie.getType().equals(type) && movie.getGenre().equals(genre))
                .flatMap(movie -> movie.getActors().stream())
                .collect(Collectors.toList());
    }

    public List<Actor> getActorsByMovieTypeAndGenre2(Type type, Genre genre){
        List<Actor> actorsByMovieTYpeAndGenre = new ArrayList<>();
        for (Movie movie: movieList){
            if (movie.getType().equals(type) && movie.getGenre().equals(genre)){
                for (Actor actor: movie.getActors()){
                    actorsByMovieTYpeAndGenre.add(actor);
                }
            }
        }
        return actorsByMovieTYpeAndGenre;
    }

    public Movie getBestRatedMovie(Type type, Genre genre) throws Exception {
        Optional<Movie> bestRatedMovieOptional = movieList.stream()
                .filter(movie -> movie.getType().equals(type) && movie.getGenre().equals(genre))
                .max((m1,m2) -> Double.compare(m1.getReviewAverage(),m2.getReviewAverage()));

        return bestRatedMovieOptional.orElseThrow(() -> new Exception("best rateed movie not found"));
    }



    public Actor getMostPopularActor(){
        //1. generez o mapa in care actorul este cheia si nr de filme in car a jucat valoarea
        //2. gasesc actorul cu valoarea maxima din mapa
        Map<Actor,Long> actorsByNumberOfMovies =  movieList.stream()
                .flatMap(movie -> movie.getActors().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

//        Optional<Map.Entry<Actor,Long>> entryOptional = actorsByNumberOfMovies.entrySet().stream()
//                .max((entry1, entry2) -> Long.compare(entry1.getValue(), entry1.getValue()));
//
//        if (entryOptional.isPresent()){
//            return entryOptional.get().getKey();
//        }





        Long maxValue = 0l;
        Actor mostPopularActor = null;
        for (Actor actor : actorsByNumberOfMovies.keySet()){
            if (actorsByNumberOfMovies.get(actor) > maxValue){
                maxValue = actorsByNumberOfMovies.get(actor);
                mostPopularActor=actor;
            }
        }
        return mostPopularActor;


    }

    //cheie -filmul, valoare - nr de aparitii in users




}
