package org.bitbucket.socialroboticshub.actions.animation;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.bitbucket.socialroboticshub.actions.RobotAction;

import eis.iilang.Identifier;
import eis.iilang.Parameter;

public class PlayMotionFileAction extends RobotAction {
	public final static String NAME = "playMotionFile";

	/**
	 * @param parameters A list with at least one identifier, referencing the XML
	 *                   file to be played, and optionally a second identifier with
	 *                   an emotion to use as a transformation to that animation.
	 */
	public PlayMotionFileAction(final List<Parameter> parameters) {
		super(parameters);
	}

	@Override
	public boolean isValid() {
		final int params = getParameters().size();
		boolean valid = (params == 1 || params == 2);
		valid &= (getParameters().get(0) instanceof Identifier);
		valid &= new File(((Identifier) getParameters().get(0)).getValue()).canRead();
		if (params == 2) {
			valid &= (getParameters().get(1) instanceof Identifier);
		}
		return valid;
	}

	@Override
	public String getTopic() {
		return "action_motion_file";
	}

	@Override
	public String getData() {
		try {
			final Path path = Paths.get(((Identifier) getParameters().get(0)).getValue());
			final String xml = new String(Files.readAllBytes(path));
			String result = getMinifiedXML(xml);
			if (getParameters().size() == 2) {
				result += (";" + ((Identifier) getParameters().get(1)).getValue());
			}
			return result;
		} catch (final Exception e) {
			throw new RuntimeException("Failed to read motion file", e);
		}
	}

	private static String getMinifiedXML(final String source) throws Exception {
		final TransformerFactory factory = TransformerFactory.newInstance();
		final InputStream xslt = PlayMotionFileAction.class.getResourceAsStream("/transform.xslt");
		final Transformer transformer = factory.newTransformer(new StreamSource(xslt));

		final Source text = new StreamSource(new StringReader(source));
		final Writer output = new StringWriter();
		transformer.transform(text, new StreamResult(output));

		return output.toString();
	}
}
