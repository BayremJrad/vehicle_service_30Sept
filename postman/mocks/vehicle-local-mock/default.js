/**
 * Get started with your virtual shopping cart.
 *   1. POST /cart/items   add items to buy
 *   2. GET  /cart         see what's in the cart
 *   3. POST /checkout     place the order
 * 
 * Click Start to build your cart and checkout.
 * 
 * Add more routes and annotate using @endpoint <method> /<path>.
 * You can also select existing requests and examples to mock.
 * 
 * Deploying this mock gives you a url which can be shared, used in CI and more.
 */
const http = require("http");
const PORT = process.env.PORT || 4500;
const JSON_HEADER = { "Content-Type": "application/json" };

const server = http.createServer(async (req, res) => {
  const { method, url } = req;
  const cart = (await pm.state.get("cart")) || [];



  // @endpoint POST /cart
  if (pm.mock.matchRequest("postman/collections/Vehicle Service Collection/Create a vehicle.request.yaml", req)) {
    return pm.mock.sendExample("postman/collections/Vehicle Service Collection/.resources/Create a vehicle.resources/examples/201 - Success.example.yaml", res);
  }
    // @endpoint GET /cart
  if (pm.mock.matchRequest("postman/collections/Vehicle Service Collection/Retrieve a vehicle.request.yaml", req)) {

    return pm.mock.sendExample("postman/collections/Vehicle Service Collection/.resources/Retrieve a vehicle.resources/examples/200 - Success.example.yaml", res);
  }
    // @endpoint PATCH /cart
  if (pm.mock.matchRequest("postman/collections/Vehicle Service Collection/Update a collection.request.yaml", req)) {
    return pm.mock.sendExample("postman/collections/Vehicle Service Collection/.resources/Update a collection.resources/examples/200 - Success.example.yaml", res);
  }
    // @endpoint DELETE /cart
  if (pm.mock.matchRequest("postman/collections/Vehicle Service Collection/Delete a vehicle.request.yaml", req)) {
    return pm.mock.sendExample("postman/collections/Vehicle Service Collection/.resources/Delete a vehicle.resources/examples/204 - Success.example.yaml", res);
  }
    // @endpoint GET /cart
  if (pm.mock.matchRequest("SELECT REQUEST", req)) {
    return pm.mock.sendExample("postman/collections/Vehicle Service Collection/.resources/Get all vehicles.resources/examples/200 - Success.example.yaml", res);
  }

  

  // Select requests and examples to mock
  if (pm.mock.matchRequest("SELECT REQUEST", req)) {
    return pm.mock.sendExample("SELECT EXAMPLE", res);
  }

  res.writeHead(404, JSON_HEADER);
  res.end(JSON.stringify({
    error: "Endpoint not defined",
    message: `Please add ${url} endpoint to this mock.`
  }));
});

server.listen(PORT, () => console.log(`Mock server on port ${PORT}`));