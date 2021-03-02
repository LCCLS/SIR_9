package org.bitbucket.socialroboticshub.actions.assistant;

import java.util.List;

import eis.iilang.Identifier;
import eis.iilang.Parameter;

public class AssistantShowCardAction extends AssistantAction {
	public final static String NAME = "assistantShowCard";

	/**
	 * @param parameters A list of 3 identifiers: the text to show (and say), the
	 *                   url of the image to show, and a description of the image.
	 */
	public AssistantShowCardAction(final List<Parameter> parameters) {
		super(parameters);
	}

	@Override
	public boolean isValid() {
		return (getParameters().size() == 3) && (getParameters().get(0) instanceof Identifier)
				&& (getParameters().get(1) instanceof Identifier) && (getParameters().get(2) instanceof Identifier);
	}

	@Override
	public String getTopic() {
		return "assistant_show_card";
	}

	@Override
	public String getData() {
		return EIStoString(getParameters().get(0)) + ";" + EIStoString(getParameters().get(1)) + ";"
				+ EIStoString(getParameters().get(2));
	}
}
