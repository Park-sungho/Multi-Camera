# Multi-Camera
 - Condition
  1) Enable Camera PIP Feature in the KERNEL.
    The camera device needs to support the two path camera at the same time such as Camera PIP Feature.
    (Supported Devices : Vega Iron2,,,)
 
    If the device is not supported the Camera PIP than the below error is happened in my case.
       AndroidRuntime Caused by: java.lang.RuntimeException: Fail to connect to camera service
       AndroidRuntime at android.hardware.Camera.<init>(Camera.java:xxx)
       AndroidRuntime at android.hardware.Camera.open(Camera.java:364)
    So, Camera PIP enabled in the device driver and then modified the camera permission on framework service.
    Than the above error is not happened.

  2) Implement the Multi-Camera Application (While Working...)
    a) The application runs in service to support the camera service.
    b) It's support the multi-recording at the same time.
    c) Add the setting menu to set the resolution and framerate for testing.
  
  3) check encoder source (TODO)
  
  4) study Open-CV for funny preview (TODO)
