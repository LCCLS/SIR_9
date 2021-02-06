package org.bitbucket.socialroboticshub.actions.memory;

import eis.iilang.Identifier;
import eis.iilang.Parameter;
import org.bitbucket.socialroboticshub.actions.RobotAction;

import java.util.List;

public class DeleteAllUsers extends RobotAction {
	public final static String NAME = "deleteAllUsers";

	public DeleteAllUsers() {
		super(null);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getTopic() {
		return "memory_delete_all_users";
	}

	@Override
	public String getData() {
		return "";
	}

	@Override
	public String getExpectedEvent() {
		return "AllUserSDeleted";
	}
}
