package register;

import movieutility.Movie;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Represents a movie collection where movies can be added, retrieved,
 * and managed. The collection supports operations such as:
 * <ul>
 *     <li>Adding and removing movies to the collection</li>
 *     <li>Emptying the entire collection</li>
 *     <li>Retrieving details of a specific movie</li>
 *     <li>Retrieving a list of all movies</li>
 *     <li>Retrieving a list of movies by category</li>
 * </ul>
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.1
 * @since 11.09.2024
 */

public class MovieCollection {
  private final HashMap<String, Movie> collection;

  /**
   * <p>Initializes an instance of a movie collection that can be used in a
   * user interface. The collection uses a <b>HashMap</b> to store movies,
   * where the key is the name of the movie and the value is the movie object.</p>
   */
  public MovieCollection() {
    collection = new HashMap<>();
  }

  /**
   * <p>Prompts the user for input to add a new movie to the collection.</p>
   *
   * @param name the name of the movie (e.g., <b>Harry Potter</b>),
   *             which is also used as the key to retrieve the movie from the collection
   * @param category the category of the movie (e.g., <b>Fantasy</b>)
   * @param releaseDate the release date of the movie in <b>YYYY-MM-DD</b> format
   *                   (e.g., <b>2001-11-10</b>)
   * @param runTime the runtime of the movie
   *                (e.g., <b>152 minutes</b>)
   * @return <b>true</b> if the movie is already in the collection,
      *<b>false</b> if the movie is not in the collection
   */
  public boolean addMovie(String name, String category, LocalDate releaseDate, String runTime) {
    boolean movieExists = false;

    Movie newMovie = new Movie(name, category, releaseDate, runTime);

    if (collection.containsKey(newMovie.getName())) {
      movieExists = true;
    } else {
      collection.put(newMovie.getName(), newMovie);
    }
    return movieExists;
  }

  /**
   * <p>Prompts the user for a <b>String</b> input to remove an existing movie
   * from the collection.</p>
   *
   * @param movieName the name of the movie as a <b>String</b> (e.g., <b>Harry Potter</b>),
   *                  which is the key used to retrieve the movie from the collection
   * @return <b>true</b> if the movie exists in the collection, <b>false</b> if the movie does not
   */

  public boolean removeMovie(String movieName) {
    boolean movieExists = false;
    Movie existingMovie = collection.get(movieName);

    if (existingMovie != null) {
      collection.remove(movieName);
      movieExists = true;
    }
    return movieExists;
  }

  /**
   * <p>Clears all movies from the collection.</p>
   *
   * @return <b>true</b> if the collection contained movies,
     *<b>false</b> if the collection was empty.
   */

  public boolean clearAll() {
    boolean wasCleared = false;

    if (!collection.isEmpty()) {
      collection.clear();
      wasCleared = true;
    }
    return wasCleared;
  }

  /**
   * <p>Prompts the user for a <b>String</b> input to retrieve details of a specific movie
   * (e.g., <b>Harry Potter</b>).</p>
   *
   * @param movieName the name of the movie as a <b>String</b> (e.g., <b>Harry Potter</b>),
   *                  which is used as the key to retrieve the movie from the collection
   * @return <i>Movie details if the movie exists</i>,
   *         <i>"Movie not found: " followed by the name of the non-existent movie</i>
   */

  public String getMovieDetails(String movieName) {
    Movie movie = collection.get(movieName);
    if (movie != null) {
      return  "Name: " + movie.getName()
          +
          ", Category: " + movie.getCategory()
          +
          ", Release Date: " + movie.getReleaseDate()
          +
          ", Run Time: " + movie.getRunTime() + " minutes ";
    } else {
      return "Movie not found: " + movieName;
    }
  }

  /**
   * <p>Provides iteration access to the collection, allowing the user interface
   * to retrieve a list of all movies stored in the collection.</p>
   *
   * @return an iterator for the set of movie names in the collection
   */

  public Iterator<String> getListOfMovies() {
    return collection.keySet().stream().iterator();
  }

  /**
   * <p>Provides iteration access to a filtered list of movies from the collection,
   * allowing the user interface to retrieve movies by category.</p>
   *
   * @param category the category used to filter the movies (e.g., <b>Fantasy</b>)
   * @return an iterator for the list of movies in the specified category
   */
  public Iterator<String> getListOfMoviesByCategory(String category) {
    return collection.values().stream()
        .filter(movie -> movie.getCategory().equalsIgnoreCase(category))
        .map(Movie::getName)
        .iterator();
  }
}