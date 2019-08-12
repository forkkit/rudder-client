using UnityEngine;
using System.Collections.Generic;
using System;

namespace CompleteProject
{
    public class FPSManager : MonoBehaviour
    {
        float deltaTime = 0.0f;
        int currentEpochTime = (int)(DateTime.UtcNow - new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc)).TotalSeconds;
        int timeFreeze = 0;
        bool check = false;

        void Update()
        {
            deltaTime += (Time.unscaledDeltaTime - deltaTime) * 0.1f;
            if (timeFreeze == 0 || timeFreeze == currentEpochTime)
            {
                check = true;
                timeFreeze = currentEpochTime + 10;
            }
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

            // if (check)
            // {
            Dictionary<string, object> FPSData = new Dictionary<string, object>() {
                {"category" , "Comparision" },
                {"FPS Value" , fps}
            };
            Amplitude.Instance.logEvent("FPS Direct without sdk", FPSData);
            check = false;
            // }
        }
    }
}
