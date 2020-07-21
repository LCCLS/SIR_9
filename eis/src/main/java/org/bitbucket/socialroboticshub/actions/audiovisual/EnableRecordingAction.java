package org.bitbucket.socialroboticshub.actions.audiovisual;

import org.bitbucket.socialroboticshub.actions.RobotAction;

public class EnableRecordingAction extends RobotAction {
	public final static String NAME = "enableRecording";

	public EnableRecordingAction() {
		super(null);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getTopic() {
		return "dialogflow_record";
	}

	@Override
	public String getData() {
		return "1";
	}
}
