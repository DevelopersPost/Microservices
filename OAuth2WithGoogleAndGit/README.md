# OAuth2 Login with Spring Boot

This is a Spring Boot application that demonstrates OAuth2 login with GitHub and Google, including logout functionality.

## Prerequisites

- Java 17 or later
- Maven 3.6 or later
- GitHub OAuth App (for GitHub login)
- Google OAuth 2.0 Client (for Google login)

## Setup Instructions

### 1. Configure OAuth2 Applications

#### GitHub OAuth App
1. Go to [GitHub Developer Settings](https://github.com/settings/developers)
2. Click on "New OAuth App"
3. Set the following:
   - Application name: Your App Name
   - Homepage URL: http://localhost:8080
   - Authorization callback URL: http://localhost:8080/login/oauth2/code/github
4. Click "Register application"
5. Generate a new client secret and save it

#### Google OAuth 2.0 Client
1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Navigate to "APIs & Services" > "Credentials"
4. Click "Create Credentials" > "OAuth client ID"
5. Configure the consent screen if not already done
6. Set the following:
   - Application type: Web application
   - Name: Your App Name
   - Authorized JavaScript origins: http://localhost:8080
   - Authorized redirect URIs: http://localhost:8080/login/oauth2/code/google
7. Click "Create" and save the client ID and secret

### 2. Configure Application Properties

Update the `src/main/resources/application.yml` file with your OAuth2 credentials:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: your-github-client-id
            client-secret: your-github-client-secret
          google:
            client-id: your-google-client-id
            client-secret: your-google-client-secret
```

## Running the Application

1. Build the application:
   ```bash
   mvn clean package
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Open your browser and navigate to: http://localhost:8080

## Features

- Login with GitHub
- Login with Google
- Secure session management
- Proper logout functionality
- User profile page
- Responsive design with Bootstrap

## Security Notes

- In production, always use HTTPS
- Store sensitive information (client secrets) in environment variables or a secure vault
- Enable CSRF protection (enabled by default in Spring Security)
- Configure proper CORS policies if needed
- Set appropriate session timeouts
