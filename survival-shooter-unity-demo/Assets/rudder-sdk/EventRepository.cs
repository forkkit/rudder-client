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
        internal static bool loggingEnabled = true;
        internal static int flushQueueSize;
        internal static string endPointUri { get; set; }
        // internal buffer for events which will be cleared upon successful transmission of events to server
        private List<RudderEvent> eventBuffer = new List<RudderEvent>();

        internal EventRepository(int _flushQueueSize, string _endPointUri, bool _loggingEnabled)
        {
            endPointUri = _endPointUri;
            flushQueueSize = _flushQueueSize;
            loggingEnabled = _loggingEnabled;
        }

        // generic method for dumping all events
        internal void Dump(RudderEvent rudderEvent)
        {
            // temporary for torpedo only as torpedo is specific to amplitude
            rudderEvent.AddIntegrations(RudderIntegrationPlatform.AMPLITUDE);
            // add incoming event to buffer 
            eventBuffer.Add(rudderEvent);
            // if flushQueueSize is full flush the events to server
            if (eventBuffer.Count == flushQueueSize)
            {
                FlushEventsAsync();
            }
        }

        internal void FlushEventsAsync()
        {
            // consturuct payload with "sent_at" and "batch" 
            RudderEventPayload eventPayload = new RudderEventPayload(eventBuffer);

            // serialize payload to JSON string
            string payloadString = JsonConvert.SerializeObject(eventPayload,
                                                            Formatting.None,
                                                            new JsonSerializerSettings
                                                            {
                                                                NullValueHandling = NullValueHandling.Ignore
                                                            });

            // make network request to flush the events 
            PostEventToServer(payloadString);

            // TODO: implement retry logic

            // empty buffer
            eventBuffer.RemoveRange(0, eventBuffer.Count);
        }

        private static async void PostEventToServer(string payload)
        {
            using (var client = new HttpClient())
            {
                using (var request = new HttpRequestMessage(HttpMethod.Post, endPointUri + "/hello"))
                {
                    if (loggingEnabled)
                    {
                        Debug.Log("REQUEST: " + payload);
                    }
                    request.Content = new StringContent(payload, Encoding.UTF8, "application/json");
                    if (loggingEnabled)
                    {
                        Debug.Log("REQUEST LENGTH: " + request.Content.ToString());
                    }
                    HttpResponseMessage response = await client.SendAsync(request);
                    string responseString = await response.Content.ReadAsStringAsync();
                    HttpStatusCode statusCode = response.StatusCode;
                    if (loggingEnabled)
                    {
                        Debug.Log("RESPONSE STATUS_CODE: " + statusCode);
                        Debug.Log("RESPONSE BODY: " + responseString);
                    }

                }
            }
        }
    }
}
