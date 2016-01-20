/*
 * ===========================================
 * Java Pdf Extraction Decoding Access Library
 * ===========================================
 *
 * Project Info:  http://www.idrsolutions.com
 * Help section for developers at http://www.idrsolutions.com/support/
 *
 * (C) Copyright 1997-2016 IDRsolutions and Contributors.
 *
 * This file is part of JPedal/JPDF2HTML5
 *
     This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


 *
 * ---------------
 * RandomAccessDataBuffer.java
 * ---------------
 */

package org.jpedal.io;

import java.io.IOException;

public class RandomAccessDataBuffer implements RandomAccessBuffer {

  private byte[] data;
  private long pointer;

  public RandomAccessDataBuffer(final byte[] data)
  {
    this.data = data;
    this.pointer = -1;
  }

  @Override
  public long getFilePointer() throws IOException {
    return pointer;
  }

  @Override
  public void seek(final long pos) throws IOException {
    if ( checkPos(pos) ) {
      this.pointer = pos;
    } else {
      throw new IOException("Position out of bounds");
    }
  }

  @Override
  public void close() throws IOException {
    this.data = null;
    this.pointer = -1;
  }

  @Override
  public long length() throws IOException {
  
    if (data!=null) {
      return data.length;
    } else {
      throw new IOException("Data buffer not initialized.");
    }
  }

  @Override
  public int read() throws IOException {
    if (checkPos(this.pointer)) {
      return b2i(this.data[(int)pointer++]);
    } else {
      return -1;
    }
  }

  private int peek() throws IOException {
    if (checkPos(this.pointer)) {
      return b2i(this.data[(int)pointer]);
    } else {
      return -1;
    }
  }

  /**
   * return next line (returns null if no line)
   */
  @Override
  public String readLine() throws IOException {

        if (this.pointer >= this.data.length - 1) {
            return null;
        } else {

            final StringBuilder buf = new StringBuilder();
            int c;
            while ((c = read()) >= 0) {
                if ((c == 10) || (c == 13)) {
                    if (((peek() == 10) || (peek() == 13)) && (peek() != c)) {
                        read();
                    }
                    break;
                }
                buf.append((char) c);
            }
            return buf.toString();
        }
    }

  @Override
  public int read(final byte[] b) throws IOException {
    if (data==null) {
        throw new IOException("Data buffer not initialized.");
    }
    if (pointer<0 || pointer>=data.length) {
        return -1;
    }
    final int length=Math.min(b.length, data.length-(int)pointer);
    for (int i=0; i<length; i++) {
      b[i] = data[ (int)pointer++ ];
    }
    return length;
  }

  private static int b2i(final byte b) {
    if (b>=0) {
        return b;
    }
    return 256+b;
  }

  private boolean checkPos(final long pos) throws IOException {
    return ( (pos>=0) && (pos<length()) );
  }

/* returns the byte data*/
@Override
public byte[] getPdfBuffer(){
	return data;
}
}

