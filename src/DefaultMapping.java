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
 * Represents the default Base64 encoding/decoding scheme
 *
 * @author Stephen Yingling
 */
public class DefaultMapping implements B64Mapping {

    //Default Base64 Encoding
    protected byte[] mapForwardVals =
            {'A','B','C','D','E','F','G','H','I','J','K','L',
             'M','N','O','P','Q','R','S','T','U','V','W','X',
             'Y','Z','a','b','c','d','e','f','g','h','i','j',
             'k','l','m','n','o','p','q','r','s','t','u','v',
             'w','x','y','z','0','1','2','3','4','5','6','7',
             '8','9','+','/'};

    @Override
    public byte mapForward(byte b) {
        if(b <0 ||b >= 64){
            return 0;
        }
        return mapForwardVals[b];
    }

    @Override
    public byte mapBack(byte b) {
        if(b <= 'Z' && b >= 'A'){
            return (byte)(b - 'A');
        }

        if(b <= 'z' && b >= 'a'){
            return (byte)(b-'a'+26);
        }

        if(b <= '9' && b >= '0'){
            return (byte)(b-'0'+52);
        }

        if(b == '+'){
            return 62;
        }

        if(b =='/'){
            return 63;
        }

        return 0;
    }
}
