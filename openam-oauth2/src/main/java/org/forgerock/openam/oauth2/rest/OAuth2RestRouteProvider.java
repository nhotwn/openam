/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */

package org.forgerock.openam.oauth2.rest;

import static org.forgerock.openam.audit.AuditConstants.Component.OAUTH;
import static org.forgerock.openam.rest.Routers.ssoToken;

import org.forgerock.openam.rest.AbstractRestRouteProvider;
import org.forgerock.openam.rest.ResourceRouter;
import org.forgerock.openam.rest.authz.ResourceOwnerOrSuperUserAuthzModule;

/**
 * OAuth2 REST route provider which registers the OAuth2 REST endpoints.
 *
 * @since 13.0.0
 */
public class OAuth2RestRouteProvider extends AbstractRestRouteProvider {

    @Override
    public void addResourceRoutes(ResourceRouter rootRouter, ResourceRouter realmRouter) {
        realmRouter.route("users/{user}/oauth2/applications")
                .auditAs(OAUTH)
                .authenticateWith(ssoToken())
                .authorizeWith(ResourceOwnerOrSuperUserAuthzModule.class)
                .toAnnotatedCollection(OAuth2UserApplications.class);
    }
}
