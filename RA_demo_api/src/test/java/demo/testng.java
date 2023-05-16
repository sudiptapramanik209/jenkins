package demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testng {
	@BeforeTest
	public void bt() {
		System.out.println("i am very good");
		String expected="sudipta";
		String get="bapan";
		Assert.assertEquals(expected, get);
	}
	@Test
	public void testcase() {
		System.out.println("this is my testng");
	}
	@AfterTest
	public void test() {
		System.out.println("i am bad");
	
}

}
