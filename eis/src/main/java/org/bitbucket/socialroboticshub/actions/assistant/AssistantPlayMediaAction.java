package org.bitbucket.socialroboticshub.actions.assistant;

import java.util.ArrayList;
import java.util.List;

import eis.iilang.Identifier;
import eis.iilang.Parameter;
import eis.iilang.ParameterList;

public class AssistantPlayMediaAction extends AssistantAction {
	public final static String NAME = "assistantPlayMedia";

	/**
	 * @param parameters A list of 3 identifiers and optionally a parameterlist: the
	 *                   text to show (and say), the name of the audio to play, the
	 *                   url to the audio (MP3 on HTTPS), and the response hints.
	 */
	public AssistantPlayMediaAction(final List<Parameter> parameters) {
		super(parameters);
	}

	@Override
	public boolean isValid() {
		final int params = getParameters().size();
		boolean valid = (params == 3 || params == 4);
		if (valid) {
			valid &= (getParameters().get(0) instanceof Identifier);
			valid &= (getParameters().get(1) instanceof Identifier);
			valid &= (getParameters().get(2) instanceof Identifier);
			if (params == 4) {
				valid &= (getParameters().get(3) instanceof ParameterList);
			}
		}
		return valid;
	}

	@Override
	public String getTopic() {
		return "assistant_play_media";
	}

	@Override
	public String getData() {
		final ParameterList list = (getParameters().size() == 4) ? (ParameterList) getParameters().get(1)
				: new ParameterList(new ArrayList<>(0));
		return EIStoString(getParameters().get(0)) + "|" + EIStoString(getParameters().get(1)) + "|"
				+ EIStoString(getParameters().get(2)) + "|" + EIStoString(list);
	}
}
