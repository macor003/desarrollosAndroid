/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Date;

/**
 */
public class NTPServer {

	/**
	 * Field TIMEOUT. (value is 2000)
	 */
	private static final int TIMEOUT = 2000;
	/**
	 * Field ip.
	 */
	private String ip;
	/**
	 * Field localtime.
	 */
	private double localtime;
	/**
	 * Field remotetime.
	 */
	private double remotetime;

	/**
	 * Field localDate.
	 */
	private Date localDate;
	/**
	 * Field remoteDate.
	 */
	private Date remoteDate;

	/**
	 * Field offset.
	 */
	private double offset;

	/**
	 * Constructor for NTPServer.
	 * 
	 * @param ip
	 *            String
	 */
	public NTPServer(String ip) {
		super();
		this.ip = ip;

	}

	/**
	 * Method requestRemoteTime.
	 * 
	 * @throws IOException
	 */
	public void requestRemoteTime() throws IOException {
		DatagramSocket socket = new DatagramSocket();
		try {
			socket.setSoTimeout(TIMEOUT);
			InetAddress address = InetAddress.getByName(this.ip);
			NtpMessage ntptmp = new NtpMessage();
			byte[] buf = ntptmp.toByteArray();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 123);

			NtpMessage.encodeTimestamp(packet.getData(), 40, (System.currentTimeMillis() / 1000.0) + 2208988800.0);

			socket.send(packet);

			// Get response
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			// Immediately record the incoming timestamp
			double destinationTimestamp = (System.currentTimeMillis() / 1000.0) + 2208988800.0;

			// Process response
			NtpMessage msg = new NtpMessage(packet.getData());

			this.remotetime = msg.transmitTimestamp;
			this.localtime = msg.originateTimestamp;

			this.remoteDate = NtpMessage.timestampToDate(this.remotetime);
			this.localDate = NtpMessage.timestampToDate(this.localtime);

			// Corrected, according to RFC2030 errata
			/*
			 * setRoundTripDelay((destinationTimestamp-msg.originateTimestamp) -
			 * (msg.transmitTimestamp-msg.receiveTimestamp));
			 */

			setOffset((-1
					* ((msg.receiveTimestamp - msg.originateTimestamp) + (msg.transmitTimestamp - destinationTimestamp)) / 2) / 60);

			socket.close();
			msg = null;
			ntptmp = null;
		} catch (SocketTimeoutException e) {
			socket.close();
			setOffset(Double.MAX_VALUE);
			throw new IOException();
		}
	}

	/**
	 * 
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * @return the localtime
	 */
	public double getLocaltime() {
		return localtime;
	}

	/**
	 * @param localtime
	 *            the localtime to set
	 */
	public void setLocaltime(double localtime) {
		this.localtime = localtime;
	}

	/**
	 * 
	 * @return the remotetime
	 */
	public double getRemotetime() {
		return remotetime;
	}

	/**
	 * @param remotetime
	 *            the remotetime to set
	 */
	public void setRemotetime(double remotetime) {
		this.remotetime = remotetime;
	}

	/**
	 * 
	 * @return the offset
	 */
	public double getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(double offset) {
		this.offset = offset;
	}

	/**
	 * 
	 * @return the localDate
	 */
	public Date getLocalDate() {
		return localDate;
	}

	/**
	 * 
	 * @return the remoteDate
	 */
	public Date getRemoteDate() {
		return remoteDate;
	}

}
