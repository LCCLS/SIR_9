package org.bitbucket.socialroboticshub.actions.memory;

import eis.iilang.Identifier;
import eis.iilang.Parameter;
import org.bitbucket.socialroboticshub.actions.RobotAction;

import java.util.List;

public class GetDialogHistoryAction extends RobotAction {
    public final static String NAME = "getDialogHistory";

    /**
     * @param parameters A list of 1 identifier: a (string) interactant id.
     */
    public GetDialogHistoryAction(final List<Parameter> parameters) {
        super(parameters);
    }

    @Override
    public boolean isValid() {
        return (getParameters().size() == 1) && (getParameters().get(0) instanceof Identifier);
    }

    @Override
    public String getTopic() {
        return "memory_get_dialog_history";
    }

    @Override
    public String getData() {
        return EIStoString(getParameters().get(0));
    }

    @Override
    public String getExpectedEvent() {
        return null;
    }
}
