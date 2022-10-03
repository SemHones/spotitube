package services;

import resources.exceptions.TokenException;

public class UserService {

    public int verifyToken(String token) throws TokenException {
        //TODO: create way to get token from user
        if(token.equals("1234-1234")){
            return 1;
        }
        throw new TokenException();
    }
}
