package bci.app.work;


import bci.core.LibraryManager;
import bci.core.Work;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Change the number of exemplars of a work.
 */
class DoChangeWorkInventory extends Command<LibraryManager> {

  DoChangeWorkInventory(LibraryManager receiver) {
    super(Label.CHANGE_WORK_INVENTORY, receiver);
    addIntegerField("id",Prompt.workId());
    addIntegerField("inventoryChange",Prompt.amountToDecrement());
  }

  @Override
  protected final void execute() throws CommandException {
    int id = integerField("id");
    Work work = _receiver.getWorkLM(id);
    int amount = integerField("inventoryChange");
    
    // add verificacao de work
    
    if (_receiver.changeWorkInventoryLM(work, amount) == false){
      _display.popup(Message.notEnoughInventory(id, amount));
    }
  }
}