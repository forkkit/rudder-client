using System.Collections.Generic;
using com.rudderlabs.unity.library.Event;
using com.rudderlabs.unity.library.Event.Property;
using UnityEngine;

namespace CompleteProject
{
    public class GameOverManager : MonoBehaviour
    {
        public PlayerHealth playerHealth;       // Reference to the player's health.


        Animator anim;                          // Reference to the animator component.


        void Awake()
        {
            // Set up the reference.
            anim = GetComponent<Animator>();
        }


        void Update()
        {
            // If the player has run out of health...
            if (playerHealth.currentHealth <= 0)
            {
                // ... tell the animator the game is over.
                anim.SetTrigger("GameOver");

                Debug.Log("Tracking GameOver");
                RudderProperty rudderProperty = new RudderProperty();
                rudderProperty.AddProperty("category", "GameOver");
                rudderProperty.AddProperty("transform_position", transform.position.ToString());
                RudderEvent rudderEvent = new RudderEventBuilder()
                .SetEventName("GameOverManager_GameOver")
                .SetRudderProperty(rudderProperty)
                .Build();
                CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);

                Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
                    {"category" , "GameOver" },
                    {"transform_position" , transform.position.ToString()}
                };
                Amplitude.Instance.logEvent("GameOverManager_GameOver Direct", demoOptions);
            }
        }
    }
}