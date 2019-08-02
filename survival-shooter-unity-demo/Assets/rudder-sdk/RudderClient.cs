using com.rudderlabs.unity.library.Event;

namespace com.rudderlabs.unity.library
{
    public class RudderClient
    {
        private static RudderClient instance;
        private static EventRepository repository;

        public static RudderClient GetInstance()
        {
            return GetInstance(Constants.BASE_URL, Constants.FLUSH_QUEUE_SIZE);
        }

        public static RudderClient GetInstance(int flushQueueSize)
        {
            return GetInstance(Constants.BASE_URL, flushQueueSize);
        }

        public static RudderClient GetInstance(string endPointUri)
        {
            return GetInstance(endPointUri, Constants.FLUSH_QUEUE_SIZE);
        }

        public static RudderClient GetInstance(string endPointUri, int flushQueueSize)
        {
            if (instance == null)
            {
                instance = new RudderClient();

                repository = new EventRepository(flushQueueSize, endPointUri);
            }
            return instance;
        }

        public string GetEndPointUri()
        {
            return repository.GetEndPointUri();
        }

        public int GetFlushQueueSize()
        {
            return repository.GetFlushQueueSize();
        }

        public void SetEndPointUri(string endPointUri)
        {
            repository.SetEndPointUri(endPointUri);
        }

        public void SetFlushQueueSize(int flushQueueSize)
        {
            repository.SetFlushQueueSize(flushQueueSize);
        }

        public void Track(RudderEvent rudderEvent)
        {
            rudderEvent.SetEventType("track");
            repository.Dump(rudderEvent);
        }

        public void Track(RudderEventBuilder builder)
        {
            this.Track(builder.Build());
        }

        public void Page(RudderEvent rudderEvent)
        {
            rudderEvent.SetEventType("page");
            repository.Dump(rudderEvent);
        }

        public void Page(RudderEventBuilder builder)
        {
            this.Page(builder.Build());
        }

        public void Screen(RudderEvent rudderEvent)
        {
            rudderEvent.SetEventType("screen");
            repository.Dump(rudderEvent);
        }

        public void Screen(RudderEventBuilder builder)
        {
            this.Screen(builder.Build());
        }

        public void Identify(RudderTraits rudderTraits)
        {
            RudderEvent rudderEvent = new RudderEventBuilder()
                .SetChannel("Identification Channel")
                .SetEventName("Identify")
                .SetUseId(rudderTraits.id)
                .Build();
            rudderEvent.SetEventType("identify");
            rudderEvent.SetRudderTraits(rudderTraits);
            repository.Dump(rudderEvent);
        }

        public void Identify(RudderTraitsBuilder builder)
        {
            Identify(builder.Build());
        }

        public void Flush()
        {
            repository.FlushEventsAsync();
        }
    }
}
