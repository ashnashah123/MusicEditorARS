package cs3500.music.model;

import java.util.Objects;

/**
 * Created by ashnashah on 10/17/16.
 */
// To represent a note.
public class Note extends AMusicSymbol {
  private final Pitch pitch;
  private final int octave;

  /**
   *
   * @param duration the duration of this note.
   * @param startBeat the starting beat of this note.
   * @param pitch the pitch of this note.
   * @param octave the octave of this note.
   */
  public Note(int duration, int startBeat, Pitch pitch, int octave) {
    super(duration, startBeat);
    if (octave < 0) {
      throw new IllegalArgumentException("Octave can't be negative");
    }
    this.pitch = pitch;
    this.octave = octave;
  }

  public Note(Pitch pitch, int octave) {
    this.pitch = pitch;
    this.octave = octave;
  }

  // To return the string value of this note.
  @Override
  public String toString() {
    return this.pitch.toString() + this.octave;
  }

  // To return the octave of this note.
  public int getOctave() {
    return this.octave;
  }

  // To return this note's pitch.
  public Pitch getPitch() {
    return this.pitch;
  }

  // To check if this note is equal to a given object.
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof Note)) {
      return false;
    } else {
      Note that = ((Note) o);
      return this.octave == that.octave &&
              this.pitch == that.pitch;
    }
  }

  // Overriding the hashCode() method because overriding the equals() method.
  @Override
  public int hashCode() {
    return Objects.hash(this.octave);
  }
}
