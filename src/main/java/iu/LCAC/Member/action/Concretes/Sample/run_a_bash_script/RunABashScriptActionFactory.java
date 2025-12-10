package iu.LCAC.Member.action.Concretes.Sample.run_a_bash_script;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class RunABashScriptActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new RunABashScriptAction(action_name, short_name);
    }
    return action;
  }
}
