package org.bitbucket.socialroboticshub.ga;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.google.cloud.dialogflow.v2.QueryResult;

public class WebSocket extends WebSocketClient {
	public static final String VUserver = "socialai4.labs.vu.nl";
	private final GoogleAssistant parent;
	private final JsonUtils json;
	private boolean started = false;

	public WebSocket(final GoogleAssistant parent, final String projectId) throws Exception {
		super(new URI("ws://" + VUserver));
		this.parent = parent;
		this.json = new JsonUtils();
		connectBlocking();
		System.out.println("Connected to " + VUserver);
		start(projectId);
	}

	public void start(final String projectId) {
		if (this.started) {
			stop();
		}
		System.out.println("Starting with " + projectId + "...");
		send(">>>" + projectId);
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
		try {
			final QueryResult queryResult = this.json.getQueryResult(message);
			final String text = this.parent.getResponse(queryResult);
			final String jsonResponse = this.json.convertResponse(text);
			send(jsonResponse);
		} catch (final Exception e) {
			e.printStackTrace();
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
