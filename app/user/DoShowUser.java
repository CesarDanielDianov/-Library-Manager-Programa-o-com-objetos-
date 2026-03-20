package bci.app.user;

import bci.app.exception.NoSuchUserException;
import bci.core.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * 4.2.2. Show specific user.
 */
class DoShowUser extends Command<LibraryManager> {

  DoShowUser(LibraryManager receiver) {
    super(Label.SHOW_USER, receiver);
    addIntegerField("id",Prompt.userId());
  }

  @Override
  protected final void execute() throws CommandException {
    int id=integerField("id");

    _receiver.setStateLM(id);

    if(_receiver.getUserLM(id)==null){
      throw new NoSuchUserException(id);
    }else _display.popup(_receiver.getUserLM(id).toString());
  }
}
