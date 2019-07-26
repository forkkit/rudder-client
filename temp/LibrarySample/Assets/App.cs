using UnityEngine;
using com.rudderlabs.unity.library;
using com.rudderlabs.unity.library.Event;
using com.rudderlabs.unity.library.Event.Property;

public class App : MonoBehaviour
{
    private RudderClient rudderClient;

    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("Start");
        rudderClient = new RudderClient().GetInstance("http://3fbda96f.ngrok.io", 10);
    }

    // Update is called once per frame
    private bool isTracked;
    void Update()
    {
        if (!isTracked)
        {
            Debug.Log("I have been called");
            RudderEvent rudderEvent = new RudderEventBuilder().SetChannel("Test Channel").SetEventName("Test Track").SetRudderProperty(new TrackPropertyBuilder().SetCategory("Test Category").Build()).Build();
            Debug.Log("Event Created");
            rudderClient.Track(rudderEvent);
            Debug.Log("Event Tracked");
            rudderClient.Flush();
            Debug.Log("Event Flushed");

            isTracked = true;
        }
        
    }
}
