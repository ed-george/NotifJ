/**
 * GrowlNotification.java
 * NotifJ
 *
 * Created by Ed George on 30 Dec 2014
 *
 */
package uk.co.edgeorgedev.notifj.notification;

import java.net.URL;

import org.apache.commons.lang3.SystemUtils;

import uk.co.edgeorgedev.notifj.notification.exception.NotificationException;
import uk.co.edgeorgedev.notifj.notification.exception.NotificationOperatingSystemException;

/**
 * @author edgeorge
 *
 */
public class GrowlNotification implements Notification{
	
	public Notification notification;
	public String application_name;
	/**
	 * @throws NotificationException 
	 * 
	 */
	public GrowlNotification(String application_name) throws NotificationException {
		this.application_name = application_name;
		if(SystemUtils.IS_OS_WINDOWS){
			notification = new WindowsGrowlNotification(application_name);
		}else if (SystemUtils.IS_OS_MAC_OSX){
			notification = new OSXGrowlNotification(application_name);
		}else{
			throw new NotificationOperatingSystemException("Only Windows and Mac OS X operrating systems are supported");
		}
	}
	

	@Override
	public void open() throws NotificationException {
		notification.open();
	}


	@Override
	public void show(String title, String message) throws NotificationException {
		notification.show(title, message);
	}


	@Override
	public void close() throws NotificationException {
		notification.close();	
	}


	@Override
	public GrowlNotification sticky() {
		notification.sticky();
		return this;
	}


	@Override
	public Notification sticky(boolean sticky) {
		notification.sticky(sticky);
		return this;
	}

	@Override
	public boolean isSticky() {
		return notification.isSticky();
	}

	@Override
	public Notification callbackUrl(URL callback_url) {
		notification.callbackUrl(callback_url);
		return this;
	}


	@Override
	public Notification removeCallback() {
		notification.removeCallback();
		return this;
	}

	@Override
	public URL getCallbackUrl() {
		return notification.getCallbackUrl();
	}

}
