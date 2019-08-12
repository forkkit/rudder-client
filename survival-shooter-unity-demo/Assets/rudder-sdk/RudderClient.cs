using com.rudderlabs.unity.library.Event;

namespace com.rudderlabs.unity.library
{
    public class RudderClient
    {
        // singleton instnace of the client
        private static RudderClient instance;
        // instance for event repository (to be used internally)
        private static EventRepository repository;

        private RudderClient() {
            // prevent instance creation through constructor
        }

        // instance initialization method
        public static RudderClient GetInstance(string writeKey)
        {
            return GetInstance(writeKey, Constants.BASE_URL, Constants.FLUSH_QUEUE_SIZE, false);
        }

        // instance initialization method
        public static RudderClient GetInstance(string writeKey, int flushQueueSize)
        {
            return GetInstance(writeKey, Constants.BASE_URL, flushQueueSize, false);
        }

        // instance initialization method
        public static RudderClient GetInstance(string writeKey, string endPointUri)
        {
            return GetInstance(writeKey, endPointUri, Constants.FLUSH_QUEUE_SIZE, false);
        }

        // instance initialization method
        public static RudderClient GetInstance(string writeKey, string endPointUri, int flushQueueSize) {
            return GetInstance(writeKey, endPointUri, flushQueueSize, false);
        }

        // instance initialization method
        public static RudderClient GetInstance(string writeKey, string endPointUri, int flushQueueSize, bool loggingEnabled)
        {
            if (instance == null)
            {
                instance = new RudderClient();

                repository = new EventRepository(writeKey, flushQueueSize, endPointUri, loggingEnabled);
            }
            return instance;
        }

        // getter & setter for endPointUri
        public string GetEndPointUri()
        {
            return EventRepository.endPointUri;
        }
        public void SetEndPointUri(string _endPointUri)
        {
            EventRepository.endPointUri = _endPointUri;
        }

        // getter & setter for flushQueueSize
        public int GetFlushQueueSize()
        {
            return EventRepository.flushQueueSize;
        }
        public void SetFlushQueueSize(int _flushQueueSize)
        {
            EventRepository.flushQueueSize = _flushQueueSize;
        }

        // end point for track events
        public void Track(RudderEvent rudderEvent)
        {
            rudderEvent.message.type = RudderEventType.TRACK.value;
            repository.Dump(rudderEvent);
        }
        public void Track(RudderEventBuilder builder)
        {
            this.Track(builder.Build());
        }

        // end point for page events
        public void Page(RudderEvent rudderEvent)
        {
            rudderEvent.message.type = RudderEventType.PAGE.value;
            repository.Dump(rudderEvent);
        }
        public void Page(RudderEventBuilder builder)
        {
            this.Page(builder.Build());
        }

        // end point for screen events
        public void Screen(RudderEvent rudderEvent)
        {
            rudderEvent.message.type = RudderEventType.PAGE.value;
            repository.Dump(rudderEvent);
        }
        public void Screen(RudderEventBuilder builder)
        {
            this.Screen(builder.Build());
        }

        // end point for identify calls
        public void Identify(RudderTraits rudderTraits)
        {
            RudderEvent rudderEvent = new RudderEventBuilder()
                .SetEventName("Identify")
                .SetUseId(rudderTraits.id)
                .Build();
            rudderEvent.message.type = RudderEventType.IDENTIFY.value;
            rudderEvent.message.context.traits = rudderTraits;
            repository.Dump(rudderEvent);
        }
        public void Identify(RudderTraitsBuilder builder)
        {
            Identify(builder.Build());
        }

        // end point for flushing events
        public void Flush()
        {
            repository.FlushEventsAsync();
        }
    }
}
