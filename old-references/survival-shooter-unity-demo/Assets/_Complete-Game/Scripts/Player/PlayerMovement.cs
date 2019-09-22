using com.rudderlabs.unity.library;
using com.rudderlabs.unity.library.Event;
using com.rudderlabs.unity.library.Event.Property;
using UnityEngine;
using UnitySampleAssets.CrossPlatformInput;

namespace CompleteProject
{
    public class PlayerMovement : MonoBehaviour
    {
        public static RudderClient rudderInstance;
        // private static string RUDDER_API_URL = "https://192.168.1.101";
        // private static string RUDDER_API_URL = "https://torpedo.rudderlabs.com";
        // private static string RUDDER_API_URL = "https://torpedo-dev.rudderlabs.com";
        // private static string RUDDER_API_URL = "https://rudderlabs.com";
        private static string RUDDER_API_URL = "https://92c02fd7.ngrok.io";
        // private static string RUDDER_WRITE_KEY = "1QH0xOiRBxiKht41YBe90rJVVXR";
        private static string RUDDER_WRITE_KEY = "1P6q8fcXrkmekovCdk0a3gFq30X";
        private static int RUDDER_FLUSH_QUEUE_SIZE = 30;
        private static string AMPLITUDE_API_KEY = "93e43f5663a58bc385048a27c4e90c30";
        public float speed = 6f;            // The speed that the player will move at.
        Vector3 movement;                   // The vector to store the direction of the player's movement.
        Animator anim;                      // Reference to the animator component.
        Rigidbody playerRigidbody;          // Reference to the player's rigidbody.
#if !MOBILE_INPUT
        int floorMask;                      // A layer mask so that a ray can be cast just at gameobjects on the floor layer.
        float camRayLength = 100f;          // The length of the ray from the camera into the scene.
#endif

        void Awake()
        {
#if !MOBILE_INPUT
            // Create a layer mask for the floor layer.
            floorMask = LayerMask.GetMask("Floor");
#endif

            // Set up references.
            anim = GetComponent<Animator>();
            playerRigidbody = GetComponent<Rigidbody>();
#if !UNITY_EDITOR
            Debug.Log("Initializing Rudder");
            rudderInstance = RudderClient.GetInstance(RUDDER_WRITE_KEY, RUDDER_API_URL, RUDDER_FLUSH_QUEUE_SIZE);
#endif
            // Debug.Log("Initializing Amplitude");
            // Amplitude amplitude = Amplitude.Instance;
            // amplitude.logging = true;
            // amplitude.init(AMPLITUDE_API_KEY);
            // amplitude.setUserId(SystemInfo.deviceUniqueIdentifier.ToLower());
        }


        void FixedUpdate()
        {
            // Store the input axes.
            float h = CrossPlatformInputManager.GetAxisRaw("Horizontal");
            float v = CrossPlatformInputManager.GetAxisRaw("Vertical");

            // Move the player around the scene.
            Move(h, v);

            // Turn the player to face the mouse cursor.
            Turning();

            // Animate the player.
            Animating(h, v);
        }


        void Move(float h, float v)
        {
            // Set the movement vector based on the axis input.
            movement.Set(h, 0f, v);

            // Normalise the movement vector and make it proportional to the speed per second.
            movement = movement.normalized * speed * Time.deltaTime;

            // Move the player to it's current position plus the movement.
            playerRigidbody.MovePosition(transform.position + movement);


            // Debug.Log("Tracking Player Movement");
            // RudderProperty rudderProperty = new RudderProperty();
            // rudderProperty.AddProperty("category", "Move");
            // rudderProperty.AddProperty("transform_position", transform.position.ToString());
            // RudderEvent rudderEvent = new RudderEventBuilder()
            // .SetEventName("PlayerMovement_Move")
            // .SetRudderProperty(rudderProperty)
            // .SetUserId("w240adxe7fseasn34m6u-4vpw1n7iac2jz9b135s7")
            // .Build();
            // CompleteProject.PlayerMovement.rudderInstance.Track(rudderEvent);

            // Dictionary<string, object> demoOptions = new Dictionary<string, object>() {
            //     {"category" , "Move" },
            //     {"transform_position" , transform.position.ToString()},
            //     {"insert_id" , rudderEvent.message.messageId}
            // };
            // Amplitude.Instance.logEvent("PlayerMovement_Move Direct", demoOptions);
        }


        void Turning()
        {
#if !MOBILE_INPUT
            // Create a ray from the mouse cursor on screen in the direction of the camera.
            Ray camRay = Camera.main.ScreenPointToRay(Input.mousePosition);

            // Create a RaycastHit variable to store information about what was hit by the ray.
            RaycastHit floorHit;

            // Perform the raycast and if it hits something on the floor layer...
            if (Physics.Raycast(camRay, out floorHit, camRayLength, floorMask))
            {
                // Create a vector from the player to the point on the floor the raycast from the mouse hit.
                Vector3 playerToMouse = floorHit.point - transform.position;

                // Ensure the vector is entirely along the floor plane.
                playerToMouse.y = 0f;

                // Create a quaternion (rotation) based on looking down the vector from the player to the mouse.
                Quaternion newRotatation = Quaternion.LookRotation(playerToMouse);

                // Set the player's rotation to this new rotation.
                playerRigidbody.MoveRotation(newRotatation);
            }
#else

            Vector3 turnDir = new Vector3(CrossPlatformInputManager.GetAxisRaw("Mouse X") , 0f , CrossPlatformInputManager.GetAxisRaw("Mouse Y"));

            if (turnDir != Vector3.zero)
            {
                // Create a vector from the player to the point on the floor the raycast from the mouse hit.
                Vector3 playerToMouse = (transform.position + turnDir) - transform.position;

                // Ensure the vector is entirely along the floor plane.
                playerToMouse.y = 0f;

                // Create a quaternion (rotation) based on looking down the vector from the player to the mouse.
                Quaternion newRotatation = Quaternion.LookRotation(playerToMouse);

                // Set the player's rotation to this new rotation.
                playerRigidbody.MoveRotation(newRotatation);
            }
#endif
        }


        void Animating(float h, float v)
        {
            // Create a boolean that is true if either of the input axes is non-zero.
            bool walking = h != 0f || v != 0f;

            // Tell the animator whether or not the player is walking.
            anim.SetBool("IsWalking", walking);
        }
    }
}