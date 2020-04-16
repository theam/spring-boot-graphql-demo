package com.theagilemonkeys.hiring.crmtest.graphql.resolvers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

@Component
@Secured({"IS_AUTHENTICATED_FULLY", "ROLE_ADMIN"})
public class AdminOnlyMutationResolver {
}
