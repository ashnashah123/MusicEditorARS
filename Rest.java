package cs3500.music.model;

/**
 * Created by ashnashah on 10/16/16.
 */

// To represent a rest in music (i.e. when there are no notes playing.
public class Rest extends AMusicSymbol {

  public Rest() {
    super();
  }

  public Rest(int duration, int startBeat) {
    super(duration, startBeat);
  }

  // Return the String value of this rest (5 spaces).
  public String toString() {
    return "     ";
  }

  public int getOctave() {
    return 999999;
  }

  public Pitch getPitch() {
    return null;
  }
}
