package validation;

import custom_exceptions.UserHandlingException;
import org.store.book.User;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static data.sample.UserSample.sampleReadyUser;
import static data.sample.UserSample.userData;
import static org.store.book.Book.sdf;

public class ValidationForBookAndUser {
    public static final String PASSWORD_PATTERN = "^[A-Za-z]\\w{5,29}$";
    public static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private ValidationForBookAndUser() {
        System.out.println("Inside validations");
    }
    
    public static Date convertDate(String date) throws ParseException {
        return sdf.parse(date);
    }
    public static boolean validateBookName(String name){
        boolean isValid = false;
        if((name != null) && name.matches("^[a-zA-Z0-9]*$")){
            isValid = true;
        }
        return isValid;
    }
public static boolean validateName(String name){
        boolean isValid = false;
        if((name != null) && name.matches("^[a-zA-Z]*$")){
            isValid = true;
        }
        return isValid;
}
public static boolean
checkIfUserExist(String email) {
    Map<String , User> users = userData(sampleReadyUser());
        boolean isUserExists = false;
        for(User user : users.values()){
            if(user.getEmail().equals(email)){
                isUserExists = true;
            }
        }
        return isUserExists;
}
    public static boolean isValidEmailAddress(String email)
    {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(regex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static void checkEmail(String email) throws UserHandlingException {
        if(!isValidEmailAddress(email)){
           throw new UserHandlingException("email format wrong");
        }
    }
    public static void checkPassword(String password) throws UserHandlingException {
        if (isValid(password)){
            throw new UserHandlingException("password format wrong");
        }
    }
}
