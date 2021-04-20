package org.bitbucket.socialroboticshub.actions.memory;

import eis.iilang.Identifier;
import eis.iilang.Parameter;
import org.bitbucket.socialroboticshub.actions.RobotAction;

import java.util.List;

public class SetMemoryEntryAction extends RobotAction {
    public final static String NAME = "setMemoryEntry";

    /**
     * @param parameters A list of 3 identifiers represent the interactant ID, the
     *                   entry key and the entry data that needs to be stored.
     */
    public SetMemoryEntryAction(final List<Parameter> parameters) {
        super(parameters);
    }

    @Override
    public boolean isValid() {
        return (getParameters().size() == 3) && (getParameters().get(0) instanceof Identifier)
                && (getParameters().get(1) instanceof Identifier); // 3rd arg can be anything
    }

    @Override
    public String getTopic() {
        return "memory_set_entry";
    }

    @Override
    public String getData() {
        final String interactantID = EIStoString(getParameters().get(0));
        final String key = EIStoString(getParameters().get(1));
        final String data = EIStoString(getParameters().get(2));

        return (interactantID + ";" + key + ";" + data);
    }

    @Override
    public String getExpectedEvent() {
        return null;
    }
}
