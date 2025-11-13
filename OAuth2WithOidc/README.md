# OAuth2 and OpenID Connect (OIDC) Implementation

This project demonstrates the implementation of both OAuth2 and OpenID Connect (OIDC) authentication flows using Spring Security.

## Table of Contents
- [OIDC Flow](#oidc-flow)
- [OAuth2 Flow](#oauth2-flow)
- [Key Differences](#key-differences)
- [Getting Started](#getting-started)

## OIDC Flow

### Initial Request
```
GET http://localhost:8080/oauth2/authorization/google
```
Spring Security intercepts this and constructs the authorization URL:

```
https://accounts.google.com/o/oauth2/v2/auth
  ?response_type=code
  &client_id=327663326722-t3cfnsvkqtc2tsdm55unlu7qcnokr06r.apps.googleusercontent.com
  &scope=openid%20profile%20email
  &state=MzdqcW7yD0kLHSgWVsOXeu6EkpMLJ34ji6Bx1KrhJrI%3D
  &redirect_uri=http://localhost:8080/login/oauth2/code/google
  &nonce=ZvW9IBUf917QSV5A-G6f31MlJEjuBCyDevektKBpG7c
```

#### Parameters Explained:
- `response_type=code`: Using Authorization Code flow
- `scope=openid profile email`: Requesting OIDC scopes
  - `openid`: Enables OIDC (ID Token)
  - `profile`: Basic profile info
  - `email`: User's email address
- `state`: CSRF protection token
- `nonce`: Security parameter to prevent replay attacks (OIDC-specific)

### Callback
```
GET http://localhost:8080/login/oauth2/code/google
  ?state=MzdqcW7yD0kLHSgWVsOXeu6EkpMLJ34ji6Bx1KrhJrI%3D
  &code=4%2F0Ab32j915Gq4hKTT9L3pfLaxbrsUL8DTJtNzjANixi4Y9ZWXzHo8Kk465QYY07L4QyPgr0A
  &scope=email+profile+openid
  &authuser=0
  &prompt=none
```
- Google redirects back with an authorization code
- The state matches the one sent initially (security check)
- The code is a short-lived authorization code

## OAuth2 Flow

### Initial Request
```
GET http://localhost:8080/oauth2/authorization/google
```

### Authorization URL (OAuth2)
```
https://accounts.google.com/o/oauth2/v2/auth
  ?response_type=code
  &client_id=327663326722-t3cfnsvkqtc2tsdm55unlu7qcnokr06r.apps.googleusercontent.com
  &scope=email%20profile
  &state=sCn5OQqb6QreDT5GmZ8dQEd5g9OQD69F7Pwjvsbz0o8%3D
  &redirect_uri=http://localhost:8080/login/oauth2/code/google
```

### Callback (OAuth2)
```
GET http://localhost:8080/login/oauth2/code/google
  ?state=sCn5OQqb6QreDT5GmZ8dQEd5g9OQD69F7Pwjvsbz0o8%3D
  &code=4%2F0Ab32j93S5B3LUKmYj8A3numkBNQiyCRxyfexSgyOm-l75lEy3YlweJqaw2uPt1gnPQ1Skg
  &scope=email+profile
  &authuser=0
  &prompt=none
```

## Key Differences

### OIDC
- Uses `openid` scope
- Includes `nonce` parameter
- Returns ID Token (JWT) with user info
- Provides standardized user info claims
- Single round-trip for user authentication

### OAuth2
- No `openid` scope
- No `nonce` parameter
- No ID Token (only access token)
- Requires additional API call for user info
- Primarily for authorization, not authentication

## Getting Started

1. Clone the repository
2. Configure your OAuth2 client credentials in `application.yml`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Access the application at `http://localhost:8080`
