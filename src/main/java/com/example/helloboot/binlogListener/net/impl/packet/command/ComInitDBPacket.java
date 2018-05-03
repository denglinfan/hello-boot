/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
public class ComInitDBPacket extends AbstractCommandPacket {
	//
	private static final long serialVersionUID = 449639496684376511L;
	
	//
	private StringColumn databaseName;
	
	/**
	 * 
	 */
	public ComInitDBPacket() {
		super(MySqlConstants.COM_INIT_DB);
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("databaseName", databaseName).toString();
	}
	
	/**
	 * 
	 */
	public byte[] getPacketBody() throws IOException {
		final XSerializer ps = new XSerializer();
		ps.writeInt(this.command, 1);
		ps.writeFixedLengthString(this.databaseName);
		return ps.toByteArray();
	}
	
	/**
	 * 
	 */
	public StringColumn getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(StringColumn databaseName) {
		this.databaseName = databaseName;
	}
}
