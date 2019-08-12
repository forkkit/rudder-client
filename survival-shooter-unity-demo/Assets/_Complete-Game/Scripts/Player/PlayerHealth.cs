using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using UnityEngine.SceneManagement;
using System.Collections.Generic;
using Firebase;
using Firebase.Analytics;

namespace CompleteProject
{
    public class PlayerHealth : MonoBehaviour
    {
        public int startingHealth = 100000;                            // The amount of health the player starts the game with.
        public int currentHealth;                                   // The current health the player has.
        public Slider healthSlider;                                 // Reference to the UI's health bar.
        public Image damageImage;                                   // Reference to an image to flash on the screen on being hurt.
        public AudioClip deathClip;                                 // The audio clip to play when the player dies.
        public float flashSpeed = 5f;                               // The speed the damageImage will fade at.
        public Color flashColour = new Color(1f, 0f, 0f, 0.1f);     // The colour the damageImage is set to, to flash.

        Animator anim;                                              // Reference to the Animator component.
        AudioSource playerAudio;                                    // Reference to the AudioSource component.
        PlayerMovement playerMovement;                              // Reference to the player's movement.
        PlayerShooting playerShooting;                              // Reference to the PlayerShooting script.
        bool isDead;                                                // Whether the player is dead.
        bool damaged;                                               // True when the player gets damaged.


        void Awake()
        {
            // Setting up the references.
            anim = GetComponent<Animator>();
            playerAudio = GetComponent<AudioSource>();
            playerMovement = GetComponent<PlayerMovement>();
            playerShooting = GetComponentInChildren<PlayerShooting>();

            // Set the initial health of the player.
            currentHealth = startingHealth;
        }


        void Update()
        {
            // If the player has just been damaged...
            if (damaged)
            {
                // ... set the colour of the damageImage to the flash colour.
                damageImage.color = flashColour;
            }
            // Otherwise...
            else
            {
                // ... transition the colour back to clear.
                damageImage.color = Color.Lerp(damageImage.color, Color.clear, flashSpeed * Time.deltaTime);
            }

            // Reset the damaged flag.
            damaged = false;
        }


        public void TakeDamage(int amount)
        {
            // Set the damaged flag so the screen will flash.
            damaged = true;

            // Reduce the current health by the damage amount.
            currentHealth -= amount / 2;

            // Set the health bar's value to the current health.
            healthSlider.value = currentHealth;

            // Play the hurt sound effect.
            playerAudio.Play();

            // If the player has lost all it's health and the death flag hasn't been set yet...
            if (currentHealth <= 0 && !isDead)
            {
                // ... it should die.
                Death();
            }

            // Debug.Log("Tracking Player TakeDamage");
            // RudderProperty rudderProperty = new RudderProperty();
            // rudderProperty.AddProperty("category", "TakeDamage");
            // rudderProperty.AddProperty("transform_position", transform.position.ToString());
            // RudderEvent rudderEvent = new RudderEventBuilder()
            // .SetEventName("PlayerHealth_TakeDamage")
            // .SetRudderProperty(rudderProperty)
            // .Build();
            // CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);
            // // Sample firebase analytics event
            // Firebase.Analytics.FirebaseAnalytics
            //     .LogEvent("progress", "percent", 0.4f);
            // Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
            //     {"category" , "TakeDamage" },
            //     {"transform_position" , transform.position.ToString()},
            //     {"insert_id" , rudderEvent.message.messageId}
            // };
            // Amplitude.Instance.logEvent("PlayerHealth_TakeDamage Direct", demoOptions);
        }


        void Death()
        {
            // Set the death flag so this function won't be called again.
            isDead = true;

            // Turn off any remaining shooting effects.
            playerShooting.DisableEffects();

            // Tell the animator that the player is dead.
            anim.SetTrigger("Die");

            // Set the audiosource to play the death clip and play it (this will stop the hurt sound from playing).
            playerAudio.clip = deathClip;
            playerAudio.Play();

            // Turn off the movement and shooting scripts.
            playerMovement.enabled = false;
            playerShooting.enabled = false;

            // Debug.Log("Tracking Player Death");
            // RudderProperty rudderProperty = new RudderProperty();
            // rudderProperty.AddProperty("category", "Death");
            // rudderProperty.AddProperty("transform_position", transform.position.ToString());
            // RudderEvent rudderEvent = new RudderEventBuilder()
            // .SetEventName("PlayerHealth_Death")
            // .SetRudderProperty(rudderProperty)
            // .Build();
            // CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);

            // Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
            //     {"category" , "Death" },
            //     {"transform_position" , transform.position.ToString()},
            //     {"insert_id" , rudderEvent.message.messageId}
            // };
            // Amplitude.Instance.logEvent("PlayerHealth_Death Direct", demoOptions);
        }


        public void RestartLevel()
        {
            // Reload the level that is currently loaded.
            SceneManager.LoadScene(0);
        }
    }
}