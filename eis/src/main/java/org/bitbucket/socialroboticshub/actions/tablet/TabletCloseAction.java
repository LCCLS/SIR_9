package org.bitbucket.socialroboticshub.actions.tablet;

import org.bitbucket.socialroboticshub.actions.RobotAction;

public class TabletCloseAction extends RobotAction {
	public final static String NAME = "tabletClose";

	public TabletCloseAction() {
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
		return "hide";
	}
}
