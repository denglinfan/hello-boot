package com.example.helloboot.springBoot.binlogListener.net.impl.packet.command;

import com.example.helloboot.springBoot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.springBoot.binlogListener.common.utils.ToStringBuilder;

import java.io.IOException;

/**
 * 
 * @author Jingqi Xu
 */
public class ComQuit extends AbstractCommandPacket {
	//
	private static final long serialVersionUID = 4569517082177697955L;

	/**
	 * 
	 */
	public ComQuit() {
		super(MySqlConstants.COM_QUIT);
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
	
	/**
	 * 
	 */
	public byte[] getPacketBody() throws IOException {
		return new byte[0];
	}
}
