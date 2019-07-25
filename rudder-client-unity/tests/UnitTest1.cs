using com.rudderlabs.unity.library;
using com.rudderlabs.unity.library.Event;
using com.rudderlabs.unity.library.Event.Property;
using NUnit.Framework;

namespace Tests
{
    public class Tests
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void Test1()
        {
            Assert.Pass();
        }

        [Test]
        public void TestSimpleTrackEvent() 
        {
            RudderClient rudderClient = new RudderClient().GetInstance();
            rudderClient.Track(new RudderEventBuilder()
                .SetChannel("Test Channel")
                .SetEventName("Test Event Name")
                .SetRudderProperty(new TrackPropertyBuilder()
                    .SetCategory("Test Category")
                    .Build())
                .Build()
            );
            rudderClient.Flush();
        }

        [Test]
        public void TestSimpleScreenEvent()
        {
            RudderClient rudderClient = new RudderClient().GetInstance();
            rudderClient.Screen(new RudderEventBuilder()
                .SetChannel("Test Channel")
                .SetEventName("Test Event Name")
                .SetRudderProperty(new ScreenPropertyBuilder()
                    .SetName("Test Screen Name")
                    .Build())
                .Build()
            );
            rudderClient.Flush();
        }

        [Test]
        public void TestSimplePageEvent()
        {
            RudderClient rudderClient = new RudderClient().GetInstance();
            rudderClient.Page(new RudderEventBuilder()
                .SetChannel("Test Channel")
                .SetEventName("Test Event Name")
                .SetRudderProperty(new PagePropertyBuilder()
                    .SetUrl("http://www.example.com")
                    .Build())
                .Build()
            );
            rudderClient.Flush();
        }

        [Test]
        public void TestSimpleIdentify()
        {
            RudderClient rudderClient = new RudderClient().GetInstance();
            rudderClient.Identify(new RudderTraitsBuilder()
                .SetCity("New York")
                .SetCountry("USA")
                .SetPostalCode("ZA1234")
                .SetState("New York")
                .SetStreet("Wall Street")
                .SetAge(25)
                .SetBirthday("20-06-1998")
                .SetCompanyName("Rudder Labs")
                .SetCompanyId("--test--company--id--")
                .SetIndustry("Software Engg")
                .SetCreatedAt("20-05-1998 20:00:00Z")
                .SetDescription("Test Description")
                .SetEmail("example@gmail.com")
                .SetFirstName("Rudder")
                .SetGender("Female")
                .SetId("test--unique--id")
                .SetLastName("Labs")
                .SetName("Rudder Labs")
                .SetPhone("9876543210")
                .SetTitle("Mrs")
                .SetUserName("rudder_labs")
            );
            rudderClient.Flush();
        }
    }
}