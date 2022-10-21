package kr.co.applestar.hash;

public class HashCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String str1 = "0-42L";
		//String str2 = "0-43-";
		
		String encrytSHA256 = sha256.SHA256("1");
		
		System.out.println("str1.hashCode() : " + encrytSHA256);
	}

}
