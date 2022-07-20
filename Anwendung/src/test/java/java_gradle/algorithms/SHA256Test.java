package java_gradle.algorithms;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class SHA256Test {

	private String message 	= "Hello World!";

	@Test
	public void testLongAsByte () {
		byte[][] expected  	= {{ 0, 0, 0, 0, 0, 0, 0, 3 }, 
				{ 0, 0, 0, 0, 0, 0, 0, (byte)0x90 },
				{ 0, 0, 0, 0, 0, 0, 1, 0 }};
		assertArrayEquals	(expected[0], SHA256.longAsByte(3));
		assertArrayEquals	(expected[1], SHA256.longAsByte(144));
		assertArrayEquals	(expected[2], SHA256.longAsByte(256));
	}
	
	@Test
	public void testIntAsByte () {
		byte[][] expected  	= {{ 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1 },
				{ (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF },
				{ 0, 0, 0, (byte)0x90 },
				{ 0, 0, 1, 0 }, 
				{ (byte)0xFF, 0, 0, (byte)0xFF }};
		assertArrayEquals	("Test 0", expected[0], SHA256.intAsByte(0));
		assertArrayEquals	("Test 1", expected[1], SHA256.intAsByte(1));
		assertArrayEquals	("Test -1", expected[2], SHA256.intAsByte(-1));
		assertArrayEquals	("Test 144", expected[3], SHA256.intAsByte(144));
		assertArrayEquals	("Test 256", expected[4], SHA256.intAsByte(256));
		assertArrayEquals	("Test ff0000ff", expected[5], SHA256.intAsByte(0xff0000ff));
		
	}
	
	@Test
	public void testIntArrayAsByte () {
		int[][] quelle 		= { { 0, 1 }, { 1, 1 }};
		byte[][] expected   = {{ 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0, 1 }};
		assertArrayEquals	(expected[0], SHA256.intArrayAsByte(quelle[0]));
		assertArrayEquals	(expected[1], SHA256.intArrayAsByte(quelle[1]));
	}
	
	@Test
	public void testByteAsInt () {
		byte[][] quelle   	= { { 0, 0, 0, 4 }, { 10, 0, 0, 0 }, { 0, 0, 0 , (byte)0b111111111 } };
		assertEquals		(4, SHA256.byteAsInt(quelle[0]));
		assertEquals		(167772160, SHA256.byteAsInt(quelle[1]));
		assertEquals		(255, SHA256.byteAsInt(quelle[2]));
	}
	
	@Test
	public void testPower () {
		assertEquals		(125, SHA256.power(5, 3));
		assertEquals		(256, SHA256.power(2, 8));
		assertEquals		(10000000, SHA256.power(10, 7));
	}
	
	@Test
	public void testRotate () {
		int[] value			= { 0b10110000100001101100001010100110 };
		int[] expected		= { 0b00110101100001000011011000010101 };
 		assertEquals		( Integer.toBinaryString(expected[0]), Integer.toBinaryString(Integer.rotateRight(value[0], 5)));
 		assertEquals		( Integer.toBinaryString(expected[0]), Integer.toBinaryString(SHA256.rotr(5, value[0])));
	}
	
	@Test
	public void testInt2Hex () {
		int[] values = { -2, 0, 11, 22456, 17489844 };
		assertEquals ("FFFFFFFE", SHA256.int2Hex(values[0]));
		assertEquals ("00000000", SHA256.int2Hex(values[1]));
		assertEquals ("0000000B", SHA256.int2Hex(values[2]));
		assertEquals ("000057B8", SHA256.int2Hex(values[3]));
		assertEquals ("010ADFB4", SHA256.int2Hex(values[4]));
	}
	
	@Test
	public void testByte2Hex () {
		byte[] values = { 0, 11, -2 };
		assertEquals ("00", SHA256.byte2Hex(values[0]));
		assertEquals ("0B", SHA256.byte2Hex(values[1]));
		assertEquals ("FE", SHA256.byte2Hex(values[2]));
	}

	@Test
	public void testPadding() {
		byte[] msgBytes 	= message.getBytes();
		byte[] minByte		= { (byte)0b10000000 };
		byte[] nullBytes	= new byte[43];
		byte[] lenBytes		= { 0, 0, 0, 0, 0, 0, 0, 96 };
		byte[] padded		= SHA256.add(msgBytes, SHA256.add(minByte, SHA256.add(nullBytes, lenBytes)));
		
		assertArrayEquals	(padded, SHA256.padding(message));
	} 
	
	@Test
	public void testMakeChunks () {
		int len			= 4;
		byte[] origin  	= new byte[64*len];
		int[][] chunks	= SHA256.makeChunks(origin);
		assertEquals	("Anzahl Chunks", 4, chunks.length);
		assertEquals	("L�nge erster Chunk", 16, chunks[0].length);
	}
	
	@Test
	public void testHashCode () {
		String message		= "Dies ist eine wichtige Nachricht";
		
		byte[] encoded		= SHA256.encode(message);
		MessageDigest md	= null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
		}
		md.update			(message.getBytes());
		byte[] digest		= md.digest();
		
		assertArrayEquals	(digest, encoded);
	}
	
	

}
