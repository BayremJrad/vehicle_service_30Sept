package com.vehicleservicecollectionsdk;

import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;
import com.vehicleservicecollectionsdk.http.Environment;
import com.vehicleservicecollectionsdk.http.interceptors.DefaultHeadersInterceptor;
import com.vehicleservicecollectionsdk.http.interceptors.LoggingInterceptor;
import com.vehicleservicecollectionsdk.http.interceptors.RetryInterceptor;
import com.vehicleservicecollectionsdk.logging.Logger;
import com.vehicleservicecollectionsdk.services.VehicleServiceCollectionSdkService;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/**
 * # Vehicle Service Collection — Getting Started
 *
 * ## Overview
 *
 * The **Vehicle Service Collection** provides a complete set of CRUD operations for managing vehicle records through a RESTful API. With this collection you can:
 *
 * - **Create** a new vehicle record
 * - **Retrieve** a single vehicle by ID
 * - **Update** an existing vehicle's details
 * - **Delete** a vehicle record
 * - **List** all vehicles
 *
 * ---
 *
 * ## Base URL
 *
 * All requests in this collection use the `{{baseUrl}}` variable. This variable is pre-configured in the **Mock Environment**:
 *
 * | Variable | Value |
 * |---|---|
 * | `baseUrl` | `https://26f30cc1-9cd3-40cd-bd9f-47db01a45f66.mock.pstmn.io` |
 *
 * &gt; **Before sending any requests**, select the **Mock Environment** from the environment dropdown in the top-right corner of Postman. Without an active environment, `{{baseUrl}}` will not resolve and requests will fail.
 *
 * ---
 *
 * ## Authentication
 *
 * This collection uses **API Key** authentication. The API key is stored securely in the **Postman Vault**.
 *
 * ### What is the Postman Vault?
 *
 * The Postman Vault is a secure, encrypted store built into Postman for managing sensitive values such as API keys, tokens, and passwords. Values stored in the Vault are never exposed in plain text in your collection or environment configuration, keeping your credentials safe.
 *
 * ### How to Add Your API Key to the Vault
 *
 * 1. Open the **Vault** — click the lock/vault icon in the environment selector area or navigate to it via your workspace settings.
 * 2. Add a new secret with:
 * - **Key:** `apiKey`
 * - **Value:** your actual API key value
 * 3. Save the entry.
 *
 * The collection's auth configuration references this secret as `vault:apiKey`. Postman will automatically resolve this reference and inject the key into your requests at send time — no manual copy-pasting required.
 *
 * ---
 *
 * ## Available Requests
 *
 * | Method | Endpoint | Description |
 * |---|---|---|
 * | `POST` | `{{baseUrl}}/vehicles` | Create a new vehicle |
 * | `GET` | `{{baseUrl}}/vehicles/:id` | Retrieve a vehicle by ID |
 * | `PATCH` | `{{baseUrl}}/vehicles/:id` | Update a vehicle by ID |
 * | `DELETE` | `{{baseUrl}}/vehicles/:id` | Delete a vehicle by ID |
 * | `GET` | `{{baseUrl}}/vehicles` | Get all vehicles |
 *
 * ---
 *
 * ## Request &amp; Response Format
 *
 * All requests and responses in this collection use **JSON**.
 *
 * The following headers are already configured on the collection:
 *
 * - `Content-Type: application/json` — tells the server the request body is JSON
 * - `Accept: application/json` — tells the server to return a JSON response
 *
 * You do not need to set these headers manually on individual requests.
 *
 * ---
 *
 * ## Example: Creating a Vehicle
 *
 * Open the **Create a vehicle** request (`POST {{baseUrl}}/vehicles`). The request body is pre-populated with the following sample payload:
 *
 * ```json
 * {
 * "nickName": "The Lisa Marie",
 * "vin": "4M2DV11W4RDJ53329",
 * "make": "Mercury",
 * "model": "Villager",
 * "year": "1994",
 * "miles": 159864
 * }
 * ```
 *
 * Send this request to create your first vehicle. The response will include the newly created vehicle object along with its unique `id`.
 *
 * ---
 *
 * ## Path Variables
 *
 * Requests that target a specific vehicle — **Retrieve**, **Update**, and **Delete** — include a `:id` path variable in the URL (e.g. `{{baseUrl}}/vehicles/:id`).
 *
 * Before sending one of these requests, set the `id` path variable to the vehicle's ID:
 *
 * 1. Open the request in Postman.
 * 2. Go to the **Params** tab.
 * 3. Under **Path Variables**, enter the vehicle `id` value in the **Value** column next to `id`.
 *
 * ---
 *
 * ## Quick Start Steps
 *
 * 1. **Select the Mock Environment** — choose **Mock Environment** from the environment dropdown in the top-right corner of Postman.
 * 2. **Add your API key to the Vault** — open the Postman Vault and add a secret with key `apiKey` and your API key as the value.
 * 3. **Create your first vehicle** — open the **Create a vehicle** request and click **Send**. A new vehicle record will be returned in the response.
 * 4. **Use the vehicle ID** — copy the `id` from the response and paste it into the path variable of the **Retrieve a vehicle**, **Update a collection**, or **Delete a vehicle** requests to interact with that specific record.
 *
 */
