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
 * Created by Ed George on 27 Dec 2014
 *
 */
package uk.co.edgeorgedev.notifj.notification;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;

import uk.co.edgeorgedev.notifj.notification.exception.NotificationException;
import uk.co.edgeorgedev.notifj.notification.exception.NotificationOperatingSystemException;

import com.google.code.jgntp.Gntp;
import com.google.code.jgntp.GntpApplicationInfo;
import com.google.code.jgntp.GntpClient;
import com.google.code.jgntp.GntpNotificationInfo;

/**
 * This class consists of methods to create 
 * <a href="http://www.growlforwindows.com/gfw/">Growl For Windows (GFW) Notification</a> 
 * messages on the Windows platform
 *
 * <p>The <tt>open()</tt> and <tt>show()</tt> methods of this class throw a <tt>NotificationException</tt>
 * if the creation of a GFW Notification fails.
 * 
 * <p>It should also be noted that <tt>open()</tt> will throw a <tt>NotificationOperatingSystemException</tt>
 * should it be called on a non-Windows operating system.
 *
 * @author  Ed George
 * @see     Notification
 * @since   1.1
 */
public class WindowsGrowlNotification implements Notification {

	private static final long DURATION = 3;
	private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
	
	private GntpClient mClient;
	private GntpApplicationInfo mApplicationInfo;
	private String application_name;

	/**
	 * Constructs an unregistered named GFW notification application
	 * 
	 * <p>The registration process occurs within the {@link #open()} method.
	 * 
	 * @param application_name name of application to be registered within GFW
	 *
	 */
	public WindowsGrowlNotification(String application_name){
		this.application_name = application_name;
	}

	/**
	 * Creates a new Growl Notification Transport Protocol (GNTP) client which is used to create notifications
	 * 
	 * @throws NotificationException if the client registration fails
	 * @throws NotificationOperatingSystemException if the system operating system is <i>not</i> Windows
	 */
	@Override
	public void open() throws NotificationException {
		if (!SystemUtils.IS_OS_WINDOWS)
			throw new NotificationOperatingSystemException("Operating System is not Windows");
		try{
			mApplicationInfo = Gntp.appInfo(application_name).build();
			mClient = Gntp.client(mApplicationInfo).forHost("localhost").build();
			mClient.register();
		}catch(Exception e){
			throw new NotificationException(e);
		}
	}

	/**
	 * Displays a GFW Notification with a given title and message
	 * 
	 * @param  title the title of the notification.
	 * @param  message the message body of the notification. 
	 * @throws NotificationException if an error occurs trying to display the message
	 */
	@Override
	public void show(String title, String message) throws NotificationException {
		try {
			GntpNotificationInfo notificationInfo = Gntp.notificationInfo(mApplicationInfo, application_name).build();
			mClient.notify(Gntp.notification(notificationInfo, title).text(message).build(), DURATION, TIME_UNIT);
		} catch (Exception e) {
			throw new NotificationException(e);
		}
	}

	/**
	 * Cleans-up object by shutting down GNTP client
	 * 
	 * @throws NotificationException if GNTP client shutdown fails
	 */
	@Override
	public void close() throws NotificationException {
		try {
			application_name = null;
			mClient.shutdown(DURATION, TIME_UNIT);
		} catch (Exception e) {
			throw new NotificationException(e);
		}
	}
}
