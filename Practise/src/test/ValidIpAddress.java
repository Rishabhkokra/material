package test;

public class ValidIpAddress {

	public static boolean inRange(int n) { // check if every split is in range 0-255
		if (n >= 0 && n <= 255) {
			return true;
		}
		return false;
	}

	public static boolean hasLeadingZero(String n) { // check if every split has leading zero or not.
		if (n.length() > 1) {
			if (n.charAt(0) == '0') {
				return true;
			}
		}
		return false;
	}

	public static int isValid(String s) {
		String[] parts = s.split("\\.");
		if (parts.length != 4) { // if number of splitting element is not
									// 4 it is not a valid IP address
			return 0;
		}
		for (String part : parts) {
			if (hasLeadingZero(part)) {
				return 0;
			}
			if (part.length() == 0) {
				return 0;
			}
			try {
				int num = Integer.parseInt(part);
				if (!inRange(num)) {
					return 0;
				}
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		String ip1 = "222.111.111.111";
		String ip2 = "5555..555";
		String ip3 = "0000.0000.0000.0000";
		String ip4 = "1.1.1.1";
		System.out.println(isValid(ip1));
		System.out.println(isValid(ip2));
		System.out.println(isValid(ip3));
		System.out.println(isValid(ip4));
	}
}
