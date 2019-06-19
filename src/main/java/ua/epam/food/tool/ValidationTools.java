package ua.epam.food.tool;

public class ValidationTools {
    public static boolean checkEmailFormat(String email){
        if (email==null){
            return false;
        }
        return email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
	
	public static void main(String[] args) {
		System.out.println(checkEmailFormat("gcjcjk"));
	}
}
