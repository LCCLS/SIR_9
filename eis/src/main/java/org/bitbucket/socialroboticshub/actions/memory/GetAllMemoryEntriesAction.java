package org.bitbucket.socialroboticshub.actions.memory;

import eis.iilang.Identifier;
import eis.iilang.Parameter;
import org.bitbucket.socialroboticshub.actions.RobotAction;

import java.util.List;

public class GetAllMemoryEntriesAction extends RobotAction {
	public final static String NAME = "getAllMemoryEntries";

	/**
	 * @param parameters A list of 2 identifiers representing the interactant ID and the entry
	 *                   key that needs to be retrieved.
	 */
	public GetAllMemoryEntriesAction(final List<Parameter> parameters) {
		super(parameters);
	}

	@Override
	public boolean isValid() {
		return (getParameters().size() == 2) && (getParameters().get(0) instanceof Identifier)
				&& (getParameters().get(1) instanceof Identifier);
	}

	@Override
	public String getTopic() {
		return "memory_get_all_entries";
	}

	@Override
	public String getData() {
		return (EIStoString(getParameters().get(0)) + ";"
				+ EIStoString(getParameters().get(1)));
	}

	@Override
	public String getExpectedEvent() {
		return null;
	}
}
