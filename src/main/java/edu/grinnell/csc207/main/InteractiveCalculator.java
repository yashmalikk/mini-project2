/**
 * @author: Yash Malik.
 * Made for CSC207 2024Fa.
*/
package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.CommandProcessing;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class contains the main for InteractiveCalculator, which uses methods
 * made in BigFraction and BFRegisterSet.
 *
*/
public class InteractiveCalculator {

  /** Creates a new Register Set. */
  private static final BFRegisterSet REGISTER_SET = new BFRegisterSet();


  /**
   *
   * @param args holds the arguments passed in cmd.
   */
  public static void main(String[] args) {
    REGISTER_SET.makeregister();  // Initialize the registers
    Scanner scanner = new Scanner(System.in);
    String input;

    while (true) {
      System.out.print("> ");
      PrintWriter pen = new PrintWriter(System.out, true);
      input = scanner.nextLine().trim();

      if (input.equalsIgnoreCase("QUIT")) {
        break;
      } // Check to see if user wants to exit program.

      if (input.startsWith("STORE")) {
        CommandProcessing.handleStoreCommand(input);
      } else {
        BigFraction result = CommandProcessing.handleCalculation(input);
        if (result != null) {
          pen.println(result);
          CommandProcessing.setLastResult(result); // Update lastResult with the current result.
        } else {
          System.err.println("Error: Invalid calculation.");
        } // Check for valid input.
      } // Check if user wants to store in register.
    } // Run program indefinitely.
    scanner.close();
  } // InteractiveCalculator Main.
} //end of InteractiveCalculator.
