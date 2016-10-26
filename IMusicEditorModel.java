package cs3500.music.model;

/**
 * Created by ashnashah on 10/16/16.
 */

import java.util.List;

/**
 * This is the interface of the MusicEditorModel.
 */
public interface IMusicEditorModel {
  /**
   * To write the given note that will be part of the given piece.
   *
   * @param sym   note that will be written.
   * @param piece piece that the note will be written to.
   */
  public Piece writeMusic(AMusicSymbol sym, Piece piece);

  /**
   * To edit a piece of music by replacing a certain note with a new note.
   *
   * @param oldSym the note that is to be replaced.
   * @param newSym the note that the oldNote will be replaced with.
   * @param piece  the piece that is to be edited. -- pass a list<Pieces> </Pieces>
   */
  public void edit(AMusicSymbol oldSym, AMusicSymbol newSym, Piece piece);

  /**
   * To remove the given note from the given piece. If there is only one note in that beat, all the
   * subsequent notes should be shifted back.
   *
   * @param sym   note to be removed.
   * @param piece piece that the note will be removed from.
   */
  public void remove(AMusicSymbol sym, Piece piece) throws IllegalArgumentException;

  /**
   * To combine pieces of music to be played either simultaneously or consecutively.
   *
   * @param type       The type of combination you want to make (simultaneous or consecutive).
   * @param morePieces The pieces to be combined.
   */
  public Piece combine(CombineType type, List<Piece> morePieces);

  /**
   * @param piece The piece that is part of the music editor.
   * @return String showing the current state of the music editor.
   */
  public String getEditorState(Piece piece);
}
