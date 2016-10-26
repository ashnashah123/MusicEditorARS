package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashnashah on 10/17/16.
 */
// To represent a beat in a song.
public class Beat {
  private List<AMusicSymbol> musicSymbols;

  public Beat() {
    this.musicSymbols = new ArrayList<AMusicSymbol>();
  }

  /**
   * Add a symbol to this beat.
   *
   * @param sym the symbol to be added to this beat.
   */
  public void addMusicSym(AMusicSymbol sym) {
    musicSymbols.add(sym);
  }

  /**
   * Remove a symbol from this beat.
   *
   * @param oldSym symbol to be removed.
   */
  public void removeSym(AMusicSymbol oldSym) {
    musicSymbols.remove(oldSym);
  }

  /**
   * @param sym symbol to be checked if part of this beat.
   * @return boolean if the given symbol is part of this beat's list of symbols.
   */
  public boolean containsSym(AMusicSymbol sym) {
    return musicSymbols.contains(sym);
  }

  /**
   * @return boolean if this beat contains no music symbols.
   */
  public boolean containsNoSym() {
    return this.musicSymbols.size() == 0;
  }

  /**
   * Add the musicSymbols of the given beat to this beat.
   *
   * @param beat Bea who's symbols will be added to this beat.
   */
  public void addSymbols(Beat beat) {
    for (AMusicSymbol sym : beat.musicSymbols) {
      if (musicSymbols.contains(sym)) {
        // do nothing
      }
      musicSymbols.add(sym);
    }
  }

  /**
   * @return boolean if this beat contains rests.
   */
  public boolean containsRest() {
    boolean containsRest = false;
    for (AMusicSymbol sym : musicSymbols) {
      containsRest = sym instanceof Rest;
    }
    return containsRest;
  }

  /**
   * @param beatIndex index of this beat in the piece.
   * @param n         note that is to be checked if an X or | should be used to represent it.
   * @return String output of a note depending on whether its starting beat is this beat.
   */
  public String makeXandLines(int beatIndex, Note n) {
    String finalStr = "";
    boolean noteExists = false;
    AMusicSymbol note = null;
    for (AMusicSymbol sym : musicSymbols) {
      if (n.getOctave() == sym.getOctave()
              && n.getPitch() == sym.getPitch()) {
        noteExists = true;
        note = sym;
      }
    }
    if (noteExists) {
      if (beatIndex == note.getStartBeat()) {
        finalStr = finalStr + "  X  ";
      } else {
        finalStr = finalStr + "  |  ";
      }
    } else {
      finalStr = finalStr + "     ";
    }
    return finalStr;
  }

  /**
   * @param note note to be checked if part of this beat's list of symbols.
   * @return boolean of whether this beat contains the given note.
   */
  public boolean containsSameNote(Note note) {
    boolean containsNote = false;
    List<Note> notesInThisBeat = new ArrayList<>();
    for (AMusicSymbol sym : musicSymbols) {
      if (sym instanceof Note) {
        notesInThisBeat.add((Note) sym);
      }
    }
    if (notesInThisBeat.contains(note)) {
      containsNote = true;
    }
    return containsNote;
  }

  /**
   * @param startBeat In order to only add the unique notes in the beats.
   * @return List<Note></Note> of all the unique notes in the beat.
   */
  public List<Note> allNotesInBeat(int startBeat) {
    List<Note> notes = new ArrayList<>();
    for (AMusicSymbol sym : musicSymbols) {
      if (sym instanceof Note) {
        if (sym.getStartBeat() == startBeat) {
          notes.add((Note) sym);
        }
      }
    }
    return notes;
  }

  // "this" is the new beat that needs to be added to
  public void addOldSymbols(Beat oldBeat) {
    this.musicSymbols.addAll(oldBeat.musicSymbols);
  }
}
