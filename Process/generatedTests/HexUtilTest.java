/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

package freenet.support;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test case for {@link freenet.support.HexUtil} class.
 *
 * @author Alberto Bacchelli &lt;sback@freenetproject.org&gt;
 */
public class HexUtilTest extends TestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test the bytesToHex(byte[]) method
     * against every possible single byte value.
     */
    public void testBytesToHex_byte() {
        byte[] methodByteArray = new byte[1];
        String expectedResult;
        for (int i = 255; i >= 0; i--) {
            methodByteArray[0] = (byte) i;
			/* Integer.toHexString works with int so it doesn't return always a two digit hex. 
			   For this reason we need the next "switch case". */
            expectedResult = (i <= 15 ?
                    "0" + (Integer.toHexString(i)) :
                    (Integer.toHexString(i)));
            assertEquals(expectedResult, HexUtil.bytesToHex(methodByteArray));
        }
    }

    @Test
    public void testHexToBytes() {
        assertEquals(8, HexUtil.hexToBytes("0123456789ABCDEF").length);
        assertEquals(9, HexUtil.hexToBytes("0123456789ABCDEF", 1).length);
    }

    @Test
    public void testHexToBytes10() throws IndexOutOfBoundsException, NumberFormatException {
        thrown.expect(NumberFormatException.class);
        HexUtil.hexToBytes("S", new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, 1);
    }

    @Test
    public void testHexToBytes11() throws IndexOutOfBoundsException, NumberFormatException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.hexToBytes("", new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, 1);
    }

    @Test
    public void testHexToBytes12() throws IndexOutOfBoundsException, NumberFormatException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.hexToBytes("0123456789ABCDEF", new byte[]{65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65},
                1);
    }

    @Test
    public void testHexToBytes13() throws IndexOutOfBoundsException, NumberFormatException {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        HexUtil.hexToBytes("0123456789ABCDEF", new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, -1);
    }

    @Test
    public void testHexToBytes14() throws IndexOutOfBoundsException, NumberFormatException {
        thrown.expect(NumberFormatException.class);
        HexUtil.hexToBytes("freenet.support.HexUtil",
                new byte[]{65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65}, 1);
    }

    @Test
    public void testHexToBytes2() {
        thrown.expect(NumberFormatException.class);
        HexUtil.hexToBytes("S");
    }

    @Test
    public void testHexToBytes3() {
        thrown.expect(NumberFormatException.class);
        HexUtil.hexToBytes("freenet.support.HexUtil");
    }

    @Test
    public void testHexToBytes4() {
        thrown.expect(NumberFormatException.class);
        HexUtil.hexToBytes("S", 1);
    }

    @Test
    public void testHexToBytes5() {
        thrown.expect(NumberFormatException.class);
        HexUtil.hexToBytes("freenet.support.HexUtil", 1);
    }

    @Test
    public void testHexToBytes6() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        HexUtil.hexToBytes("0123456789ABCDEF", -1);
    }

    @Test
    public void testHexToBytes7() {
        thrown.expect(NegativeArraySizeException.class);
        HexUtil.hexToBytes("0123456789ABCDEF", -2147483648);
    }

    @Test
    public void testHexToBytes8() throws IndexOutOfBoundsException, NumberFormatException {
        thrown.expect(IndexOutOfBoundsException.class);
        HexUtil.hexToBytes("0123456789ABCDEF", new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, 1);
    }

    @Test
    public void testHexToBytes9() throws IndexOutOfBoundsException, NumberFormatException {
        thrown.expect(IndexOutOfBoundsException.class);
        HexUtil.hexToBytes("Output buffer too small for input (", new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, 1);
    }

    /**
     * Test the hexToBytes(String) method
     * against the hex representation of
     * every possible single byte.
     * The starting offset is always 0.
     */
    public void testHexToBytes_String() {
        byte[] expectedByteArray = new byte[1];
        String methodHexString;
        for (int i = 255; i >= 0; i--) {
            expectedByteArray[0] = (byte) i;
			/* Integer.toHexString works with int so it doesn't return always a two digit hex. 
			   For this reason we need the next "switch case". */
            methodHexString = (i <= 15 ?
                    "0" + (Integer.toHexString(i)) :
                    (Integer.toHexString(i)));
            assertTrue(Arrays.equals(expectedByteArray, HexUtil.hexToBytes(methodHexString)));
        }
    }

    /**
     * Test the hexToBytes(String,int) method
     * against the hex representation of
     * every possible single byte.
     * The starting offset is always 0.
     */
    public void testHexToBytes_StringInt() {
        byte[] expectedByteArray = new byte[1];
        String methodHexString;
        for (int i = 255; i >= 0; i--) {
            expectedByteArray[0] = (byte) i;
			/* Integer.toHexString works with int so it doesn't return always a two digit hex. 
			   For this reason we need the next "switch case". */
            methodHexString = (i <= 15 ?
                    "0" + (Integer.toHexString(i)) :
                    (Integer.toHexString(i)));
            assertTrue(Arrays.equals(expectedByteArray, HexUtil.hexToBytes(methodHexString, 0)));
        }
    }

    /**
     * Test the hexToBytes(String,byte[],int) method
     * against the hex representation of
     * every possible single byte.
     */
    public void testHexToBytes_StringByteInt() {
        byte[] expectedByteArray = new byte[1];
        byte[] outputArray = new byte[1];
        String methodHexString;
        for (int i = 255; i >= 0; i--) {
            expectedByteArray[0] = (byte) i;
			/* Integer.toHexString works with int so it doesn't return always a two digit hex. 
			   For this reason we need the next "switch case". */
            methodHexString = (i <= 15 ?
                    "0" + (Integer.toHexString(i)) :
                    (Integer.toHexString(i)));
            HexUtil.hexToBytes(methodHexString, outputArray, 0);
            assertTrue(Arrays.equals(expectedByteArray, outputArray));
        }
    }

    @Test
    public void testBitsToBytes() {
        assertEquals(1, HexUtil.bitsToBytes(BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 3).length);
        assertEquals(0, HexUtil.bitsToBytes(BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 0).length);
    }

    @Test
    public void testBitsToBytes2() {
        thrown.expect(NegativeArraySizeException.class);
        HexUtil.bitsToBytes(BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), -2147483648);
    }

    /**
     * Test the bitsToByte(BitSet,int) method
     * against the bit representation of
     * every possible single byte.
     */
    public void testBitsToBytes_BitSetInt() {
        byte[] expectedByteArray = new byte[1];
        byte[] outputArray = new byte[1];
        BitSet methodBitSet = new BitSet(8);
        for (int i = 0; i < 256; i++) {
            outputArray = HexUtil.bitsToBytes(methodBitSet, 8);
            expectedByteArray[0] = (byte) i;
            assertTrue(Arrays.equals(expectedByteArray, outputArray));
            addOne(methodBitSet);
        }
    }

    /**
     * It adds 1 to a given BitSet
     *
     * @param aBitSet
     */
    private void addOne(BitSet aBitSet) {
        int bitSetIndex = 0;
        while (aBitSet.get(bitSetIndex) == true) {
            aBitSet.flip(bitSetIndex);
            bitSetIndex++;
        }
        aBitSet.flip(bitSetIndex);
    }

    @Test
    public void testCountBytesForBits() {
        assertEquals(1, HexUtil.countBytesForBits(3));
    }

    /**
     * Test countBytesForBits(int) method
     * against all possible values until 256 bytes
     */
    public void testCountBytesForBits_int() {
        //border case
        assertEquals(HexUtil.countBytesForBits(0), 0);
        for (int expectedBytesCount = 1; expectedBytesCount < 256; expectedBytesCount++)
            for (int bits = (expectedBytesCount - 1) * 8 + 1; bits <= (expectedBytesCount) * 8; bits++)
                assertEquals(HexUtil.countBytesForBits(bits), expectedBytesCount);
    }

    @Test
    public void testBytesToBits() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.bytesToBits(new byte[]{65, 65, 65, 65, 65, 65, 65, 65},
                BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 3);
    }

    @Test
    public void testBytesToBits2() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.bytesToBits(new byte[]{}, null, 3);
    }

    @Test
    public void testBytesToBits3() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.bytesToBits(new byte[]{65, 65, 65, 65, 65, 65, 65, 65},
                BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 8);
    }

    /**
     * Test bytesToBits(byte[],BitSet,int) method
     * against all possible single byte value.
     * It uses HexUtil.bitsToBytes() method for the check,
     * so be sure that method works correctly!
     */
    public void testBytesToBits_byteBitSetInt() {
        byte[] methodByteArray = new byte[1];
        BitSet methodBitSet = new BitSet(8);
        for (int i = 0; i < 255; i++) {
            methodByteArray[0] = (byte) i;
            HexUtil.bytesToBits(methodByteArray, methodBitSet, 7);
            assertTrue(Arrays.equals(methodByteArray, HexUtil.bitsToBytes(methodBitSet, 8)));
        }
    }

    @Test
    public void testBiToHex() {
        assertEquals("01", HexUtil.biToHex(BigInteger.valueOf(1L)));
        assertEquals("584141414141414141414141414141414141414141414141", HexUtil.biToHex(new BigInteger(
                new byte[]{88, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65})));
    }

    /**
     * Test biToHex(BigInteger) method
     * comparing its results to results provided
     * by different scientific valid calculators.
     */
    public void testBiToHex_BigInteger() {
        BigInteger methodBigInteger = new BigInteger("999999999999999");
        String expectedHexValue = "038d7ea4c67fff";
        assertEquals(HexUtil.biToHex(methodBigInteger), expectedHexValue);
        methodBigInteger = BigInteger.ZERO;
        expectedHexValue = "00";
        assertEquals(HexUtil.biToHex(methodBigInteger), expectedHexValue);
        methodBigInteger = new BigInteger("72057594037927935");
        expectedHexValue = "00ffffffffffffff";
        assertEquals(HexUtil.biToHex(methodBigInteger), expectedHexValue);
    }

    /**
     * Test bitsToHexString(BitSet,int) method
     * comparing its results to results provided
     * by different scientific valid calculators.
     */
    public void testBitsToHexString() {
        BitSet methodBitSet = new BitSet(8);
        String expectedString = "00";
        assertEquals(HexUtil.bitsToHexString(methodBitSet, 8), expectedString);
        methodBitSet.set(0, 7, true); /*0x7f*/
        expectedString = "7f";
        assertEquals(HexUtil.bitsToHexString(methodBitSet, 8), expectedString);
        methodBitSet.set(0, 9, true); /*0xff*/
        expectedString = "ff";
        assertEquals(HexUtil.bitsToHexString(methodBitSet, 8), expectedString);
    }

    @Test
    public void testBitsToHexString2() {
        assertEquals("01", HexUtil.bitsToHexString(BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 3));
    }

    @Test
    public void testBitsToHexString3() {
        assertEquals("", HexUtil.bitsToHexString(BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 0));
    }

    @Test
    public void testBitsToHexString4() {
        thrown.expect(NegativeArraySizeException.class);
        HexUtil.bitsToHexString(BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), -2147483648);
    }

    @Test
    public void testToHexString() {
        assertEquals("01", HexUtil.toHexString(BigInteger.valueOf(1L)));
        assertEquals("584141414141414141414141414141414141414141414141", HexUtil.toHexString(new BigInteger(
                new byte[]{88, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65})));
    }

    /**
     * Tests hexToBits(String,BitSet,int) method
     */
    public void testHexToBits() {
        String methodStringToStore = "00";
        BitSet methodBitSet = new BitSet(8);
        HexUtil.hexToBits(methodStringToStore, methodBitSet, methodBitSet.size());
        assertTrue(methodBitSet.cardinality() == 0);
        BitSet expectedBitSet = new BitSet(8);
        expectedBitSet.set(0, 7, true); /*0x7f*/
        methodStringToStore = "7f";
        methodBitSet = new BitSet(8);
        HexUtil.hexToBits(methodStringToStore, methodBitSet, methodBitSet.size());
        assertTrue(methodBitSet.intersects(expectedBitSet));
        expectedBitSet.set(0, 9, true); /*0xff*/
        methodStringToStore = "ff";
        methodBitSet = new BitSet(8);
        HexUtil.hexToBits(methodStringToStore, methodBitSet, methodBitSet.size());
        assertTrue(methodBitSet.intersects(expectedBitSet));
    }

    @Test
    public void testHexToBits2() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.hexToBits("0123456789ABCDEF", BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 3);
    }

    @Test
    public void testHexToBits3() {
        thrown.expect(NumberFormatException.class);
        HexUtil.hexToBits("S", BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 3);
    }

    @Test
    public void testHexToBits4() {
        thrown.expect(NumberFormatException.class);
        HexUtil.hexToBits("freenet.support.HexUtil", BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 3);
    }

    @Test
    public void testHexToBits5() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.hexToBits("0123456789ABCDEF", BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 0);
    }

    @Test
    public void testHexToBits6() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.hexToBits("0123456789ABCDEF", BitSet.valueOf(new long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L}), 8);
    }

    @Test
    public void testWriteBigInteger() throws IOException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        BigInteger integer = BigInteger.valueOf(1L);
        HexUtil.writeBigInteger(integer, new DataOutputStream(new ByteArrayOutputStream()));
    }

    @Test
    public void testWriteBigInteger2() throws IOException {
        BigInteger integer = BigInteger.valueOf(-1L);
        thrown.expect(IllegalStateException.class);
        HexUtil.writeBigInteger(integer, new DataOutputStream(new ByteArrayOutputStream()));
    }

    @Test
    public void testReadBigInteger() throws IOException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.readBigInteger(new DataInputStream(new ByteArrayInputStream(
                new byte[]{65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65})));
    }

    @Test
    public void testReadBigInteger2() throws IOException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.readBigInteger(new DataInputStream(new ByteArrayInputStream(
                new byte[]{0, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65})));
    }

    @Test
    public void testReadBigInteger3() throws IOException {
        thrown.expect(IOException.class);
        HexUtil.readBigInteger(new DataInputStream(new ByteArrayInputStream(
                new byte[]{-1, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65})));
    }

    @Test
    public void testReadBigInteger4() throws IOException {
        assertEquals("0", HexUtil
                .readBigInteger(new DataInputStream(new ByteArrayInputStream(
                        new byte[]{0, 0, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65})))
                .toString());
    }

    /**
     * Tests writeBigInteger(BigInteger,DataOutputStream)
     * and readBigInteger(DataInputStream) comparing a
     * BigInteger after writing it to a Stream and
     * reading it from the writing result.
     */
    public void testWriteAndReadBigInteger() {
        BigInteger methodBigInteger = new BigInteger("999999999999999");
        ByteArrayOutputStream methodByteArrayOutStream = new ByteArrayOutputStream();
        DataOutputStream methodDataOutStream = new DataOutputStream(methodByteArrayOutStream);
        try {
            HexUtil.writeBigInteger(methodBigInteger, methodDataOutStream);
            ByteArrayInputStream methodByteArrayInStream =
                    new ByteArrayInputStream(methodByteArrayOutStream.toByteArray());
            DataInputStream methodDataInStream = new DataInputStream(methodByteArrayInStream);
            assertTrue(methodBigInteger.compareTo(HexUtil.readBigInteger(methodDataInStream)) == 0);
        } catch (IOException aException) {
            fail("Not expected exception thrown : " + aException.getMessage());
        }
    }

    /**
     * Test bytesToHex(byte[],int,int) method
     * with a too long starting offset. The tested
     * method should raise an exception.
     */
    public void testBytesToHex_byteIntInt_WithLongOffset() {
        try {
            int arrayLength = 3;
            byte[] methodBytesArray = new byte[arrayLength];
            HexUtil.bytesToHex(methodBytesArray, arrayLength + 1, 1);
            fail("Expected Exception Error Not Thrown!");
        } catch (IllegalArgumentException anException) {
            assertNotNull(anException);
        }
    }

    /**
     * Test bytesToHex(byte[],int,int) method
     * with asking to read too many bytes. The tested
     * method should raise an exception.
     */
    public void testBytesToHex_byteIntInt_WithLongReading() {
        try {
            int arrayLength = 3;
            byte[] methodBytesArray = new byte[arrayLength];
            HexUtil.bytesToHex(methodBytesArray, 0, arrayLength + 1);
            fail("Expected Exception Error Not Thrown!");
        } catch (IllegalArgumentException anException) {
            assertNotNull(anException);
        }
    }

    /**
     * Test bytesToHex(byte[],int,int) method
     * with a 0 length.
     */
    public void testBytesToHex_byteIntInt_WithZeroLength() {
        int length = 0;
        byte[] methodBytesArray = {1, 2, 3};        //a non-zero bytes array
        assertEquals("", HexUtil.bytesToHex(methodBytesArray, 0, length));
    }

    /**
     * Test hexToBytes(String,byte[],int) method
     * with a too long offset.
     * The method should raise an exception.
     */
    public void testHexToBytes_StringByteInt_WithLongOffset() {
        try {
            String methodString = "0";
            byte[] methodByteArray = new byte[1];
            HexUtil.hexToBytes(methodString, methodByteArray, methodByteArray.length);
            fail("Expected Exception Error Not Thrown!");
        } catch (ArrayIndexOutOfBoundsException anException) {
            assertNotNull(anException);
        }
    }

    /**
     * Test hexToBytes(String,byte[],int) method
     * with a too short byte[] to put the result.
     * The method should raise an exception.
     */
    public void testHexToBytes_StringByteInt_WithShortArray() {
        try {
            String methodString = "0000";
            byte[] methodByteArray = new byte[1];
            HexUtil.hexToBytes(methodString, methodByteArray, 0);
            fail("Expected Exception Error Not Thrown!");
        } catch (IndexOutOfBoundsException anException) {
            assertNotNull(anException);
        }
    }

    /**
     * Test all hexToBytes() methods
     * with a not valid character.
     * The method should raise an exception.
     */
    public void testHexToBytes_WithBadDigit() {
        String methodString = "00%0";
        try {
            byte[] methodByteArray = new byte[methodString.length()];
            HexUtil.hexToBytes(methodString, methodByteArray, 0);
            fail("Expected Exception Error Not Thrown!");
        } catch (NumberFormatException anException) {
            assertNotNull(anException);
        }
        try {
            HexUtil.hexToBytes(methodString, 0);
            fail("Expected Exception Error Not Thrown!");
        } catch (NumberFormatException anException) {
            assertNotNull(anException);
        }
        try {
            HexUtil.hexToBytes(methodString);
            fail("Expected Exception Error Not Thrown!");
        } catch (NumberFormatException anException) {
            assertNotNull(anException);
        }
    }

    /**
     * Test the bitsToByte(BitSet,int) method
     * using a size smaller than the actual number
     * of bits in the BitSet.
     */
    public void testBitsToBytes_WithShortSize() {
        byte[] expectedByteArray = new byte[1];
        byte[] outputArray = new byte[1];
        BitSet methodBitSet = new BitSet(8);

        /* 0x01 */
        methodBitSet.flip(0);
        expectedByteArray[0] = (byte) 1;
        /* 0x01 & 0x00 == 0x01 */
        outputArray = HexUtil.bitsToBytes(methodBitSet, 0);
        assertFalse(Arrays.equals(expectedByteArray, outputArray));
        /* 0x01 & 0x01 == 0x01 */
        outputArray = HexUtil.bitsToBytes(methodBitSet, 1);
        assertTrue(Arrays.equals(expectedByteArray, outputArray));

        /* 0x80 */
        methodBitSet.flip(7);
        /* 0x08 */
        methodBitSet.flip(3);
        expectedByteArray[0] = (byte) 128 + 8 + 1;
        /* 0x89 & 0x08 == 0x89 */
        outputArray = HexUtil.bitsToBytes(methodBitSet, 3);
        assertFalse(Arrays.equals(expectedByteArray, outputArray));
        /* 0x89 & 0xff == 0x89 */
        outputArray = HexUtil.bitsToBytes(methodBitSet, 8);
        assertTrue(Arrays.equals(expectedByteArray, outputArray));
    }

    @Test
    public void testBytesToHex() {
        assertEquals("4141414141414141", HexUtil.bytesToHex(new byte[]{65, 65, 65, 65, 65, 65, 65, 65}));
        assertEquals("414141", HexUtil.bytesToHex(new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, 1, 3));
    }

    @Test
    public void testBytesToHex2() {
        thrown.expect(IllegalArgumentException.class);
        HexUtil.bytesToHex(new byte[]{}, 1, 3);
    }

    @Test
    public void testBytesToHex3() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        HexUtil.bytesToHex(new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, -1, 3);
    }

    @Test
    public void testBytesToHex4() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.bytesToHex(new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, 1, -1);
    }

    /* Checks that offset == array length is allowed, which is needed for
     * handling zero length arrays */
    public void testBytesToHexZeroLength() {
        assertEquals("", HexUtil.bytesToHex(new byte[0], 0, 0));
        assertEquals("", HexUtil.bytesToHex(new byte[2], 2, 0));
    }

    @Test
    public void testBytesToHexAppend() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        HexUtil.bytesToHexAppend(new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, 1, 3, new StringBuilder());
    }

    @Test
    public void testBytesToHexAppend2() {
        thrown.expect(IllegalArgumentException.class);
        HexUtil.bytesToHexAppend(new byte[]{}, 1, 3, new StringBuilder());
    }

    @Test
    public void testBytesToHexAppend3() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        HexUtil.bytesToHexAppend(new byte[]{65, 65, 65, 65, 65, 65, 65, 65}, -1, 3, new StringBuilder());
    }

    @Test
    public void testBytesToHexAppend4() {
        thrown.expect(IllegalArgumentException.class);
        HexUtil.bytesToHexAppend(new byte[]{}, 1, 3, null);
    }
}
