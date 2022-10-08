package services;

import datasource.dao.UserDAO;
import datasource.objects.User;
import resources.exceptions.TokenException;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public int verifyToken(String token) throws TokenException {
        User user = userDAO.getSingleUserByToken(token);
        if(!user.getUsername().isEmpty()){
            return user.getId();
        }
        throw new TokenException();
    }
}
