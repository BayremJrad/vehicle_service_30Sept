# Vehicle Service API — Python Implementation

## Overview

A Flask REST API server implementing the Vehicle Service Collection API. All vehicle data is stored in an in-memory store — no database setup required. The server exposes a standard CRUD interface for managing vehicles.

## Prerequisites

- Python 3.8+
- pip

## Installation

```bash
cd app
pip install -r requirements.txt
```

## Running the Server

```bash
python main.py
```

The server starts on `http://localhost:3000` by default. To use a different port, set the `PORT` environment variable before starting:

```bash
PORT=8080 python main.py
```

## API Endpoints

| Method | Path | Description | Success Status |
|--------|------|-------------|----------------|
| GET | /vehicles | List all vehicles | 200 |
| POST | /vehicles | Create a new vehicle | 201 |
| GET | /vehicles/{id} | Retrieve a vehicle by ID | 200 |
| PATCH | /vehicles/{id} | Update a vehicle by ID | 200 |
| DELETE | /vehicles/{id} | Delete a vehicle by ID | 204 |

## Vehicle Model

A vehicle object has the following structure:

```json
{
  "id": 1,
  "nickName": "The Lisa Marie",
  "vin": "4M2DV11W4RDJ53329",
  "make": "Mercury",
  "model": "Villager",
  "year": "1994",
  "miles": 159864
}
```

## Error Responses

| Status Code | Reason |
|-------------|--------|
| 400 | Missing required field (e.g. `vin`) |
| 404 | Vehicle not found |
| 409 | Duplicate VIN — a vehicle with that VIN already exists |
| 500 | Unexpected server error |

## Testing with Postman

The **Vehicle Service Collection** in Postman includes pre-built requests for all endpoints above. To run them against your local server:

1. Open the **Vehicle Service Collection** in Postman.
2. Select (or create) an environment and set the `baseUrl` variable to:
   ```
   http://localhost:3000
   ```
3. Send requests directly from the collection to test each endpoint.
