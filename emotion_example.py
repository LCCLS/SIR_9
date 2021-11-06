from threading import Event
from social_interaction_cloud.basic_connector import BasicSICConnector


class Emotion_Connector_Example:
    """This is just an attempt to understand emotion_detection in Nao"""

    def __init__(self, server_ip: str):
        self.awake_lock = Event()
        self.sic = BasicSICConnector(server_ip)

    def run(self) -> None:
        self.sic.start()  # starting the SIC
        self.sic.set_language('en-US')  # setting the speech to english
        self.sic.wake_up(self.awake)  # waking up Nao
        self.awake_lock.wait()  # making Nao wait until the event it was woken up for, is over.

        # event -- emotion_detection
        self.sic.say('activating emotion_detection')  # saying that it is engaging in emotion-detection so we know
        # that is is about to initialise into emotional_detection

        # activating emotion_detection
        self.sic.start_emotion_detection(self.emotion_detected)  # what is the input parameter "self" - is it an
        #  emotion ? or a list of emotions? or is it simply "'onEmotionDetected'" ?
        self.sic.say('Emotion successfully detected. I will shut off now.')  # just so we know that is has successfully
        # ran emotion_detection
        self.sic.stop_emotion_detection() # again missing the input parameter "self" 

    def emotion_detected(self) -> None:
        """Callback function that just says that some emotion is detected. It does not differentiate between
        emotions though. So this will be the next step. """
        self.sic.say('I detected an emotion!')

    def awake(self) -> None:
        """Callback function for wake_up action. Called only once.
        It lifts the lock, making the program continue from self.awake_lock.wait()"""
        self.awake_lock.set()


example = Emotion_Connector_Example('127.0.0.1')
example.run()