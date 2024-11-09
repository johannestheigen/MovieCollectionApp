package app;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;
import register.MovieCollection;

/**
 * <p>Represents the text-based user interface for interacting with the movie collection application.</p>
 * <p>Provides the user with various options to manage and interact with the movie collection, such as adding, removing, and displaying movies.</p>
 * <p>The <code>UserInterface</code> interacts with the <code>MovieCollection</code> class to handle user input and manage the collection.</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.1
 * @since 11.09.2024
 */
public class UserInterface {
  private MovieCollection collection;

  /**
   * <p>Initializes a movie collection and populates it with default movie entries.</p>
   *
   * <p>Example movies <b>Harry Potter and the Sorcerer's Stone</b> and
   * <b>Madagascar</b> are added to the collection as default movies.</p>
   */
  public void init() {
    collection = new MovieCollection();
    collection.addMovie("Harry Potter and the Sorcerer's Stone",
        "Fantasy", LocalDate.of(2001, 11, 10), "152");
    collection.addMovie("Madagascar", "Animated comedy", LocalDate.of(2005, 5, 27), "86");
  }

  /**
   * Starts the text-based user interface by displaying the menu and
   * allowing user interaction.
   */
  public void start() {
    init();
    showMenu();
    userInput();
  }

  /**
   * <p>Allows the user to interact with the program through a scanner for input,
   * providing several options.</p>
   * <ul>
   *     <li><b>'help'</b> to view the command list</li>
   *     <li><b>'x'</b> to exit the program</li>
   *     <li><b>'add'</b> to add a new movie to the collection</li>
   *     <li><b>'remove'</b> to remove an existing movie from the collection</li>
   *     <li><b>'print-details'</b> to print the details of a specific movie</li>
   *     <li><b>'print-all'</b> to print a list of all the movies in the collection</li>
   *     <li><b>'print-category'</b> to print a list of all movies of a specific category</li>
   *     <li><b>'home'</b> to return to the main menu</li>
   * </ul>
   *
   * <p>If the input is invalid, an error message is displayed:
   * 'Invalid input, type 'help' to view the command list.'</p>
   */
  public void userInput() {
    boolean running = true;
    Scanner sc = new Scanner(System.in);

    while (running) {
      String input = sc.nextLine();

      if (input.equalsIgnoreCase("help")) {
        showCommands();
        continue;
      }
      switch (input.toLowerCase()) {
        case "x":
          System.out.println("Exiting the program...");
          running = false;
          break;
        case "add":
          addMovie(sc);
          break;
        case "remove":
          removeMovie(sc);
          break;
        case "print-details":
          printMovieDetails(sc);
          break;
        case "print-all":
          printAllMovies();
          break;
        case "print-category":
          printMoviesByCategory(sc);
          break;
        case "home":
          showMenu();
          break;
        case "clear":
          clearMovies(sc);
          break;
        default:
          System.out.println("Invalid input, type 'help' to view command list");
          break;
      }
      if (running) {
        showMenu();
      }
    }
  }

  /**
   * Displays the main menu to the user.
   */
  public void showMenu() {
    System.out.println("""
        Welcome to the Movie Collection App.
        Type 'help' to view commands
        Type 'x' to exit
        """);
  }

  /**
   * Displays the command list to the user.
   */
  public void showCommands() {
    System.out.println("""
        Type 'Add' to add a movies.
        Type 'Remove' to add a movies.
        Type 'Print-details' to print movie details.
        Type 'Print-all' to print all movies.
        Type 'Print-Category' to print movies by category.
        Type 'Clear' to remove everything from the collection.
        Type 'Home' to return to main menu.
        """);
  }

