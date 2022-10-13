package resources;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import resources.dto.LoginResponseDTO;
import services.LoginService;
import resources.dto.LoginRequestDTO;
import services.exceptions.TokenException;
import services.exceptions.UnauthorizedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoginResourceTest {

    private LoginResource loginResource;
    private LoginRequestDTO loginRequestDTO;
    private LoginResponseDTO loginResponseDTO;

    private LoginService loginService = mock(LoginService.class);

    private static final String USERNAME ="thomas";
    private static final String PASSWORD ="543";
    private static final String TOKEN = "5431-321";

    @BeforeEach
    void setup() {
        loginResource = new LoginResource();
        loginResource.setLoginService(loginService);

        loginRequestDTO = new LoginRequestDTO(USERNAME, PASSWORD);

        loginResponseDTO = new LoginResponseDTO(USERNAME, TOKEN);
    }

    @Test
    public void loginCredentialsAreOkTest() throws UnauthorizedException {

        Mockito.when(loginService.login(loginRequestDTO.getUser(), loginRequestDTO.getPassword())).thenReturn(loginResponseDTO);

        Response response = loginResource.login(loginRequestDTO);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void loginCredentialsAreNotOkTest() throws UnauthorizedException {

        Mockito.when(loginService.login(Mockito.anyString(), Mockito.anyString())).thenThrow(UnauthorizedException.class);

        assertThrows(UnauthorizedException.class, () -> loginResource.login(loginRequestDTO));
    }
}
