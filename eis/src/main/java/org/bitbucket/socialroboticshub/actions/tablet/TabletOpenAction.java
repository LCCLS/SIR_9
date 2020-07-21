package org.bitbucket.socialroboticshub.actions.tablet;

import org.bitbucket.socialroboticshub.actions.RobotAction;

public class TabletOpenAction extends RobotAction {
	public final static String NAME = "tabletOpen";

	public TabletOpenAction() {
		super(null);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getTopic() {
		return "tablet_control";
	}

	@Override
	public String getData() {
		return "show";
	}
}
