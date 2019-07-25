# rudder-client

## rudder-client-android
  * only SDK project written in Kotlin 
  
## rudder-android-sample-kotlin
  * sample project to use the library written in Kotlin
  
## client-unittest
  * simple Flask server to test the validity of the generated JSONs
  * install Flask
  * Command: `export FLASK_APP=validator.py`
  * Command: `flask run`
  * Optional: install ngrok
  * Optional command: `your/path/to/ngrok http 5000`
  * payload directory contains all schema JSONs for all sorts of events
  * schema file nomenclature : `{rl_event_type}-{rl_event.replace(" ", "-")}.schema.json`
  
## rudder-client-ios
  * SDK + sample project written in Swift
  
## rudder-client-unity
  * SDK + unit test cases written in C#
