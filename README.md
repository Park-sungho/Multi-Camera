# Multi-Camera
 - Condition<p>
 <p>
  1. Enable Camera PIP Feature in the KERNEL.<br>
    The camera device needs to support the two path camera at the same time such as Camera PIP Feature.<p>
    (Supported Devices : Vega Iron2,,,)<p>
 <p>
    If the device is not supported the Camera PIP than the below error is happened in my case.<p>
    <hr>
       AndroidRuntime Caused by: java.lang.RuntimeException: Fail to connect to camera service<p>
       AndroidRuntime at android.hardware.Camera.<init>(Camera.java:xxx)<p>
       AndroidRuntime at android.hardware.Camera.open(Camera.java:364)<p>
    <hr><br>
    So, Camera PIP enabled in the device driver and then modified the camera permission on framework service.<p>
    Than the above error is not happened.<p>
<p>
  2. Implement the Multi-Camera Application (While Working...)<p>
    a) The application runs in service to support the camera service.<p>
    b) It's support the multi-recording at the same time.<p>
    c) Add the setting menu to set the resolution and framerate for testing.<p>
  <p>
  3. check encoder source (TODO)<p>
  <p>
  4. study Open-CV for funny preview (TODO)<p>
