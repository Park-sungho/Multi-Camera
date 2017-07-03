# Multi-Camera
 ## Condition<p>
 <p>
  1. Enable Camera PIP Feature in the device driver.<br>
    The camera device needs to support the two path of camera interface at the same time.<br>
    (Supported Devices : Vega Iron2,,,)<br>
 <p>
    If the device is not supported the Camera PIP than the below error is happened in my case.<br>
    <hr>
       AndroidRuntime Caused by: java.lang.RuntimeException: Fail to connect to camera service<br>
       AndroidRuntime at android.hardware.Camera.<init>(Camera.java:)<br>
       AndroidRuntime at android.hardware.Camera.open(Camera.java:)<br>
    <hr>
    So, Camera PIP enabled in the device driver and then modified the camera permission on framework service.<br>
    Than the above error is not happened.<br>
  <p>
  2. Implement the Multi-Camera Application (While Working...)<br>
    a) The application runs in service to support the camera service.<br>
    b) It's support the multi-recording at the same time.<br>
    c) Add the setting menu to set the resolution and framerate for testing.<br>
  <p>
  3. check encoder source (TODO)<br>
  <p>
  4. study Open-CV for funny preview (TODO)<br>
