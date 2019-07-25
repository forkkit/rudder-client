using System;
using com.rudderlabs.unity.library.Event;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace com.rudderlabs.unity.library
{
    internal class EventRepository
    {
        private int flushQueueSize = Constants.FLUSH_QUEUE_SIZE;
        private string endPointUri = Constants.BASE_URL;
        private List<RudderEvent> eventBuffer = new List<RudderEvent>();

        internal EventRepository(int flushQueueSize, string endPointUri)
        {
            this.endPointUri = endPointUri;
            this.flushQueueSize = flushQueueSize;
        }

        internal void Dump(RudderEvent rudderEvent)
        {
            eventBuffer.Add(rudderEvent);

            if (eventBuffer.Count == flushQueueSize)
            {
                FlushEventsAsync();
            }
        }

        internal void FlushEventsAsync()
        {
            RudderEventPayload eventPayload = new RudderEventPayload(eventBuffer);
            string payloadString = JsonConvert.SerializeObject(eventPayload);
            Console.Write("~~REQUEST: " + payloadString);

            Task<HttpResponseMessage> task = PostEventToServer(payloadString);

            
        }

        private async Task<HttpResponseMessage> PostEventToServer(string payload)
        {
            using (var client = new HttpClient())
            {
                using (var request = new HttpRequestMessage(HttpMethod.Post, endPointUri+ "/hello"))
                {
                    request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                    request.Content = new StringContent(payload, Encoding.UTF8, "application/json");
                    return await client.SendAsync(request);
                }
            }
        }

        internal int GetFlushQueueSize()
        {
            return flushQueueSize;
        }

        internal string GetEndPointUri()
        {
            return endPointUri;
        }

        internal void SetFlushQueueSize(int _flushQueueSize)
        {
            flushQueueSize = _flushQueueSize;
        }

        internal void SetEndPointUri(string _endPointUri)
        {
            endPointUri = _endPointUri;
        }
    }
}
