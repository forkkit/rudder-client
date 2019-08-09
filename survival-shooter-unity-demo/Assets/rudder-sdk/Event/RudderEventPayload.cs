using System;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace com.rudderlabs.unity.library.Event
{
    internal class RudderEventPayload
    {
        [JsonProperty(PropertyName = "sent_at")]
        internal string timestamp;
        [JsonProperty(PropertyName = "batch")]
        internal List<RudderEvent> events;

        [JsonProperty(PropertyName = "writeKey")]
        internal string writeKey = "1P6q8fcXrkmekovCdk0a3gFq30X";

        internal RudderEventPayload(List<RudderEvent> events)
        {
            timestamp = DateTime.UtcNow.ToString("u");
            this.events = events;
        }
    }
}
