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

package org.apache.submarine.server.submitter.k8s.util;

import java.util.ArrayList;

import io.kubernetes.client.openapi.models.V1OwnerReference;
import org.apache.submarine.server.k8s.utils.OwnerReferenceConfig;

public class OwnerReferenceUtils {
  
  public static ArrayList<V1OwnerReference> getOwnerReference() {
    ArrayList<V1OwnerReference> ownerReferences = new ArrayList<>();
    V1OwnerReference owner = new V1OwnerReference();
    String uid = OwnerReferenceConfig.getSubmarineUid();
    if (uid != null) {
      String apiVersion = OwnerReferenceConfig.getSubmarineApiversion();
      String kind = OwnerReferenceConfig.getSubmarineKind();
      String name = OwnerReferenceConfig.getSubmarineName();
      Boolean blockOwnerDeletion = true;
      Boolean controller = true;
      owner.setApiVersion(apiVersion);
      owner.setKind(kind);
      owner.setName(name);
      owner.setUid(uid);
      owner.setBlockOwnerDeletion(blockOwnerDeletion);
      owner.setController(controller);
      ownerReferences.add(owner);
    }
    return ownerReferences;
  }
}
