# VehicleServiceCollectionSdkService

A list of all methods in the `VehicleServiceCollectionSdkService` service. Click on the method name to view detailed information about that method.

| Methods                                 | Description |
| :-------------------------------------- | :---------- |
| [createAVehicle](#createavehicle)       |             |
| [retrieveAVehicle](#retrieveavehicle)   |             |
| [updateACollection](#updateacollection) |             |
| [deleteAVehicle](#deleteavehicle)       |             |
| [getAllVehicles](#getallvehicles)       |             |

## createAVehicle

- HTTP Method: `POST`
- Endpoint: `/vehicles`

**Parameters**

| Name                  | Type                                                        | Required | Description  |
| :-------------------- | :---------------------------------------------------------- | :------- | :----------- |
| createAVehicleRequest | [CreateAVehicleRequest](../models/CreateAVehicleRequest.md) | ✅       | Request Body |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;
import com.vehicleservicecollectionsdk.models.CreateAVehicleRequest;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    CreateAVehicleRequest createAVehicleRequest = CreateAVehicleRequest.builder()
      .nickName("Bayrem's car")
      .vin("4M2DV11W4RDJ53329")
      .make("Mercury")
      .model("Villager")
      .year("1994")
      .miles(159864L)
      .build();

    Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.createAVehicle(
      createAVehicleRequest
    );

    System.out.println(response);
  }
}

```

## retrieveAVehicle

- HTTP Method: `GET`
- Endpoint: `/vehicles/{id}`

**Parameters**

| Name | Type   | Required | Description |
| :--- | :----- | :------- | :---------- |
| id   | String | ✅       |             |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.retrieveAVehicle(
      "{{vehicleID}}"
    );

    System.out.println(response);
  }
}

```

## updateACollection

- HTTP Method: `PATCH`
- Endpoint: `/vehicles/{id}`

**Parameters**

| Name                  | Type                                                        | Required | Description  |
| :-------------------- | :---------------------------------------------------------- | :------- | :----------- |
| id                    | String                                                      | ✅       |              |
| createAVehicleRequest | [CreateAVehicleRequest](../models/CreateAVehicleRequest.md) | ✅       | Request Body |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;
import com.vehicleservicecollectionsdk.models.CreateAVehicleRequest;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    CreateAVehicleRequest createAVehicleRequest = CreateAVehicleRequest.builder()
      .nickName("Bayrem's car")
      .vin("4M2DV11W4RDJ53329")
      .make("Mercury")
      .model("Villager")
      .year("1994")
      .miles(159864L)
      .build();

    Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.updateACollection(
      "{{vehicleID}}",
      createAVehicleRequest
    );

    System.out.println(response);
  }
}

```

## deleteAVehicle

- HTTP Method: `DELETE`
- Endpoint: `/vehicles/{id}`

**Parameters**

| Name | Type   | Required | Description |
| :--- | :----- | :------- | :---------- |
| id   | String | ✅       |             |

**Return Type**

`String`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    String response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.deleteAVehicle(
      "{{vehicleID}}"
    );

    System.out.println(response);
  }
}

```

## getAllVehicles

- HTTP Method: `GET`
- Endpoint: `/vehicles`

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.getAllVehicles();

    System.out.println(response);
  }
}

```
