/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.submarine.server.security;

import org.apache.submarine.server.security.common.AuthFlowType;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.engine.CallbackLogic;
import org.pac4j.core.engine.DefaultCallbackLogic;
import org.pac4j.core.engine.DefaultLogoutLogic;
import org.pac4j.core.engine.DefaultSecurityLogic;
import org.pac4j.core.engine.LogoutLogic;
import org.pac4j.core.engine.SecurityLogic;
import org.pac4j.core.http.adapter.HttpActionAdapter;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.util.FindBest;
import org.pac4j.jee.context.JEEContextFactory;
import org.pac4j.jee.context.session.JEESessionStoreFactory;
import org.pac4j.jee.http.adapter.JEEHttpActionAdapter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Provide security methods for different authentication types
 */
public abstract class SecurityProvider<T extends Filter, R extends CommonProfile> {

  protected final String DEFAULT_AUTHORIZER = "isAuthenticated";

  protected Config pac4jConfig;

  /**
   * Get authentication flow type
   */
  public AuthFlowType getAuthFlowType() {
    return AuthFlowType.TOKEN;
  }

  /**
   * Get filter class
   */
  public abstract Class<T> getFilterClass();

  /**
   * Get pac4j config
   */
  public Config getConfig() {
    if (this.pac4jConfig == null) this.pac4jConfig = createConfig();
    return pac4jConfig;
  }

  /**
   * Create pac4j config
   */
  protected abstract Config createConfig();

  /**
   * Get pac4j client
   */
  public abstract String getClient(HttpServletRequest httpServletRequest);

  /**
   * Create {@link WebContext}
   */
  protected WebContext createWebContext(HttpServletRequest hsRequest, HttpServletResponse hsResponse) {
    return FindBest.webContextFactory(null, getConfig(), JEEContextFactory.INSTANCE)
        .newContext(hsRequest, hsResponse);
  }

  /**
   * Create {@link SessionStore}
   */
  protected SessionStore createSessionStore(HttpServletRequest hsRequest, HttpServletResponse hsResponse) {
    return FindBest.sessionStoreFactory(null, getConfig(), JEESessionStoreFactory.INSTANCE)
        .newSessionStore(hsRequest, hsResponse);
  }

  /**
   * Create {@link HttpActionAdapter}
   */
  protected HttpActionAdapter createHttpActionAdapter() {
    return FindBest.httpActionAdapter(null, getConfig(), JEEHttpActionAdapter.INSTANCE);
  }

  /**
   * Create {@link SecurityLogic}
   */
  protected SecurityLogic createSecurityLogic() {
    return FindBest.securityLogic(null, getConfig(), DefaultSecurityLogic.INSTANCE);
  }

  /**
   * Create {@link CallbackLogic}
   */
  protected CallbackLogic createCallbackLogic() {
    return FindBest.callbackLogic(null, getConfig(), DefaultCallbackLogic.INSTANCE);
  }

  /**
   * Create {@link LogoutLogic}
   */
  protected LogoutLogic createLogoutLogic() {
    return FindBest.logoutLogic(null, this.pac4jConfig, DefaultLogoutLogic.INSTANCE);
  }

  /**
   * Process authentication information and return user profile
   */
  public abstract Optional<R> perform(HttpServletRequest hsRequest, HttpServletResponse hsResponse);

  /**
   * Handling login perform
   */
  public void login(HttpServletRequest hsRequest, HttpServletResponse hsResponse) { }

  /**
   * Handling callback perform
   */
  public void callback(HttpServletRequest hsRequest, HttpServletResponse hsResponse) { }

  /**
   * Handling logout perform
   */
  public void logout(HttpServletRequest hsRequest, HttpServletResponse hsResponse) { }

}
