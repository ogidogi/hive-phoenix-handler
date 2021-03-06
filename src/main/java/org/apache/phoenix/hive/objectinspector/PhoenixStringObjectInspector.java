/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.phoenix.hive.objectinspector;

import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.Text;

/**
 * @author JeongMin Ju
 *
 */
public class PhoenixStringObjectInspector extends AbstractPhoenixObjectInspector<Text> implements StringObjectInspector {

	private boolean escaped;
	private byte escapeChar;

	public PhoenixStringObjectInspector(boolean escaped, byte escapeChar) {
		super(TypeInfoFactory.stringTypeInfo);
		this.escaped = escaped;
		this.escapeChar = escapeChar;
	}

	@Override
	public Object copyObject(Object o) {
		return o == null ? null : new String((String)o);
	}

	@Override
	public String getPrimitiveJavaObject(Object o) {
		return (String)o;
	}

	@Override
	public Text getPrimitiveWritableObject(Object o) {
		Text value = null;
		
		if (o != null) {
			try {
				value = new Text((String)o);
			} catch (Exception e) {
				logExceptionMessage(o, "STRING");
			}
		}
		
		return value;
	}

	public boolean isEscaped() {
		return escaped;
	}

	public byte getEscapeChar() {
		return escapeChar;
	}

}
