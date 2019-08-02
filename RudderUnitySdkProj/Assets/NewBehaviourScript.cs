using com.rudderlabs.unity.library;
using com.rudderlabs.unity.library.Event;
using com.rudderlabs.unity.library.Event.Property;
using UnityEngine;
public class NewBehaviourScript : MonoBehaviour
{
    private RudderClient rudderClient;
    // Start is called before the first frame update
    void Start()
    {
        rudderClient = new RudderClient().GetInstance("http://a90bf7fe.ngrok.io");
        Debug.Log("Client Initialized");
    }

    private bool tracked = false;
    // Update is called once per frame
    void Update()
    {
        if (!tracked)
        {
            Debug.Log("Tracking Started");
            TrackPropertyBuilder trackBuilder = new TrackPropertyBuilder().SetCategory("Test Category").SetLabel("Test Label").SetValue("Test Value");
            RudderUserProperty userProperty = new RudderUserProperty();
            userProperty.AddProperty("user_id", "test-user-id");
            RudderEventBuilder builder = new RudderEventBuilder()
                .SetChannel("Test Channel")
                .SetEventName("Test Track")
                .SetRudderProperty(trackBuilder)
                .SetUserProperty(userProperty);
            rudderClient.Track(builder);
            Debug.Log("Tracking Event Tracked");

            // ScreenPropertyBuilder screenBuilder = new ScreenPropertyBuilder().SetName("Test Name");
            // RudderEventBuilder builder1 = new RudderEventBuilder()
            //     .SetChannel("Test Channel")
            //     .SetRudderProperty(screenBuilder);
            // rudderClient.Screen(builder1);

            // PagePropertyBuilder pageBuilder = new PagePropertyBuilder()
            //     .SetUrl("http://www.example.com/")
            //     .SetPath("Test Path")
            //     .SetKeywords("Test Keywords")
            //     .SetReferrer("Test referrer")
            //     .SetSearch("Test Search")
            //     .SetTitle("Test Title");
            // RudderEventBuilder builder2 = new RudderEventBuilder()
            //     .SetChannel("Test Channel")
            //     .SetRudderProperty(pageBuilder);
            // rudderClient.Page(builder2);

            // RudderTraitsBuilder traitsBuilder = new RudderTraitsBuilder()
            // .SetCity("New York")
            // .SetCountry("USA")
            // .SetPostalCode("ZA1234")
            // .SetState("New York")
            // .SetStreet("Wall Street")
            // .SetAge(28)
            // .SetBirthday("20-09-2019")
            // .SetCompanyName("Rudder")
            // .SetCompanyId("company--id--test")
            // .SetIndustry("Software Engg")
            // .SetCreatedAt("20-09-2019 19:00:00Z")
            // .SetDescription("Test Description")
            // .SetEmail("example@gmail.com")
            // .SetFirstName("Example")
            // .SetGender("Female")
            // .SetId("40d6c905-9ef0-506f-9468-a56e3085bfc2")
            // .SetLastName("Name")
            // .SetName("Example Name")
            // .SetPhone("test phone num")
            // .SetTitle("Test Titke")
            // .SetUserName("testUserName");
            // rudderClient.Identify(traitsBuilder);

            rudderClient.Flush();
            Debug.Log("Event Flushed");
            tracked = true;
        }

    }
}
