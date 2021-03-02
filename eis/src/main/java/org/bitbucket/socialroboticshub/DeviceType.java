package org.bitbucket.socialroboticshub;

enum DeviceType {
	CAMERA("cam"), MICROPHONE("mic"), ROBOT("robot"), SPEAKER("speaker"), BROWSER("browser"), GOOGLE_ASSISTANT("ga");

	private final String name;

	DeviceType(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static DeviceType fromString(final String string) {
		switch (string) {
		case "cam":
			return CAMERA;
		case "mic":
			return MICROPHONE;
		case "robot":
			return ROBOT;
		case "speaker":
			return SPEAKER;
		case "browser":
			return BROWSER;
		case "ga":
			return GOOGLE_ASSISTANT;
		default:
			return null;
		}
	}

	public static int size() {
		return 6;
	}
}
