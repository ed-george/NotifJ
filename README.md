NotifJ
=======

A Java library to provide an easy method for creating desktop notifications.

Please check the [javadocs](http://ed-george.github.io/NotifJ/apidoc/index.html) to learn more.

##Examples

**Create and show a simple Growl notification**

```java
public void notification(String title, String message) throws NotificationException{
	GrowlNotification notification = new GrowlNotification("Test App");
	notification.open();
	notification.show(title, message);
}
```
**Customise the Growl notification**

```java
// Show notification until dismissed by user
notification.sticky();

// Add callback url to notification - opens browser on click
notification.callbackUrl(new URL("http://example.com"));

// Show notification with new settings
notification.show(title, message);
```

##Support
####Currently Supported:
+ Growl for Mac OSX
  + Successfully tested on Growl 2.1.3 and OS X 10.10.1
  + **Required:** OSX 10.11.2 El Capitan - [Download legacy Java](https://support.apple.com/kb/DL1572?locale=en_US)

####Yet to be fully supported:
+ Growl for Windows
  + Implemented but currently untested
  + No support for `sticky()` or `callbackURL()`
 

##License 
```
The MIT License (MIT)

Copyright (c) 2014 Ed George

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
