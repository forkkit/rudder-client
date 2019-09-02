﻿using System.Collections.Generic;
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

        private bool gameOverTracked = false;
        void Update()
        {
            // If the player has run out of health...
            if (playerHealth.currentHealth <= 0)
            {
                // ... tell the animator the game is over.
                anim.SetTrigger("GameOver");

                if (!gameOverTracked)
                {
                    Debug.Log("Tracking GameOver");
                    RudderEvent rudderEvent = new RudderEventBuilder()
                    .SetEventName("GameOverManager_GameOver")
                    .Build();
                    RudderProperty rudderProperty = new RudderProperty();
                    rudderProperty.AddProperty("category", "GameOver");
                    rudderProperty.AddProperty("total_payments", ScoreManager.score.ToString());
                    rudderProperty.AddProperty("transform_position", transform.position.ToString());
                    rudderProperty.AddProperty("rl_message_id", rudderEvent.rl_message.rl_message_id);
                    rudderEvent.SetProperties(rudderProperty);
                    CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);

                    Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
                        {"category" , "GameOver" },
                        {"total_payments" , ScoreManager.score },
                        {"transform_position" , transform.position.ToString()},
                        {"rl_message_id" , rudderEvent.rl_message.rl_message_id}
                    };
                    Amplitude.Instance.logEvent("GameOverManager_GameOver Direct", demoOptions);
                    gameOverTracked = true;
                }
            }
        }
    }
}