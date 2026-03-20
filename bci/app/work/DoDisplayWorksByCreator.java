package bci.app.work;

import bci.app.exception.NoSuchCreatorException;
import bci.core.LibraryManager;
import bci.core.Work;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Display all works by a specific creator.
 */
class DoDisplayWorksByCreator extends Command<LibraryManager> {
  DoDisplayWorksByCreator(LibraryManager receiver) {
    super(Label.SHOW_WORKS_BY_CREATOR, receiver);
    addStringField("creatorID",Prompt.creatorId());
  }

  @Override
  protected final void execute() throws NoSuchCreatorException {
    List<Work> works= _receiver.getCreatorWorksLM(stringField("creatorID"));
    if(works==null){
      throw new NoSuchCreatorException(stringField("creatorID"));
    }
    for(Work w:works){
      _display.popup(w.getDescription());
    }
  }
}
