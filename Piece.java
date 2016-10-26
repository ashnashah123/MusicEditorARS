package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashnashah on 10/16/16.
 */

// To represent a piece of music.
public class Piece {
  // The beats in this piece of music.
  private List<Beat> beats;
  //private final ScaleType type;

  public Piece() {
    this.beats = new ArrayList<Beat>();
  }

  public Piece(List<Beat> beats) {
    this.beats = beats;
  }

  /**
   * Adds the given music symbol to this piece. if the music symbol that is being added already
   * exists in this piece?? Not throwing the exception would basically just ignore it and add only
   * if its longer than existing beat Throwing the exception would prevent you from combining music
   * if 2 notes appear in the same place
   *
   * @param sym the music symbol to be added to this piece.
   */
  public void addSym(AMusicSymbol sym) throws IllegalArgumentException {
    for (int i = sym.getStartBeat(); i < sym.getStartBeat() + sym.getDuration(); i += 1) {
      if (i >= beats.size()) {
        beats.add(new Beat());
      }
      if (beats.get(i).containsRest()) {
        throw new IllegalArgumentException("Can't add notes to where there is a rest");
      }
      if (beats.get(i).containsSym(sym)) {
        // do nothing
      }
      beats.get(i).addMusicSym(sym);
    }
  }

  // Returns the size of this piece's list of beats.
  public int size() {
    return this.beats.size();
  }

  /**
   * To replace an existing symbol with a new one in this piece.
   *
   * @param oldSym symbol to be replaced.
   * @param newSym symbol to replace old symbol with.
   */
  public void editSym(AMusicSymbol oldSym, AMusicSymbol newSym) throws IllegalArgumentException {
    for (int i = oldSym.getStartBeat(); i < oldSym.getStartBeat() + oldSym.getDuration(); i += 1) {
      if (!(beats.get(i).containsSym(oldSym))) {
        throw new IllegalArgumentException("Piece does not contain the given oldSym.");
      }
    }
    if (oldSym.getStartBeat() != newSym.getStartBeat()) {
      throw new IllegalArgumentException("The new music symbol must have the same startBeat " +
              "as the one it is replacing.");
    }
    for (int i = oldSym.getStartBeat(); i < oldSym.getStartBeat() + oldSym.getDuration(); i += 1) {
      beats.get(i).removeSym(oldSym);
    }
    this.addSym(newSym);
  }

  /**
   * To remove a symbol from this piece.
   * @param sym The symbol to be removed.
   * @throws IllegalArgumentException if the piece does not contain the given symbol.
   */
  public void removeSym(AMusicSymbol sym) throws IllegalArgumentException {
    for (int i = sym.getStartBeat(); i < sym.getStartBeat() + sym.getDuration(); i += 1) {
      if (!beats.get(i).containsSym(sym)) {
        throw new IllegalArgumentException("Piece does not contain the given music symbol.");
      }
      beats.get(i).removeSym(sym);
    }
  }

  /**
   * To add beats to this piece's list of beats.
   *
   * @param maxLength The length of the list of beats that will be created for this piece.
   */
  public void addBeats(int maxLength) {
    for (int i = 0; i < maxLength; i += 1) {
      this.beats.add(new Beat());
    }
  }

  /**
   * Add musicSymbols to the given piece.
   *
   * @param piece piece to which musicSymbols will be added.
   */
  public void addMusicSymbols(Piece piece) {
    for (int i = 0; i < piece.beats.size(); i += 1) {
      beats.get(i).addSymbols(piece.beats.get(i));
    }
  }

  /**
   * @param pieces List of pieces whose beats will be added to the returned list.
   * @return List of all beats between the given pieces.
   */
  private List<Beat> getTotalBeats(List<Piece> pieces) {
    List<Beat> allBeats = new ArrayList<>();
    for (Piece p : pieces) {
      for (Beat b : p.beats) {
        allBeats.add(b);
      }
    }
    return allBeats;
  }

  /**
   * To add the beats from all the given pieces to this piece.
   * @param pieces The pieces to be appended.
   */
  public void appendPieces(List<Piece> pieces) {
    List<Beat> totalBeats = this.getTotalBeats(pieces);
    for (int i = 0; i < beats.size(); i += 1) {
      this.beats.get(i).addSymbols(totalBeats.get(i));
    }
  }

  // To find the lowest note in this piece.
  private Note findLowestNoteInPiece() {
    List<Note> allNotesInPiece = new ArrayList<>();
    for (int i = 0; i < beats.size(); i += 1) {
      allNotesInPiece.addAll(beats.get(i).allNotesInBeat(i));
    }
    int minOctave = allNotesInPiece.get(0).getOctave();
    List<Note> notesOfLowestOctave = new ArrayList<>();
    for (int i = 0; i < allNotesInPiece.size(); i += 1) {
      if (allNotesInPiece.get(i).getOctave() < minOctave) {
        minOctave = allNotesInPiece.get(i).getOctave();
      }
    }
    // makes a list of all the notes of the lowest octave.
    for (Note n : allNotesInPiece) {
      if (n.getOctave() == minOctave) {
        notesOfLowestOctave.add(n);
      }
    }
    int minPitch = notesOfLowestOctave.get(0).getPitch().getValue();
    Note minNote = notesOfLowestOctave.get(0);
    for (Note n : notesOfLowestOctave) {
      if (n.getPitch().getValue() < minPitch) {
        minPitch = n.getPitch().getValue();
        minNote = n;
      }
    }
    return minNote;
  }

