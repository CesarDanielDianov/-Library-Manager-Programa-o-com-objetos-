package bci.app.main;


import bci.app.exception.FileOpenFailedException;
import bci.core.LibraryManager;
import bci.core.exception.MissingFileAssociationException;
import bci.core.exception.UnavailableFileException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoOpenFile extends Command<LibraryManager> {
  DoOpenFile(LibraryManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
      try {
          if (_receiver.getChangesLM()==true) {
              Form form = new Form(Label.OPEN_FILE);
              if (form.confirm(Prompt.saveBeforeExit())==true) {
                  if (_receiver.getFileName() == null) {
                      addStringField("saveName", Prompt.newSaveAs());
                      _receiver.saveAs(stringField("saveName"));
                  } else {
                      _receiver.save();
                  }
              }
          }
          
          Form form2 = new Form(Label.OPEN_FILE);
          form2.addStringField("fileName", Prompt.openFile());
          _receiver.load(form2.stringField("fileName"));

      } catch (UnavailableFileException e) {
          throw new FileOpenFailedException(e);
      } catch (MissingFileAssociationException | IOException e) {
          _display.popup(e.getMessage());
      }
  }

  
}  