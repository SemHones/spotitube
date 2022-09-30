package resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.LoginService;
import services.dto.LoginRequestDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class LoginResourceTest {

    private static final int HTTP_STATUS_UNAUTHORIZED = 401;
    private LoginResource mockedLoginResource;
    private LoginService mockedLoginService;
    private LoginRequestDTO mockedRequestDTO;

    @BeforeEach
    void setup() {
        mockedLoginResource = mock(LoginResource.class);

        mockedLoginService = mock(LoginService.class);

        mockedRequestDTO = mock(LoginRequestDTO.class);
    }

//    @Test
//    void loginWithEmptyUsername() {
//        // Arrange
//        mockedLoginResource.setLoginService(mockedLoginService);
//
//        when(mockedLoginService)
//
//        // Act
//        var testValue = mockedLoginResource.login(mockedRequestDTO);
//
//        // Assert
//        assertEquals(HTTP_STATUS_UNAUTHORIZED, testValue.getStatus());
//    }
}
