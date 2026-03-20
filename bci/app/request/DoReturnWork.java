package bci.app.request;

import bci.app.exception.NoSuchUserException;
import bci.app.exception.NoSuchWorkException;
import bci.app.exception.WorkNotBorrowedByUserException;
import bci.core.LibraryManager;
import bci.core.Work;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * 4.4.2. Return a work.
 */
class DoReturnWork extends Command<LibraryManager> {

  DoReturnWork(LibraryManager receiver) {
    super(Label.RETURN_WORK, receiver);
    addIntegerField("userid",bci.app.user.Prompt.userId());
    addIntegerField("workid",bci.app.work.Prompt.workId());
  }

  @Override
  protected final void execute() throws CommandException {
    int userid=integerField("userid");
    int workid=integerField("workid");
    if(_receiver.getWorkLM(workid)==null){
      throw new NoSuchWorkException(workid);
    }
    if(_receiver.getUserLM(userid)==null){
      throw new NoSuchUserException(userid);
    }    
    if(_receiver.getRequestLM(userid,workid)==null){
        throw new WorkNotBorrowedByUserException(workid,userid);
    }
    /*ATUALIZAR O ESTADO DO USER CONFORME O CUMPRIMENTO DAS DEADLINES*/


    
    _receiver.calculateFineLM(userid, workid);
    _receiver.removeRequestLM(userid, workid);
    int fine=_receiver.fineToPayLM(userid);
    if (fine > 0) {
      _display.popup(Message.showFine(userid, fine));
      Form form = new Form(Label.RETURN_WORK);
      if (form.confirm(Prompt.finePaymentChoice())) {
        _receiver.payFineLM(userid, fine);
      } else {
        _receiver.suspend(userid);
      }
    }
    
    Work w=_receiver.getWorkLM(workid);
    if(w.getAvailable()==1){
      _receiver.sendNotificationLM(workid);
    }
  }
}
