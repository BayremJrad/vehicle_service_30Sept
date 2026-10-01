// Vehicle Service — Stateful Mock
// Uses pm.state for in-memory persistence of vehicle records
// Vehicles are stored as a JSON object keyed by vehicle ID

// @endpoint POST /vehicles
// @endpoint GET /vehicles
// @endpoint GET /vehicles/:id
// @endpoint PATCH /vehicles/:id
// @endpoint DELETE /vehicles/:id

const http = require('http');
const PORT = process.env.PORT || 4501;

// Helper: send a JSON response
function sendJson(res, statusCode, body) {
  res.writeHead(statusCode, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify(body));
}

// Helper: read and parse request body
function readBody(req) {
  return new Promise((resolve, reject) => {
    let data = '';
    req.on('data', chunk => { data += chunk; });
    req.on('end', () => {
      try { resolve(JSON.parse(data || '{}')); }
      catch (e) { reject(e); }
    });
    req.on('error', reject);
  });
}

// Helper: get the vehicles store from pm.state
async function getVehicles() {
  return (await pm.state.get('vehicles')) || {};
}

// Helper: save the vehicles store to pm.state
async function saveVehicles(vehicles) {
  await pm.state.set('vehicles', vehicles);
}

const server = http.createServer(async (req, res) => {
  const method = req.method.toUpperCase();
  const urlPath = new URL(req.url, 'http://localhost').pathname;

  // Extract :id from path (e.g. /vehicles/abc123 → abc123)
  const pathParts = urlPath.replace(/^\/+/, '').split('/');
  // pathParts[0] = 'vehicles', pathParts[1] = id (if present)
  const resourceId = pathParts.length >= 2 && pathParts[1] ? pathParts[1] : null;

  try {
    // Route: POST /vehicles — Create a new vehicle
    if (method === 'POST' && pathParts[0] === 'vehicles' && !resourceId) {
      let body = {};
      try {
        body = await readBody(req);
      } catch (e) {
        sendJson(res, 400, { error: 'Invalid JSON body' });
        return;
      }

      const id = Date.now().toString() + Math.random().toString(36).slice(2, 7);
      const vehicle = {
        id,
        nickName: body.nickName || '',
        vin:      body.vin      || '',
        make:     body.make     || '',
        model:    body.model    || '',
        year:     body.year     || '',
        miles:    body.miles    !== undefined ? body.miles : 0
      };

      const vehicles = await getVehicles();
      vehicles[id] = vehicle;
      await saveVehicles(vehicles);
      sendJson(res, 201, vehicle);

    // Route: GET /vehicles — Retrieve all vehicles
    } else if (method === 'GET' && pathParts[0] === 'vehicles' && !resourceId) {
      const vehicles = await getVehicles();
      sendJson(res, 200, Object.values(vehicles));

    // Route: GET /vehicles/:id — Retrieve a vehicle by ID
    } else if (method === 'GET' && pathParts[0] === 'vehicles' && resourceId) {
      const vehicles = await getVehicles();
      const vehicle = vehicles[resourceId];
      if (!vehicle) {
        sendJson(res, 404, { error: `Vehicle with id '${resourceId}' not found` });
        return;
      }
      sendJson(res, 200, vehicle);

    // Route: PATCH /vehicles/:id — Update a vehicle by ID
    } else if (method === 'PATCH' && pathParts[0] === 'vehicles' && resourceId) {
      const vehicles = await getVehicles();
      const existing = vehicles[resourceId];
      if (!existing) {
        sendJson(res, 404, { error: `Vehicle with id '${resourceId}' not found` });
        return;
      }

      let body = {};
      try {
        body = await readBody(req);
      } catch (e) {
        sendJson(res, 400, { error: 'Invalid JSON body' });
        return;
      }

      const updated = Object.assign({}, existing, {
        nickName: body.nickName !== undefined ? body.nickName : existing.nickName,
        vin:      body.vin      !== undefined ? body.vin      : existing.vin,
        make:     body.make     !== undefined ? body.make     : existing.make,
        model:    body.model    !== undefined ? body.model    : existing.model,
        year:     body.year     !== undefined ? body.year     : existing.year,
        miles:    body.miles    !== undefined ? body.miles    : existing.miles
      });

      vehicles[resourceId] = updated;
      await saveVehicles(vehicles);
      sendJson(res, 200, updated);

    // Route: DELETE /vehicles/:id — Delete a vehicle by ID
    } else if (method === 'DELETE' && pathParts[0] === 'vehicles' && resourceId) {
      const vehicles = await getVehicles();
      if (!vehicles[resourceId]) {
        sendJson(res, 404, { error: `Vehicle with id '${resourceId}' not found` });
        return;
      }
      delete vehicles[resourceId];
      await saveVehicles(vehicles);
      sendJson(res, 200, { message: `Vehicle '${resourceId}' deleted successfully` });

    } else {
      sendJson(res, 404, { error: 'Mock route not defined', method, url: req.url });
    }
  } catch (err) {
    sendJson(res, 500, { error: 'Internal mock error', details: err.message });
  }
});

server.listen(PORT, () => console.log('Mock server running on port ' + PORT));