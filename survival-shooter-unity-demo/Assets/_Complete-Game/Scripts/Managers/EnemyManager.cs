using System.Collections.Generic;
using com.rudderlabs.unity.library.Event;
using com.rudderlabs.unity.library.Event.Property;
using UnityEngine;

namespace CompleteProject
{
    public class EnemyManager : MonoBehaviour
    {
        public PlayerHealth playerHealth;       // Reference to the player's heatlh.
        public GameObject enemy;                // The enemy prefab to be spawned.
        public float spawnTime = 3f;            // How long between each spawn.
        public Transform[] spawnPoints;         // An array of the spawn points this enemy can spawn from.


        void Start ()
        {
            // Call the Spawn function after a delay of the spawnTime and then continue to call after the same amount of time.
            InvokeRepeating ("Spawn", spawnTime, spawnTime);
        }


        void Spawn ()
        {
            // If the player has no health left...
            if(playerHealth.currentHealth <= 0f)
            {
                // ... exit the function.
                return;
            }

            // Find a random index between zero and one less than the number of spawn points.
            int spawnPointIndex = Random.Range (0, spawnPoints.Length);

            // Create an instance of the enemy prefab at the randomly selected spawn point's position and rotation.
            Instantiate (enemy, spawnPoints[spawnPointIndex].position, spawnPoints[spawnPointIndex].rotation);

            Debug.Log("Tracking Spawn");
            RudderEvent rudderEvent = new RudderEventBuilder()
            .SetEventName("EnemyManager_Spawn")
            .Build();
            RudderProperty rudderProperty = new RudderProperty();
            rudderProperty.AddProperty("category", "Spawn");
            rudderProperty.AddProperty("transform_position", transform.position.ToString());
            rudderProperty.AddProperty("rl_message_id", rudderEvent.message.messageId);
            rudderEvent.SetProperties(rudderProperty);
            CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);

            Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
                {"category" , "Spawn" },
                {"transform_position" , transform.position.ToString()},
                {"rl_message_id" , rudderEvent.message.messageId}
            };
            Amplitude.Instance.logEvent("EnemyManager_Spawn Direct", demoOptions);
        }
    }
}