package org.bitbucket.socialroboticshub.actions.assistant;

import java.util.List;

import eis.iilang.Identifier;
import eis.iilang.Parameter;

public class AssistantShowAction extends AssistantAction {
	public final static String NAME = "assistantShow";

	/**
	 * @param parameters A list of 1 identifier: the text to show (and say)
	 */
	public AssistantShowAction(final List<Parameter> parameters) {
		super(parameters);
	}

	@Override
	public boolean isValid() {
		return (getParameters().size() == 1) && (getParameters().get(0) instanceof Identifier);
	}

	@Override
	public String getTopic() {
		return "assistant_show";
	}

	@Override
	public String getData() {
		return EIStoString(getParameters().get(0));
	}
}
