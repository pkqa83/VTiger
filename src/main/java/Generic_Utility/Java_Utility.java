package Generic_Utility;

import java.util.Random;

/**
 * Description: This method is used to random numbers
 * @author Prashanth K
 * @returns int
 * @Date 5th Feb 2025 
 **/

public class Java_Utility {
	public int getRandomValue() {
	Random ran = new Random();
	int ranNum = ran.nextInt(1000);
	return ranNum;
}
}
