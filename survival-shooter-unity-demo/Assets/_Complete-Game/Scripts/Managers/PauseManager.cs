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
        RudderProperty rudderProperty = new RudderProperty();
        rudderProperty.AddProperty("category", "GameOver");
        rudderProperty.AddProperty("transform_position", transform.position.ToString());
        RudderEvent rudderEvent = new RudderEventBuilder()
        .SetEventName("PauseManager_Pause")
        .SetRudderProperty(rudderProperty)
        .Build();
        CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);

        Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
                    {"category" , "Pause" },
                    {"transform_position" , transform.position.ToString()}
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
