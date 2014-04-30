/*
The MIT License (MIT)

Copyright (c) 2014 Stephen Yingling

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

 */
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Stephen Yingling
 */
public class B64EncoderTest {

    @Test
    public void testByteArrEncoding(){
        B64Converter con = new B64Converter();
        byte[] b = new String("Man").getBytes();
        byte[] res = con.encode(b);

        assertNotNull(res);
        assertEquals(4,res.length);
        assertEquals((byte)'T',res[0]);
        assertEquals((byte)'W',res[1]);
        assertEquals((byte)'F',res[2]);
        assertEquals((byte)'u',res[3]);

    }

    @Test
     public void testByteArrEncPad2(){
        B64Converter con = new B64Converter();
        byte[] b = new String("Mana").getBytes();
        byte[] res = con.encode(b);

        char[] expected = {'T','W','F','u','Y','Q','=','='};
        assertNotNull(res);
        assertEquals(8,res.length);

        for(byte i=0; i<expected.length; i++){
            assertEquals((byte)expected[i],res[i]);
        }
    }

    @Test
    public void testByteArrEncPad1(){
        B64Converter con = new B64Converter();
        byte[] b = new String("ManaM").getBytes();
        byte[] res = con.encode(b);

        char[] expected = {'T','W','F','u','Y','U','0','='};
        assertNotNull(res);
        assertEquals(8,res.length);

        for(byte i=0; i<expected.length; i++){
            assertEquals((byte)expected[i],res[i]);
        }
    }

    @Test
    public void testEmptyByteArr(){
        B64Converter con = new B64Converter();
        byte[] b = null;
        byte[] res = con.encode(b);
        assertNull(res);
    }

    @Test
    public void testDecodeNoPad(){
        B64Converter con = new B64Converter();
        byte[] b = new String("TWFu").getBytes();
        byte[] res = con.decode(b);

        assertNotNull(res);
        assertEquals('M', res[0]);
        assertEquals('a', res[1]);
        assertEquals('n', res[2]);

    }

    @Test
    public void testDecodePad1(){
        B64Converter con = new B64Converter();
        byte[] b = new String("TWFuYQ==").getBytes();
        byte[] res = con.decode(b);

        assertNotNull(res);
        assertEquals('M', res[0]);
        assertEquals('a', res[1]);
        assertEquals('n', res[2]);
        assertEquals('a', res[3]);
    }

    @Test
    public void testDecodePad2(){
        B64Converter con = new B64Converter();
        byte[] b = new String("TWFuYU0=").getBytes();
        byte[] res = con.decode(b);

        assertNotNull(res);
        assertEquals('M', res[0]);
        assertEquals('a', res[1]);
        assertEquals('n', res[2]);
        assertEquals('a', res[3]);
        assertEquals('M', res[4]);
    }

    @Test
    public void testShortEncode(){
        B64Converter con = new B64Converter();
        byte[] b = new String("M").getBytes();
        byte[] res = con.encode(b);

        Assert.assertNotNull(res);
        assertEquals('T',res[0]);
        assertEquals('Q',res[1]);
        assertEquals('=',res[2]);
        assertEquals('=',res[3]);
    }

    @Test
    public void testShortDecode(){
        B64Converter con = new B64Converter();
        byte[] b = new String("TQ==").getBytes();
        byte[] res = con.decode(b);

        Assert.assertNotNull(res);
        assertEquals('M',res[0]);
    }
}

