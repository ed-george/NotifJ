/*
 *
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Ed George
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Created by Ed George on 26 Dec 2014
 *
 */

package uk.co.edgeorgedev.notifj.notification;

import java.net.URL;

import uk.co.edgeorgedev.notifj.notification.exception.NotificationException;

/**
 * An object that implements the Notification interface generates visible messages to a user
 * with a specified title and message body.
 * <p>
 * For example, to create a message within OSX using <tt>OSXGrowlNotification</tt>:
 * <pre>
 * OSXGrowlNotification notif = new OSXGrowlNotification("Test Application");
 * notif.open();
 * notif.show("Hello", "World");</pre>
 * <p>
 *
 * @author  Ed George
 * @see     OSXGrowlNotification
 * @see     WindowsGrowlNotification
 * @since   1.0
 */
public interface Notification {

	/**
	 * Completes any setup required to generate messages. 
	 * @throws NotificationException if the setup required for creating notifications fails.
	 */
	public abstract void open() throws NotificationException;
	
	/**
	 * Displays a notification to the user.
	 *
	 * @param title the title of the notification.
	 * @param message the message body of the notification.
	 * 
	 * @throws NotificationException if the creation or displaying of any notification(s) fails.
	 */
	public abstract void show(String title, String message) throws NotificationException;
	
	/**
	 * Completes any cleanup required once a Notification is no longer to be used.
	 * @throws NotificationException if the cleanup fails.
	 */
	public abstract void close() throws NotificationException;
	
	//TODO: add java docs since 1.0 to others and 1.2 to this
	public abstract Notification sticky();
	public abstract Notification sticky(boolean sticky);
	public abstract boolean isSticky();

	public abstract Notification callbackUrl(URL callback_url);
	public abstract Notification removeCallback();
	public abstract URL getCallbackUrl();	

}
