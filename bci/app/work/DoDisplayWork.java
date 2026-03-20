package bci.app.work;

import bci.app.exception.NoSuchWorkException;
import bci.core.LibraryManager;
import bci.core.Work;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command to display a work.
 */
class DoDisplayWork extends Command<LibraryManager> {

  DoDisplayWork(LibraryManager receiver) {
    super(Label.SHOW_WORK, receiver);
    addIntegerField("idObra",Prompt.workId());
  }

  @Override
  protected final void execute() throws CommandException {
      int id = integerField("idObra");
      Work work = _receiver.getWorkLM(id);
      if(work==null){
        throw new NoSuchWorkException(id);
      }
     _display.popup(_receiver.getDescriptionLM((work)));
  }
}
