package cs3500.music.model;

/**
 * Created by ashnashah on 10/17/16.
 */

// To represent the various pitches of music.
public enum Pitch {
  C(1), CSharp(2), D(3), DSharp(4), E(5), F(6),
  FSharp(7), G(8), GSharp(9), A(10), ASharp(11), B(12);

  private int value;

  Pitch(int value) {
    this.value = value;
  }

  // To get the value of this enum.
  public int getValue() {
    return this.value;
  }

  /**
   * @param value the value being passed to get the corresponding enum value.
   * @return Pitch that corresponds to the given value.
   */
  public Pitch getPitchBasedOnValue(int value) {
    switch (value) {
      case 1:
        return Pitch.C;
      case 2:
        return Pitch.CSharp;
      case 3:
        return Pitch.D;
      case 4:
        return Pitch.DSharp;
      case 5:
        return Pitch.E;
      case 6:
        return Pitch.F;
      case 7:
        return Pitch.FSharp;
      case 8:
        return Pitch.G;
      case 9:
        return Pitch.GSharp;
      case 10:
        return Pitch.A;
      case 11:
        return Pitch.ASharp;
      case 12:
        return Pitch.B;
      default:
        throw new IllegalArgumentException("Invalid value");
    }
  }

  /**
   * @return String of the specified pitch value.
   */
  @Override
  public String toString() {
    switch (this) {
      case C:
        return "C";
      case CSharp:
        return "C#";
      case D:
        return "D";
      case DSharp:
        return "D#";
      case E:
        return "E";
      case F:
        return "F";
      case FSharp:
        return "F#";
      case G:
        return "G";
      case GSharp:
        return "G#";
      case A:
        return "A";
      case ASharp:
        return "A#";
      case B:
        return "B";
      default:
        throw new IllegalArgumentException("Invalid Pitch");
    }
  }
}