public class VehicleServiceCollectionSdk {

  public final VehicleServiceCollectionSdkService vehicleServiceCollectionSdk;

  private final VehicleServiceCollectionSdkConfig config;

  /**
   * Constructs a new instance of VehicleServiceCollectionSdk with default configuration.
   */
  public VehicleServiceCollectionSdk() {
    // Default configs
    this(VehicleServiceCollectionSdkConfig.builder().build());
  }

  /**
   * Constructs a new instance of VehicleServiceCollectionSdk with custom configuration.
   * Initializes all services, HTTP client, and optional OAuth token manager.
   *
   * @param config The SDK configuration including base URL, authentication, timeout, and retry settings
   */
  public VehicleServiceCollectionSdk(VehicleServiceCollectionSdkConfig config) {
    this.config = config;

    // A user-supplied client is augmented (not replaced): the SDK derives its client from
    // the injected instance so its transport settings and interceptors are preserved, then
    // layers the SDK's own interceptors on top.
    final OkHttpClient customHttpClient = config.getHttpClient();
    final OkHttpClient.Builder httpClientBuilder =
      (customHttpClient != null
          ? customHttpClient.newBuilder()
          : new OkHttpClient.Builder()).addInterceptor(new DefaultHeadersInterceptor(config))
        .addInterceptor(new RetryInterceptor(config.getRetryConfig()))
        // Logging is added last so it observes the fully-decorated request (auth headers
        // included, then redacted). Silent by default — see LogConfig.
        .addInterceptor(new LoggingInterceptor(Logger.from(config.getLogConfig())));

    // Only apply the SDK's default read timeout when building the client ourselves; a
    // user-supplied client owns its own transport (timeout) settings.
    if (customHttpClient == null) {
      httpClientBuilder.readTimeout(config.getTimeout(), TimeUnit.MILLISECONDS);
    }

    final OkHttpClient httpClient = httpClientBuilder.build();

    this.vehicleServiceCollectionSdk = new VehicleServiceCollectionSdkService(httpClient, config);
  }

  /**
   * Sets the environment for all API requests.
   *
   * @param environment The environment to use (e.g., DEFAULT, PRODUCTION, STAGING)
   */
  public void setEnvironment(Environment environment) {
    setBaseUrl(environment.getUrl());
  }

  /**
   * Sets the base URL for all API requests.
   *
   * @param baseUrl The base URL to use for API requests
   */
  public void setBaseUrl(String baseUrl) {
    this.config.setBaseUrl(baseUrl);
  }

  /**
   * Sets the API key for all API requests.
   *
   * @param apiKey The API key to use for authentication
   */
  public void setApiKey(String apiKey) {
    ApiKeyAuthConfig apiKeyAuthConfig = this.config.getApiKeyAuthConfig();
    apiKeyAuthConfig.setApiKey(apiKey);
  }

  /**
   * Sets the API key header name for all API requests.
   *
   * @param apiKeyHeader The header name to use for the API key
   */
  public void setApiKeyHeader(String apiKeyHeader) {
    ApiKeyAuthConfig apiKeyAuthConfig = this.config.getApiKeyAuthConfig();
    apiKeyAuthConfig.setApiKeyHeader(apiKeyHeader);
  }
}
// c029837e0e474b76bc487506e8799df5e3335891efe4fb02bda7a1441840310c
