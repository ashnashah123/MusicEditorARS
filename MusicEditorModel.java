package cs3500.music.model;

import java.util.List;

/**
 * Created by ashnashah on 10/17/16.
 */


// To represent a music editor.
public class MusicEditorModel implements IMusicEditorModel {

  MusicEditorModel() {
    // empty constructor.
  }

  /**
   * @param sym   note that will be written.
   * @param piece piece that the note will be written to.
   * @return Piece of music that the notes will be written to.
   */
  @Override
  public Piece writeMusic(AMusicSymbol sym, Piece piece) {
    piece.addSym(sym);
    return piece;
  }

  /**
   * To replace an old note with a new note.
   *
   * @param oldSym the note that is to be replaced.
   * @param newSym the note that the oldNote will be replaced with.
   * @param piece  the piece that is to be edited. -- pass a list<Pieces> </Pieces>
   */
  @Override
  public void edit(AMusicSymbol oldSym, AMusicSymbol newSym, Piece piece) {
    piece.editSym(oldSym, newSym);
  }

  /**
   * To remove a given note from the given piece.
   *
   * @param sym   note to be removed.
   * @param piece piece that the note will be removed from.
   */
  @Override
  public void remove(AMusicSymbol sym, Piece piece) throws IllegalArgumentException {
    if (piece.validPiece()) {
      piece.removeSym(sym);
    } else {
      throw new IllegalArgumentException("Invalid piece");
    }
  }

  /**
   * To combine a list of pieces either simultaneously or consecutively.
   *
   * @param type   The type of combination you want to make (simultaneous or consecutive).
   * @param pieces The list of pieces that are to be combined.
   */
  @Override
  public Piece combine(CombineType type, List<Piece> pieces) {
    switch (type) {
      case CONSECUTIVE:
        Piece consecPiece = new Piece();
        int lengthOfPiece = 0;
        for (Piece p : pieces) {
          lengthOfPiece = lengthOfPiece + p.size();
        }
        consecPiece.addBeats(lengthOfPiece);
        consecPiece.appendPieces(pieces);
        return consecPiece;

      case SIMULTANEOUS:
        int maxLength = 0;
        for (Piece p : pieces) {
          if (p.size() > maxLength) {
            maxLength = p.size();
          }
        }
        Piece simulPiece = new Piece();
        simulPiece.addBeats(maxLength);
        for (Piece p : pieces) {
          simulPiece.addMusicSymbols(p);
        }
        return simulPiece;
      default:
        throw new IllegalArgumentException("Invalid combination type");
    }
  }


  /**
   * To return a string of the editorState of the given piece.
   *
   * @param piece The piece that is part of the music editor.
   * @return String of the editorState of the given piece.
   */
  @Override
  public String getEditorState(Piece piece) {
    return piece.setUpState();
  }
}
