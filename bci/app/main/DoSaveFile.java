package bci.app.main;

import bci.core.LibraryManager;
import bci.core.exception.MissingFileAssociationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import pt.tecnico.uilib.menus.Command;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<LibraryManager> {

  DoSaveFile(LibraryManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }




  @Override
  protected final void execute() {
    if(_receiver.getFileName()==null){
      addStringField("fileName",Prompt.newSaveAs());
    }    
    try {
      if (_receiver.getFileName() == null) {
        String filename = stringField("fileName");
        if (filename == null || filename.isEmpty()) {
          throw new MissingFileAssociationException();
        }
        _receiver.saveAs(filename);
      } else {
        _receiver.save();
      }
    } catch (MissingFileAssociationException | FileNotFoundException e){
      _display.popup(e.getMessage());
    } catch (IOException e) {
      _display.popup(e.getMessage());
    }
  }
}
