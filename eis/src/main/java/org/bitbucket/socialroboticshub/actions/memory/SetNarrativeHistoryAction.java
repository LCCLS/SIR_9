package org.bitbucket.socialroboticshub.actions.memory;

import eis.iilang.Identifier;
import eis.iilang.Numeral;
import eis.iilang.Parameter;
import org.bitbucket.socialroboticshub.actions.RobotAction;

import java.util.List;

public class SetNarrativeHistoryAction extends RobotAction {
    public final static String NAME = "setNarrativeHistory";

    /**
     * @param parameters A list of 3 identifiers: a (string) interactant id,
     *                   a thread label (string) and a thread position (integer).
     */
    public SetNarrativeHistoryAction(final List<Parameter> parameters) {
        super(parameters);
    }

    @Override
    public boolean isValid() {
        return (getParameters().size() == 3) && (getParameters().get(0) instanceof Identifier)
                && (getParameters().get(1) instanceof Identifier)
                && (getParameters().get(2) instanceof Numeral);
    }

    @Override
    public String getTopic() {
        return "memory_set_narrative_history";
    }

    @Override
    public String getData() {
        return EIStoString(getParameters().get(0)) + ";" + EIStoString(getParameters().get(1)) + ';'
                + EIStoString(getParameters().get(2));
    }

    @Override
    public String getExpectedEvent() {
        return "NarrativeHistorySet";
    }
}
