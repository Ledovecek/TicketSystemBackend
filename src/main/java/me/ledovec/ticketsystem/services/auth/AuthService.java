package me.ledovec.ticketsystem.services.auth;

import com.google.common.hash.Hashing;
import me.ledovec.ticketsystem.entities.credentials.Credentials;
import me.ledovec.ticketsystem.entities.luckperms.UserGroup;
import me.ledovec.ticketsystem.entities.tickets.Token;
import me.ledovec.ticketsystem.repositories.credentials.CredentialsRepository;
import me.ledovec.ticketsystem.repositories.luckperms.UserGroupRepository;
import me.ledovec.ticketsystem.repositories.tickets.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://127.0.0.1:5173/", methods = RequestMethod.POST, allowedHeaders = "Content-Type", allowCredentials = "true")
public class AuthService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("auth")
    public AuthResponse auth(@RequestBody Credentials credentials) {
        Credentials credentialsIntern = credentialsRepository.getCredentialsByRealName(credentials.getRealName());
        if (credentialsIntern != null) {
            String passwordIntern = credentialsIntern.getPassword();
            Pattern passwordRegexPattern = Pattern.compile("^\\$([^$]+)\\$([^$]+)");
            Matcher passwordMatcher = passwordRegexPattern.matcher(passwordIntern);

            if(passwordMatcher.find()) {
                String group1 = passwordMatcher.group(1); // Algorithm prefix
                String group2 = passwordMatcher.group(2); // Salt

                String firstHash = Hashing.sha256().hashString(credentials.getPassword(), StandardCharsets.UTF_8).toString();
                String secondPassword = Hashing.sha256().hashString(firstHash + group2, StandardCharsets.UTF_8).toString();
                String encryptedPassword = "$" + group1 + "$" + group2 + "$" + secondPassword;

                UserGroup userGroup = userGroupRepository.getUserGroupByUsername(credentialsIntern.getRealName());
                String token = UUID.randomUUID().toString();
                Token databaseToken = new Token(0L, token, userGroup.getUid());
                tokenRepository.save(databaseToken);
                return new AuthResponse(encryptedPassword.equals(credentialsIntern.getPassword()), token);
            }
            return AuthResponse.empty();
        }
        return AuthResponse.empty();
    }

}
