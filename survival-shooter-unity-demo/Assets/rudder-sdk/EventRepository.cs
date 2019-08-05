using com.rudderlabs.unity.library.Event;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using Newtonsoft.Json;
using UnityEngine;
using System.Net;

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

            string payloadString = JsonConvert.SerializeObject(eventPayload, 
                                                            Formatting.None, 
                                                            new JsonSerializerSettings { 
                                                                NullValueHandling = NullValueHandling.Ignore
                                                            });

            PostEventToServer(this, payloadString);

            eventBuffer.RemoveRange(0, eventBuffer.Count);
        }

        private static async void PostEventToServer(EventRepository instance, string payload)
        {
            using (var client = new HttpClient())
            {
                using (var request = new HttpRequestMessage(HttpMethod.Post, instance.endPointUri+ "/hello"))
                {
                    Debug.Log("REQUEST: "+ payload);
                    request.Content = new StringContent(payload, Encoding.UTF8, "application/json");
                    Debug.Log("REQUEST LENGTH: "+ request.Content.ToString());
                    HttpResponseMessage response = await client.SendAsync(request);
                    string responseString = await response.Content.ReadAsStringAsync();
                    HttpStatusCode statusCode = response.StatusCode;

                    Debug.Log("RESPONSE STATUS_CODE: " + statusCode);
                    Debug.Log("RESPONSE BODY: " + responseString);
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
