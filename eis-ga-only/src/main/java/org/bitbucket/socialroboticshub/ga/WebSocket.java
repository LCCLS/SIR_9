package org.bitbucket.socialroboticshub.ga;

import java.awt.Frame;
import java.net.URI;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.google.cloud.dialogflow.v2.QueryResult;

public class WebSocket extends WebSocketClient {
	public static final String VUserver = "socialai4.labs.vu.nl";
	private final GoogleAssistant parent;
	private final JDialog status;
	private final JsonUtils json;
	private boolean started = false;

	public WebSocket(final GoogleAssistant parent, final String projectId) throws Exception {
		super(new URI("ws://" + VUserver));
		this.parent = parent;
		this.json = new JsonUtils();
		this.status = new JDialog((Frame) null, "Connection Status");
		this.status.setAlwaysOnTop(true);
		this.status.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.status.add(new JTextField(20));
		this.status.pack();
		setStatus("Connecting to " + VUserver + " ...");
		this.status.setVisible(true);
		connectBlocking();
		start(projectId);
	}

	private void setStatus(final String status) {
		((JTextField) this.status.getComponents()[0]).setText(status);
	}

	public void start(final String projectId) {
		if (this.started) {
			stop();
		}
		System.out.println("Starting with " + projectId + "...");
		send(">>>" + projectId + "|" + System.getenv("user.name"));
		this.started = true;
	}

	public void stop() {
		if (this.started) {
			System.out.println("Stopping current run...");
			send("<<<");
			this.started = false;
		}
	}

	@Override
	public void onOpen(final ServerHandshake handshakedata) {
	}

	@Override
	public void onMessage(final String message) {
		if (message.endsWith("!")) {
			setStatus(message);
		} else {
			try {
				this.status.dispose();
				final QueryResult queryResult = this.json.getQueryResult(message);
				final String text = this.parent.getResponse(queryResult);
				final String jsonResponse = this.json.convertResponse(text);
				send(jsonResponse);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClose(final int code, final String reason, final boolean remote) {
		this.parent.disconnect();
	}

	@Override
	public void onError(final Exception ex) {
		ex.printStackTrace();
	}
}
