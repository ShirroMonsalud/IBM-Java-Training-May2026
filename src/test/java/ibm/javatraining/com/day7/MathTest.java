package ibm.javatraining.com.day7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ibm.javatraining.com.day7.MathAct;

class MathTest {


	@Test
	void should_ReturnSum_WhenAdded() {
		float a = 12f;
		float b = 32f;
		
		float sum = MathAct.add(a, b);
		float realSum = a + b;
		assertEquals(realSum, sum);
	}
	
	@Test
	void should_ReturnDifference_WhenSubtracted() {
		float a = 12f;
		float b = 32;
		
		float difference = MathAct.subtract(a, b);
		float realDifference = a - b;
		assertEquals(realDifference, difference);
	}
	
	@Test
	void should_ReturnProduct_WhenMultiplied() {
		float a = 12;
		float b = 32;
		
		float product = MathAct.multiply(a, b);
		float realProduct = a * b;
		assertEquals(realProduct, product);
	}
	
	@Test
	void should_ReturnQuotient_WhenDivided() {
		float a = 12;
		float b = 32;
		
		float quotient = MathAct.divide(a, b);
		float realQuotient = a / b;
		assertEquals(realQuotient, quotient);
	}
	
	@Test
	void should_ThrowArithmeticException_When_bZero() {
		// given
		float a = 12;
		float b = 0;
		
		// when
		Executable executable = () -> MathAct.divide(a, b);
		
		// then
		assertThrows(ArithmeticException.class, executable);
	}
	
}
