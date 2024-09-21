/**
 * @author: Yash Malik.
 * Made for CSC207 2024Fa.
*/
package edu.grinnell.csc207.util;

import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * This class contains all the methods incoporated in a
 *  calculator.
 */
public class CommandProcessing {

  /** Creates a new Register Set. */
  private static final BFRegisterSet REGISTER_SET = new BFRegisterSet();

  /** Store the last computed value. */
  private static BigFraction lastResult;

  /**
   * Getter for lastResult.
   *
   * @return returns the lastResult.
   */
  public BigFraction getlastResult() {
    return lastResult;
  } // end of getlastResult.

  /**
   * Setter for last result.
   * @param curlastResult
   */
  public static void setLastResult(BigFraction curlastResult) {
    CommandProcessing.lastResult = curlastResult;
  } // end of setLastResult.

  /**
   *
   * @param input input from cmd.
   */
  public static void handleStoreCommand(String input) {
    String[] parts = input.split("\\s+", 2);
    PrintWriter pen = new PrintWriter(System.out, true);
    if (parts.length != 2) {
      System.err.println("Invalid STORE command. Please specify a register.");
      return;
    } // Check to see if STORE was used correctly.
    char registerName = parts[1].charAt(0);

    if (registerName < 'a' || registerName > 'z') {
      System.err.println("Error: register must be between 'a' and 'z'.");
      return;
    } // Check if the registerName is a valid character between 'a' and 'z'.


    if (parts[1].length() == 1) {
      if (lastResult != null) {
        REGISTER_SET.store(registerName, lastResult);
        pen.println("Stored " + lastResult + " in register " + registerName);
      } else {
        System.err.println("Error: No value to store. Please compute a value first.");
      } // Confirmation for storing in register (helped while debugging,
          // seemed useful so I didn't remove it).
    } else {
      BigFraction value = parseFraction(parts[1].substring(2));  // Skip the register name
      REGISTER_SET.store(registerName, value);
      pen.println("Stored " + value + " in register " + registerName);
    }       // If there's a value, parse it and store it.
  } // If the command is just "STORE a", store the last computed value.

  /**
   *
   * @param input string from cmd.
   * @return required BigFraction.
   */
  public static BigFraction handleCalculation(String input) {
    String[] tokens = input.split("\\s+");
    final int tokenNum  = 3;

    if (tokens.length < tokenNum  || tokens.length % 2 == 0) {
      System.err.println("Error: Invalid calculation format.");
      return null;
    } // Check for valid inputs.

    BigFraction result = parseFraction(tokens[0]);

    for (int i = 1; i < tokens.length; i += 2) {
      String operator = tokens[i];
      BigFraction nextValue = parseFraction(tokens[i + 1]);

      if (nextValue == null) {
        System.err.println("Error: Invalid operand.");
        return null;
      } // Check for valid operand.

      switch (operator) {
        case "+":
          result = result.add(nextValue);
          break;
        case "-":
          result = result.subtract(nextValue);
          break;
        case "*":
          result = result.multiply(nextValue);
          break;
        case "/":
          result = result.divide(nextValue);
          break;
        default:
          System.err.println("Error: Invalid operator: " + operator);
          return null;
      } // Use switch-case to decide what function to use.
    } // Loop for all elements in token.
    return result;
  } // end of handleCalculation.

  /**
   *
   * @param token string to parse.
   * @return required BigFraction.
   */
  public static BigFraction parseFraction(String token) {
    if (token.length() == 1 && token.charAt(0) >= 'a' && token.charAt(0) <= 'z') {
      return REGISTER_SET.get(token.charAt(0));
    } // check for whole number.

    String[] parts = token.split("/");
    if (parts.length == 2) {
      return new BigFraction(new BigInteger(parts[0]), new BigInteger(parts[1]));
    } else {
      return new BigFraction(new BigInteger(token), BigInteger.ONE);
    } // Return as a fraction
  } // end of parseFraction.
} // end of CommandProcessing.
