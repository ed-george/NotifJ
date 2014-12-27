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

package uk.co.edgeorgedev.notifj.notification.exception;

/**
 * A subclass of {@link java.lang.Exception} that indicates an error occurred 
 * within a notification class.
 * @author Ed George
 * @since  1.0
 */
public class NotificationException extends Exception{

	/**
	 * Constructs a <code>NotificationException</code> object.
	 */
	public NotificationException(){
		super();
	}

	/**
	 * Constructs a <code>NotificationException</code> object.
	 * @param message the message to display for this <code>NotificationException</code>
	 * @param cause the underlying reason for this <code>NotificationException</code>
	 */
	public NotificationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a <code>NotificationException</code> object.
	 * @param message the message to display for this <code>NotificationException</code>
	 */
	public NotificationException(String message) {
		super(message);
	}

	/**
	 * Constructs a <code>NotificationException</code> object.
	 * @param cause the underlying reason for this <code>NotificationException</code>
	 */
	public NotificationException(Throwable cause) {
		super(cause);
	}
	
	private static final long serialVersionUID = 9071128760689753837L;
}