  // To find the highest note in this piece.
  private Note findHighestNoteInPiece() {
    List<Note> allNotesInPiece = new ArrayList<>();
    for (int i = 0; i < beats.size(); i += 1) {
      allNotesInPiece.addAll(beats.get(i).allNotesInBeat(i));
    }
    int maxOctave = allNotesInPiece.get(0).getOctave();
    List<Note> notesOfHighestOctave = new ArrayList<>();
    for (int i = 0; i < allNotesInPiece.size(); i += 1) {
      if (allNotesInPiece.get(i).getOctave() > maxOctave) {
        maxOctave = allNotesInPiece.get(i).getOctave();
      }
    }
    // makes a list of all the notes of the lowest octave.
    for (Note n : allNotesInPiece) {
      if (n.getOctave() == maxOctave) {
        notesOfHighestOctave.add(n);
      }
    }
    int maxPitch = notesOfHighestOctave.get(0).getPitch().getValue();
    Note maxNote = notesOfHighestOctave.get(0);
    for (Note n : notesOfHighestOctave) {
      if (n.getPitch().getValue() > maxPitch) {
        maxPitch = n.getPitch().getValue();
        maxNote = n;
      }
    }
    return maxNote;
  }

  // To find the maximum pitch value in this piece.
  private int maxPitchValue() {
    int maxPitchValue = Pitch.A.getValue();
    for (Pitch p : Pitch.values()) {
      if (p.getValue() > maxPitchValue) {
        maxPitchValue = p.getValue();
      }
    }
    return maxPitchValue;
  }

  // to find the minimum pitch value in this piece.
  private int minPitchValue() {
    int minPitchValue = Pitch.A.getValue();
    for (Pitch p : Pitch.values()) {
      if (p.getValue() < minPitchValue) {
        minPitchValue = p.getValue();
      }
    }
    return minPitchValue;
  }

  /**
   *
   * @return boolean of whether this piece is a valid piece or not.
   */
  public boolean validPiece() {
    boolean validPiece = true;
    if (this.beats == null) {
      validPiece = false;
    }
    return validPiece;
  }

  /**
   * @param lowestNote  The lowest note in this piece.
   * @param highestNote The highest note in this piece.
   * @return List<Note> </Note> of all the notes within the range of the higest and lowest notes.
   */
  private List<Note> getAllPossibleNotesInRange(Note lowestNote, Note highestNote) {
    List<Note> allPossibleNotesInRange = new ArrayList<>();
    // Stay within the octave of the lowest note first
    for (int i = lowestNote.getPitch().getValue(); i <= this.maxPitchValue(); i += 1) {
      Note n1 = new Note(lowestNote.getPitch().getPitchBasedOnValue(i), lowestNote.getOctave());
      allPossibleNotesInRange.add(n1);
    }
    for (int j = lowestNote.getOctave() + 1; j < highestNote.getOctave(); j += 1) {
      int nextOctave = j;
      if (nextOctave < highestNote.getOctave()) {
        for (Pitch p : Pitch.values()) {
          Note n2 = new Note(p, nextOctave);
          allPossibleNotesInRange.add(n2);
        }
      }
    }
    if (lowestNote != highestNote) {
      for (int i = minPitchValue(); i <= highestNote.getPitch().getValue(); i += 1) {
        Note n3 = new Note(highestNote.getPitch().getPitchBasedOnValue(i), highestNote.getOctave());
        allPossibleNotesInRange.add(n3);
      }
    }
    return allPossibleNotesInRange;
  }

  /**
   * @return String of the current state of this piece.
   */
  public String setUpState() {
    //StringBuilder builder = new StringBuilder();
    String finalStr = "";
    List<Note> allNotesInRange = this.getAllPossibleNotesInRange(this.findLowestNoteInPiece(),
            this.findHighestNoteInPiece());
    int size = beats.size() - 1;
    String stringSize = Integer.toString(size);
    for (char c : stringSize.toCharArray()) {
      finalStr += " ";
    }
    for (int i = 0; i < allNotesInRange.size(); i += 1) {
      finalStr += String.format("%1$" + 4 + "s", allNotesInRange.get(i).toString()) + " ";
    }

    for (int j = 0; j < beats.size(); j += 1) {
      if (j == 0) {
        finalStr += '\n';
      }
      finalStr += String.format("%1$" + stringSize.length() + "s", j);
      for (int i = 0; i < allNotesInRange.size(); i += 1) {
        finalStr += beats.get(j).makeXandLines(j, allNotesInRange.get(i));
      }
      if (j != beats.size() - 1) {
        finalStr += '\n';
      }

    }
    return finalStr;
  }
}

