package services;

import datasource.dao.UserDAO;
import datasource.objects.User;
import resources.dto.LoginResponseDTO;

public class LoginService {
    private UserDAO userDao = new UserDAO();
    public LoginResponseDTO login(String username, String password){
        User user = userDao.getSingleUser(username, password);
        if (!user.getUsername().isEmpty()){
            return new LoginResponseDTO(user.getUsername(), user.getToken());
        }
        return new LoginResponseDTO("", "");
    }
}
