package org.bitbucket.socialroboticshub.actions.memory;

import eis.iilang.Identifier;
import eis.iilang.Parameter;
import org.bitbucket.socialroboticshub.actions.RobotAction;

import java.util.List;

public class SetInteractantDataAction extends RobotAction {
    public final static String NAME = "setInteractantData";

    /**
     * @param parameters A list of 3 identifiers: a (string) interactant id, a (string)
     *                   user data key and a corresponding user data value.
     */
    public SetInteractantDataAction(final List<Parameter> parameters) {
        super(parameters);
    }

    @Override
    public boolean isValid() {
        return (getParameters().size() == 3) && (getParameters().get(0) instanceof Identifier)
                && (getParameters().get(1) instanceof Identifier); // 3rd arg can be anything
    }

    @Override
    public String getTopic() {
        return "memory_set_interactant_data";
    }

    @Override
    public String getData() {
        return EIStoString(getParameters().get(0)) + ";" + EIStoString(getParameters().get(1)) + ';'
                + EIStoString(getParameters().get(2));
    }

    @Override
    public String getExpectedEvent() {
        return "InteractantDataSet";
    }
}
