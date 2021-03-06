package org.bitbucket.socialroboticshub.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bitbucket.socialroboticshub.actions.animation.DisableBreathingAction;
import org.bitbucket.socialroboticshub.actions.animation.EnableBreathingAction;
import org.bitbucket.socialroboticshub.actions.animation.GestureAction;
import org.bitbucket.socialroboticshub.actions.animation.GoToPostureAction;
import org.bitbucket.socialroboticshub.actions.animation.PlayMotionAction;
import org.bitbucket.socialroboticshub.actions.animation.RestAction;
import org.bitbucket.socialroboticshub.actions.animation.SetEarColourAction;
import org.bitbucket.socialroboticshub.actions.animation.SetEyeColourAction;
import org.bitbucket.socialroboticshub.actions.animation.SetHeadColourAction;
import org.bitbucket.socialroboticshub.actions.animation.SetIdleAction;
import org.bitbucket.socialroboticshub.actions.animation.SetLedColorAction;
import org.bitbucket.socialroboticshub.actions.animation.SetNonIdleAction;
import org.bitbucket.socialroboticshub.actions.animation.SetStiffnessAction;
import org.bitbucket.socialroboticshub.actions.animation.StartLedAnimationAction;
import org.bitbucket.socialroboticshub.actions.animation.StartMotionRecordingAction;
import org.bitbucket.socialroboticshub.actions.animation.StartMotionRelayingAction;
import org.bitbucket.socialroboticshub.actions.animation.StopLedAnimationAction;
import org.bitbucket.socialroboticshub.actions.animation.StopMotionRecordingAction;
import org.bitbucket.socialroboticshub.actions.animation.StopMotionRelayingAction;
import org.bitbucket.socialroboticshub.actions.animation.TurnLeftAction;
import org.bitbucket.socialroboticshub.actions.animation.TurnRightAction;
import org.bitbucket.socialroboticshub.actions.animation.WakeUpAction;
import org.bitbucket.socialroboticshub.actions.assistant.AssistantPlayMediaAction;
import org.bitbucket.socialroboticshub.actions.assistant.AssistantShowAction;
import org.bitbucket.socialroboticshub.actions.assistant.AssistantShowCardAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.ClearLoadedAudioAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.DisableRecordingAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.EnableRecordingAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.LoadAudioAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.PlayLoadedAudioAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.PlayRawAudioAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.SayAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.SayAnimatedAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.SetLanguageAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.SetSpeechParamAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.StartListeningAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.StartWatchingAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.StopListeningAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.StopTalkingAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.StopWatchingAction;
import org.bitbucket.socialroboticshub.actions.audiovisual.TakePictureAction;
import org.bitbucket.socialroboticshub.actions.browser.BrowserRenderAction;
import org.bitbucket.socialroboticshub.actions.memory.ClearHistoryAction;
import org.bitbucket.socialroboticshub.actions.memory.DeleteAllInteractants;
import org.bitbucket.socialroboticshub.actions.memory.DeleteInteractant;
import org.bitbucket.socialroboticshub.actions.memory.GetAllMemoryEntriesAction;
import org.bitbucket.socialroboticshub.actions.memory.GetDialogHistoryAction;
import org.bitbucket.socialroboticshub.actions.memory.GetInteractantDataAction;
import org.bitbucket.socialroboticshub.actions.memory.GetMemoryEntryAction;
import org.bitbucket.socialroboticshub.actions.memory.GetMoveHistoryAction;
import org.bitbucket.socialroboticshub.actions.memory.GetNarrativeHistoryAction;
import org.bitbucket.socialroboticshub.actions.memory.SetDialogHistoryAction;
import org.bitbucket.socialroboticshub.actions.memory.SetInteractantDataAction;
import org.bitbucket.socialroboticshub.actions.memory.SetMemoryEntryAction;
import org.bitbucket.socialroboticshub.actions.memory.SetMoveHistoryAction;
import org.bitbucket.socialroboticshub.actions.memory.SetNarrativeHistoryAction;
import org.bitbucket.socialroboticshub.actions.memory.GetTopicsOfInterestAction;
import org.bitbucket.socialroboticshub.actions.memory.SetTopicsOfInterestAction;
import org.bitbucket.socialroboticshub.actions.memory.SetSession;

