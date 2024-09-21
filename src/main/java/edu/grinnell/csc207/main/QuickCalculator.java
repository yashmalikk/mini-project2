/**
 * @author: Yash Malik.
 * Made for CSC207 2024Fa.
*/
package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.CommandProcessing;

import java.io.PrintWriter;


/**
 * This class contains the main for QuickCalculator, which uses methods
 * made in BigFraction and BFRegisterSet.
 *
*/
public class QuickCalculator {

  /** Create a new registerset. */
  private static final BFRegisterSet REGISTER_SET = new BFRegisterSet();

  /**
   *
   * @param args holds the arguments passed in cmd.
   */
  public static void main(String[] args) {
    REGISTER_SET.makeregister();  // Initialize the registers
    PrintWriter pen = new PrintWriter(System.out, true);
    for (String input : args) {
      input = input.trim();

      if (input.startsWith("STORE")) {
        CommandProcessing.handleStoreCommand(input);
      } else {
        BigFraction result = CommandProcessing.handleCalculation(input);
        if (result != null) {
          pen.println(input + " = " + result);
          CommandProcessing.setLastResult(result); // Update lastResult with the current result.
        } else {
          System.err.println("Error: Invalid calculation.");
        } // Check for valid calculation.
      } // Check for STORE command.
    } // Loop for all arguments in args
  } // end of main.
} // end of QuickCalculator.
