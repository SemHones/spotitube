package services;

public class LoginService {
    public String login(String username, String password){
        if (username.equals("sem") && password.equals("123")){
            return "Sem Hones";
        }
        return "";
    }
}
