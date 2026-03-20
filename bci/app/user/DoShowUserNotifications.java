package bci.app.user;

import bci.app.exception.NoSuchUserException;
import bci.core.LibraryManager;
import bci.core.Notification;
import bci.core.User;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * 4.2.3. Show notifications of a specific user.
 */
class DoShowUserNotifications extends Command<LibraryManager> {

  DoShowUserNotifications(LibraryManager receiver) {
    super(Label.SHOW_USER_NOTIFICATIONS, receiver);
    addIntegerField("userID",Prompt.userId());
  }

  @Override
  protected final void execute() throws CommandException {
    int id=integerField("userID");
    if(_receiver.getUserLM(id)==null){
      throw new NoSuchUserException(id);
    }

    _receiver.setStateLM(id);

    User user=_receiver.getUserLM(id);
    for(Notification n:user.getNotifications()){
      _display.popup(n.toString());
    }
      _receiver.clearNotificationsLM(id);    
  }
}
