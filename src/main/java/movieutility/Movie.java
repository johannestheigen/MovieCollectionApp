package movieutility;

import java.time.LocalDate;

/**
 <p>Represents an instance of a movie
 * with key attributes <b>name</b>, <b>category</b>,
 * <b>release date</b> and <b>runtime</b>
 * which can be added to a collection of movies.</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.2
 * @since 11.11.2024
 **/
public class Movie {
  private String name;
  private String category;
  private LocalDate releaseDate;
  private String runTime;

  /**
   * <p>Initializes an instance of a movie.</p>
   *
   * @param name        the name of the movie, e.g., <b>Harry Potter and the Sorcerer's Stone</b>.
   *                    Set by user input.
   * @param category    the category of the movie, e.g., <b>Fantasy</b>.
   *                    Set by user input.
   * @param releaseDate the release date of the movie in the format <b>YYYY-MM-DD</b>
   *                    e.g., <b>2001-11-10</b>
   *                    Set by user input.
   * @param runTime     the runtime of the movie e.g., <b> 152 minutes</b>. Set by user input.
   */
  public Movie(String name, String category, LocalDate releaseDate, String runTime) {
    setName(name);
    setCategory(category);
    setReleaseDate(releaseDate);
    setRunTime(runTime);
  }

  /**
   * <p>Returns the name of the movie in the form of a <b>String</b>.</p>
   *
   * @return the name of a movie e.g., <b>Harry Potter and the Sorcerer's Stone</b></p>
   */
  public String getName() {
    return name;
  }

  /**
   * <p>Sets the name of the movie.</p>
   *
   * @param name the name of the movie, e.g., <b>Harry Potter and the Sorcerer's Stone</b>
   * @throws IllegalArgumentException if the input <b>null</b> or <b>empty</b>
   */
  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name cannot be null or empty");
    }
    this.name = name;
  }

  /**
   * <p>Returns the category of the movie as a <b>String</b>.</p>
   *
   * @return the category of the movie, e.g., <b>Fantasy</b>
   */
  public String getCategory() {
    return category;
  }

  /**
   * <p>Sets the category of the movie.</p>
   *
   * @param category the category of the movie e.g., <b>Fantasy</b>
   * @throws IllegalArgumentException if the input is <b>null</b> or <b>empty</b>
   */
  public void setCategory(String category) {
    if (category == null || name.isEmpty()) {
      throw new IllegalArgumentException("The category cannot be null or empty");
    }
    this.category = category;
  }

  /**
   * Returns the release date of the movie as a <b>LocalDate</b>.
   *
   * @return the release date of the movie in <b>YYYY-MM-DD</b> format. e.g., <b>2001-11-10</b>
   */
  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  /**
   * <p>Sets the release date of the movie.</p>
   *
   * @param releaseDate the release date of a movie. in the format <b>YYYY-MM-DD</b>
   *                    e.g, <b>2001-11-10</b>
   * @throws IllegalArgumentException if the input is <b>null</b>.
   */
  public void setReleaseDate(LocalDate releaseDate) {
    if (releaseDate == null) {
      throw new IllegalArgumentException("The release date cannot be null");
    }
    this.releaseDate = releaseDate;
  }

  /**
   * <p>Returns the runtime of a movie in the form a <b>String</b></p>.
   *
   * @return the runtime of a movie e.g., <b>152 minutes</b>
   */
  public String getRunTime() {
    return runTime;
  }

  /**
   * Sets the run time of the movie.
   *
   *  @param runTime the runtime of a movie e.g, <b>152 minutes</b>
   * @throws IllegalArgumentException if the runtime is <b>null</b> or <b>empty</b>
   */
  public void setRunTime(String runTime) {
    if (runTime == null || runTime.isEmpty()) {
      throw new IllegalArgumentException("The runtime cannot be null or empty");
    }
    this.runTime = runTime;
  }
}