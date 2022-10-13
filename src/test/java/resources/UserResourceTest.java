package resources;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import resources.dto.LoginResponseDTO;
import resources.dto.LoginRequestDTO;
import services.UserService;
import services.exceptions.UnauthorizedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserResourceTest {

    private UserResource userResource;
    private LoginRequestDTO loginRequestDTO;
    private LoginResponseDTO loginResponseDTO;

    private final UserService userService = mock(UserService.class);

    private static final String USERNAME ="thomas";
    private static final String PASSWORD ="543";
    private static final String TOKEN = "5431-321";

    @BeforeEach
    void setup() {
        userResource = new UserResource();
        userResource.setUserService(userService);

        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser(USERNAME);
        loginRequestDTO.setUser(PASSWORD);

        loginResponseDTO = new LoginResponseDTO(USERNAME, TOKEN);
    }

    @Test
    void loginCredentialsAreOkTest() throws UnauthorizedException {

        Mockito.when(userService.login(loginRequestDTO.getUser(), loginRequestDTO.getPassword())).thenReturn(loginResponseDTO);

        Response response = userResource.login(loginRequestDTO);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void loginCredentialsAreNotOkTest() throws UnauthorizedException {

        Mockito.when(userService.login(Mockito.anyString(), Mockito.anyString())).thenThrow(UnauthorizedException.class);

        assertThrows(UnauthorizedException.class, () -> userResource.login(loginRequestDTO));
    }
}
