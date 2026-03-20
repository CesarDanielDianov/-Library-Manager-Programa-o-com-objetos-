package bci.app.user;

import bci.app.exception.UserRegistrationFailedException;
import bci.core.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * 4.2.1. Register new user.
 */
class DoRegisterUser extends Command<LibraryManager> {

  DoRegisterUser(LibraryManager receiver) {
    super(Label.REGISTER_USER, receiver);
    addStringField("name",Prompt.userName());
    addStringField("email",Prompt.userEMail());
  }

  
  @Override
  protected final void execute() throws CommandException {
    String name=stringField("name");
    String email=stringField("email");
    if(name.equals("") || email.equals("")){
      throw new UserRegistrationFailedException(name,email);
    }
    int id=_receiver.rcreateUser(name,email);
    if(id!=0){
      _display.popup(Message.registrationSuccessful(id));
    }else throw new UserRegistrationFailedException(name,email);
  }
}