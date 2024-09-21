/**
 * @author: Yash Malik.
 * Made for CSC207 2024Fa.
*/
package edu.grinnell.csc207.util;

/**
 * This class contains all the methods incoporated in a
 *  register set.
 */
public class BFRegisterSet {

  /** Create the register. */
  private char[] register;
  /** Create an array to store BigFraction values for each register.  */
  private BigFraction[] values;
  /** 26 characters for a-z. */
  private final int registerSize = 26;

  /** Initializes the registers in the constructor. */
  public BFRegisterSet() {
    makeregister();
  } // end of BFRegisterSet.

  /**
   * Initialize the register array with size registerSize.
   * Initialize BigFraction array to store values.
   */
  public void makeregister() {
    register = new char[registerSize];
    values = new BigFraction[registerSize];
    for (int i = 0; i < registerSize; i++) {
      register[i] = (char) ('a' + i);
    }  // Populate with a-z
  } // end of makeregister.

  /**
   *
   * @param reg is the register in which to store the value.
   * @param val is the value to store
   */
  public void store(char reg, BigFraction val) {
    if (reg < 'a' || reg > 'z') {
      System.err.println("Error: register must be between 'a' and 'z'.");
      return;
    } else {
      int index = reg - 'a';
      values[index] = val;
    }   // Store BigFraction in the corresponding register.
  } // end of store.

  /**
   *
   * @param reg is the register that holds value.
   * @return returns BigFraction value.
   */
  public BigFraction get(char reg) {
    if (reg < 'a' || reg > 'z') {
      System.err.println("Error: register must be between 'a' and 'z'.");
      return null;
    } else {
      return values[reg - 'a'];
    }   // Return the value stored in the corresponding register
  } // Check for valid register.
} // end of BFRegisterSet.
