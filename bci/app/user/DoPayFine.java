package bci.app.user;

import bci.app.exception.NoSuchUserException;
import bci.app.exception.UserIsActiveException;
import bci.core.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * 4.2.5. Settle a fine.
 */
class DoPayFine extends Command<LibraryManager> {

  DoPayFine(LibraryManager receiver) {
    super(Label.PAY_FINE, receiver);
    addIntegerField("userid",Prompt.userId());
  }

  @Override
  protected final void execute() throws CommandException {
    int userid=integerField("userid");
    if(_receiver.getUserLM(userid)==null){
      throw new NoSuchUserException(userid);      
    }

    if(_receiver.allowedToRequestLM(userid)==true){
      throw new UserIsActiveException(userid);
    }else{
      _receiver.payAllLM(userid);    
      _receiver.allowedToRequestLM(userid);
    }
  }
}
