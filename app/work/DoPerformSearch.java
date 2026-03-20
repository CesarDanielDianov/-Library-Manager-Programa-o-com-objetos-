package bci.app.work;

import bci.core.LibraryManager;
import bci.core.Work;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Perform search according to miscellaneous criteria.
 */
class DoPerformSearch extends Command<LibraryManager> {

  DoPerformSearch(LibraryManager receiver) {
    super(Label.PERFORM_SEARCH, receiver);
    addStringField("nomeObra",Prompt.searchTerm());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  protected final void execute() {
    String input = stringField("nomeObra");
    
    for(Work w:_receiver.getWorksLM()){
      if(w.getDescription().contains(input)){
          _display.popup(w.getDescription());
      }
    }
  }
}