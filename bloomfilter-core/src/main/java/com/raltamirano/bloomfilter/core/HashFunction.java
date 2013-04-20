package com.raltamirano.bloomfilter.core;

/**
 * The interface all hash functions must implement to interact with this Bloom filter implementation
 */
public interface HashFunction {
	int getHash(byte[] data);
}
