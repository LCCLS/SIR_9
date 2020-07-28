package org.bitbucket.socialroboticshub.actions.animation;

import java.util.List;

import org.bitbucket.socialroboticshub.actions.RobotAction;

import eis.iilang.Identifier;
import eis.iilang.Parameter;

abstract class TurnAction extends RobotAction {
	protected final String direction;

	/**
	 * @param parameters A list of 1 optional boolean identifier (small=true/false).
	 * @param direction  Left or Right.
	 */
	TurnAction(final List<Parameter> parameters, final String direction) {
		super(parameters);
		this.direction = direction;
	}

	@Override
	public boolean isValid() {
		return getParameters().isEmpty()
				|| (getParameters().size() == 1 && getParameters().get(0) instanceof Identifier);
	}

	@Override
	public String getTopic() {
		String toAdd = "";
		if (getParameters().size() == 1
				&& ((Identifier) (getParameters().get(0))).getValue().equalsIgnoreCase("true")) {
			toAdd += "_small";
		}
		return ("action_turn" + toAdd);
	}

	@Override
	public String getData() {
		return this.direction;
	}
}
