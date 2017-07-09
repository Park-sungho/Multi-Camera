# Multi-Camera
 ## Condition
 <p>
  1. Enable Camera PIP Feature in the device driver.<br>
    The camera device needs to support the two path of camera interface at the same time.<br>
    (Supported Devices : Vega Iron2,,,)<br>
 <p>
    If the device is not supported the Camera PIP than the below error is happened in my case.
    <table border="1">
  <tr>
  <th>Test Case</td>
  <th>Video Container</td>
  <th>Video Encoder</td>
  <th>Audio Encoder</td>
  <th>Performance</td>
  </tr>
  <tr>
  <td>Mpeg4/H264</td>
  <td>MPEG4 (Base Media / Version 2)</td>
  <td>AVC (Advanced Video Codec, Baseline@L4.1)</td>
  <td>AAC (Advanced Audio Coding)</td>
  <td>1080P, 29fps, 12.2Mbps, High quality</td>
  </tr>
  <td>Mpeg4/MPEG4_SP</td>
  <td>MPEG4 (Base Media / Version 2)</td>
  <td>MPEG-4 Visual (Main@L4)</td>
  <td>AAC (Advanced Audio Coding)</td>
  <td>1080P, 14fps, 5.7Mbps, Normal quality</td>
  </tr>
  <td>3GPP/H264</td>
  <td>MPEG4 (3GPP Media Release 4)</td>
  <td>AVC (Advanced Video Code, Baseline@L4.1)</td>
  <td>AAC (Advanced Audio Coding)</td>
  <td>1080P, 30fps, 12.2Mbps, High quality</td>
  </tr>
  <td>WEBM/VP8</td>
  <td>WebM (Version 2)</td>
  <td>VP8</td>
  <td>AAC (Advanced Audio Coding)</td>
  <td>1080P, 30fps, 1,720kbps, Low quality</td>
  </tr>
  </table>
  
    After I enabled the Camera PIP feature in the device driver and modified the camera permission on the Android's frameworks.
    the above errors were not happened.<br>
  <p>
  2. Implement the Multi-Camera Application.<br>
    a) The multi-camera recording runs on the background by the android service to record previews on the screen.<br>
    b) It's support the multi-recording at the same time.<br>
    c) Add the setting menu to set the resolution, bitrate and framerate for testing.<br>
  <p>
  3. check codec source
  <table border="1">
  <tr>
  <th>Test Case</td>
  <th>Video Container</td>
  <th>Video Encoder</td>
  <th>Audio Encoder</td>
  <th>Performance</td>
  </tr>
  <tr>
  <td>Mpeg4/H264</td>
  <td>MPEG4 (Base Media / Version 2)</td>
  <td>AVC (Advanced Video Codec, Baseline@L4.1)</td>
  <td>AAC (Advanced Audio Coding)</td>
  <td>1080P, 29fps, 12.2Mbps, High quality</td>
  </tr>
  <td>Mpeg4/MPEG4_SP</td>
  <td>MPEG4 (Base Media / Version 2)</td>
  <td>MPEG-4 Visual (Main@L4)</td>
  <td>AAC (Advanced Audio Coding)</td>
  <td>1080P, 14fps, 5.7Mbps, Normal quality</td>
  </tr>
  <td>3GPP/H264</td>
  <td>MPEG4 (3GPP Media Release 4)</td>
  <td>AVC (Advanced Video Code, Baseline@L4.1)</td>
  <td>AAC (Advanced Audio Coding)</td>
  <td>1080P, 30fps, 12.2Mbps, High quality</td>
  </tr>
  <td>WEBM/VP8</td>
  <td>WebM (Version 2)</td>
  <td>VP8</td>
  <td>AAC (Advanced Audio Coding)</td>
  <td>1080P, 30fps, 1,720kbps, Low quality</td>
  </tr>
  </table>
