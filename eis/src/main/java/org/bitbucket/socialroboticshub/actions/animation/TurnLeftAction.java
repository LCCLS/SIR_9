package org.bitbucket.socialroboticshub.actions.animation;

import org.bitbucket.socialroboticshub.actions.RobotAction;

public class TurnLeftAction extends RobotAction {
	public final static String NAME = "turnLeft";

	public TurnLeftAction() {
		super(null);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getTopic() {
		return "action_turn";
	}

	@Override
	public String getData() {
		return "left";
	}
}
