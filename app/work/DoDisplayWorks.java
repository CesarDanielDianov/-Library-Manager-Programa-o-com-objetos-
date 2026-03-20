package bci.app.work;

import bci.core.LibraryManager;
import bci.core.Work;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Command to display all works.
 */
class DoDisplayWorks extends Command<LibraryManager> {

  DoDisplayWorks(LibraryManager receiver) {
    super(Label.SHOW_WORKS, receiver);
  }

  @Override
  protected final void execute() {
    for(Work w:_receiver.getWorksLM()){
      _display.popup(w.getDescription());
    }
  }
}
