package bci.app.request;

import bci.app.exception.BorrowingRuleFailedException;
import bci.app.exception.NoSuchUserException;
import bci.app.exception.NoSuchWorkException;
import bci.core.LibraryManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * 4.4.1. Request work.
 */
class DoRequestWork extends Command<LibraryManager> {

  DoRequestWork(LibraryManager receiver) {
    super(Label.REQUEST_WORK, receiver);
    addIntegerField("userid",bci.app.user.Prompt.userId());
    addIntegerField("workid",bci.app.work.Prompt.workId());
  }

  @Override
  protected final void execute() throws CommandException {
    int workid=integerField("workid");
    int userid=integerField("userid");

    if(_receiver.getUserLM(userid)==null){
      throw new NoSuchUserException(userid);      
    }
    if(_receiver.getWorkLM(workid)==null){
      throw new NoSuchWorkException(workid);      
    }
    /*VERIFICAR SE O UTENTE PODE FAZER REQUISIÇOES(SUSPENSO/ACTIVO) */


      int ruleid=_receiver.checkRulesLM(workid,userid);

      if(ruleid!=3 && ruleid!=0){
        throw new BorrowingRuleFailedException(userid,workid,ruleid);
      }
      else if(ruleid==3){
        Form form = new Form(Label.REQUEST_WORK);
        if (form.confirm(Prompt.returnNotificationPreference())){
            _receiver.addInterestedLM(userid, workid);
        }
      }
      else if(ruleid==0){
        int deadLine = _receiver.doRequestLM(_receiver.getWorkLM(integerField("workid")),_receiver.getUserLM(integerField("userid")));
        _display.popup(Message.workReturnDay(integerField("workid"), deadLine));
      }
  }  
}
