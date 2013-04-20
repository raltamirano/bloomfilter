package com.raltamirano.bloomfilter;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.raltamirano.bloomfilter.core.BloomFilter;

/**
 * Unit test for Bloom Filter core project.
 */
public class BloomFilterTest 
    extends TestCase
{

	/**
	 * Size in bits of the test bloom filters
	 */
	private static final int BLOOM_FILTER_SIZE = 3*1024;

	/**
	 * We need to specify a charset for string-to-bytes conversion in order to mantain
	 * the required invariants on these tests
	 */
	private static final String CHARSET = "utf-8";
	
	private final static String[] WORD_LIST_01 = {
		"A",
		"A'asia",
		"A's",
		"AA's",
		"AARP's",
		"AAS's",
		"AATech",
		"AATech's",
		"AAeE",
		"AAeE's",
		"AAgr",
		"AAgr's",
		"AAvTech",
		"AAvTech's",
		"AB's",
		"ABD's",
		"ABEd",
		"ABEd's",
		"ABLS's",
		"ABM's",
		"ABus",
		"ABus's",
		"AC's",
		"ACTH's",
		"AD's",
		"ADP's",
		"ADR's",
		"AEC's",
		"AEd",
		"AEd's",
		"AEng",
		"AEng's",
		"AFAIK's",
		"AFP's",
		"AI's",
		"AIDS's",
		"AInd",
		"AInd's",
		"ALGOL's",
		"ALS's",
		"AM's",
		"AMEX's",
		"AMP's",
		"AMus",
		"AMus's",
		"AMusD",
		"AMusD's",
		"AN's",
		"ANSI's",
		"AOL",
		"AOL's",
		"APC's",
		"ARel",
		"ARel's",
		"ASCII's",
		"ASL's",
		"ATM's",
		"ATP's",
		"ATPase's",
		"ATV's",
		"ATech",
		"ATech's",
		"AU's",
		"AWOL's",
		"AZ's",
		"AZT's",
		"Aaberg",
		"Aaberg's",
		"Aachen",
		"Aalborg",
		"Aalesund",
		"Aaliyah",
		"Aaliyah's",
		"Aalst",
		"Aalto",
		"Aandahl",
		"Aandahl's",
		"Aani",
		"Aani's",
		"Aaqbiye",
		"Aaqbiye's",
		"Aar",
		"Aar's",
		"Aara",
		"Aara's",
		"Aarau",
		"Aaren",
		"Aaren's",
		"Aargau",
		"Aarhus",
		"Aarika",
		"Aarika's",
		"Aaron",
		"Aaronic",
		"Aaronical",
		"Aaronite",
		"Aaronite's",
		"Aaronitic",
		"Aaronitic's",
		"Aaronsburg"		
	};
	
	private final static String[] WORD_LIST_02 = {
		"Franky's",
		"Franni",
		"Franni's",
		"Frannie",
		"Frannie's",
		"Franny",
		"Franny's",
		"Frans",
		"Fransen",
		"Fransen's",
		"Fransis",
		"Fransis's",
		"Fransisco",
		"Fransisco's",
		"Frants",
		"Frants's",
		"Frantz",
		"Frantz's",
		"Franz",
		"Franz's",
		"Franza",
		"Franza's",
		"Franzen",
		"Franzen's",
		"Franzoni",
		"Franzoni's",
		"Fras",
		"Frascati",
		"Frascati's",
		"Frascatis",
		"Frasch",
		"Frasch's",
		"Frasco",
		"Frasco's",
		"Fraser",
		"Frasera",
		"Frasera's",
		"Frasier",
		"Frasier's",
		"Frasquito",
		"Frasquito's",
		"Fratercula",
		"Fratercula's",
		"Fraticelli",
		"Fraticelli's",
		"Fraticellian",
		"Fraticellian's",
		"Fratricelli",
		"Fratricelli's",
		"Frau",
		"Frau's",
		"Frauen",
		"Frauendienst",
		"Frauendienst's",
		"Frauenfeld",
		"Fraulein",
		"Fraulein's",
		"Frauleins",
		"Fraunhofer",
		"Fraus",
		"Fravashi",
		"Fravashi's",
		"Fraxinella",
		"Fraxinella's",
		"Fraxinus",
		"Fraxinus's",
		"Fraya",
		"Fraya's",
		"Frayda",
		"Frayda's",
		"Frayn",
		"Frayn's",
		"Frayne",
		"Frayne's",
		"Fraze",
		"Fraze's",
		"Frazee",
		"Frazee's",
		"Frazer",
		"Frazeysburg",
		"Frazeysburg's",
		"Frazier",
		"Frazier's",
		"Frear",
		"Frear's",
		"Freberg",
		"Freberg's",
		"Frecciarossa",
		"Frecciarossa's",
		"Frech",
		"Frech's",
		"Frechet",
		"Frechet's",
		"Frechette",
		"Frechette's",
		"Fred",
		"Fred's",
		"Freda",
		"Freda's",
		"Freddi"
	};

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BloomFilterTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BloomFilterTest.class );
    }

    public void testElementsShouldBeFound() throws UnsupportedEncodingException
    {
    	BloomFilter filter = new BloomFilter(BLOOM_FILTER_SIZE);
    	
    	// Add the words defined on word list 1
    	for(int index=0; index<WORD_LIST_01.length; index++)
    		filter.add(WORD_LIST_01[index].getBytes(CHARSET));
    	
    	// Test that every word on word list 1 is contained on the filter
    	for(int index=0; index<WORD_LIST_01.length; index++)
    		if (!filter.contains(WORD_LIST_01[index].getBytes(CHARSET)))
    			Assert.fail(String.format("Word '%s' should be detected as included on this bloom filter but it did not!", new Object[] { WORD_LIST_01[index] }));    	
    }

    public void testElementsShouldNotBeFound() throws UnsupportedEncodingException
    {
    	BloomFilter filter = new BloomFilter(BLOOM_FILTER_SIZE);    	
    	
    	// Add the words defined on word list 1
    	for(int index=0; index<WORD_LIST_01.length; index++)
    		filter.add(WORD_LIST_01[index].getBytes(CHARSET));
    	
    	// Test that no word on word list 2 is contained on the filter
    	for(int index=0; index<WORD_LIST_02.length; index++)
    		if (filter.contains(WORD_LIST_02[index].getBytes(CHARSET)))
    			Assert.fail(String.format("Word '%s' should NOT be detected as included on this bloom filter but it did!", new Object[] { WORD_LIST_02[index] }));    	
    }
}
