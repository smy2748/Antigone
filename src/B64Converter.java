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
 * Converts byte[] to/from Base64 encoding
 *
 * @author Stephen Yingling
 */
public class B64Converter {

    protected B64Mapping mapping;   //The mapping used for this encoder

    /**
     * Create a new converter with the default mapping
     */
    public B64Converter(){
        mapping = new DefaultMapping();
    }

    /**
     * Create a new converter with the supplied mapping
     * @param m The mapping that this converter should use
     */
    public B64Converter(B64Mapping m){
        mapping = m;
    }

    /**
     * Encode a byte[] with Base64 encoding
     * @param b The byte[] to encode
     * @return The Base64 encoded byte[]
     */
    public byte[] encode(byte[] b){
        if(b == null){
            return null;
        }
        byte jag = (byte)(b.length%3),
             padding = (byte)((3-jag)%3);
        int len = (int)(((b.length + padding)*4)/(3.0));
        byte[] res = new byte[len];
        int resI=0;
        for(int i=0; i<b.length-2; i+=3, resI+=4){
            int holder = 0;
            for(int j=0; j<=2;j++){
                holder <<=8;
                holder |= (b[i+j]&0xFF);
            }
            for(int j=3; j >=0; j--){
                res[j+resI] = mapping.mapForward((byte)(holder &0x3F));
                holder >>>= 6;
            }
        }

        if(padding != 0){
            int holder =0;
            for(int i=b.length-jag; i<b.length;i++){
                holder<<=8;
                holder |= (b[i]&0xFF);
            }
            for(int i=0; i<padding;i++){
                holder <<=8;
            }
            for(int j=3; j >=0; j--){
                res[j+resI] = mapping.mapForward((byte)(holder &0x3F));
                holder >>>= 6;
            }

            for(int i=0; i<padding; i++){
                res[res.length-1-i] = (byte)61;
            }
        }
        return res;
    }

    /**
     * Decode a byte[] using Base64 encoding
     * @param b The byte[] to decode
     * @return The decoded byte[]
     */
    public byte[] decode(byte[] b){
        if (b == null){
            return null;
        }

        int len =  (int)((b.length *3)/4.0);
        byte res[] = new byte[len];

        int resI=0;

        for(int i=0; i<b.length-3; i+=4, resI+=3){
            int holder = 0;
            for(int j=0; j<4; j++){
                holder <<=6;
                holder |= mapping.mapBack((byte)(b[i+j] &0xFF));
            }

            for(int j=2; j >=0;j-- ){
                res[resI+j] = (byte)(holder & 0xFF);
                holder >>>= 8;
            }
        }

        return res;
    }

}
