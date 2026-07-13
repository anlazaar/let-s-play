## TODO
## Remaining
[X] - Are the annotations @EnableWebSecurity, @EnableMethodSecurity, @PermitAll, @PostAuthorize and @PreAuthorize used correctly?

## Bonus
[X] - Check if CORS policies are set appropriately.
[X] - Check if rate limiting is implemented to prevent brute force attacks.

401 → missing/invalid/expired JWT
403 → authenticated user lacks required role or isn't the product owner
404 → user/product not found
400 → validation or malformed request body
409 → email already exists (registration conflict)

# README

- Before lunch

```
export JWT_SECRET="THIS_IS_A_VERY_LONG_SECRET_KEY_CHANGE_IT_123456789"
```
