package iu.SwingStyle.LCCA.Member.Concretes.action.Sample.run_a_bash_script;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

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