import org.json.JSONObject;

import eis.iilang.Action;
import eis.iilang.Function;
import eis.iilang.Identifier;
import eis.iilang.Numeral;
import eis.iilang.Parameter;
import eis.iilang.ParameterList;
import eis.iilang.TruthValue;

public abstract class RobotAction {
	private final List<Parameter> parameters;

	protected RobotAction(final List<Parameter> parameters) {
		this.parameters = parameters;
	}

	protected List<Parameter> getParameters() {
		return this.parameters;
	}

	public abstract boolean isValid();

	public abstract String getTopic();

	public abstract String getData();

	public abstract String getExpectedEvent();

	public static RobotAction getRobotAction(final Action action) {
		final List<Parameter> parameters = action.getParameters();
		switch (action.getName()) {
		// ROBOT ACTIONS
		case SayAction.NAME:
			return new SayAction(parameters);
		case SayAnimatedAction.NAME:
			return new SayAnimatedAction(parameters);
		case StartListeningAction.NAME:
			return new StartListeningAction(parameters);
		case StopListeningAction.NAME:
			return new StopListeningAction();
		case EnableRecordingAction.NAME:
			return new EnableRecordingAction();
		case DisableRecordingAction.NAME:
			return new DisableRecordingAction();
		case GestureAction.NAME:
			return new GestureAction(parameters);
		case SetEyeColourAction.NAME:
			return new SetEyeColourAction(parameters);
		case SetEarColourAction.NAME:
			return new SetEarColourAction(parameters);
		case SetHeadColourAction.NAME:
			return new SetHeadColourAction(parameters);
		case SetIdleAction.NAME:
			return new SetIdleAction(parameters);
		case SetNonIdleAction.NAME:
			return new SetNonIdleAction();
		case StartWatchingAction.NAME:
			return new StartWatchingAction(parameters);
		case StopWatchingAction.NAME:
			return new StopWatchingAction();
		case LoadAudioAction.NAME:
			return new LoadAudioAction(parameters);
		case PlayRawAudioAction.NAME:
			return new PlayRawAudioAction(parameters);
		case PlayLoadedAudioAction.NAME:
			return new PlayLoadedAudioAction(parameters);
		case ClearLoadedAudioAction.NAME:
			return new ClearLoadedAudioAction();
		case SetLanguageAction.NAME:
			return new SetLanguageAction(parameters);
		case SetSpeechParamAction.NAME:
			return new SetSpeechParamAction(parameters);
		case StopTalkingAction.NAME:
			return new StopTalkingAction();
		case TakePictureAction.NAME:
			return new TakePictureAction();
		case TurnLeftAction.NAME:
			return new TurnLeftAction(parameters);
		case TurnRightAction.NAME:
			return new TurnRightAction(parameters);
		case WakeUpAction.NAME:
			return new WakeUpAction();
		case RestAction.NAME:
			return new RestAction();
		case EnableBreathingAction.NAME:
			return new EnableBreathingAction(parameters);
		case DisableBreathingAction.NAME:
			return new DisableBreathingAction(parameters);
		case GoToPostureAction.NAME:
			return new GoToPostureAction(parameters);
		case SetStiffnessAction.NAME:
			return new SetStiffnessAction(parameters);
		case PlayMotionAction.NAME:
			return new PlayMotionAction(parameters);
		case StartMotionRecordingAction.NAME:
			return new StartMotionRecordingAction(parameters);
		case StopMotionRecordingAction.NAME:
			return new StopMotionRecordingAction();
		case StartMotionRelayingAction.NAME:
			return new StartMotionRelayingAction(parameters);
		case StopMotionRelayingAction.NAME:
			return new StopMotionRelayingAction();
		case SetLedColorAction.NAME:
			return new SetLedColorAction(parameters);
		case StartLedAnimationAction.NAME:
			return new StartLedAnimationAction(parameters);
		case StopLedAnimationAction.NAME:
			return new StopLedAnimationAction();
		// BROWSER ACTION
		case BrowserRenderAction.NAME:
			return new BrowserRenderAction(parameters);
		// MEMORY ACTIONS
		case SetSession.NAME:
			return new SetSession(parameters);
		case GetMemoryEntryAction.NAME:
			return new GetMemoryEntryAction(parameters);
		case GetAllMemoryEntriesAction.NAME:
			return new GetAllMemoryEntriesAction(parameters);
		case SetMemoryEntryAction.NAME:
			return new SetMemoryEntryAction(parameters);
		case GetInteractantDataAction.NAME:
			return new GetInteractantDataAction(parameters);
		case SetInteractantDataAction.NAME:
			return new SetInteractantDataAction(parameters);
		case GetDialogHistoryAction.NAME:
			return new GetDialogHistoryAction(parameters);
		case SetDialogHistoryAction.NAME:
			return new SetDialogHistoryAction(parameters);
		case ClearHistoryAction.NAME:
			return new ClearHistoryAction(parameters);
		case GetNarrativeHistoryAction.NAME:
			return new GetNarrativeHistoryAction(parameters);
		case SetNarrativeHistoryAction.NAME:
			return new SetNarrativeHistoryAction(parameters);
		case GetMoveHistoryAction.NAME:
			return new GetMoveHistoryAction(parameters);
		case SetMoveHistoryAction.NAME:
			return new SetMoveHistoryAction(parameters);
		case GetTopicsOfInterestAction.NAME:
			return new GetTopicsOfInterestAction(parameters);
		case SetTopicsOfInterestAction.NAME:
			return new SetTopicsOfInterestAction(parameters);
		case DeleteInteractant.NAME:
			return new DeleteInteractant(parameters);
		case DeleteAllInteractants.NAME:
			return new DeleteAllInteractants();
		// GOOGLE ASSISTANT ACTIONS
		case AssistantShowAction.NAME:
			return new AssistantShowAction(parameters);
		case AssistantShowCardAction.NAME:
			return new AssistantShowCardAction(parameters);
		case AssistantPlayMediaAction.NAME:
			return new AssistantPlayMediaAction(parameters);
		default:
			return null;
		}
	}

