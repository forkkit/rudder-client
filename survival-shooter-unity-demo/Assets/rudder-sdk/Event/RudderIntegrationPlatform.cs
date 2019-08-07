using System;
namespace com.rudderlabs.unity.library.Event
{
    public class RudderIntegrationPlatform
    {
        private RudderIntegrationPlatform(string _value)
        {
            value = _value;
        }
        public string value { get; private set; }

        public static RudderIntegrationPlatform RUDDER_LABS { get { return new RudderIntegrationPlatform("rudderlabs"); } }
        public static RudderIntegrationPlatform GOOGLE_ANALYTICS { get { return new RudderIntegrationPlatform("ga"); } }
        public static RudderIntegrationPlatform AMPLITUDE { get { return new RudderIntegrationPlatform("amplitude"); } }
    }
}
