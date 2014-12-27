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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.SystemUtils;

import uk.co.edgeorgedev.notifj.notification.exception.NotificationException;
import uk.co.edgeorgedev.notifj.notification.exception.NotificationOperatingSystemException;

/**
 * This class consists of methods to create <a href="http://growl.info">Growl Notification</a> messages on the OS X platform
 *
 * <p>The <tt>open()</tt> and <tt>show()</tt> methods of this class throw a <tt>NotificationException</tt>
 * if the creation of a Growl Notification fails.
 * 
 * <p>It should also be noted that <tt>open()</tt> will throw a <tt>NotificationOperatingSystemException</tt>
 * should it be called on a non-OSX operating system.
 *
 * @author  Ed George
 * @see     Notification
 * @since   1.0
 */
public class OSXGrowlNotification implements Notification {

	private static final String GROWL_APPLICATION = "com.Growl.GrowlHelperApp";
	private static final String SYSTEM_EVENTS = "System Events";
	private static final String DEFAULT_ICON_NAME = "Script Editor";

	private ScriptEngine mScriptEngine;
	private String application_name;

	/**
	 * Constructs an unregistered named Growl notification application
	 * 
	 * <p>The registration process occurs within the {@link #open()} method.
	 * 
	 * @param application_name name of application to be registered within Growl
	 *
	 */
	public OSXGrowlNotification(String application_name) {
		if(application_name == null)
			throw new IllegalArgumentException("Application name cannot be null");
		this.application_name = application_name;
	}

	/**
	 * Creates new ApppleScript Engine which is used to create notifications
	 * 
	 * @throws NotificationOperatingSystemException if the system operating system is <i>not</i> Mac OSX
	 */
	@Override
	public void open() throws NotificationException {
		if(!SystemUtils.IS_OS_MAC_OSX)
			throw new NotificationOperatingSystemException("Operating System is not Mac OSX");

		ScriptEngineManager engineManager = new ScriptEngineManager();
		mScriptEngine = engineManager.getEngineByName("AppleScript");
	}


	/**
	 * Displays a Growl Notification with a given title and message
	 * 
	 * <p>Should the application, specified by the {@code application_name}, not be registered
	 * within the Growl Notification settings, it is first registered before a message is displayed.
	 * 
	 * <p>As of Growl Version 2.1.3, this method correctly generates and displays notifications.
	 * 
	 * @param  title the title of the notification.
	 * @param  message the message body of the notification. 
	 * @throws NotificationException if no Script Engine is available or no Growl process
	 *         is running. 
	 */
	@Override
	public void show(String title, String message) throws NotificationException{
		if (mScriptEngine == null) {
			throw new NotificationException("No AppleScriptEngine available - Have you called open()?");
		}

		if(!isGrowlEnabled()){
			throw new NotificationException("No Growl process located");
		}

		runScript(messageScript(title, message).build(), 0L);

	}

	/**
	 * Cleans-up object by resetting the script engine and application name 
	 */
	@Override
	public void close() throws NotificationException {
		mScriptEngine = null;
		application_name = null;
	}

	/**
	 * Determines whether Growl Notifications are enabled on the machine
	 *
	 * @return <tt>true</tt> if Growl Notifications are enabled
	 */
	public boolean isGrowlEnabled() {
		String script = getGrowlEnabledScript().build();
		long count = runScript(script, 0L);
		return count > 0;
	}

	/*
	 * Generate AppleScript to test if Growl Notifications are enabled
	 */
	private ScriptBuilder getGrowlEnabledScript() {
		return script().add("tell application ").quote(SYSTEM_EVENTS).newLine(
				"return count of (every process whose bundle identifier is ").quote(
						GROWL_APPLICATION).add(") > 0").newLine("end tell");
	}

	/*
	 * Generate AppleScript to generate Growl Notification
	 */
	private ScriptBuilder messageScript(String title, String message) {
		return script()
				.add("tell application ").quote(SYSTEM_EVENTS)
				.newLine("set isRunning to (count of (every process whose bundle identifier is ").quote(GROWL_APPLICATION).add(")) > 0")
				.newLine("end tell")
				.newLine("if isRunning then")
				.newLine("tell application id ").quote(GROWL_APPLICATION)
				.newLine("set the allNotificationsList to ").cont().newLine().array(title)
				.newLine("set the enabledNotificationsList to ").cont().newLine().array(title)
				.newLine("register as application ").cont()
				.newLine().quote(this.application_name).add(" all notifications allNotificationsList ").cont()
				.newLine("default notifications enabledNotificationsList ").cont()
				.newLine("icon of application ").quote(DEFAULT_ICON_NAME)
				.newLine("notify with name ").cont()
				.newLine().quote(title).add(" title ").cont()
				.newLine().quote(title).add(" description ").cont()
				.newLine().quote(message).add(" application name ").quote(this.application_name)
				.newLine("end tell")
				.newLine("end if");
	}

	/*
	 * Return new ScriptBuilder
	 */
	private ScriptBuilder script() {
		return new ScriptBuilder();
	}

	/*
	 * Run AppleScript and specify a default value on failure
	 */
	@SuppressWarnings("unchecked")
	private <T> T runScript(String script, T defaultValue) {
		try {
			return (T) runScript(script);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/*
	 * Run AppleScript using the AppleScript Script Engine
	 */
	private Object runScript(String script) throws Exception {
		try {
			return mScriptEngine.eval(script, mScriptEngine.getContext());
		} catch (Exception e) {
			throw new Exception("Could not execute script", e);
		}
	}

	/*
	 * Helper class to Build valid AppleScript
	 */
	private class ScriptBuilder {

		StringBuilder builder = new StringBuilder();

		/*
		 * Append string to current line
		 */
		public ScriptBuilder add(String text) {
			this.builder.append(text);
			return this;
		}

		/*
		 * Append quoted text to current line
		 */
		public ScriptBuilder quote(String text) {
			this.builder.append("\"");
			this.builder.append(text);
			this.builder.append("\"");
			return this;
		}

		/*
		 * Append continue symbol to current line
		 */
		public ScriptBuilder cont(){
			this.builder.append("¬");
			return this;
		}

		/*
		 * Append new line
		 */
		public ScriptBuilder newLine() {
			this.builder.append("\n");
			return this;
		}

		/*
		 * Append new line with text
		 */
		public ScriptBuilder newLine(String text) {
			this.builder.append("\n");
			this.builder.append(text);
			return this;
		}

		/*
		 * Append array to current line
		 */
		public ScriptBuilder array(String... values) {
			this.builder.append("{");

			for (int i = 0; i < values.length; i++) {
				if (i > 0) {
					this.builder.append(", ");
				}

				this.builder.append("\"");
				this.builder.append(values[i]);
				this.builder.append("\"");
			}

			this.builder.append("}");
			return this;
		}
		
		/*
		 * Return built AppleScript
		 */
		public String build() {
			return this.builder.toString();
		}

	}

}
