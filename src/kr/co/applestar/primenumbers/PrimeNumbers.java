package kr.co.applestar.primenumbers;

import java.util.logging.Logger;
import kr.co.applestar.dbconnection.DBConnection;
import kr.co.applestar.hash.sha256;

public class PrimeNumbers {
	
	private static Logger logger = Logger.getLogger(PrimeNumbers.class.getName());
	private static DBConnection conn = new DBConnection();

	public static void main(String[] args) {
		logger.info("Find PrimeNumbers Java Version 11 Start Github~");
				
		//DB connection
		//DBConnection conn = new DBConnection();
		
		// 현재 DB의 max값을 가져온다
		double startVal;
		startVal =  conn.getCurrentMaxPrimeNumber();
		logger.info("현재 DB의 최대값 startVal : " + startVal);
		
		//직전 prime number와 연산 대상 수를 구한다
		double currentVal = startVal + 2;
		double lastPrimeNumber = startVal;
		
		//연산소요시간 기록
		long startTime;
		long endTime;
		
		// loop 시작
		while (true) {
			try {
				//연산 시작시간 기록
				startTime = System.currentTimeMillis();
				
				//prime number 이면...
				if (isPrime(currentVal)) {
					//logger.info("[" + currentVal + "] is PrimeNumber!");
					//hash값 얻기
					String encryptSHA = sha256.SHA256(Double.toString(currentVal));
					
					//연산 종료시각을 구하고
					endTime = System.currentTimeMillis();
					
					//소요시간을 계산
					int diff = (int)(endTime - startTime);
					//logger.info("currentVal : " + currentVal + " duration : " + diff);
					
					// DB insert
					if (conn.insertNumber(currentVal, (int)(currentVal - lastPrimeNumber), encryptSHA, diff)) {
						
					}else {
						logger.info("Insert Fail");
					}
					
					lastPrimeNumber = currentVal;
				} 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentVal += 2;
		}
	}
	
	public static boolean isPrime(double d) {

		double targetNum = d;

		try {
			
			double seq = 2;
			
			while (seq < targetNum) {
				if (targetNum % seq == 0){
					return false;
				}
				
				/*
				if (conn.getMinNumberBiggerThenInput(seq) == 0 ) {
					seq++;
				} else {
					seq = conn.getMinNumberBiggerThenInput(seq);	
				}
				*/
				seq++;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
}
