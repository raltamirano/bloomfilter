/**
 * This class provides an implementation of HashFunction backed by a MD5 
 * message digest object. This is NOT the kind of hash function you would 
 * use in common scenarios, as it is designed for cryptographic robustness 
 * instead of speed.
 * The reason for this implementation is to have one more hash 
 * function to feed into a bloom filter :) 
 */

package com.raltamirano.bloomfilter.hash;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.raltamirano.bloomfilter.core.HashFunction;

public class SHAHash implements HashFunction {

	private static final SHAHash INSTANCE = new SHAHash();
	
	private SHAHash () { }
	
	public static SHAHash getInstance() {
		return INSTANCE;
	}
	
	
	public int getHash(byte[] data) {
		try {
			MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-1");
			return ByteBuffer.wrap(messageDigest.digest(data)).getInt();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
}
