using com.rudderlabs.unity.library.Event;
using com.rudderlabs.unity.library.Event.Property;
using UnityEngine;

public class FPSManager : MonoBehaviour
{
    float deltaTime = 0.0f;

    void Update()
    {
        deltaTime += (Time.unscaledDeltaTime - deltaTime) * 0.1f;
    }

    void OnGUI()
    {
        int w = Screen.width, h = Screen.height;

        GUIStyle style = new GUIStyle();

        Rect rect = new Rect(0, 0, w, h * 2 / 100);
        style.alignment = TextAnchor.UpperLeft;
        style.fontSize = h * 2 / 100;
        style.normal.textColor = new Color(1.0f, 1.0f, 0.5f, 1.0f);
        float msec = deltaTime * 1000.0f;
        float fps = 1.0f / deltaTime;
        string text = string.Format("{0:0.0} ms ({1:0.} fps)", msec, fps);
        GUI.Label(rect, text, style);

        Debug.Log("Tracking FPS ");
        RudderProperty rudderProperty = new RudderProperty();
        rudderProperty.AddProperty("category", "TrackFPS");
        rudderProperty.AddProperty("transform_position", transform.position.ToString());
        RudderEvent rudderEvent = new RudderEventBuilder()
        .SetEventName("Track_FPS")
        .SetRudderProperty(rudderProperty)
        .Build();
        CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);

        // Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
        //         {"category" , "Track_FPS" },
        //         {"transform_position" , transform.position.ToString()}
        //     };
        // Amplitude.Instance.logEvent("Track_FPS");
    }
}
