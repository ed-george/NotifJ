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
 * The subclass of {@link NotificationException} that indicates a notification was created for an 
 * operating system that differed from that of the intended. 
 * @author Ed George
 * @since  1.0
 *
 */
public class NotificationOperatingSystemException extends NotificationException{

	/**
	 * Constructs a <code>NotificationOperatingSystemException</code> object.
	 */
	public NotificationOperatingSystemException(){
		super();
	}

	/**
	 * Constructs a <code>NotificationOperatingSystemException</code> object.
	 * @param message the message to display for this <code>NotificationOperatingSystemException</code>
	 * @param cause the underlying reason for this <code>NotificationOperatingSystemException</code>
	 */
	public NotificationOperatingSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a <code>NotificationOperatingSystemException</code> object.
	 * @param message the message to display for this <code>NotificationOperatingSystemException</code>
	 */
	public NotificationOperatingSystemException(String message) {
		super(message);
	}

	/**
	 * Constructs a <code>NotificationOperatingSystemException</code> object.
	 * @param cause the underlying reason for this <code>NotificationOperatingSystemException</code>
	 */
	public NotificationOperatingSystemException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 3062366854156794491L;
}
