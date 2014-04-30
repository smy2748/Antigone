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

/**
 * A wrapper for easy String conversion
 *
 * @author Stephen Yingling
 */
public class B64StringConverter {

    protected B64Converter converter;

    /**
     * Create a new converter with the default mappings
     */
    public B64StringConverter(){
        converter = new B64Converter();
    }

    /**
     * Create a new String converter using an existing B64Converter
     * @param c The B64Converter to use
     */
    public B64StringConverter(B64Converter c){
        converter = c;
    }

    /**
     * Create a new String converter using the given B64Mapping
     * @param dm The B64Mapping to be used
     */
    public B64StringConverter(B64Mapping dm){
        converter = new B64Converter(dm);
    }

    /**
     * Encode a String and get a String back
     * @param s The string to encode
     * @return A B64 encoded string
     */
    public String encode(String s){
        byte[] res = converter.encode(s.getBytes());
        return new String(res);
    }

    /**
     * Decode a String and get a String back
     * @param s The string to decode
     * @return The B64 decoded String
     */
    public String decode(String s){
        byte[] res = converter.decode(s.getBytes());
        return new String(res);
    }

    /**
     * Encode a byte[] and get a String in return
     * @param b The byte[] to encode
     * @return A String representing the B64 encoded byte[]
     */
    public String encode(byte[] b){
        return new String(converter.encode(b));
    }

    /**
     * Decode a byte[] and get a String in return
     * @param b The byte[] to decode
     * @return A String representing the B64 decoded byte[]
     */
    public String decode(byte[] b){
        return new String(converter.decode(b));
    }

    /**
     * Encode a String and get a byte[] in return
     * @param s The string to encode
     * @return A byte[] representation of the B64 encoded String
     */
    public byte[] encodeWithByteRet(String s){
        return converter.encode(s.getBytes());
    }

    /**
     * Decode a String and get a byte[] in return
     * @param s The string to decode
     * @return A byte[] representation of the B64 decoded String
     */
    public byte[] decodeWithByteRet(String s){
        return converter.decode(s.getBytes());
    }
}
