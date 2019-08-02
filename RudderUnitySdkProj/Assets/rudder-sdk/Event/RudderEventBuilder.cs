using com.rudderlabs.unity.library.Event.Property;

namespace com.rudderlabs.unity.library.Event
{
    public class RudderEventBuilder
    {
        private RudderProperty rudderProperty;
        public RudderEventBuilder SetRudderProperty(RudderProperty rudderProperty)
        {
            this.rudderProperty = rudderProperty;
            return this;
        }

        public RudderEventBuilder SetRudderProperty(RudderPropertyBuilder propertyBuilder)
        {
            this.rudderProperty = propertyBuilder.Build();
            return this;
        }

        private string eventName;
        public RudderEventBuilder SetEventName(string eventName)
        {
            this.eventName = eventName;
            return this;
        }

        private string userId;
        public RudderEventBuilder SetUseId(string userId)
        {
            this.userId = userId;
            return this;
        }

        private string channel;
        public RudderEventBuilder SetChannel(string channel)
        {
            this.channel = channel;
            return this;
        }

        private RudderUserProperty userProperty;
        public RudderEventBuilder SetUserProperty(RudderUserProperty userProperty)
        {
            this.userProperty = userProperty;
            return this;
        }

        public RudderEvent Build()
        {
            RudderEvent rudderEvent = new RudderEvent();
            if (this.rudderProperty != null)
            {
                rudderEvent.SetProperty(this.rudderProperty);
            }
            if (this.userProperty != null)
            {
                rudderEvent.SetUserProperty(this.userProperty);
            }
            rudderEvent.message.eventName = eventName;
            rudderEvent.message.userId = userId;
            rudderEvent.message.channel = channel;
            return rudderEvent;
        }
    }
}