  /**
   * <p>Allows the user to add a new movie to the collection through user interaction.</p>
   * <p>The user is prompted to enter the movie's name,
   * category, release date (in the format YYYY-MM-DD),
   * and runtime. If the date format is invalid, the user will be asked to re-enter it.</p>
   *
   * @param sc Scanner object used for capturing user input
   */
  private void addMovie(Scanner sc) {
    System.out.print("Enter movie name: ");
    final String name = sc.nextLine();

    System.out.print("Enter category: ");
    String category = sc.nextLine();

    LocalDate releaseDate = null;
    while (releaseDate == null) {
      System.out.print("Enter release date (YYYY-MM-DD): ");
      String releaseDateStr = sc.nextLine();

      try {
        releaseDate = LocalDate.parse(releaseDateStr);
      } catch (DateTimeParseException e) {
        System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD");
      }
    }

    System.out.print("Enter runtime: ");
    String runTime = sc.nextLine();

    boolean movieExists = collection.addMovie(name, category, releaseDate, runTime);
    if (movieExists) {
      System.out.println(name + " already exists.");
    } else {
      System.out.println(name + " added successfully!");
    }
  }

  /**
   * <p>Allows the user to remove an existing movie from the collection
   * through user interaction.</p>
   * <p>The user is prompted to enter the name of
   * the movie they want to remove. The movie's name is used as the key to
   * retrieve the movie from the collection.
   * If the movie exists, it will be removed. If the movie doesn't exist,
   * it prompts the user tha the movie does not exist and asks it try again.'</p>
   *
   * @param sc Scanner object used for capturing user input.
   */
  private void removeMovie(Scanner sc) {
    System.out.print("Enter movie to remove: ");
    final String name = sc.nextLine();

    boolean removed = collection.removeMovie(name);
    if (removed) {
      System.out.println(name + " removed successfully!");
    } else {
      System.out.println(name + " does not exists. Please try again");
    }
  }

  /**
   * <p>Prints the details of a specific movie.</p>
   * <ul>
   *     <li>Movie name</li>
   *     <li>Movie category</li>
   *     <li>Release date</li>
   *     <li>Runtime</li>
   * </ul>
   * <p>The user is prompted to enter the name of the movie they want to view details for.
   * If the movie exists in the collection, its details are displayed. If the movie doesn't exist,
   * it prompts the user that movie does not exist and asks it to try again.</p>
   *
   * @param sc Scanner object used for capturing user input
   */
  private void printMovieDetails(Scanner sc) {
    System.out.print("Enter movie: ");
    String name = sc.nextLine();

    String details = collection.getMovieDetails(name);
    if (details != null) {
      System.out.println(details);
    } else {
      System.out.println(name + "does not exist. Please try again.");
    }
  }

  /**
   * <p>Prints a list of all movies in the collection using an iterator. </p>
   * <p>If the collection is empty, it prompts the user that
   * there are currently no movies in the collection.'</p>
   */
  public void printAllMovies() {
    boolean containsMovies = false;
    System.out.println("Current movies in the collection : ");
    Iterator<String> iterator = collection.getListOfMovies();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      containsMovies = true;
    }
    if (!containsMovies) {
      System.out.println("There are currently no movies in the collection");
    }
  }

  /**
   * <p>Prints a list of all movies in the collection
   * filtered by the specified category using an iterator.</p>
   * <p>If no movies match the category, it prompts the user,
   * there are currently no movies in this category.</p>
   */
  public void printMoviesByCategory(Scanner sc) {
    boolean containsMovies = false;
    System.out.print("Enter a category: ");
    String category = sc.nextLine();
    System.out.println("Current movies in this category: " + category);
    Iterator<String> iterator = collection.getListOfMoviesByCategory(category);
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      containsMovies = true;
    }
    if (!containsMovies) {
      System.out.println("There are currently no movies in this category.");
    }
  }

  /**
   * <p>Prompts the user to confirm and clears the movie collection.
   * Informs the user if the collection is empty or if the clearing was successful.</p>
   *
   * @param sc The scanner object to capture user input
   */
  private void clearMovies(Scanner sc) {
    System.out.print("Are you sure you want to clear the collection? (y/n): ");
    String userInput = sc.nextLine().trim().toLowerCase();

    if (userInput.equals("y")) {
      boolean success = collection.clearAll();
      if (success) {
        System.out.println("The movie collection has been cleared.");
      } else {
        System.out.println("There are currently no movies in the collection to clear.");
      }
    } else {
      System.out.println("Clear operation canceled.");
    }
  }
}