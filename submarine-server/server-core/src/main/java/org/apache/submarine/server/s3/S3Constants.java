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

package org.apache.submarine.server.s3;

/**
 * S3(Minio) Constants
 */
public class S3Constants {

  /* default endpoint, it is the same as `submarine.s3.endpoint` */
  public static final String DEFAULT_ENDPOINT = "http://submarine-minio-service:9000";

  /* default access key, it is the same as `submarine.s3.accessKeyId` */
  public static final String DEFAULT_ACCESSKEY = "submarine_minio";

  /* default secret key, it is the same as `submarine.s3.secretAccessKey` */
  public static final String DEFAULT_SECRETKEY = "submarine_minio";

  /* default bucket */
  public static final String BUCKET = "submarine";
}
