package resources.dto;

public class LoginResponseDTO {
    private String user;
    private String token;

    public LoginResponseDTO(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
