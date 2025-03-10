package com.qspider.vtiger.com.test.cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertions {
	@Test
	
	public void m2() {
		System.out.println("step5");
		System.out.println("step6");
		SoftAssert soft = new SoftAssert();
		String exp="PK";
		String act="pk";
		soft.assertEquals(exp, act);
		System.out.println("step7");
		soft.assertAll();
	}
	
	public void m1() {
		System.out.println("step1");
		System.out.println("step2");
		Assert.assertEquals("PK", "pk");
		System.out.println("step3");
		System.out.println("step4");

	}
	@Test
	public void m3() {
		
		Assert.assertEquals(10, 20, "both are not equal");
		System.out.println("both are equal");
	}
	
	@Test
	public void m4() {
		
		Assert.assertNotEquals(10, 10, "both are equal");
		System.out.println("both are not equal");
	}
	
	@Test
	public void m5() {
		String s1 ="Qspider";
		String s2 ="Jspider";
		Assert.assertTrue(s1.equalsIgnoreCase(s2), "both are NOT equal");
		System.out.println("both are equal");
	}
	@Test
	public void m6() {
		String s1 ="Qspider";
		String s2 ="qspider";
		Assert.assertTrue(s1.equalsIgnoreCase(s2), "both are NOT equal");
		System.out.println("both are equal");
	}
	
	@Test
	public void m7() {
		String s1 ="Qspider";
		String s2 ="Jspider";
		Assert.assertFalse(s1.equalsIgnoreCase(s2), "both are equal");
		System.out.println("both are NOT equal");
	}
	
	@Test
	public void m8() {
		String s1 ="";
		Assert.assertNull(s1, "given String is null");
		System.out.println("given String is NOT null");
	}
	
	@Test
	public void m9() {
		String s1 =null;
		Assert.assertNull(s1, "given String is NOT null");
		System.out.println("given String is null");
	}
	
	@Test
	public void m10() {
		String s1 ="PK";
		Assert.assertNotNull(s1, "given String is null");
		System.out.println("given String is NOT null");
	}
}

