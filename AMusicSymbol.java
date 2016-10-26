package cs3500.music.model;

/**
 * Created by ashnashah on 10/17/16.
 */

/**
 * Represents a music symbol; which can be either a rest or a note.
 */
public abstract class AMusicSymbol {
  private int duration;
  private int startBeat;

  /**
   * To represent a music symbol.
   * @param duration the duration of the music symbol.
   * @param startBeat the start beat of the music symbol.
   */
  public AMusicSymbol(int duration, int startBeat) {
    /**
     * To ensure that the duration and startBeat are not negative.
     */
    if (duration < 0 || startBeat < 0) {
      throw new IllegalArgumentException("Duration or startBeat can't be negative");
    }
    this.duration = duration;
    this.startBeat = startBeat;
  }

  public AMusicSymbol() {
    // empty ctor.
  }

  // A getter is being used here in order to take away from the problems that could arise when
  // trying to edit music. If the user were asked to input the duration of the note being
  // edited, the inputted duration could end up being too long or short, which would require
  // extra checks to know this, before even being able to remove the music symbol and
  // add the new one. With the getter, it is ensured that the duration will be correct so the
  // music symbol can be directly removed and the new one can be added. Additionally, a note and
  // rest will always have a duration, so there is no issue of having to maintain this field.
  public int getDuration() {
    return this.duration;
  }

  /*
   * A getter is being used here in order to take away from the problems that could arise when
   * trying to edit music. If the user were asked to input the startBeat of the note being
   * edited, the inputted startBeat of either the old or new note could be the wrong beat.
   * This would require extra checks to know this, before being able to edit the correct beat.
   * By using the getter method, it is ensured that the startBeat will be correct because
   * it is coming from the exact music symbol from which it is requested. Additionally, a note
   * will always have a startBeat, so there is no issue of having to maintain this field.
   */
  public int getStartBeat() {
    return this.startBeat;
  }

  public abstract int getOctave();

  public abstract Pitch getPitch();

  /**
   * @return String representation of this music symbol.
   */
  @Override
  public abstract String toString();
}
