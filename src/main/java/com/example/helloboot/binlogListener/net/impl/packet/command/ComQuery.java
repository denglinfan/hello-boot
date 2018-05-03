package com.example.helloboot.binlogListener.net.impl.packet.command;

import com.example.helloboot.binlogListener.common.glossary.column.StringColumn;
import com.example.helloboot.binlogListener.common.utils.MySqlConstants;
import com.example.helloboot.binlogListener.common.utils.ToStringBuilder;
import com.example.helloboot.binlogListener.io.util.XSerializer;

import java.io.IOException;

/**
 * 
 * @author Jingqi Xu
 */
public class ComQuery extends AbstractCommandPacket {
	//
	private static final long serialVersionUID = 1580858690926781520L;
	
	//
	private StringColumn sql;
	
	/**
	 * 
	 */
	public ComQuery() {
		super(MySqlConstants.COM_QUERY);
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("sql", sql).toString();
	}
	
	/**
	 * 
	 */
	public byte[] getPacketBody() throws IOException {
		final XSerializer ps = new XSerializer();
		ps.writeInt(this.command, 1);
		ps.writeFixedLengthString(this.sql);
		return ps.toByteArray();
	}
	
	/**
	 * 
	 */
	public StringColumn getSql() {
		return sql;
	}

	public void setSql(StringColumn sql) {
		this.sql = sql;
	}
}
