package app;

/**
 * The entry point for the movie collection application.
 * Initializes the <code>UserInterface</code> to handle user interactions
 * and manages the movie collection. The <code>main</code> method ensures smooth execution
 * by handling exceptions.
 *d
 * @author Johannes Nupen Theigen
 * @version 0.0.2
 * @since 11.11.2024
 */

public class Main {

  /**
   * Starts the movie collection application. It initializes the
   * <code>UserInterface</code> and handles any errors to ensure the
   * program runs correctly. If an error occurs, a message is displayed to the user.
   *
   * @param args Command-line arguments (not used in this case).
   */
  public static void main(String[] args) {
    try {
      UserInterface userInterface = new UserInterface();
      userInterface.start();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("An unexpected error occurred. Please try again later.");
    }
  }
}
