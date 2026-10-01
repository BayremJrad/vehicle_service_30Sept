# VehicleServiceCollectionSdk Java SDK 1.0.0

Welcome to the VehicleServiceCollectionSdk SDK documentation. This guide will help you get started with integrating and using the VehicleServiceCollectionSdk SDK in your project.

## Versions

- SDK version: `1.0.0`

## About the API

# Vehicle Service Collection — Getting Started

## Overview

The **Vehicle Service Collection** provides a complete set of CRUD operations for managing vehicle records through a RESTful API. With this collection you can:

- **Create** a new vehicle record
- **Retrieve** a single vehicle by ID
- **Update** an existing vehicle's details
- **Delete** a vehicle record
- **List** all vehicles

---

## Base URL

All requests in this collection use the `{{baseUrl}}` variable. This variable is pre-configured in the **Mock Environment**:

| Variable  | Value                                                        |
| --------- | ------------------------------------------------------------ |
| `baseUrl` | `https://26f30cc1-9cd3-40cd-bd9f-47db01a45f66.mock.pstmn.io` |

> **Before sending any requests**, select the **Mock Environment** from the environment dropdown in the top-right corner of Postman. Without an active environment, `{{baseUrl}}` will not resolve and requests will fail.

---

## Authentication

This collection uses **API Key** authentication. The API key is stored securely in the **Postman Vault**.

### What is the Postman Vault?

The Postman Vault is a secure, encrypted store built into Postman for managing sensitive values such as API keys, tokens, and passwords. Values stored in the Vault are never exposed in plain text in your collection or environment configuration, keeping your credentials safe.

### How to Add Your API Key to the Vault

1. Open the **Vault** — click the lock/vault icon in the environment selector area or navigate to it via your workspace settings.
2. Add a new secret with:
   - **Key:** `apiKey`
   - **Value:** your actual API key value
3. Save the entry.

The collection's auth configuration references this secret as `vault:apiKey`. Postman will automatically resolve this reference and inject the key into your requests at send time — no manual copy-pasting required.

---

## Available Requests

| Method   | Endpoint                   | Description              |
| -------- | -------------------------- | ------------------------ |
| `POST`   | `{{baseUrl}}/vehicles`     | Create a new vehicle     |
| `GET`    | `{{baseUrl}}/vehicles/:id` | Retrieve a vehicle by ID |
| `PATCH`  | `{{baseUrl}}/vehicles/:id` | Update a vehicle by ID   |
| `DELETE` | `{{baseUrl}}/vehicles/:id` | Delete a vehicle by ID   |
| `GET`    | `{{baseUrl}}/vehicles`     | Get all vehicles         |

---

## Request & Response Format

All requests and responses in this collection use **JSON**.

The following headers are already configured on the collection:

- `Content-Type: application/json` — tells the server the request body is JSON
- `Accept: application/json` — tells the server to return a JSON response

You do not need to set these headers manually on individual requests.

---

## Example: Creating a Vehicle

Open the **Create a vehicle** request (`POST {{baseUrl}}/vehicles`). The request body is pre-populated with the following sample payload:

```json
{
  "nickName": "The Lisa Marie",
  "vin": "4M2DV11W4RDJ53329",
  "make": "Mercury",
  "model": "Villager",
  "year": "1994",
  "miles": 159864
}
```

Send this request to create your first vehicle. The response will include the newly created vehicle object along with its unique `id`.

---

## Path Variables

Requests that target a specific vehicle — **Retrieve**, **Update**, and **Delete** — include a `:id` path variable in the URL (e.g. `{{baseUrl}}/vehicles/:id`).

Before sending one of these requests, set the `id` path variable to the vehicle's ID:

1. Open the request in Postman.
2. Go to the **Params** tab.
3. Under **Path Variables**, enter the vehicle `id` value in the **Value** column next to `id`.

---

## Quick Start Steps

1. **Select the Mock Environment** — choose **Mock Environment** from the environment dropdown in the top-right corner of Postman.
2. **Add your API key to the Vault** — open the Postman Vault and add a secret with key `apiKey` and your API key as the value.
3. **Create your first vehicle** — open the **Create a vehicle** request and click **Send**. A new vehicle record will be returned in the response.
4. **Use the vehicle ID** — copy the `id` from the response and paste it into the path variable of the **Retrieve a vehicle**, **Update a collection**, or **Delete a vehicle** requests to interact with that specific record.

## Table of Contents

