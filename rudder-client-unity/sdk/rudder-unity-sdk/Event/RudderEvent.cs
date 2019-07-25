using System;
using UnityEngine;
using System.Collections.Generic;
using com.rudderlabs.unity.library.Event.Property;
using Newtonsoft.Json;

namespace com.rudderlabs.unity.library.Event
{
    public class RudderEvent
    {
        [JsonProperty(PropertyName = "rl_message")]
        internal RudderMessage message = new RudderMessage();

        internal void SetEventType(string eventType)
        {
            message.type = eventType;
        }

        internal void SetRudderTraits(RudderTraits rudderTraits)
        {
            message.context.traits = rudderTraits;
        }
    }

    public class RudderMessage
    {
        [JsonProperty(PropertyName = "rl_channel")]
        internal string channel;
        [JsonProperty(PropertyName = "rl_context")]
        internal RudderContext context = new RudderContext();
        [JsonProperty(PropertyName = "rl_type")]
        internal string type;
        [JsonProperty(PropertyName = "rl_action")]
        internal string action;
        [JsonProperty(PropertyName = "rl_message_id")]
        internal string messageId = System.Guid.NewGuid().ToString();
        [JsonProperty(PropertyName = "rl_timestamp")]
        internal string timestamp = DateTime.UtcNow.ToString("u");
        [JsonProperty(PropertyName = "rl_anonymous_id")]
        internal string anonymousId;
        [JsonProperty(PropertyName = "rl_user_id")]
        internal string userId;
        [JsonProperty(PropertyName = "rl_event")]
        internal string eventName;
        [JsonProperty(PropertyName = "rl_properties")]
        internal RudderProperty properties;
        [JsonProperty(PropertyName = "rl_integrations")]
        internal List<string> integrations = new List<string>();

        internal RudderMessage()
        {
            integrations.Add("rudderlabs");
        }
    }

    public class RudderContext
    {
        [JsonProperty(PropertyName = "rl_app")]
        internal RudderApp rudderApp = new RudderApp();
        [JsonProperty(PropertyName = "rl_traits")]
        internal RudderTraits traits;
        [JsonProperty(PropertyName = "rl_library")]
        internal RudderLibraryInfo libraryInfo = new RudderLibraryInfo();
        [JsonProperty(PropertyName = "rl_os")]
        internal RudderOsInfo osInfo = new RudderOsInfo();
        [JsonProperty(PropertyName = "rl_screen")]
        internal RudderScreenInfo screenInfo = new RudderScreenInfo();
        [JsonProperty(PropertyName = "rl_user_agent")]
        internal string userAgent = "RudderUnitySdk";
        [JsonProperty(PropertyName = "rl_locale")]
        internal string locale = System.Globalization.CultureInfo.CurrentCulture.DisplayName;
        [JsonProperty(PropertyName = "rl_device")]
        internal RudderDeviceInfo deviceInfo = new RudderDeviceInfo();
        [JsonProperty(PropertyName = "rl_network")]
        internal RudderNetwork network = new RudderNetwork();
    }

    class RudderApp
    {
        [JsonProperty(PropertyName = "rl_build")]
        internal string build;
        [JsonProperty(PropertyName = "rl_name")]
        internal string name;
        [JsonProperty(PropertyName = "rl_id")]
        internal string nameSpace;
        [JsonProperty(PropertyName = "rl_version")]
        internal string version;
    }

    class RudderLibraryInfo
    {
        [JsonProperty(PropertyName = "rl_name")]
        internal string name = "rudder-unity-client";
        [JsonProperty(PropertyName = "rl_version")]
        internal string version = "1.0.0";
    }

    class RudderOsInfo
    {
        [JsonProperty(PropertyName = "rl_name")]
        internal string name = SystemInfo.operatingSystem.Split()[0];
        [JsonProperty(PropertyName = "rl_version")]
        internal string version = SystemInfo.operatingSystem.Split()[2];
    }

    class RudderScreenInfo
    {
        [JsonProperty(PropertyName = "rl_density")]
        internal int density = (int) Screen.dpi;
        [JsonProperty(PropertyName = "rl_width")]
        internal int width = Screen.width;
        [JsonProperty(PropertyName = "rl_height")]
        internal int height = Screen.height;
    }

    class RudderDeviceInfo
    {
        [JsonProperty(PropertyName = "rl_id")]
        internal string id = SystemInfo.deviceUniqueIdentifier;
        [JsonProperty(PropertyName = "rl_manufacturer")]
        internal string manufacturer = SystemInfo.deviceModel;
        [JsonProperty(PropertyName = "rl_model")]
        internal string model = SystemInfo.deviceModel;
        [JsonProperty(PropertyName = "rl_name")]
        internal string name = SystemInfo.deviceName;
    }

    class RudderNetwork
    {
        [JsonProperty(PropertyName = "rl_carrier")]
        internal string carrier;
    }
}
