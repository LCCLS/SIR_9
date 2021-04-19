package org.bitbucket.socialroboticshub.actions.memory;

import eis.iilang.Identifier;
import eis.iilang.Numeral;
import eis.iilang.Parameter;
import org.bitbucket.socialroboticshub.actions.RobotAction;

import java.util.List;

public class GetNarrativeHistoryAction extends RobotAction {
	public final static String NAME = "getNarrativeHistory";

	/**
	 * @param parameters A list of 3 identifiers: a (string) interactant id and
	 *                   a thread label (string).
	 */
	public GetNarrativeHistoryAction(final List<Parameter> parameters) {
		super(parameters);
	}

	@Override
	public boolean isValid() {
		return (getParameters().size() == 2) && (getParameters().get(0) instanceof Identifier)
				&& (getParameters().get(1) instanceof Identifier);
	}

	@Override
	public String getTopic() {
		return "memory_get_narrative_history";
	}

	@Override
	public String getData() {
		return EIStoString(getParameters().get(0)) + ";"
				+ EIStoString(getParameters().get(1));
	}

	@Override
	public String getExpectedEvent() {
		return null;
	}
}