- [Setup & Configuration](#setup--configuration)
  - [Supported Language Versions](#supported-language-versions)
  - [Installation](#installation)
- [Authentication](#authentication)
  - [API Key Authentication](#api-key-authentication)
- [Setting a Custom Timeout](#setting-a-custom-timeout)
- [Injecting a Custom HTTP Client](#injecting-a-custom-http-client)
- [Accessing the Raw HTTP Response](#accessing-the-raw-http-response)
- [Sample Usage](#sample-usage)
- [Services](#services)
- [Models](#models)

# Setup & Configuration

## Supported Language Versions

This SDK is compatible with the following versions: `Java >= 1.8`

## Installation

If you use Maven, place the following within the _dependency_ tag in your `pom.xml` file:

```XML
<dependency>
    <groupId>com</groupId>
    <artifactId>vehicleservicecollectionsdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

If you use Gradle, paste the next line inside the _dependencies_ block of your `build.gradle` file:

```Gradle
implementation("com:vehicleservicecollectionsdk:1.0.0")
```

If you use JAR files, package the SDK by running the following command:

```shell
mvn compile assembly:single
```

Then, add the JAR file to your project's classpath.

## Authentication

### API Key Authentication

The VehicleServiceCollectionSdk API uses API keys as a form of authentication. An API key is a unique identifier used to authenticate a user, developer, or a program that is calling the API.

#### Setting the API key

When you initialize the SDK, you can set the API key as follows:

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;

public class Main {

  public static void main(String[] args) {
    ApiKeyAuthConfig apiKeyAuthConfig = ApiKeyAuthConfig.builder()
      .apiKey("YOUR_API_KEY")
      .apiKeyHeader("YOUR_API_KEY_HEADER")
      .build();

    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(apiKeyAuthConfig)
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );
  }
}

```

If you need to set or update the API key after initializing the SDK, you can use:

```java
vehicleServiceCollectionSdk.setApiKey('YOUR_API_KEY');
vehicleServiceCollectionSdk.setApiKeyHeader('YOUR_API_KEY_HEADER');
```

## Setting a Custom Timeout

You can set a custom timeout for the SDK's HTTP requests as follows:

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .timeout(10000)
      .build();
    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );
  }
}

```

## Injecting a Custom HTTP Client

You can supply your own `OkHttpClient` — for example to configure a proxy, a shared connection pool, custom TLS, timeouts, or your own interceptors. The SDK derives its client from the one you provide (preserving your transport settings and interceptors) and layers its own interceptors (such as authentication and retry) on top, so the SDK keeps working as usual.

```java
OkHttpClient customClient = new OkHttpClient.Builder().addInterceptor(new MyInterceptor()).build();

VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
  VehicleServiceCollectionSdkConfig.builder().httpClient(customClient).build()
);

```

`MyInterceptor` above is a placeholder for your own `okhttp3.Interceptor`.

> Your client's interceptors are added ahead of the SDK's, so on the outbound request they run before the SDK adds its own headers. A logging interceptor placed this way will **not** see SDK-injected headers such as authentication.

> **Timeout precedence:** when you inject a client, the config-level `timeout` is not applied — your client's own timeout settings are preserved. Per-request, method, and service-level timeout overrides still apply, layered on top of your client.

## Accessing the Raw HTTP Response

Every service method returns the parsed response body by default. When you also need the status code, response headers, or the raw HTTP response, call the same method through the per-call `withRawResponse()` accessor. The default methods are unchanged, so this is fully opt-in.

```java
VehicleServiceCollectionSdkResponse<Object> response =
    vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.withRawResponse().getAllVehicles();

response.getData();
response.getMetadata().getStatusCode();
response.getMetadata().getHeaders();
response.getRaw();
```

`getData()` returns the same value the default method would; `getMetadata()` exposes the status code and headers, and `getRaw()` exposes the underlying HTTP response.

# Sample Usage

Below is a comprehensive example demonstrating how to authenticate and call a simple endpoint:

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;
import com.vehicleservicecollectionsdk.exceptions.ApiError;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    try {
      Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.getAllVehicles();

      System.out.println(response);
    } catch (ApiError e) {
      e.printStackTrace();
    }

    System.exit(0);
  }
}

```

## Services

The SDK provides various services to interact with the API.

<details>
<summary>Below is a list of all available services with links to their detailed documentation:</summary>

| Name                                                                                               |
| :------------------------------------------------------------------------------------------------- |
| [VehicleServiceCollectionSdkService](documentation/services/VehicleServiceCollectionSdkService.md) |

</details>

## Models

The SDK includes several models that represent the data structures used in API requests and responses. These models help in organizing and managing the data efficiently.

<details>
<summary>Below is a list of all available models with links to their detailed documentation:</summary>

| Name                                                                   | Description |
| :--------------------------------------------------------------------- | :---------- |
| [CreateAVehicleRequest](documentation/models/CreateAVehicleRequest.md) |             |

</details>