	protected static String EIStoString(final Parameter param) {
		if (param instanceof Identifier) {
			return ((Identifier) param).getValue();
		} else if (param instanceof Numeral) {
			return String.valueOf(((Numeral) param).getValue());
		} else if (param instanceof TruthValue) {
			return ((TruthValue) param).getBooleanValue() ? "1" : "0";
		} else if (param instanceof Function) {
			final Function func = (Function) param;
			final List<Parameter> params = func.getParameters();
			switch (params.size()) {
			case 0:
				return func.getName();
			case 1:
				final Map<String, String> res1 = new HashMap<>(1);
				res1.put(func.getName(), EIStoString(params.get(0)));
				return new JSONObject(res1).toString();
			case 2: // assume :, =, etc.
				final Map<String, String> res2 = new HashMap<>(1);
				res2.put(EIStoString(params.get(0)), EIStoString(params.get(1)));
				return new JSONObject(res2).toString();
			default:
				throw new IllegalArgumentException("Functions with more than 2 parameters are not supported.");
			}

		} else if (param instanceof ParameterList) {
			final ParameterList list = (ParameterList) param;
			final int size = list.size();
			final StringBuilder result = new StringBuilder("[");
			for (int i = 0; i < size; i++) {
				final Parameter item = list.get(i);
				String subresult = EIStoString(item);
				if (item instanceof Identifier) {
					subresult = '"' + subresult + '"';
				}
				result.append(subresult);
				if (i < (size - 1)) {
					result.append(",");
				}
			}
			return result.append("]").toString();
		} else {
			throw new IllegalArgumentException("Unknown parameter type '" + param.getClass() + "'.");
		}
	}
}
