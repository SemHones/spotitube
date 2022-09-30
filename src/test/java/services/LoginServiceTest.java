package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServiceTest {

    private LoginService sut;

    @BeforeEach
    void setup() {
        sut = new LoginService();
    }

    @Test
    void loginWithCorrectInformationAndReturnFullName() {
        // Arrange
        String username = "sem";
        String password = "123";

        // Act
        var testValue = sut.login(username, password);

        // Assert
        assertEquals("Sem Hones", testValue);
    }

    @Test
    void loginWithFalsePasswordAndReturnEmptyString() {
        // Arrange
        String username = "sem";
        String password = "321";

        // Act
        var testValue = sut.login(username, password);

        // Assert
        assertEquals("", testValue);
    }

    @Test
    void loginWithoutInformationAndReturnEmptyString() {
        // Arrange
        String username = "";
        String password = "";

        // Act
        var testValue = sut.login(username, password);

        // Assert
        assertEquals("", testValue);
    }
}
