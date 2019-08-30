using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.Audio;
using System.Collections.Generic;
using com.rudderlabs.unity.library.Event;
using com.rudderlabs.unity.library.Event.Property;
#if UNITY_EDITOR
using UnityEditor;
#endif

public class PauseManager : MonoBehaviour
{

    public AudioMixerSnapshot paused;
    public AudioMixerSnapshot unpaused;

    Canvas canvas;

    void Start()
    {
        canvas = GetComponent<Canvas>();
    }

    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            canvas.enabled = !canvas.enabled;
            Pause();
        }
    }

    public void Pause()
    {
        Time.timeScale = Time.timeScale == 0 ? 1 : 0;
        Lowpass();

        Debug.Log("Tracking Pause");
        RudderEvent rudderEvent = new RudderEventBuilder()
        .SetEventName("PauseManager_Pause")
        .Build();
        RudderProperty rudderProperty = new RudderProperty();
        rudderProperty.AddProperty("category", "Pause");
        rudderProperty.AddProperty("transform_position", transform.position.ToString());
        rudderProperty.AddProperty("rl_message_id", rudderEvent.rl_message.rl_message_id);
        rudderEvent.SetProperties(rudderProperty);
        CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);

        Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
                    {"category" , "Pause" },
                    {"transform_position" , transform.position.ToString()},
                    {"rl_message_id" , rudderEvent.rl_message.rl_message_id}
                };
        Amplitude.Instance.logEvent("PauseManager_Pause Direct", demoOptions);
    }

    void Lowpass()
    {
        if (Time.timeScale == 0)
        {
            paused.TransitionTo(.01f);
        }

        else

        {
            unpaused.TransitionTo(.01f);
        }
    }

    public void Quit()
    {
#if UNITY_EDITOR
        EditorApplication.isPlaying = false;
#else
		Application.Quit();
#endif
    }
}
